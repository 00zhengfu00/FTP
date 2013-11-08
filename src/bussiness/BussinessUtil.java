package bussiness;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import com.util.IpUtil;

public class BussinessUtil
{
    
    private static BussinessUtil bussinessUtil = null;
    
    private Boolean flag = false;
    
    private String filepath1 = "";
    
    private String filepath2 = "";
    
    public static HashMap<Long, String> map1 = new HashMap<Long, String>();
    
    public static HashMap<Long, String> map2 = new HashMap<Long, String>();
    
    private BussinessUtil()
    {
        // Exists only to defeat instantiation.
    }
    
    public static BussinessUtil getInstance()
    {
        if (bussinessUtil == null)
        {
            bussinessUtil = new BussinessUtil();
        }
        return bussinessUtil;
    }
    
    /**
     * �ж�ĳ��IP�Ƿ�������
     * 
     * @param ipadress
     * @return
     * @throws IOException
     */
    public boolean checkIPwhitelist(Long ipadress)
        throws IOException
    {
        // ���ļ�δ����ʹ�õ�ǰ�ļ������ڴ�
        if (flag == false)
        {
            if (!"".equals(filepath1))
            {
                map1 = readFile(filepath1);
            }
            else
            {
                System.out.println("FTP�������ļ�δ���سɹ�");
            }
            if (map1.containsKey(ipadress))
            {
                return true;
            }
        }
        // ���ļ�����ʹ�ø��µ��ļ������ڴ�
        if (flag == true)
        {
            if (!"".equals(filepath2))
            {
                map2 = readFile(filepath2);
            }
            else
            {
                System.out.println("FTP�������ļ�δ���سɹ�");
            }
            if (map2.containsKey(ipadress))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * һ��һ�ж�ȡ�ļ��������ȡ�����ַ�ʱ��������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ� �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException
     */
    public HashMap<Long, String> readFile(String filepath)
        throws IOException
    {
        FileInputStream fis = new FileInputStream(filepath);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        String[] arrs = null;
        
        HashMap<Long, String> map = new HashMap<Long, String>();
        
        while ((line = br.readLine()) != null)
        {
            arrs = line.split(",");
            System.out.println(arrs[0] + " : " + arrs[1]);
            map.put(IpUtil.ipToLong(arrs[1]), arrs[0]);
        }
        br.close();
        isr.close();
        fis.close();
        return map;
    }
    
    /**
     * һ��һ��д���ļ������д�������ַ�ʱ��������
     * 
     * ���Ĺر�˳���ȴ򿪵ĺ�أ���򿪵��ȹأ� �����п��ܳ���java.io.IOException: Stream closed�쳣
     * 
     * @throws IOException
     */
    public void writeFile(String filepath)
        throws IOException
    {
        String[] arrs = {"ad8553443,222.115.14.28", "ad6653858,111.62.90.254"};
        // д�������ַ�ʱ���������������
        FileOutputStream fos = new FileOutputStream(new File(filepath));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        
        for (String arr : arrs)
        {
            bw.write(arr + "\t\n");
        }
        
        // ע��رյ��Ⱥ�˳���ȴ򿪵ĺ�رգ���򿪵��ȹر�
        bw.close();
        osw.close();
        fos.close();
    }
    
    public String getFilepath1()
    {
        return filepath1;
    }
    
    public void setFilepath1(String filepath1)
    {
        this.filepath1 = filepath1;
    }
    
    public String getFilepath2()
    {
        return filepath2;
    }
    
    public void setFilepath2(String filepath2)
    {
        this.filepath2 = filepath2;
    }
    
    public Boolean getFlag()
    {
        return flag;
    }
    
    public void setFlag(Boolean flag)
    {
        this.flag = flag;
    }
}
