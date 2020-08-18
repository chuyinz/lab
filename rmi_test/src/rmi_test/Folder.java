package rmi_test;

import java.io.IOException;
import java.rmi.*;

public interface Folder extends java.rmi.Remote{


    String addFile(String fileName,String fileContent) throws IOException;
    String alterFile(String fileName, String oldstr, String newStr) throws java.rmi.RemoteException;
    String listFile() throws java.rmi.RemoteException;
    Integer numFile()throws java.rmi.RemoteException;
    double spaceFile()throws java.rmi.RemoteException;

}
