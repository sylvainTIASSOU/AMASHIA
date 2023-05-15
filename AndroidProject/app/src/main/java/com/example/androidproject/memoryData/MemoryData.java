package com.example.androidproject.memoryData;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class MemoryData
{
//laste message
    public static void saveLastMsg(String data,String chatId, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput(chatId+".txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static String getLastMsg(Context context, String chatId)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = context.openFileInput(chatId+".txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader buff = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = buff.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();

        }
        catch (IOException e){ e.printStackTrace();}

        return data;
    }








    public static void saveData(String data, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static String getData(Context context)
    {
         String data = "";
         try
         {
             FileInputStream fileInputStream = context.openFileInput("data.txt");
             InputStreamReader isr = new InputStreamReader(fileInputStream);
             BufferedReader buff = new BufferedReader(isr);
             StringBuffer sb = new StringBuffer();
             String line;

             while ((line = buff.readLine()) != null)
             {
                 sb.append(line);
             }
             data = sb.toString();

         }
         catch (IOException e){ e.printStackTrace();}

        return data;
    }


    //name
    public static void saveName(String data, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("name.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static String getName(Context context)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = context.openFileInput("name.txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader buff = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = buff.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();

        }
        catch (IOException e){ e.printStackTrace();}

        return data;
    }




    //patient or doctor
    public static void saveUser(String data, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("user.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static String getUser(Context context)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = context.openFileInput("user.txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader buff = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = buff.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();

        }
        catch (IOException e){ e.printStackTrace();}

        return data;
    }




    //Mobile nomber
    public static void saveMobile(String data, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("mobile.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static String getMobile(Context context)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = context.openFileInput("mobile.txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader buff = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = buff.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();

        }
        catch (IOException e){ e.printStackTrace();}

        return data;
    }


//doctore enregistré
    public static String getDoctorUser(Context context)
    {
        String data = "";
        try
        {
            FileInputStream fileInputStream = context.openFileInput("doctorUser.txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader buff = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = buff.readLine()) != null)
            {
                sb.append(line);
            }
            data = sb.toString();

        }
        catch (IOException e){ e.printStackTrace();}

        return data;
    }


    public static void saveDoctorUser(String data, Context context)
    {
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("doctorUser.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
