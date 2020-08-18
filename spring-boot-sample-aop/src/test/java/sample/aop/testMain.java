package sample.aop;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.aop.entity.Alumni;
import sample.aop.service.AdminService;
import sample.aop.service.AlumniService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Principal;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class testMain {

    @Autowired
    private AlumniService alumniService;
    @Autowired
    private AdminService adminService;

        @Test
        public  void testFind () {

            adminService.loginValid("a","a");
            String year="2016";
           List<Alumni>alumniList=new ArrayList<Alumni>();
                   alumniList=alumniService.findAlumniByYear(year);
           for(int i=0;i<alumniList.size();i++)
           {
               System.out.println(alumniList.get(i).getName());
           }

        }

        @Test
        public void testUpdateAlumni()
        {
            adminService.loginValid("a","a");
            BigInteger bi=new BigInteger("1");
            Alumni alumni=new Alumni(bi,"zyy",2,"1998.4.24","2016","ggg","sad","qqw","12024649","45648@qq.com","faf");
            Alumni oldAlumni=alumniService.updateAlumni(alumni);
        }

   @Test
    public void testDeleteAlumni()
    {
    adminService.loginValid("a","a");
        BigInteger id=new BigInteger("1");
      alumniService.updateAlumniByDelete(id);
  }




}
