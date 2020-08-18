package rmi_test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.RMIClientSocketFactory;

public class Client {
    private Client() {
    }

    public static void main(String[] args) throws IOException, NotBoundException {
        String host = (args.length < 1) ? "localhost" : args[0];

       String urlo = "rmi://192.168.230.129:3333/Folder";
        Folder stub = (Folder) Naming.lookup(urlo);

     /*
        Registry registry = LocateRegistry.getRegistry("17.16.1.59",3333, new RMIClientSocketFactory()  {
            @Override
            public Socket createSocket(String host, int port) throws IOException {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 2000);
                return socket;
            }
        });
        Folder stub = (Folder)registry.lookup("Folder");
        */

        System.out.println("link to the server: \n" + urlo);
        String str=stub.listFile();
        System.out.println("\n File list: \n" + str);

        Integer num=stub.numFile();
        System.out.println("numbers of files(including folders):"+"\n"+num+"\n");

        double space=stub.spaceFile();
        System.out.println("space of folders:"+"\n"+space);

        System.out.println("\n create file test:");
        String fileContent="test inserting content.";
        String fileName="testAddFile";
        String result=stub.addFile(fileName,fileContent);
        System.out.println(fileName+":"+result);


        System.out.println("\n alter file test:");
        String fileName2="2";
        String oldStr="is";
        String newStr="are";
        String result2=stub.alterFile(fileName2,oldStr,newStr);
        System.out.println(fileName2+":"+result2);
    }
}