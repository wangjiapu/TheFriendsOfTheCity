package utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import beans.BookInfo;
import beans.CityInfo;
import beans.DistrictsInfo;
import beans.InfoLists;
import beans.ProvinceInfo;
import beans.UserInfo;

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

    public static void getInterestBookList(String s,int flag){
        if (TextUtils.isEmpty(s)){
            return;
        }
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("bookInfoList");
                for (int i=0;i<array.length();i++){
                    JSONObject jj=array.getJSONObject(i);
                    Gson gson=new Gson();
                    BookInfo b=gson.fromJson(jj.toString(),BookInfo.class);
                  switch (flag){
                      case 0://兴趣书
                          InfoLists.BInfos.add(b);
                          break;
                      case 1:
                          InfoLists.SameBInfos.add(b);
                          break;
                      case 2://获取已经读完的书
                          InfoLists.readedInfos.add(b);
                          break;
                      case 3://已借阅
                          InfoLists.borrowedInfos.add(b);
                          break;
                      case 4://已收藏
                          InfoLists.collectionedInfos.add(b);
                          break;
                  }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void getInterestUserList(String s){
        if (TextUtils.isEmpty(s)){
            return;
        }
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("userInfoList");
                for (int i=0;i<array.length();i++){
                    JSONObject jj=array.getJSONObject(i);
                    Gson gson=new Gson();
                    UserInfo u=gson.fromJson(jj.toString(), UserInfo.class);
                    InfoLists.UInfos.add(u);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void getSameCityBookList(String s){
        if (TextUtils.isEmpty(s)){
            return;
        }
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            String msg=jsonObject.getString("code");
            if (msg.equals("200")){
                JSONArray array=jsonObject.getJSONArray("bookInfoList");
                for (int i=0;i<array.length();i++){
                    JSONObject jj=array.getJSONObject(i);
                    Gson gson=new Gson();
                    UserInfo u=gson.fromJson(jj.toString(), UserInfo.class);
                    InfoLists.UInfos.add(u);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static boolean sendSMSOk(String s){
        String code=null;
        if (TextUtils.isEmpty(s)) {
            return false;
        }
        try {
            JSONObject jsonObject=new JSONObject(s);
             code=jsonObject.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (code.equals("200")){
            return true;
        }
        return false;
    }

}
