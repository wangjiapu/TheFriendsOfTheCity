package beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PUJW on 2017/9/26.
 */

public class InfoLists {

    public static List<ProvinceInfo> PInfos=new ArrayList<>();//省

    public static List<CityInfo> CInfos=new ArrayList<>();//市

    public static List<DistrictsInfo> DInfos=new ArrayList<>();//区

    public static List<BookInfo> BInfos=new ArrayList<>();//感兴趣的书

    public static List<UserInfo> UInfos=new ArrayList<>();//感兴趣的人的列表

    public static List<BookInfo> SameBInfos=new ArrayList<>();//同城

    public static List<BookInfo> searchInfos=new ArrayList<>();//搜索

    public static List<BookInfo> readedInfos=new ArrayList<>();//已读完

    public static List<BookInfo> collectionedInfos=new ArrayList<>();//已收藏

    public static List<BookInfo> borrowedInfos=new ArrayList<>();//已借阅

}
