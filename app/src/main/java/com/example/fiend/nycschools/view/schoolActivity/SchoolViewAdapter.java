package com.example.fiend.nycschools.view.schoolActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import com.example.fiend.nycschools.R;
import com.example.fiend.nycschools.model.NYCSchool;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SchoolViewAdapter extends RecyclerView.Adapter<SchoolViewAdapter.ViewHolder> {

    private final onClickListener listener;
    List<NYCSchool> items = new ArrayList<>();
    private int lastPosition = -1;
    Context context;

    public SchoolViewAdapter(List<NYCSchool> items, onClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public void setList(List<NYCSchool> itemList) {
        this.items = itemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,textViewAddress,textViewZipCode,textViewWebSite,textViewEmail,textViewTotalStudents;

        public ViewHolder(View itemView) {
            super(itemView);
            //Binding textViews
            textViewName = itemView.findViewById(R.id.tvSchools);
            textViewAddress = itemView.findViewById(R.id.tvAddress);
            textViewZipCode = itemView.findViewById(R.id.tvZipCode);
            textViewWebSite = itemView.findViewById(R.id.tvWebSite);
            textViewEmail = itemView.findViewById(R.id.tvEmail);
            textViewTotalStudents = itemView.findViewById(R.id.tvTotalStudents);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.school_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final NYCSchool school = items.get(position);
        holder.textViewName.setText(school.getSchoolName());
        holder.textViewAddress.setText(school.getPrimaryAddressLine1());
        holder.textViewZipCode.setText(school.getZip());
        holder.textViewWebSite.setText(school.getWebsite());
        holder.textViewEmail.setText(school.getSchoolEmail());
        holder.textViewTotalStudents.setText(school.getTotalStudents());
        setAnimation(holder.itemView, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.onSchoolSelected(school);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.ZORDER_BOTTOM, 0.5f, Animation.ZORDER_BOTTOM, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    interface onClickListener{
        void onSchoolSelected(NYCSchool school);
    }

}
