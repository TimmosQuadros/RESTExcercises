/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTAndJSON;

import entity.Person;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author TimmosQuadros
 */
@Path("addresses")
public class RESTGetTestData {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RESTGetTestData
     */
    TestData tsd;

    public RESTGetTestData() {
        tsd = new TestData();
    }

    /**
     * Retrieves representation of an instance of RESTAndJSON.RESTGetTestData
     *
     * @param sampleSize
     * @param args
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{sampleSize}/{args}")
    public String getTestData(@PathParam("sampleSize") int sampleSize, @PathParam("args") String args) {
        return tsd.dataGenerator(sampleSize, args);
    }

    /**
     * Retrieves representation of an instance of RESTAndJSON.RESTGetTestData
     *
     * @param sampleSize
     * @param args
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("header/{sampleSize}/{args}")
    public String getTestDataWithHeader(@PathParam("sampleSize") int sampleSize, @PathParam("args") String args) {
        String JSON = tsd.dataGenerator(sampleSize, args);
        JSON=JSON.substring(1,JSON.length()-1);
        String header = "\"Header\": [{\"Content-Type\": \"application/json\", \"Accept\": \"application/json\"}]";
        return "{"+header+","+""+JSON+"}";
    }
}
