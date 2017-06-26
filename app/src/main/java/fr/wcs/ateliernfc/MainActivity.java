package fr.wcs.ateliernfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Intent intent;
    private ListView listViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listViewRecords =(ListView) findViewById(R.id.listViewRecords);


    }


    @Override
    protected void onNewIntent(Intent intent){
        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Toast.makeText(this, "NFC is here", Toast.LENGTH_SHORT).show();

            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];

                }
                List<NdefRecord> records = new ArrayList<>();

                for (NdefMessage msg : messages){
                    records.addAll(Arrays.asList(msg.getRecords()));
                }
                this.listViewRecords.setAdapter(new NdefRecordAdapter(records, this.getLayoutInflater()));
            }

        }
    }

}