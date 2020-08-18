/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejb.remote.client;

import org.jboss.as.quickstarts.ejb.remote.entity.Admin;
import org.jboss.as.quickstarts.ejb.remote.entity.Alumni;
import org.jboss.as.quickstarts.ejb.remote.stateless.AdminBeanRemote;
import org.jboss.as.quickstarts.ejb.remote.stateless.AlumniBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.time.Year;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

/**
 * A sample program which acts a remote client for a EJB deployed on AS7 server. This program shows how to lookup stateful and
 * stateless beans via JNDI and then invoke on them
 *
 * @author Jaikiran Pai
 */
public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            props.put("jboss.naming.client.ejb.context", true);

            InitialContext context = new InitialContext(props);
            AdminBeanRemote ubr = (AdminBeanRemote)context.lookup("ejb:/wildfly-ejb-remote-server-side/AdminBean!org.jboss.as.quickstarts.ejb.remote.stateless.AdminBeanRemote");
            AlumniBeanRemote ubr2=(AlumniBeanRemote)context.lookup("ejb:/wildfly-ejb-remote-server-side/AlumniBean!org.jboss.as.quickstarts.ejb.remote.stateless.AlumniBeanRemote");


            //验证操作用户（录入人员）的登陆
            Admin admin=new Admin();
            admin.setAccount("a");
            admin.setPassword("a");
            boolean login=ubr.loginAdmin(admin);
            if(login==true) {
                System.out.println("login admin:"+admin.getAccount()+" successfully!\n");
            }

            //录入校友数据
            Date date=new Date(1998,4,25);

            Alumni alumni=new Alumni("zyy",0,"1998-4-25","1998","aaa","sss","ww","15060809856","123@qq.com","4252");
            ubr2.createAlumni(alumni);
            System.out.println("insert an alumni:"+alumni.getName()+" successfully!\n");

            //新建管理员
            Admin admin2=new Admin("sd","sd");
            ubr.createAdmin(admin2);
            System.out.println("create admin:"+admin2.getAccount()+" successfully!\n");

            //根据姓名查询校友信息
            System.out.println("begin to search for alumni:");
            String name = "zyy";

            Alumni resultAlumni=ubr2.findByName(name);
            System.out.println(resultAlumni);
            System.out.println("basic information of "+name+":");
            System.out.println(resultAlumni.getName()+"\n"+resultAlumni.getDate()+"\n"+resultAlumni.getEmail()+"\n"+resultAlumni.getPhone()+"\n");

            //根据姓名修改校友信息（仅演示修改邮箱）
            System.out.println("altering alumni info beginning...\n");
            ubr2.alterAlumniEmail(name,"321@qq.com");
            System.out.println("altering alumni info successfully!\n");

            //统计所有校友
            List<Alumni>list=ubr2.calculateAlumni();
            System.out.println("number of alumni:"+list.size());
            System.out.println(list+"\n");

            //根据姓名删除校友
            System.out.println("deleting alumni beginning...\n");
             ubr2.deleteAlumni(name);
            System.out.println("deleting alumni successfully...\n");



        }
	catch (Exception ex) {
            ex.printStackTrace();
        }


    }



}