package com.util;

import java.io.File;
import java.util.GregorianCalendar;

public class FileUtil
{
    
    public static boolean fileDelete(String filepath)
    {
        boolean flag1 = false;
        java.io.File file = new java.io.File(filepath);
        
        flag1 = file.delete();
        return flag1;
    }
    
    // ��ѡ�����������ļ�����ɾ��
    public static boolean getlistfilename(String path, GregorianCalendar deletetime)
    {
        
        java.io.File f = new java.io.File(path);
        
        if (f.exists())
        {
            if (f.isFile())
                return f.delete();
            else if (f.isDirectory())
            {
                File[] files = f.listFiles();
                
                for (int i = 0; i < files.length; i++)
                {
                    
                    if (files[i].lastModified() < deletetime.getTimeInMillis())
                    {
                        
                        if (!deleteFile(files[i]))
                            return false;
                    }
                }
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    // ����ɾ��Ŀ¼�µ��ļ�
    public static boolean deleteFile(File f)
    {
        if (f.exists())
        {
            if (f.isFile())
                return f.delete();
            else if (f.isDirectory())
            {
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++)
                {
                    if (!deleteFile(files[i]))
                        return false;
                }
                
                // return f.delete(); ����Ŀ¼һ��ɾ��
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    // �ж��ļ��Ƿ����
    public static boolean getlistfilename(String path, String time)
    {
        
        java.io.File f = new java.io.File(path);
        
        if (f.exists())
        {
            if (f.isFile())
                return f.delete();
            else if (f.isDirectory())
            {
                File[] files = f.listFiles();
                
                for (int i = 0; i < files.length; i++)
                {
                    // System.out.println((files[i].getName()).substring(10,18));
                    // System.out.println(time);
                    if (((files[i].getName()).substring(10, 18)).equals(time))
                        return true;
                }
                return false;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    public static boolean isExists_file(String filepath)
    {
        boolean flag1 = false;
        java.io.File file = new java.io.File(filepath);
        if (file.exists())
        {
            flag1 = true;
        }
        return flag1;
    }
    
}
