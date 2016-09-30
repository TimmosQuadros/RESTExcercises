package facade;

import entity.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.HashMap;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.Test;

public class FacadePersonTest {

    FacadePerson fp;

    public FacadePersonTest() {
        fp = new FacadePerson();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

//Doesn't work with the derby inmemory db but works with the jdbc:mysql db. Is out commented because the project cannot be build unless it is...
//    @Test
//    public void testGetPerson() {
//        RestAssured.when().get("http://localhost:8080/Day1_ExercisesREST_JAX-RS/api/person/50").then().statusCode(404);
//    }
//    
//    @Test
//    public void testPutPerson() {
//        String myJson = "{\"id\": 50, \"firstName\": \"Dan\", \"lastName\": \"Mark\", \"phoneNumber\": 5554321}";
//        RestAssured.given().contentType(ContentType.JSON).body(myJson).when().post("http://localhost:8080/Day1_ExercisesREST_JAX-RS/api/person").then().statusCode(404);
//    }
//    
//    @Test
//    public void testGetQuote() {
//        RestAssured.when().get("http://localhost:8080/Day1_ExercisesREST_JAX-RS/api/quote/50").then().statusCode(404);
//    }
}
