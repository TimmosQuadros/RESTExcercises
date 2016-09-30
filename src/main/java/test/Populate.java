package test;

import entity.Person;
import facade.FacadePerson;
import facade.IPersonFacade;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class Populate
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "com.mycompany_Day1_ExercisesREST_JAX-RS_war_1.0-SNAPSHOTPU" );
        EntityManager em = emf.createEntityManager();
        
        IPersonFacade fp = new FacadePerson(emf);
        
        em.getTransaction().begin();
        
        Person p = new Person("Dan", "Mark", 5554321);
        em.persist(p);
        p = new Person("Mark", "Benson", 5557878);
        em.persist(p);
        p = new Person("Ben", "Winter", 5551112);
        em.persist(p);
        
        em.getTransaction().commit();
        
        em.close();
        
    }
}