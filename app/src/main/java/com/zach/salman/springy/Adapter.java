package com.zach.salman.springy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zach.salman.springylib.springyRecyclerView.SpringyAdapterAnimationType;
import com.zach.salman.springylib.springyRecyclerView.SpringyAdapterAnimator;

import java.util.List;

/**
 * Created by Zach on 6/30/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private SpringyAdapterAnimator mAnimator;
    private final List<String> TitleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
        }
    }


    public Adapter(List<String> titleList , RecyclerView recyclerView) {
        this.TitleList = titleList;
        mAnimator = new SpringyAdapterAnimator(recyclerView);
        mAnimator.setSpringAnimationType(SpringyAdapterAnimationType.SLIDE_FROM_BOTTOM);
        mAnimator.addConfig(85,15);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        mAnimator.onSpringItemCreate(itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(TitleList.get(position));
        mAnimator.onSpringItemBind(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return TitleList.size();
    }
}