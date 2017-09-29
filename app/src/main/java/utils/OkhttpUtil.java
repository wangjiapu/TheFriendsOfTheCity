package utils;

import java.io.File;
import java.util.List;

import cookies.CookiesManager;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkhttpUtil {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager()).build();

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
    private static final String uploadImage="/picture/uploadPicture";
    private static final String searchBook="/bookInfo/searchBooks";

    private static final String detailBookInfo="/bookInfo/getDetailBookInfo";

    public static String getDetailBookInfo() {
        return detailBookInfo;
    }

    public static String getSearchBook() {
        return searchBook;
    }

    public static String getUploadImage() {
        return uploadImage;
    }

    public static String getSameCityBooks() {
        return SameCityBooks;
    }


    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
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

    /**
     * 获取 cityname下的图书
     * @param num
     * @param cityname
     * @return
     */
    public static Call requestSameCityBooks(String num,String cityname){
        RequestBody body=new FormBody.Builder()
                .add("num",num)
                .add("cityName",cityname)
                .build();
        Request request=getRequest(getHOST()+getSameCityBooks(),body);
        return  getOkHttpClient().newCall(request);
    }

    /**
     * 上传图片
     * @param files  图片地址所在的url集合
     * @return
     */

    public static Call upImage(List<String> files){
        MultipartBody.Builder builder=new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        for(String f:files){
            File file=new File(f);
            if (file.exists()){
                builder.addFormDataPart("img",file.getName(),
                        RequestBody.create(MEDIA_TYPE_PNG,file));

            }

        }
        MultipartBody body=builder.build();
        Request request=getRequest(getHOST()+getUploadImage(),body);
        return getOkHttpClient().newCall(request);
    }

    /**
     * 搜索书籍
     * @param bookname
     * @return
     */
    public static Call searchBooks(String bookname){
        RequestBody body=new FormBody.Builder()
                .add("bookName",bookname)
                .build();
        Request request=getRequest(getHOST()+getSearchBook(),body);
        return  getOkHttpClient().newCall(request);
    }

    /**
     * 获取书本详细信息
     * @param bookinfoid
     * @return
     */
    public static Call requestdetailBookInfo(String bookinfoid){
        RequestBody body=new FormBody.Builder()
                .add("bookInfoId",bookinfoid)
                .build();
        Request request=getRequest(getHOST()+getDetailBookInfo(),body);
        return  getOkHttpClient().newCall(request);
    }

    private static final String pubishedBook="/bookInfo/getPublishedBook";

    public static String getPubishedBook() {
        return pubishedBook;
    }
    public static Call requestpublishedBook(){
        RequestBody body=new FormBody.Builder()
                .build();
        Request request=getRequest(getHOST()+getPubishedBook(),body);
        return  getOkHttpClient().newCall(request);
    }

    private static final String borrowedBooks ="/bookInfo/getBorrowedBooks";

    public static String getBorrowedBooks() {
        return borrowedBooks;
    }
    public static Call requestBorrowedBook(String num){
        RequestBody body=new FormBody.Builder()
                .add("rows",num)
                .build();
        Request request=getRequest(getHOST()+getPubishedBook(),body);
        return  getOkHttpClient().newCall(request);
    }




    private static final String finishReadBook="/bookInfo/finishReadBook";

    private static String getFinishReadBook() {
        return finishReadBook;
    }
    public static Call requestFinishReadBook(String bookinfoid){
        RequestBody body=new FormBody.Builder()
                .add("bookInfoId",bookinfoid)
                .build();
        Request request=getRequest(getHOST()+getFinishReadBook(),body);
        return  getOkHttpClient().newCall(request);
    }





    private static final String readedBook="/bookInfo/getReadBooks";

    private static String getReadedBook() {
        return readedBook;
    }


    public static Call requestReadedBook(String num){
        RequestBody body=new FormBody.Builder()
                .add("rows",num)
                .build();
        Request request=getRequest(getHOST()+getReadedBook(),body);
        return  getOkHttpClient().newCall(request);
    }


}
