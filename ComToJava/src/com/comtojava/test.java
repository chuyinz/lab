package com.comtojava;
import com.jacob.activeX.ActiveXComponent;    
import com.jacob.com.ComThread;    
import com.jacob.com.Dispatch;    
import com.jacob.com.Variant;    
public class test {    
  
    /**    
     * @param args    
     */    
    public static void main(String[] args) {    
        // TODO Auto-generated method stub  

  
        try{    
            ActiveXComponent dotnetCom = null;
            String str_org[]={"aaaaaaaaaabbbbbbb","sa5xsahjz","jxasxbx","ihasjbcnx"};
            String str_comp[]=new String[str_org.length];
            String str_uncomp[]=new String[str_org.length];
            dotnetCom = new ActiveXComponent("Compress.Application");     //需要调用的C#代码中的命名空间名和类名。

            for(int i=0;i<str_org.length;i++) {
                Variant var = Dispatch.call(dotnetCom, "Zip", str_org[i]);   //需要调用的方法名和参数值
                str_comp[i] = var.toString();  //返回压缩后的字符串
                Variant var2 = Dispatch.call(dotnetCom, "UnZip", str_comp[i]);   //需要调用的方法名和参数值
                str_uncomp[i] = var2.toString();  //返回解压后的字符串
                System.out.println("str_org["+i +"]:"+ str_org[i] + "\n");  //输出原始字符串。
                System.out.println("str_comp["+i +"]:"+ str_comp[i] + "\n");  //输出压缩后的字符串。
                System.out.println("str_uncomp["+i +"]:" + str_uncomp[i] + "\n");  //输出解压后的字符串。检查结果是否正确。
                System.out.println("\n");
            }
        } catch (Exception ex) {
                ex.printStackTrace();    
            }    
    }      
}
