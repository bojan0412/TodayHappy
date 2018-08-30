package com.android.bojan.happytoday.adapter;

import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.model.entry.TodayBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class TodayAdapter extends BaseQuickAdapter<TodayBean.ResultBean, BaseViewHolder> {
    public TodayAdapter() {
        super(R.layout.item_today);
    }

    @Override
    protected void convert(BaseViewHolder helper, TodayBean.ResultBean item) {
        helper.setText(R.id.tv_today_title, item.getTitle());
        helper.setText(R.id.tv_today_date, item.getDate());
        helper.addOnClickListener(R.id.ll_today_detail);
    }
}
