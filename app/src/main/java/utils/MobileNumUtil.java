package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiyou3g on 2017/9/27.
 * 手机号判断正则表达式
 */

public class MobileNumUtil {
    public static boolean isMobileNO(String mobiles){
        Pattern p=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher=p.matcher(mobiles);
        return matcher.matches();
    }
}
