package fragemt;

/**
 * Created by heshu on 2017/9/18.
 */

public class MessageAtten {
    private String news;
    private int imageId;
    public MessageAtten(String news, int imageId){
        this.news = news;
        this.imageId = imageId;
    }
    public String getNews(){
        return news;
    }
    public int getImageId(){
        return imageId;
    }
}
