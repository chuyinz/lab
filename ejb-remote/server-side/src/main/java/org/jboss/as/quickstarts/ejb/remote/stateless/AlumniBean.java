package org.jboss.as.quickstarts.ejb.remote.stateless;

import org.jboss.as.quickstarts.ejb.remote.entity.Alumni;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AlumniBean implements AlumniBeanRemote{
    @PersistenceContext(unitName = "entity")
    private EntityManager manager;

    @Override
    public void createAlumni(Alumni alumni) {
        manager.persist(alumni);
    }

    @Override
    public void alterAlumniEmail(String name,String email) {

        Query query = manager.createQuery("update Alumni p set p.email=:email where p.name=:name");
        query.setParameter("name", name);
        query.setParameter("email", email);
        query.executeUpdate();


    }

    @Override
    public void deleteAlumni(String name) {

        Query query1 = manager.createQuery(" delete Alumni p where p.name=:name");
        query1.setParameter("name", name);
        query1.executeUpdate();

    }

    @Override
    public List<Alumni> calculateAlumni() {
        Query query1 = manager.createQuery("From Alumni as p");
        List<Alumni>alumniList=query1.getResultList();
        return alumniList;
    }

    @Override
    public Alumni findByName(String name) {
        String sql="SELECT p FROM Alumni p WHERE p.name=:name";
        Query query = manager.createQuery(sql).setParameter("name",name);
        Alumni alumni=(Alumni)query.getSingleResult();
        return alumni;
    }
}
