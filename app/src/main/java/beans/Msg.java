package beans;

import java.io.Serializable;

/**
 * Created by heshu on 2017/8/27.
 */

public class Msg implements Serializable{
    public static final int TYPE_RECEIVED = 0;//收到的消息
    public static final int TYPE_SENT = 1;//发出去的消息
    private String content;
    public String time;
    private int type;
    //content表示消息内容，type表示类型
    public Msg(String content , int type,String time){
        this.content = content;
        this.type = type;
        this.time=time;
    }
    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
}
