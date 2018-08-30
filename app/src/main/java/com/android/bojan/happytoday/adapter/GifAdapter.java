package com.android.bojan.happytoday.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.model.entry.GifBean;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class GifAdapter extends CommonAdapter<GifBean.ResultBean> {
    private Context context;

    public GifAdapter(Context context, List<GifBean.ResultBean> datas) {
        super(context, R.layout.item_gif, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, GifBean.ResultBean gifBean, int position) {
        holder.setText(R.id.tv_gif_title, gifBean.getContent());
        String url = gifBean.getUrl();

        if (url.endsWith("f")) {
            Glide.with(context)
                    .load(gifBean.getUrl())
                    .asGif()
                    .placeholder(R.mipmap.ic_error)
                    .into((ImageView) holder.getView(R.id.iv_gif));
        } else {
            Glide.with(context)
                    .load(gifBean.getUrl())
                    .placeholder(R.mipmap.ic_error)
                    .into((ImageView) holder.getView(R.id.iv_gif));
        }
    }
}
