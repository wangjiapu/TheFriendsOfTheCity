package utils;

import android.support.v4.util.ArrayMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.Msg;
import xiyou.mobile.User;

/**
 * Created by Administrator on 2017/9/29.
 */

public class MsgManager {

    private static MsgManager mDefault=new MsgManager();

    private ArrayList<MsgListener> mLs=new ArrayList<>();
    private ArrayList<Key> mAllMsg=new ArrayList<>();
    private ArrayMap<String,ArrayList<Msg>> mAllMsgMap=new ArrayMap<>();
    private User.OnDataSetListener mL=new User.OnDataSetListener() {        //消息通知
        @Override
        public void onDataSet(String data, String name) {
            //mMeddageAttenList.add(new MessageAtten(data, R.mipmap.atten_image,name, new SimpleDateFormat("HH:mm:ss").format(new Date())));
            ArrayList<Msg> list=getList(name);
            list.add(new Msg(data,Msg.TYPE_RECEIVED,new SimpleDateFormat("HH:mm:ss").format(new Date())));

            for (int i=0;i<mLs.size();i++)
            {
                mLs.get(i).onReceive(name,data);
            }
        }
    };

    public static MsgManager get()
    {
        return mDefault;
    }

    public static void init()
    {
        User.get().addOnDataSetListener(get().mL);
    }

    public void addOnMsgListener(MsgListener l){
        mLs.add(l);
    }

    public void removeListener(MsgListener l)
    {
        mLs.remove(l);
    }

    public void sendMsg(String name,String content)
    {
        mAllMsgMap.get(name).add(new Msg(content,Msg.TYPE_SENT,new SimpleDateFormat("HH:mm:ss").format(new Date())));
        User.get().sendPlayData(content,name);
    }

    public ArrayList<Msg> getList(String name)
    {
        ArrayList<Msg> list;
        if ((list=mAllMsgMap.get(name))==null)
        {
            list=new ArrayList<>();
            mAllMsgMap.put(name,list);
            mAllMsg.add(new Key(name,list));
        }

        return list;
    }

    public ArrayList<Key> getList()
    {
        return mAllMsg;
    }

    public static class Key
    {
        public String name;
        public ArrayList<Msg> msg;

        Key(String n,ArrayList m)
        {
            name=n;
            msg=m;
        }
    }

    public static interface MsgListener
    {
        public void onReceive(String name,String content);
    }
}
