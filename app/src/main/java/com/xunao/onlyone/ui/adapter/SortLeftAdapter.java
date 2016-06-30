package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunao.onlyone.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SortLeftAdapter extends BaseAdapter {
    private String[] leftStr;
    boolean[] flagArray;
    private Context context;
    private View red_line;

    public SortLeftAdapter(Context context, String[] leftStr, boolean[] flagArray) {
        this.leftStr = leftStr;
        this.context = context;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return leftStr.length;
    }

    @Override
    public Object getItem(int arg0) {
        return leftStr[arg0];
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sort_left, null);
            holder.left_list_item = (TextView) convertView.findViewById(R.id.left_list_item);
            holder.red_line = convertView.findViewById(R.id.red_line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.updataView(position);
        return convertView;
    }

    private class ViewHolder {
         TextView left_list_item;
        View red_line;

        public void updataView(final int position) {
            left_list_item.setText(leftStr[position]);
            if (flagArray[position]) {
                left_list_item.setBackgroundColor(Color.parseColor("#f3f3f3"));
                red_line.setVisibility(View.VISIBLE);
            } else {
                left_list_item.setBackgroundColor(Color.WHITE);
                red_line.setVisibility(View.INVISIBLE);
            }
        }

    }
}

