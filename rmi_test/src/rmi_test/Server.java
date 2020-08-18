package rmi_test;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class    Server{
    public Server() {}
    public static void main(String args[]) {
        System.setProperty("java.rmi.server.hostname", "192.168.230.129");
        System.setProperty("java.security.policy", "/home/myubuntu425/Desktop/my.policy");//配置自定义的权限文件
        System.setSecurityManager(new RMISecurityManager());//启动安全管理器，使用系统默认的策略文件
        final FolderImpl obj = new FolderImpl("/home/myubuntu425/Desktop/test");
        try {                    // 0 - anonymous TCP port ↓
                                 //单播，把远程对象添加到RMI命名注册表中
            Folder stub = (Folder)UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(3333);
            registry.rebind("Folder", stub);//绑定
            for(int i = 0; i < registry.list().length; i++)
                System.out.println(registry.list()[i]);
            System.err.println("Server ready....");
            System.err.println("Listinging on port 3333 ....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}