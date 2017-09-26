package utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkhttpUtil {

    private static OkHttpClient okHttpClient=new OkHttpClient();
    private static final String HOST="http://bf.corefuture.cn/bookfriend";

    private static final String AllProvince="/place/getAllProvince";


    private static final String CitiesByProvinceId="/place/getCitiesByProvinceId";

    private static final String DistrictsByCityId="/place/getDistrictsByCityId";

    private static final String Sms="/user/sendSMS";

    private static final String Register="/user/register";

    private static final String Login="/user/login";

    private static final String Quit="/user/quit";

    private static final String LoginUserInfo="/user/getLoginUserInfo";

    public static Request getRequest(String url,RequestBody body){

        Request request=new Request.Builder().url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .post(body)
                .build();
        return request;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        OkhttpUtil.okHttpClient = okHttpClient;
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getAllProvince() {
        return AllProvince;
    }

    public static String getCitiesByProvinceId() {
        return CitiesByProvinceId;
    }

    public static String getDistrictsByCityId() {
        return DistrictsByCityId;
    }

    public static String getSms() {
        return Sms;
    }

    public static String getRegister() {
        return Register;
    }

    public static String getLogin() {
        return Login;
    }

    public static String getQuit() {
        return Quit;
    }

    public static String getLoginUserInfo() {
        return LoginUserInfo;
    }
}