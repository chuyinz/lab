package rmi_test;

import java.io.*;

public class FolderImpl implements Folder {

    private String folderPath;
    public FolderImpl(String path){
        folderPath=path;
    }

    @Override
    public String addFile(String fileName,String fileContent) throws IOException {
        File file=new File(folderPath+"/"+fileName);
        if(!file.exists())
        {
            file.createNewFile();
        }//创建文本文件
        try {
            FileOutputStream out = new FileOutputStream(folderPath + "/" + fileName);
            PrintStream p = new PrintStream(out);
            p.println(fileContent);
            out.close();
            p.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }//写入
        return "create file successfully!";
    }

    @Override
    public String alterFile(String fileName, String oldstr, String newStr) throws java.rmi.RemoteException{
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(folderPath+"/"+fileName, "rw");
            String line = null;
            long lastPoint = 0; //记住上一次的偏移量
            while ((line = raf.readLine()) != null) {
                final long ponit = raf.getFilePointer();
                if(line.contains(oldstr)){
                    String str=line.replace(oldstr, newStr);
                    raf.seek(lastPoint);
                    raf.writeBytes(str);
                }
                lastPoint = ponit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "alter the file successfully!";
    }

    @Override
    public String listFile() throws java.rmi.RemoteException{
        File file=new File(folderPath);
        String list=new String();
        if(file!=null) {
            for (File temp : file.listFiles()) {
                    list += temp.toString()+"\n";

                }
            }
        return list;
    }
    @Override
    public Integer numFile()throws java.rmi.RemoteException{
        File file=new File(folderPath);
       Integer num=0;
        for (File temp : file.listFiles()) {
           num++;
        }
        return num;
    }
    @Override
    public double spaceFile()throws java.rmi.RemoteException{
        File file=new File(folderPath);
        double userdSpace =  ((file.getTotalSpace() - file.getFreeSpace()) / 1024.0 / 1024 / 1024);
        return userdSpace;
    }


}
