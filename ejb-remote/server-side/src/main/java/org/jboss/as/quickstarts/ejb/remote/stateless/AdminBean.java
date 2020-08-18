package org.jboss.as.quickstarts.ejb.remote.stateless;

import org.jboss.as.quickstarts.ejb.remote.entity.Admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Stateless
public class AdminBean implements AdminBeanRemote{
    @PersistenceContext(unitName = "entity")
    private EntityManager manager;

    @Override
    public void createAdmin(Admin admin) {
        manager.persist(admin);
    }



    @Override
    public boolean loginAdmin(Admin admin) {
        String sql = "select p.password from Admin p where p.account=:account";
        Query query = manager.createQuery(sql).setParameter("account",admin.getAccount());
       String password=(String)query.getSingleResult();
        if(password.equals(admin.getPassword()))
        {
            return true;
        }
        else {
            return false;
        }
    }

}
