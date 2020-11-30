package com.example.bytedance.Info;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//读取本地json文件 并解析json数据
public class JsonUtils {
    private static JsonUtils instance; // 单例模式
    private JsonUtils(){

    }
    public static JsonUtils getInstance(){
        if (instance == null){
            instance = new JsonUtils();
        }
        return instance;
    }

    private String readJson(InputStream inputStream){
        BufferedReader bufferedReader = null;
        StringBuilder sb = null;
        String line = null;

        try{
            sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (bufferedReader != null) bufferedReader.close();
                if (inputStream != null) inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public List<JsonLoad> getJsonData(Context context){
        List<JsonLoad> list = new ArrayList<>();
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open("data.json");
            String json = readJson(inputStream);
            Gson gson = new Gson();
            // 通过反射机制，定义一个类型解析器
            Type listType = new TypeToken<List<JsonLoad>>(){}.getType();
            list = gson.fromJson(json,listType);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return list;
    }

}


