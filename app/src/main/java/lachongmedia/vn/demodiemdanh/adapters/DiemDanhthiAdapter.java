package lachongmedia.vn.demodiemdanh.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lachongmedia.vn.demodiemdanh.R;
import lachongmedia.vn.demodiemdanh.adapters.viewholders.DiemDanhViewHolder;
import lachongmedia.vn.demodiemdanh.adapters.viewholders.DiemDanhthiViewHolder;
import lachongmedia.vn.demodiemdanh.databases.model.Student;

/**
 * Created by tranh on 5/30/2017.
 */

public class DiemDanhthiAdapter extends RecyclerView.Adapter<DiemDanhthiViewHolder> {
    private List<Student> students;

    public DiemDanhthiAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public DiemDanhthiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.diemdanh_itemview, parent, false);
        return new DiemDanhthiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiemDanhthiViewHolder holder, int position) {
        holder.bind(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
