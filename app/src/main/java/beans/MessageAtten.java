package beans;

/**
 * Created by heshu on 2017/9/18.
 */

public class MessageAtten {
    private String news;
    private int imageId;
    private String neme;
    private String timt;
    public MessageAtten(String news, int imageId,String neme,String timt){
        this.news = news;
        this.imageId = imageId;
        this.neme = neme;
        this.timt=timt;
    }
    public String getNews(){
        return news;
    }
    public int getImageId(){
        return imageId;
    }
    public String getNeme(){return neme;}
    public String getTimt(){return timt;}

}
