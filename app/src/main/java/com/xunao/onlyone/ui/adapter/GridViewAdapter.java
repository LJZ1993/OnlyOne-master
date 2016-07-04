package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.util.ToastUtil;

/**
 * Created by Administrator on 2016/6/30.
 */
public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private String[] rightStr;
    private String leftStr;
    //baseAdapter的四个抽象方法
    public GridViewAdapter(Context mContext, String leftSelectStr, String[] rightSelectStr) {
        this.mContext = mContext;
        this.leftStr = leftSelectStr;
        this.rightStr = rightSelectStr;

    }

    @Override
    public int getCount() {
       // return rightStr.length-5;
        return rightStr.length;
    }

    @Override
    public Object getItem(int position) {
        return rightStr[position];
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
            holder.tv_good = (TextView) convertView.findViewById(R.id.tv_grid_good);
            holder.imag_good = (ImageView) convertView.findViewById(R.id.imag_grid_good);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.Infotoast(mContext,"点击提示");
            }
        });
        holder.tv_good.setText(rightStr[position]);
       // Log.w("rightStr[position]",rightStr[position]);
        holder.imag_good.setImageResource(R.drawable.ic_loading_daisy);
        return convertView;
    }
        class ViewHolder {
        TextView tv_good;
        ImageView imag_good;
    }


}


