/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTAndJSON;


import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author TimmosQuadros
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(RESTAndJSON.GenericResource.class);
        resources.add(RESTAndJSON.RESTGetTestData.class);
        resources.add(RESTAndJSON.RESTPerson.class);
        resources.add(RESTException.NotFoundExceptionMapper.class);
        resources.add(RESTException.PersonMissingDataExceptionMapper.class);
        resources.add(RESTException.PersonNotFoundExceptionMapper.class);
        resources.add(RESTException.QuoteNotFoundExceptionMapper.class);
        resources.add(RESTException.RuntimeExceptionMapper.class);
    }
    
}
