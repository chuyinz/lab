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
            dotnetCom = new ActiveXComponent("Compress.Application");     //��Ҫ���õ�C#�����е������ռ�����������

            for(int i=0;i<str_org.length;i++) {
                Variant var = Dispatch.call(dotnetCom, "Zip", str_org[i]);   //��Ҫ���õķ������Ͳ���ֵ
                str_comp[i] = var.toString();  //����ѹ������ַ���
                Variant var2 = Dispatch.call(dotnetCom, "UnZip", str_comp[i]);   //��Ҫ���õķ������Ͳ���ֵ
                str_uncomp[i] = var2.toString();  //���ؽ�ѹ����ַ���
                System.out.println("str_org["+i +"]:"+ str_org[i] + "\n");  //���ԭʼ�ַ�����
                System.out.println("str_comp["+i +"]:"+ str_comp[i] + "\n");  //���ѹ������ַ�����
                System.out.println("str_uncomp["+i +"]:" + str_uncomp[i] + "\n");  //�����ѹ����ַ�����������Ƿ���ȷ��
                System.out.println("\n");
            }
        } catch (Exception ex) {
                ex.printStackTrace();    
            }    
    }      
}
