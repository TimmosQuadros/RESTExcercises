/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManagerFactory;
import entity.*;
import java.util.List;
/**
 *
 * @author TimmosQuadros
 */
public interface IPersonFacade {
    public void addEntityManagerFactory(EntityManagerFactory emf);
    public Person addPerson(Person p);
    public Person deletePerson(int id);
    public Person getPerson(int id);
    public List<Person> getPersons();
    public Person editPerson(Person p);
    
}
