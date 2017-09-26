package activitys;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import adapters.MsgAdapter;
import beans.Msg;

public class ChatActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputTest;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        msgList=  (ArrayList<Msg>) getIntent().getSerializableExtra("msgList");
        //初始化消息99
        //initMsgs();
        //各种赋值初始化
        inputTest = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgRecyclerView =(RecyclerView)findViewById(R.id.msg_recycler_view);
        //创建一个LinearLayoutManager（线性布局）对象将它设置到RecyclerView
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutmanager);
        //调用构造方法创造实例,参数消息集合
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        //发送按钮监听器
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String content = inputTest.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有新消息时，刷新RecyclView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);//将RecyclerView定位到最后一行
                    inputTest.setText("");//清空输入框
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello guy",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
