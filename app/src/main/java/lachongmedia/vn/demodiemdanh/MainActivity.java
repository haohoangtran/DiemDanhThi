package lachongmedia.vn.demodiemdanh;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import lachongmedia.vn.demodiemdanh.adapters.StudentAdapter;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.databases.model.Student;

public class MainActivity extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 200;
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


    @BindView(R.id.rv_std)
    RecyclerView rvStudent;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Danh sách điểm danh");
        }
        studentAdapter = new StudentAdapter();
        rvStudent.setAdapter(studentAdapter);
        rvStudent.setLayoutManager(new LinearLayoutManager(this));
        int a=studentAdapter.getItemCount();
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
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String id = Utils.byteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
            Log.e("UID", String.format("onNewIntent: %s", id));
            Student student = DbContext.instance.findStudent(id);
            if (DbContext.instance.isInClass(student)){
//                Intent intent1=new Intent(MainActivity.this,FlashActivity.class);
//                startActivity(intent1);
                showFlash();
            }
            if (student != null) {
                DbContext.instance.setStudentNow(student);
                DbContext.instance.addStudent(student);
                studentAdapter.notifyDataSetChanged();
            }
        }
    }
    public void showFlash(){
        if (getSupportActionBar()!=null)
            getSupportActionBar().hide();
        ivBg.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                ivBg.setVisibility(View.GONE);
                if (getSupportActionBar()!=null)
                    getSupportActionBar().show();
            }
        }, SPLASH_DISPLAY_LENGTH);
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
    }
}
