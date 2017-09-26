package utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.CityInfo;
import beans.DistrictsInfo;
import beans.InfoLists;
import beans.ProvinceInfo;

/**
 * Created by PUJW on 2017/9/26.
 */

public class JsonParserUtil {
    public static void getAllProvince(String s){
        if (TextUtils.isEmpty(s)){
            return;
        }
        try {

            JSONObject jsonObject=new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("provinces");

                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject1=array.getJSONObject(i);
                    ProvinceInfo provinceInfo=new ProvinceInfo();
                    provinceInfo.setId(jsonObject1.getInt("provinceId"));
                    provinceInfo.setProvinceName(jsonObject1.getString("provinceName"));
                    provinceInfo.setGmtCreate(jsonObject1.getString("gmtCreate"));
                    provinceInfo.setGmtModified(jsonObject1.getString("gmtModified"));
                    InfoLists.PInfos.add(provinceInfo);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void getCitiesFromPro(String s){
        if (TextUtils.isEmpty(s)){
            return;
        }
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("cities");

                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject1=array.getJSONObject(i);
                    CityInfo city=new CityInfo();
                    city.setCityId(jsonObject1.getInt("cityId"));
                    city.setCityName(jsonObject1.getString("cityName"));
                    city.setZipCode(jsonObject1.getString("zipCode"));
                    city.setProvinceId(jsonObject1.getInt("provinceId"));
                    city.setGmtCreate(jsonObject1.getString("gmtCreate"));
                    city.setGmtModified(jsonObject1.getString("gmtModified"));
                    InfoLists.CInfos.add(city);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getDistrictsFromCity(String s){
        if (TextUtils.isEmpty(s)){
            return;
        }
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("districts");
                for (int i=0;i<array.length();i++){
                    JSONObject jj=array.getJSONObject(i);
                    DistrictsInfo d=new DistrictsInfo();
                    d.setDistrictId(jj.getInt("districtId"));
                    d.setDistrictName(jj.getString("districtName"));
                    d.setCityId(jj.getInt("cityId"));
                    d.setGmtCreate(jj.getString("gmtCreate"));
                    d.setGmtModified(jj.getString("gmtModified"));
                   InfoLists.DInfos.add(d);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
