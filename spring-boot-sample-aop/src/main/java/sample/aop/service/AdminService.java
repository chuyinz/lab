package sample.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.aop.aspect.LoggingAspect;
import sample.aop.dao.AdminMapper;
import sample.aop.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;
    /**
 * 验证登录
 * @param username
 * @param password

 * @return
 */
public Map<String, Object> loginValid(String username,String password){
    Admin admin=adminMapper.queryByUsernameAndPassword(username, password);
    Map<String, Object> map=new HashMap<>();
    if(admin==null||"".equals(admin)){
        map.put("status", "error");
        map.put("msg", "用户名或密码错误！");
    }else {
        LoggingAspect.getAdmin(admin);
        map.put("status", "ok");
       //map.put("msg", "登录成功！");

    }
    return map;
}

}
