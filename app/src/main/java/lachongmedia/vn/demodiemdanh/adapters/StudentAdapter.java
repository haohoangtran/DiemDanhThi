package lachongmedia.vn.demodiemdanh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lachongmedia.vn.demodiemdanh.R;
import lachongmedia.vn.demodiemdanh.adapters.viewholders.StudentViewHolder;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.eventbus.ViewChooseEvent;

/**
 * Created by tranh on 5/11/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.bind(DbContext.instance.getStudentListCheck().get(position));

    }
    @Override
    public int getItemCount() {
        return DbContext.instance.getStudentListCheck().size();
    }
}
