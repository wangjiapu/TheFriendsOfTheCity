package cookies;

import java.util.List;



import activitys.StartActivity;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookiesManager implements CookieJar {

     private final PersistentCookieStore cookieStore =
             new PersistentCookieStore(StartActivity.getApp());

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list != null && list.size() > 0) {
            for (Cookie item : list) {
                cookieStore.add(httpUrl, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl);
        return cookies;
    }
}
