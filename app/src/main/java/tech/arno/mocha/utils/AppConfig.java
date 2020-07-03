package tech.arno.mocha.utils;

import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import tech.arno.libcommon.global.AppGlobals;
import tech.arno.mocha.model.BottomBar;
import tech.arno.mocha.model.Destination;

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/7/2
 *     desc  :
 * </pre>
 */
public class AppConfig {
    //因为应用在首页 所以需要一直存在
    private static HashMap<String, Destination> sDestConfig;
    private static BottomBar sBottomBar;


    public static HashMap<String, Destination> getDestConfig() {
        if (sDestConfig == null) {
            //文件名规范修改?
            String content = parseFile("destination.json");
            //解析保存的json对象
            sDestConfig = JSON.parseObject(content, new TypeReference<HashMap<String, Destination>>() {
            }.getType());
        }

        return sDestConfig;
    }

    public static BottomBar getBottomBarConfig() {
        if (sBottomBar == null) {
            String content = parseFile("main_tabs_config.json");
            sBottomBar = JSON.parseObject(content, BottomBar.class);
        }
        return sBottomBar;
    }

    private static String parseFile(String fileName) {
        AssetManager assets = AppGlobals.getApplication().getAssets();
        InputStream stream = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            stream = assets.open(fileName);
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stream != null) {
                    stream.close();
                }

                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return builder.toString();
    }
}