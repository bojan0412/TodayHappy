package com.android.bojan.happytoday.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.model.entry.JokeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class JokeQuickAdapter extends BaseQuickAdapter<JokeBean.ResultBean, BaseViewHolder> {
    public JokeQuickAdapter() {
        super(R.layout.joke_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, JokeBean.ResultBean item) {
        helper.setText(R.id.tv_joke_content, item.getContent());
        helper.setText(R.id.tv_joke_date, item.getUnixtime() + "");
    }
}
