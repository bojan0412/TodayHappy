package com.android.bojan.base.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class ShareUtils {
    public static void share(Context context, String shareContent) {
        StringBuffer sb = new StringBuffer();
        sb.append(shareContent);
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "该手机不支持该操作", Toast.LENGTH_LONG).show();
        }
    }
}
