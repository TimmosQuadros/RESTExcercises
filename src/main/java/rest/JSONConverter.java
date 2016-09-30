/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author TimmosQuadros
 */
public class JSONConverter {
    public static Person getPersonFromJson(String js){
        return new Gson().fromJson(js, Person.class);
    }
    public static String getJSONFromPerson(Person p){
        //return "{\"firstName\":\""+p.getFirstName()+"\",\"lastName\":\""+p.getLastName()+"\",\"phoneNumber\":"+p.getPhoneNumber()+"}";
        return new Gson().toJson(p, Person.class);
    }
    public static String getJSONFromPerson(List<Person> persons){
        return new Gson().toJson(persons);
    }
}
