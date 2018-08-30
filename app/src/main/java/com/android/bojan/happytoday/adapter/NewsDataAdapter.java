package com.android.bojan.happytoday.adapter;

import android.widget.ImageView;

import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.model.entry.NewsBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class NewsDataAdapter extends BaseQuickAdapter<NewsBean.ResultBean.DataBean, BaseViewHolder> {

    public NewsDataAdapter() {
        super(R.layout.item_news_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.ResultBean.DataBean item) {
        helper.setText(R.id.tv_news_detail_title, item.getTitle());
        helper.setText(R.id.tv_news_detail_author_name, item.getAuthor_name());
        helper.setText(R.id.tv_news_detail_date, item.getDate());
        helper.addOnClickListener(R.id.ll_news_detail);
        Glide.with(mContext)
                .load(item.getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_error)
                .error(R.mipmap.ic_error)
                .crossFade()
                .centerCrop()
                .into((ImageView) helper.getView(R.id.iv_news_detail_pic));
    }
}
