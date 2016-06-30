package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private String[][] mList;

    public GridViewAdapter(Context mContext, String[][] rightStr) {
        super();
        this.mContext = mContext;
        this.mList = rightStr;
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        } else {
            return this.mList.length;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList == null) {
            return null;
        } else {
            return this.mList[position];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from
                    (this.mContext).inflate(R.layout.item_sort_right_gridview, null, false);
            holder.tv_good = (TextView) convertView.findViewById(R.id.tv_text);
            holder.imag_good = (ImageView) convertView.findViewById(R.id.imag_good);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;

    }


    private class ViewHolder {
        TextView tv_good;
        ImageView imag_good;
    }

}
