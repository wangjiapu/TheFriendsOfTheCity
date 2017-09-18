package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyou3g on 2017/9/17.
 *
 */

public class MessageFragment extends Fragment {
    private View rootView;
    private List<MessageAtten> messageAttenList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_message,container,false);
        initMeddageAtten();
        RecyclerView recyclerView =(RecyclerView) rootView.findViewById(R.id.message_linkman);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MessageAdapter adapterr = new MessageAdapter(messageAttenList);
        recyclerView.setAdapter(adapterr);
        return rootView;
    }

    private void initMeddageAtten() {
        for(int i=0;i<10;i++){
            MessageAtten messagrAtten = new MessageAtten("测试"+i,R.mipmap.ic_launcher);
            messageAttenList.add(messagrAtten);
        }
    }
}
