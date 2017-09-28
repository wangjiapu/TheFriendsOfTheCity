package utils;

import okhttp3.Call;
import okhttp3.FormBody;
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

    private static final String InterestBooks="/bookInfo/getInterestBooks";

    private static final String InterestUesr="/user/getInterestUser";

    private static final String SameCityBooks="/bookInfo/getSameCityBooks";

    public static String getSameCityBooks() {
        return SameCityBooks;
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

    public static String getInterestBooks() {
        return InterestBooks;
    }

    public static String getInterestUesr() {
        return InterestUesr;
    }

    public static Request getRequest(String url, RequestBody body){
        Request request=new Request.Builder().url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .post(body)
                .build();
        return request;
    }

    /**
     * 注册
     * @param userTel
     * @param userPwd
     * @param verifyCode
     * @param provinceId
     * @param cityId
     * @param districtId
     * @return
     */
    public static Call register(String userTel,String userPwd,String verifyCode,
                                String provinceId,String cityId,String districtId){
        RequestBody body=new FormBody.Builder()
                .add("userTel",userTel)
                .add("userPw",userPwd)
                .add("verifyCode",verifyCode)
                .add("provinceId",provinceId)
                .add("cityId",cityId)
                .add("districtId",districtId)
                .build();
        Request request=getRequest(getHOST()+getRegister(),body);
        return getOkHttpClient().newCall(request);
    }


    /**
     * 登录
     * @param userTel
     * @param userpwd
     * @return
     */
    public static Call login(String userTel,String userpwd){
        RequestBody body=new FormBody.Builder()
                .add("userTel",userTel)
                .add("userPw",userpwd)
                .build();
        Request request=getRequest(getHOST()+getLogin(),body);
        return getOkHttpClient().newCall(request);
    }


    public static Call requestInterestBooks(String num){
        RequestBody body=new FormBody.Builder()
                .add("num",num)
                .build();
        Request request=getRequest(getHOST()+getInterestBooks(),body);
        return getOkHttpClient().newCall(request);
    }

    public static Call requestInterestUsers(String num){
        RequestBody body=new FormBody.Builder()
                .add("num",num)
                .build();
        Request request=getRequest(getHOST()+getInterestUesr(),body);
        return  getOkHttpClient().newCall(request);
    }

    public static Call requestSameCityBooks(String num,String cityname){
        RequestBody body=new FormBody.Builder()
                .add("num",num)
                .add("cityName",cityname)
                .build();
        Request request=getRequest(getHOST()+getSameCityBooks(),body);
        return  getOkHttpClient().newCall(request);
    }

}
