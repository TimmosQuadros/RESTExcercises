package RESTAndJSON;

import RESTException.PersonMissingDataException;
import RESTException.PersonNotFoundException;
import com.google.gson.Gson;
import entity.Person;
import facade.FacadePerson;
import facade.IPersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import rest.JSONConverter;

@Path("person")
public class RESTPerson
{
    @Context
    private UriInfo context;
    
    @Context
    private HttpHeaders headers;
    
    IPersonFacade fp;
    
    
    public RESTPerson()
    {
        //If Running Junit Test with derby use
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory( "pu_test" );
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "com.mycompany_Day1_ExercisesREST_JAX-RS_war_1.0-SNAPSHOTPU" );
         fp = new FacadePerson(emf);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getPersons()
    {
        return JSONConverter.getJSONFromPerson(fp.getPersons());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException
    {
        Person p = fp.getPerson(id);
        
        if(p==null){
            throw new PersonNotFoundException("No person with provided id found");
        }
        
        return JSONConverter.getJSONFromPerson(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postPerson(String content) throws PersonMissingDataException
    {   
        Person p = fp.addPerson(new Gson().fromJson(content, Person.class));
        if(p.getFirstName()==null || p.getLastName()==null){
            throw new PersonMissingDataException("First Name or Last Name is missing");
        }
        return JSONConverter.getJSONFromPerson(p);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putPerson(String content) throws PersonMissingDataException, PersonNotFoundException
    {
        //JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
        Person p = JSONConverter.getPersonFromJson(content);
        Person p1 = fp.getPerson(p.getId());
        if(p1==null){
            throw new PersonNotFoundException("Cannot edit. Person with provided id does not exist");
        }
        if(p.getFirstName()==null || p.getLastName()==null){
            throw new PersonMissingDataException("First Name or Last Name is missing");
        }
        fp.editPerson(p);
        return content;
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException
    {
        Person p = fp.getPerson(id);
        if(p==null){
            throw new PersonNotFoundException("Could not delete. No person with provided id exists");
        }
        String js = JSONConverter.getJSONFromPerson(p);
        fp.deletePerson(id);
        return js;
    }
}