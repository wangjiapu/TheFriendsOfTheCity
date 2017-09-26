package utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.InfoLists;
import beans.ProvinceInfo;

/**
 * Created by PUJW on 2017/9/26.
 */

public class JsonParserUtil {
    public static void getAllProvince(String s){
        try {

            JSONObject jsonObject=new JSONObject(s);
            JSONArray jjjj=jsonObject.getJSONArray("provinces");

            for (int i=0;i<jjjj.length();i++){
                JSONObject jsonObject1=jjjj.getJSONObject(i);
                ProvinceInfo provinceInfo=new ProvinceInfo();
                provinceInfo.setId(jsonObject1.getInt("provinceId"));
                provinceInfo.setProvinceName(jsonObject1.getString("provinceName"));
                provinceInfo.setGmtCreate(jsonObject1.getString("gmtCreate"));
                provinceInfo.setGmtModified(jsonObject1.getString("gmtModified"));
                InfoLists.PInfos.add(provinceInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
