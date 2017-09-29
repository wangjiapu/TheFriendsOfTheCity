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

import beans.MessageAtten;

/**
 * Created by xiyou3g on 2017/9/19.
 * 附近的人
 */

public class CloseFragment extends Fragment{

    private View rootView;
    private View back;
    private List<MessageAtten> mMessageAttenList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CloseFragmentAdapter mCloseFragmentAdapter;

    class CloseFragmentAdapter extends RecyclerView.Adapter<CloseFragmentAdapter.ViewHolder>{
        private List<MessageAtten> mMessageAttenList;
        class ViewHolder extends RecyclerView.ViewHolder{

            TextView MessageAttenName;
            TextView Messageautograph;
            Button Messagegefollow;
            public ViewHolder(View itemView) {
                super(itemView);
                MessageAttenName = (TextView)itemView.findViewById(R.id.fragment_close_name);
                Messageautograph = (TextView)itemView.findViewById(R.id.fragment_close_autograph);
                Messagegefollow =(Button)itemView.findViewById(R.id.fragment_close_follow);
            }
        }

        protected CloseFragmentAdapter(List<MessageAtten> mMessageAttenList){
            this.mMessageAttenList =mMessageAttenList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_close_tiem,parent,false);
            ViewHolder holder =new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final MessageAtten messageAtten = mMessageAttenList.get(position);
            holder.MessageAttenName.setText(messageAtten.getNeme());
            holder.Messagegefollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    MessageAtten messageAtten1= mMessageAttenList.get(position);
                    Toast.makeText(view.getContext(),"关注"+messageAtten.getNeme(),Toast.LENGTH_SHORT).show();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_close,container,false);
        initView(rootView);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return rootView;
    }
    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.fragment_close_recyclerView);
        back = (TextView)rootView.findViewById(R.id.close_back);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCloseFragmentAdapter = new CloseFragmentAdapter(mMessageAttenList);
        mRecyclerView.setAdapter(mCloseFragmentAdapter);

    }
}
