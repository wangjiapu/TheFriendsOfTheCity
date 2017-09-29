package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import activitys.ChatActivity;
import beans.MessageAtten;
import beans.Msg;
import utils.MsgManager;

/**
 * Created by heshu on 2017/9/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHoldr> {

    private List<MessageAtten> mMeddageAttenList;
    private ArrayList<MsgManager.Key> mAllMsgs;
    private Context mContext;
    private View mCurrent;
    private MsgManager.MsgListener mL=new MsgManager.MsgListener() {
        @Override
        public void onReceive(String name, String content) {
            mCurrent.post(new Runnable() {
                @Override
                public void run() {
                    notifyAdd();
                }
            });
        }
    };

    public MessageAdapter(Context context, List<MessageAtten> meddageAttenList) {
        this.mContext = context;
        mMeddageAttenList = meddageAttenList;
    }

    @Override
    public ViewHoldr onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_massage_item,
                parent, false);
        final ViewHoldr holder = new ViewHoldr(view);
        return holder;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mCurrent=recyclerView;
        mAllMsgs=MsgManager.get().getList();
        MsgManager.get().addOnMsgListener(mL);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mCurrent=null;
        MsgManager.get().removeListener(mL);
    }


    public void notifyAdd()
    {
        notifyDataSetChanged();         //有消息时不一定要增加条目，方便直接这个
    }

    @Override
    public void onBindViewHolder(final ViewHoldr holder, int position) {
        MessageAtten messageAtten = mMeddageAttenList.get(position);
        //holder.attenImage.setImageResource(messageAtten.getImageId());
        holder.attenNews.setText(mAllMsgs.get(position).msg.get(mAllMsgs.get(position).msg.size()-1).getContent());
        holder.attenName.setText(mAllMsgs.get(position).name);
        holder.attenTime.setText(mAllMsgs.get(position).msg.get(mAllMsgs.get(position).msg.size()-1).time);

        //点击监听器
        holder.meddageView.setOnClickListener(new View.OnClickListener() {
            List<Msg> msgList = new ArrayList<Msg>();
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent();
                intent.setClass(view.getContext(), ChatActivity.class);
                intent.putExtra("usrId", mAllMsgs.get(position).name);
                mContext.startActivity(intent);
            }

        });
        //长按监听器
        holder.meddageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                MessageAtten messageAtten = mMeddageAttenList.get(position);
                Toast.makeText(view.getContext(), "长按控件:" + messageAtten.getNews(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAllMsgs.size();
    }

    class ViewHoldr extends RecyclerView.ViewHolder {
        View meddageView;
        ImageView attenImage;
        TextView attenNews;
        TextView attenName;
        TextView attenTime;

        ViewHoldr(View itemView) {
            super(itemView);
            meddageView = itemView;
            attenImage = (ImageView) itemView.findViewById(R.id.atten_image);
            attenNews = (TextView) itemView.findViewById(R.id.atten_news);
            attenName = (TextView)itemView.findViewById(R.id.atten_name);
            attenTime = (TextView)itemView.findViewById(R.id.atten_time);
        }
    }

}
