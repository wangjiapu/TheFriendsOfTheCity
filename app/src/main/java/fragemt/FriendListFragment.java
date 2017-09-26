package fragemt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import beans.Book;
import beans.MessageAtten;

/**
 * Created by heshu on 2017/9/25.
 */

public class FriendListFragment extends Fragment {

    private View rootView;
    private List<MessageAtten> mMessageAttenList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FriendListAdapter mFriendListAdapter;


    class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder>{

        private List<MessageAtten> mMessageAttenList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView MessageAttenName;
            TextView Messageautograph;
            Button Messagegeunfollow;
            public ViewHolder(View itemView) {
                super(itemView);
                MessageAttenName = (TextView)itemView.findViewById(R.id.frienfs_list_name);
                Messageautograph = (TextView)itemView.findViewById(R.id.frienfs_list_autograph);
                Messagegeunfollow =(Button)itemView.findViewById(R.id.frienfs_list_unfollow);
            }
        }

        protected FriendListAdapter(List<MessageAtten> mMessageAttenList){
            this.mMessageAttenList =mMessageAttenList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_friends_list_tiem,parent,false);
            ViewHolder holder =new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final MessageAtten messageAtten = mMessageAttenList.get(position);
            holder.MessageAttenName.setText(messageAtten.getNeme());
            holder.Messagegeunfollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    MessageAtten messageAtten1= mMessageAttenList.get(position);
                    Toast.makeText(view.getContext(),"取消关注"+messageAtten.getNeme(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMessageAttenList.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBookAtten();
    }

    private void initBookAtten() {
        for(int i=0;i<10;i++){
            MessageAtten messagrAtten = new MessageAtten("测试"+i,R.mipmap.atten_image,"用户"+i,"12:1"+i);
            mMessageAttenList.add(messagrAtten);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_friends_list, container, false);
        initView(rootView);
        return rootView;
    }
    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.fragment_friends_list_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFriendListAdapter = new FriendListAdapter(mMessageAttenList);
        mRecyclerView.setAdapter(mFriendListAdapter);
    }
}
