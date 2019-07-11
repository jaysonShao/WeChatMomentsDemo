package tw.jason.homework.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseUtils  {


    public static String getJson(String fileName, Context context) {
        //make json to string
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //use assetmanager
            AssetManager assetManager = context.getAssets();
            //open and read this file
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return stringBuilder.toString();
    }
}
