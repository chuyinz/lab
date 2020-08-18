using System;
  
  using System.Collections.Generic;
  
  using System.Linq;
  
  using System.Text;
  
 using System.Runtime.InteropServices;

namespace Compress
{
    [Guid("FC5F8F1E-1823-4E0D-BC98-566DA8E23C7C")]
    public interface IMyCompress
    {
        void Init();
        void Dispose();
        string Zip(string value);//压缩
        string UnZip(string value);//解压
    }

    [ClassInterface(ClassInterfaceType.None)]
    [Guid("A333D206-8295-4D3C-93BC-BE4CDF5031CC")]
    [ProgId("Compress.Application")]
    public class Compress : IMyCompress
    {
        public void Init()
        {

        }
        public void Dispose()
        {

        }
        public string Zip(string value)
        {
            //Transform string into byte[] 
           
                byte[] byteArray = new byte[value.Length];
                int indexBA = 0;
                foreach (char item in value.ToCharArray())
                {
                    byteArray[indexBA++] = (byte)item;
                }
                //Prepare for compress
                System.IO.MemoryStream ms = new System.IO.MemoryStream();
                System.IO.Compression.GZipStream sw = new System.IO.Compression.GZipStream(ms,
               System.IO.Compression.CompressionMode.Compress);
                //Compress
                sw.Write(byteArray, 0, byteArray.Length);
                //Close, DO NOT FLUSH cause bytes will go missing...
                sw.Close();
                //Transform byte[] zip data to string
                byteArray = ms.ToArray();
                System.Text.StringBuilder sB = new System.Text.StringBuilder(byteArray.Length);
                foreach (byte item in byteArray)
                {
                    sB.Append((char)item);
                }
           
            ms.Close();
            sw.Dispose();
            ms.Dispose();

            return sB.ToString();
        }
        public string UnZip(string value)
        {
            //Transform string into byte[]
                byte[] byteArray = new byte[value.Length];
                int indexBA = 0;
                foreach (char item in value.ToCharArray())
                {
                    byteArray[indexBA++] = (byte)item;
                }
                //Prepare for decompress
                System.IO.MemoryStream ms = new System.IO.MemoryStream(byteArray);
                System.IO.Compression.GZipStream sr = new System.IO.Compression.GZipStream(ms, System.IO.Compression.CompressionMode.Decompress);
                //Reset variable to collect uncompressed result
                byteArray = new byte[byteArray.Length];
                //Decompress
                int rByte = sr.Read(byteArray, 0, byteArray.Length);
                //Transform byte[] unzip data to string
                System.Text.StringBuilder sB = new System.Text.StringBuilder(rByte);
                //Read the number of bytes GZipStream red and do not a for each bytes in
                //resultByteArray;
                for (int i = 0; i < rByte; i++)
                {
                    sB.Append((char)byteArray[i]);
                }
                sr.Close();
                ms.Close();
                sr.Dispose();
                ms.Dispose();

            return sB.ToString();
        }

    }
}



