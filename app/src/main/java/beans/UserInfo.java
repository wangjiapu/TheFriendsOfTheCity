package beans;

/**
 * Created by xiyou3g on 2017/9/28.
 *
 */

public class UserInfo {
    private static String id="";
    private static String userName="";
    private static boolean sex;
    private static String signature="";
    private static String faviconUrl="";//头像url
    private static String attentionNum="";//关注数
    private static String fansNum="";//粉丝数
    private static int provinceId;

    private static int cityId;
    private static int districtId;
    private static String cityName="";//市名
    private static String provinceName="";//省名
    private static String districtName="";//区名

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserInfo.id = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserInfo.userName = userName;
    }

    public static boolean isSex() {
        return sex;
    }

    public static void setSex(boolean sex) {
        UserInfo.sex = sex;
    }

    public static String getSignature() {
        return signature;
    }

    public static void setSignature(String signature) {
        UserInfo.signature = signature;
    }

    public static String getFaviconUrl() {
        return faviconUrl;
    }

    public static void setFaviconUrl(String faviconUrl) {
        UserInfo.faviconUrl = faviconUrl;
    }

    public static String getAttentionNum() {
        return attentionNum;
    }

    public static void setAttentionNum(String attentionNum) {
        UserInfo.attentionNum = attentionNum;
    }

    public static String getFansNum() {
        return fansNum;
    }

    public static void setFansNum(String fansNum) {
        UserInfo.fansNum = fansNum;
    }

    public static int getProvinceId() {
        return provinceId;
    }

    public static void setProvinceId(int provinceId) {
        UserInfo.provinceId = provinceId;
    }

    public static int getCityId() {
        return cityId;
    }

    public static void setCityId(int cityId) {
        UserInfo.cityId = cityId;
    }

    public static int getDistrictId() {
        return districtId;
    }

    public static void setDistrictId(int districtId) {
        UserInfo.districtId = districtId;
    }

    public static String getCityName() {
        return cityName;
    }

    public static void setCityName(String cityName) {
        UserInfo.cityName = cityName;
    }

    public static String getProvinceName() {
        return provinceName;
    }

    public static void setProvinceName(String provinceName) {
        UserInfo.provinceName = provinceName;
    }

    public static String getDistrictName() {
        return districtName;
    }

    public static void setDistrictName(String districtName) {
        UserInfo.districtName = districtName;
    }
}
