package fr.wcs.ateliernfc;

import android.nfc.NdefRecord;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by apprenti on 26/06/17.
 */

public class NdefRecordAdapter extends BaseAdapter {

    private List<NdefRecord> records;
    private LayoutInflater inflater;

    public NdefRecordAdapter(List<NdefRecord> records, LayoutInflater inflater) {
        this.records = records;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return this.records.size();
    }

    @Override
    public Object getItem(int position) {
        return this.records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.ndef_record_item, parent, false);
        }
        TextView textViewMime = (TextView) convertView.findViewById(R.id.textViewMime);
        TextView textViewText = (TextView) convertView.findViewById(R.id.textViewText);

        textViewMime.setText(this.records.get(position).toMimeType());
        textViewText.setText(new String(this.records.get(position).getPayload()));


        return convertView;
    }
}
