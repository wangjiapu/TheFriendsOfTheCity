package utils;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPerferenceUtil {

    public static void saveUserInfo(Context context, String userTel,String userPwd,
                                    String u,String sign){
        SharedPreferences.Editor editor=
                context.getSharedPreferences("uData",Context.MODE_PRIVATE).edit();
        editor.putString("userTel",userTel);
        editor.putString("userpwd",userPwd);
        editor.putString("u",u);
        editor.putString("sign",sign);
        editor.apply();
    }

    public static String[] getUserInfo(Context context){
        SharedPreferences preferences=context.getSharedPreferences("uData",Context.MODE_PRIVATE);
        String[] str=new String[4];
        str[0]=preferences.getString("userTel","");
        str[1]=preferences.getString("userpwd","");
        str[2]=preferences.getString("u","");
        str[3]=preferences.getString("sign","");
        return str;
    }
}
