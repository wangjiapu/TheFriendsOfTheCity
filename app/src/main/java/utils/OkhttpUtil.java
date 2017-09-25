package utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpUtil {

    private static OkHttpClient okHttpClient=new OkHttpClient();
    private static final String HOST="http://bf.corefuture.cn/bookfriend";

    private static final String AllProvince="/place/getAllProvince";


    private static final String CitiesByProvinceId="/place/getCitiesByProvinceId";

    private static final String DistrictsByCityId="/place/getDistrictsByCityId";


    private static Request getRequest(String url){
        return null;
    }

}
