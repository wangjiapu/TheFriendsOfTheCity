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
/**
 * 聊天页面
 * */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputTest;
    private Button send;
    private Button back;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //Intent intent = getIntent();
        msgList=  (ArrayList<Msg>) getIntent().getSerializableExtra("msgList");
        initVive();
        send.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initVive() {
        inputTest = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgRecyclerView =(RecyclerView)findViewById(R.id.msg_recycler_view);
        back=(Button)findViewById(R.id.chat_bar_back);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);

        msgRecyclerView.setLayoutManager(layoutmanager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                String content = inputTest.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputTest.setText("");
                }
                break;
            case R.id.chat_bar_back:
                finish();
                break;
        }

    }
}
