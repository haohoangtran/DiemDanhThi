package lachongmedia.vn.demodiemdanh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lachongmedia.vn.demodiemdanh.R;
import lachongmedia.vn.demodiemdanh.adapters.viewholders.DiemDanhViewHolder;
import lachongmedia.vn.demodiemdanh.databases.DbContext;
import lachongmedia.vn.demodiemdanh.databases.model.Student;

/**
 * Created by tranh on 5/27/2017.
 */

public class DiemdanhAdapter extends RecyclerView.Adapter<DiemDanhViewHolder> {
    private List<Student> students;

    public DiemdanhAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public DiemDanhViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_itemview, parent, false);
        return new DiemDanhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiemDanhViewHolder holder, int position) {
        holder.bind(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
