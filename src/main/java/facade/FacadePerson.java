package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class FacadePerson implements IPersonFacade
{
    EntityManagerFactory emf;
    
    public FacadePerson(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public FacadePerson() {
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Person getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        Person p;
        
        try
        {
            em.getTransaction().begin();
            p = em.find(Person.class, id);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }    
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Person> getPersons()
    {
        EntityManager em = emf.createEntityManager();

        List<Person> persons;
        
        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public Person addPerson(Person p)
    {
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Person deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }
    
    /**
     *
     * @param pers
     * @return
     */
    @Override
    public Person editPerson(Person pers)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Person p = em.find(Person.class, pers.getId());
            if(p != null)
            {
                p = pers;
                em.merge(p);
                em.getTransaction().commit();
                return p;
            }
        }
        finally
        {
            em.close();
        }  
        
        return null;
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
