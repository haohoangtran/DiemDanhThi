package lachongmedia.vn.demodiemdanh.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lachongmedia.vn.demodiemdanh.R;

/**
 * Created by tranh on 5/30/2017.
 */

public class HAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public HAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.dialogplus_simple, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
//        Typeface type = Typeface.createFromAsset(parent.getContext().getAssets(),"fonts/Arimo-Bold.ttf");
//        textView.setTypeface(type);
        if (position == 0) {
            imageView.setImageResource(R.drawable.ic_school_black_24dp);
            textView.setText("Điểm danh phòng thi");
        } else {
            textView.setText("Nộp bài");
            imageView.setImageResource(R.drawable.ic_group_black_24dp);
        }
        return view;
    }
}
