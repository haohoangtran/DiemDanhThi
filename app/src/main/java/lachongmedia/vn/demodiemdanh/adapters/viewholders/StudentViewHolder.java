package lachongmedia.vn.demodiemdanh.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import lachongmedia.vn.demodiemdanh.R;
import lachongmedia.vn.demodiemdanh.databases.model.Student;

/**
 * Created by tranh on 5/11/2017.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.iv_avt)
    ImageView ivAvt;
    public StudentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(Student student){
        if (student!=null){
            tvClass.setText(student.getNameClass());
            tvName.setText(student.getName());
            Picasso.with(itemView.getContext()).load(student.getAvatar()).into(ivAvt);
        }
    }

}
