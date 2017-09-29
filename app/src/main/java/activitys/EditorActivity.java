package activitys;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xiyou3g.thefriendsofthecity.R;

/**
 * Created by heshu on 2017/9/30.
 */

public class EditorActivity extends AppCompatActivity {
    private Button back;
    private Button update;
    private EditText nickname;
    private EditText autograph;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        initView();
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        back = (Button) findViewById(R.id.editor_back);
        update = (Button)findViewById(R.id.editor_update);
        nickname = (EditText)findViewById(R.id.edit_edit_test1);
        autograph = (EditText)findViewById(R.id.edit_edit_test2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.hide();
        }
    }
}
