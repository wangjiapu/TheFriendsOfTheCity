package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MessageAdapter;
import beans.MessageAtten;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class MessageFragment extends Fragment {
    private View rootView;
    private List<MessageAtten> messageAttenList = new ArrayList<>();

    private RecyclerView mRecyclerView=null;
    private MessageAdapter mMessageAdapter;

    private ImageView mLeftMenu;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMeddageAtten();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_message,container,false);
        initView(rootView);
        mLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }


    private void initView(View rootView) {
        mLeftMenu=rootView.findViewById(R.id.leftitem);
        mRecyclerView = rootView.findViewById(R.id.message_linkman);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessageAdapter = new MessageAdapter(getActivity(),messageAttenList);
        mRecyclerView.setAdapter(mMessageAdapter);
    }


    private void initMeddageAtten() {
        for(int i=0;i<20;i++){
            MessageAtten messagrAtten = new MessageAtten("测试"+i,R.mipmap.atten_image,"用户"+i,"12:1"+i);
            messageAttenList.add(messagrAtten);
        }
    }
}
