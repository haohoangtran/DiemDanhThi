package lachongmedia.vn.demodiemdanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;
import lachongmedia.vn.demodiemdanh.adapters.HAdapter;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.databases.model.Teacher;

public class ChooseActivity extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_avt)
    CircleImageView ivAvatar;
    @BindView(R.id.btDiemdanh)
    FButton btDiemDanh;
    @BindView(R.id.btCoithi)
    FButton btCoithi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String id = getIntent().getStringExtra("id");
        Teacher teacher = DbContext.instance.findTeacher(id);
        if (teacher != null) {
            Picasso.with(this).load(teacher.getUrl()).into(ivAvatar);
            tvName.setText(teacher.getName());
        }
        btDiemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, LoginActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
        btCoithi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(ChooseActivity.this)
                        .setAdapter(new HAdapter(ChooseActivity.this))
                        .setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                                Intent intent;
                                if (position == 0) {
                                    intent = new Intent(ChooseActivity.this, DiemdanhThiActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("type",2);
                                    DbContext.instance.onNewTask();
                                } else {
                                    intent = new Intent(ChooseActivity.this, UnitTestActivity.class);
                                }
                                startActivity(intent);
                            }
                        })
                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();
            }
        });
    }
}
