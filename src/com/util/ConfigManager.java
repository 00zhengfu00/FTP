package com.util;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author Administrator
 * 
 *         TODO Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class ConfigManager
{
    /*
     * 
     * @author Administrator �����ļ�ȫ��
     */
    
    private static final String PFILE = System.getProperty("user.dir") + File.separator + "conf" + File.separator
        + "SystemSetup.properties";
    
    private static final String PFILE2 = System.getProperty("user.dir") + File.separator + "conf" + File.separator
        + "log4j.properties";
    
    /*
     * 
     * ��Ӧ�������ļ����ļ��������
     */
    private File m_file = null;
    
    /*
     * �����ļ�������޸�����
     */
    
    private long m_lastModifiedTime = 0;
    
    /*
     * 
     * �����ļ�����Ӧ�����Զ������
     */
    
    private Properties m_props = null;
    
    private Properties m_propsLog = null;
    
    /*
     * 
     * �������ܴ��ڵ�Ψһ��һ��ʵ��
     */
    
    private static ConfigManager m_instance = new ConfigManager();
    
    /*
     * 
     * ˽�еĹ����ӣ� ���Ա�֤����޷�ֱ��ʵ����
     */
    private ConfigManager()
    {
        // Class clazz = getClass();
        m_props = new Properties();
        setM_propsLog(new Properties());
        m_file = new File(PFILE);
        setM_lastModifiedTime(m_file.lastModified());
        try
        {
            
            // ��log4j����
            PropertyConfigurator.configure(PFILE2);
            // �������ļ�
            m_props.load(new FileInputStream(PFILE));
            // m_props.load(new OutputStreamWriter(new
            // FileInputStream(PFILE),"gbk"));
            // m_props.load(new InputStreamReader(new
            // FileInputStream(PFILE),"gbk"));
            
        }
        catch (Exception e)
        {
            System.out.println("��д�ļ�����" + e);
        }
    }
    
    /*
     * 
     * ��̬��������
     * 
     * @return ����ConfigManager ��ĵ�һʵ��
     */
    synchronized public static ConfigManager getInstance()
    {
        
        return m_instance;
    }
    
    /*
     * 
     * ��ȡһ���ض�����
     * 
     * @param name �����������
     * 
     * @param defaultVal �������Ĭ��ֵ
     * 
     * @return �������ֵ���������ڣ���Ĭ��ֵ���������ڣ�
     */
    final public String getConfigItem1(String name, String defaultVal)
    {
        String val = m_props.getProperty(name);
        if (val == null)
            return defaultVal.trim();
        else
            return val.trim();
    }
    
    final public String getConfigItem(String name)
        throws Exception
    {
        String val = m_props.getProperty(name);
        if (val == null)
            throw new Exception("�����ļ�ȡֵ����");
        else
            return val.trim();
    }
    
    public Properties getM_propsLog()
    {
        return m_propsLog;
    }
    
    public void setM_propsLog(Properties m_propsLog)
    {
        this.m_propsLog = m_propsLog;
    }
    
    public long getM_lastModifiedTime()
    {
        return m_lastModifiedTime;
    }
    
    public void setM_lastModifiedTime(long m_lastModifiedTime)
    {
        this.m_lastModifiedTime = m_lastModifiedTime;
    }
    
}
