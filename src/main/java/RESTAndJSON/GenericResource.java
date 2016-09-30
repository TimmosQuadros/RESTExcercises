/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTAndJSON;


import RESTException.QuoteNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author TimmosQuadros
 */
@Path("quote")
public class GenericResource {

    private static Map<Integer, String> quotes = new HashMap();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
//        quotes.put(1, "Friends are kisses blown to us by angels");
//        quotes.put(2, "Do not take life too seriously. You will never get out of it alive");
//        quotes.put(3, "Behind every great man, is a woman rolling her eyes");
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.day1_exercisesrest_jax.rs.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getQuote(@PathParam("id") int id) throws QuoteNotFoundException {
        JsonObject quote = new JsonObject();
        String q = quotes.get(id);
        quote.addProperty("quote", quotes.get(id));
        if(q==null){
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        return new Gson().toJson(quote);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random")
    public String getRandomQuote() throws QuoteNotFoundException {
        JsonObject quote = new JsonObject();
        int max = quotes.size();
        if(max<1){
            throw new QuoteNotFoundException("No Quotes Created yet");
        }
        int min = 1;
        int id = min + (int)(Math.random() * ((max - min) + 1));
        quote.addProperty("quote", quotes.get(id));
        return new Gson().toJson(quote);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String getQuotes() {
        JsonObject quote = new JsonObject();
        return new Gson().toJson(quotes);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postQuote(String content)
    {
        System.out.println(content);
        JsonObject newQuote = new JsonParser().parse(content).getAsJsonObject();
        String quote = newQuote.get("quote").getAsString();
        System.out.println(quote);
        int index = quotes.size()+1;
        quotes.put(index, quote);
        return "{\"id\":"+index+",\"quote\":"+"\""+quote+"\""+"}";
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String putQuote(String content,@PathParam("id") int id) throws QuoteNotFoundException
    {
        System.out.println(content);
        JsonObject newQuote = new JsonParser().parse(content).getAsJsonObject();
        String quote = newQuote.get("quote").getAsString();
        System.out.println(quote);
        if(!quotes.containsKey(id)){
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        quotes.put(id, quote);
        return "{\"id\":"+id+",\"quote\":"+"\""+quote+"\""+"}";
    }
    
    @DELETE
    @Path("delete/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public String deleteQuote(@PathParam("id") int id) throws QuoteNotFoundException
    {
        String quote = quotes.get(id);
        if(!quotes.containsKey(id)){
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        quotes.remove(id);
        return "{\"id\":"+id+",\"quote\":"+"\""+quote+"\""+"}";
    }
    
    @DELETE
    @Path("delete")
    //@Produces(MediaType.APPLICATION_JSON)
    public void deleteQuote()
    {
        quotes.clear();
    }
    

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
