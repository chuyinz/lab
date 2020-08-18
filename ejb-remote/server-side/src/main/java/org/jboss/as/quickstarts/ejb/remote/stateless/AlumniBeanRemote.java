package org.jboss.as.quickstarts.ejb.remote.stateless;

import org.jboss.as.quickstarts.ejb.remote.entity.Alumni;

import javax.ejb.Remote;
import java.util.List;
@Remote
public interface AlumniBeanRemote {
    void createAlumni(Alumni alumni);
    void alterAlumniEmail(String name,String email);
    void deleteAlumni(String name);
    List<Alumni> calculateAlumni();
    Alumni findByName(String name);
}
