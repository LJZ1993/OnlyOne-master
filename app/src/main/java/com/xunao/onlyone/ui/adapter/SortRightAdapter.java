package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SortRightAdapter extends SectionedBaseAdapter {

    private Context mContext;
    private String[] leftStr;
    private String[][] rightStr;
    private GridView gridView;

    public SortRightAdapter(Context context, String[] leftStr, String[][] rightStr) {
        this.mContext = context;
        this.leftStr = leftStr;
        this.rightStr = rightStr;
    }

    @Override
    public Object getItem(int section, int position) {
        return rightStr[section][position];
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        return leftStr.length;
    }

    @Override
    public int getCountForSection(int section) {
        return 1;
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = (LinearLayout) inflator.inflate(R.layout.item_sort_right, null);
            holder.gridView = (GridView) convertView.findViewById(R.id.listview_item_gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //选中的section
        String leftSelectStr = leftStr[section];
        String[] rightSelectStr = rightStr[section];
        GridViewAdapter gridViewAdapter = new GridViewAdapter(mContext, leftSelectStr, rightSelectStr);
        holder.gridView.setAdapter(gridViewAdapter);
        return convertView;
    }


    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            //LayoutInflate有三种写法
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_sort_header, null);
            holder.tv = (TextView) convertView.findViewById(R.id.textItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(leftStr[section]);
        return convertView;
    }

    private class ViewHolder {
        GridView gridView;
        TextView tv;
    }



}
