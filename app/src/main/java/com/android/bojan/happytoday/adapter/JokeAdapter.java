package com.android.bojan.happytoday.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.bojan.happytoday.R;

import com.android.bojan.happytoday.model.entry.JokeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by bojan
 * on 2018/8/27
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {
    private List<JokeBean.ResultBean> mList;
    private Context mContext;

    public JokeAdapter(List<JokeBean.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_joke, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.content.setText(mList.get(i).getContent());
        viewHolder.time.setText(mList.get(i).getUnixtime() + "");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.joke_time)
        TextView time;
        @BindView(R.id.joke_content)
        TextView content;
        @BindView(R.id.image)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
