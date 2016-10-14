package com.meetcity.calabash.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.meetcity.calabash.bean.DivinationBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by wds1993225 on 2016/8/23.
 */
public class SPUtil {

    public static void saveConstellation(Context context,String name){
        SharedPreferences.Editor editor = context.getSharedPreferences("constellation",Context.MODE_PRIVATE).edit();
        editor.putString("constellation",name);
        editor.commit();
    }
    public static String getConstellation(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("constellation",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("constellation","aries");
        return name;
    }

    public static void fillDivination(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("divination",Context.MODE_PRIVATE).edit();
        editor.putString("qianNum","一签");
        editor.putString("xiongji","上上");
        editor.putString("title","姜公封相");
        editor.putString("qianci","灵签求得第一枝、龙虎风云际会时、一旦凌霄扬自乐、任君来往赴瑶池。");
        editor.putString("beijing","姜公，姜子牙，世人多称之为姜太公。周朝的开国功臣，他辅佐周武王战败了暴君纣王，在多场敌强我弱的恶战中，如有神助，取得胜利。《封神榜》一书，亦有记载姜公在元始天尊、太乙真人、南极仙翁诸神的扶助，取得封神榜。您求得这支上上签，可喜可贺！龙虎得风云际会，您立下的大志，将会得偿所愿，并能到达至善至乐的境界。正所谓：东成西就，左右逢源，万事如意。");
        editor.putString("liunian","大吉大利，去年失去的，将会在今年得到。");
        editor.putString("shiye","由于有很多贵人扶持，谋事较易成就。");
        editor.putString("caifu","正财大丰收，亦有些小横财。");
        editor.putString("zishen","一年四季皆顺遂平安。");
        editor.putString("jiating","和气吉祥，宜在今年添丁。");
        editor.putString("yinyuan","爱情幸福，适婚人士莫错过今年好时机。");
        editor.putString("yiju","置业，搬迁，移民，都有机会。");
        editor.putString("mingyu","学业，事业，社会服务，各方面均易获嘉奖。");
        editor.putString("jiankang","困扰多时的小病即将成为过去。");
        editor.putString("youyi","贵人多，小人难侵，广结善缘，多结交新的朋友。");
        editor.apply();
    }

    public static void saveDivination(Context context, DivinationBean bean){
        SharedPreferences.Editor editor = context.getSharedPreferences("divination",Context.MODE_PRIVATE).edit();
        editor.putString("qianNum",bean.getQianNum());
        editor.putString("xiongji",bean.getXiongji());
        editor.putString("title",bean.getTitle());
        editor.putString("qianci",bean.getQianci());
        editor.putString("beijing",bean.getBeijing());
        editor.putString("liunian",bean.getLiunian());
        editor.putString("shiye",bean.getShiye());
        editor.putString("caifu",bean.getCaifu());
        editor.putString("zishen",bean.getZishen());
        editor.putString("jiating",bean.getJiating());
        editor.putString("yinyuan",bean.getYinyuan());
        editor.putString("yiju",bean.getYiju());
        editor.putString("mingyu",bean.getMingyu());
        editor.putString("jiankang",bean.getJiankang());
        editor.putString("youyi",bean.getYouyi());
        editor.apply();
    }

    public static DivinationBean getDivination(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("divination",Context.MODE_PRIVATE);
        String qianNum = sharedPreferences.getString("qianNum","一签");
        String xiongji = sharedPreferences.getString("xiongji","上上");
        String title = sharedPreferences.getString("title","姜公封相");
        String qianci = sharedPreferences.getString("qianci","灵签求得第一枝、龙虎风云际会时、一旦凌霄扬自乐、任君来往赴瑶池。");
        String beijing = sharedPreferences.getString("beijing","姜公，姜子牙，世人多称之为姜太公。周朝的开国功臣，他辅佐周武王战败了暴君纣王，在多场敌强我弱的恶战中，如有神助，取得胜利。《封神榜》一书，亦有记载姜公在元始天尊、太乙真人、南极仙翁诸神的扶助，取得封神榜。您求得这支上上签，可喜可贺！龙虎得风云际会，您立下的大志，将会得偿所愿，并能到达至善至乐的境界。正所谓：东成西就，左右逢源，万事如意。");
        String liunian = sharedPreferences.getString("liunian","大吉大利，去年失去的，将会在今年得到。");
        String shiye = sharedPreferences.getString("shiye","由于有很多贵人扶持，谋事较易成就。");
        String caifu = sharedPreferences.getString("caifu","正财大丰收，亦有些小横财。");
        String zishen = sharedPreferences.getString("zishen","一年四季皆顺遂平安。");
        String jiating = sharedPreferences.getString("jiating","和气吉祥，宜在今年添丁。");
        String yinyuan = sharedPreferences.getString("yinyuan","爱情幸福，适婚人士莫错过今年好时机。");
        String yiju = sharedPreferences.getString("yiju","置业，搬迁，移民，都有机会。");
        String mingyu = sharedPreferences.getString("mingyu","学业，事业，社会服务，各方面均易获嘉奖。");
        String jiankang = sharedPreferences.getString("jiankang","困扰多时的小病即将成为过去。");
        String youyi = sharedPreferences.getString("youyi","贵人多，小人难侵，广结善缘，多结交新的朋友。");
        DivinationBean bean = new DivinationBean();
        bean.setQianNum(qianNum);
        bean.setXiongji(xiongji);
        bean.setTitle(title);
        bean.setQianci(qianci);
        bean.setBeijing(beijing);
        bean.setLiunian(liunian);
        bean.setShiye(shiye);
        bean.setCaifu(caifu);
        bean.setZishen(zishen);
        bean.setJiating(jiating);
        bean.setYinyuan(yinyuan);
        bean.setYiju(yiju);
        bean.setMingyu(mingyu);
        bean.setJiankang(jiankang);
        bean.setYouyi(youyi);
        return bean;
    }

    public static void saveDivination(Context context){
        String data = "mine mine ,you like";
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = context.openFileOutput("data",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadDivination(Context context){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            in = context.openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
