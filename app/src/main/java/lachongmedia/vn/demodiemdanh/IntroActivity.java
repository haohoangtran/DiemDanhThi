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
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import lachongmedia.vn.demodiemdanh.R;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.databases.model.Student;
import lachongmedia.vn.demodiemdanh.databases.model.Teacher;

public class IntroActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent in = new Intent(this, ChooseActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("id", "F5477242");
        startActivity(in);
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
            Teacher teacher = DbContext.instance.findTeacher(id);
            if (teacher != null) {
                Intent in = new Intent(this, ChooseActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("id", teacher.getId());
                startActivity(in);
            } else {
                Toast.makeText(this, "Thẻ không hợp lệ !", Toast.LENGTH_LONG).show();
            }

        }
    }
}
