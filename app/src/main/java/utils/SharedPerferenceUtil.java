package utils;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPerferenceUtil {

    public static void saveUserInfo(Context context, String userTel,String userPwd){
        SharedPreferences.Editor editor=
                context.getSharedPreferences("uData",Context.MODE_PRIVATE).edit();
        editor.putString("userTel",userTel);
        editor.putString("userpwd",userPwd);
        editor.apply();
    }

    public static String[] getUserInfo(Context context){
        SharedPreferences preferences=context.getSharedPreferences("uData",Context.MODE_PRIVATE);
        String[] str=new String[2];
        str[0]=preferences.getString("userTel","");
        str[1]=preferences.getString("userpwd","");
        return str;
    }
}
