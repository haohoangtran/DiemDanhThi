package lachongmedia.vn.demodiemdanh;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.GradientDrawable;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import lachongmedia.vn.demodiemdanh.adapters.DiemDanhthiAdapter;
import lachongmedia.vn.demodiemdanh.adapters.DiemdanhAdapter;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.databases.model.Student;

public class UnitTestActivity extends AppCompatActivity {
    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    private static final String TAG = UnitTestActivity.class.getSimpleName();
    @BindView(R.id.iv_avt)
    CircleImageView ivAvatarStudent;
    @BindView(R.id.tv_name)
    TextView tvNameStudent;
    @BindView(R.id.sp_class)
    AppCompatSpinner spClass;
    @BindView(R.id.sp_subject)
    AppCompatSpinner spSubject;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tl_tab)
    TabLayout tabLayout;
    DiemDanhthiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        ButterKnife.bind(this);
        adapter=new DiemDanhthiAdapter(DbContext.instance.getStudents());
        rvClass.setAdapter(adapter);
        rvClass.setLayoutManager(new LinearLayoutManager(this));
        tvTotal.setText("Số sinh viên nộp bài: 0/"+DbContext.instance.getStudents().size());
        ArrayAdapter<String> productaddapter;
        List<String> productname=new ArrayList<String>();
        String[] a=new String[1];
        int type=getIntent().getIntExtra("type",-1);
        if (type==1) {
            productname.add("Lớp 5A");
            a[0]="Không";
        }else {
            productname.add("8h50-10h45");
            a[0]="Cơ sở dữ liệu";
        }
        productaddapter = new ArrayAdapter<>(this,R.layout.spinner_item, productname);
        spClass.setAdapter(productaddapter);

        spSubject.setAdapter(new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item,a));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, String.format("onTabSelected: %s",tab.getPosition() ) );
                if (tab.getPosition()==0){
                    adapter=new DiemDanhthiAdapter(DbContext.instance.getStudents());
                    rvClass.setAdapter(adapter);
                }else if (tab.getPosition()==1){
                    adapter=new DiemDanhthiAdapter(DbContext.instance.getStudentCheck());
                    rvClass.setAdapter(adapter);
                }else {
                    adapter=new DiemDanhthiAdapter(DbContext.instance.getStudentUnCheck());
                    rvClass.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        NfcManager manager = (NfcManager) this.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (!(adapter != null && adapter.isEnabled())) {
            AlertDialog dialog = new AlertDialog.Builder(this).setMessage("Bạn chưa bật NFC ấn ok để vào menu").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent setnfc = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(setnfc);
                }
            }).create();
            dialog.setCancelable(false);
            dialog.show();
        }
        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.white));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String id = Utils.byteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
            Log.e("HEX", String.format("onNewIntent: %s", Utils.bytesToHex(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID))));
            Student student=DbContext.instance.findStudent(id);
            if (student!=null){
                Picasso.with(this).load(student.getAvatar()).into(ivAvatarStudent);
                tvNameStudent.setText(student.getName());
                student.setHereNow(true);
                tvTotal.setText("Số sinh viên nộp bài: "+DbContext.instance.countStudentnow()+'/'+DbContext.instance.getStudents().size());
            }
            if (tabLayout.getSelectedTabPosition()==0){
                adapter=new DiemDanhthiAdapter(DbContext.instance.getStudents());
                rvClass.setAdapter(adapter);
            }else if (tabLayout.getSelectedTabPosition()==1){
                adapter=new DiemDanhthiAdapter(DbContext.instance.getStudentCheck());
                rvClass.setAdapter(adapter);
            }
            else if (tabLayout.getSelectedTabPosition()==2){
                adapter=new DiemDanhthiAdapter(DbContext.instance.getStudentUnCheck());
                rvClass.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DbContext.instance.onNewTask();
    }
}
