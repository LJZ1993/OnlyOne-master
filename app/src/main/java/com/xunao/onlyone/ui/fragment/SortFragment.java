package com.xunao.onlyone.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.activity.SearchActivity;
import com.xunao.onlyone.ui.adapter.SortLeftAdapter;
import com.xunao.onlyone.ui.adapter.SortRightAdapter;
import com.xunao.onlyone.ui.base.BaseFragment;
import com.xunao.onlyone.ui.widget.PinnedHeaderListView;

import butterknife.BindView;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 分类
 */
public class SortFragment extends BaseFragment {

    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.listview_left)
    ListView listviewLeft;
    @BindView(R.id.pinnedheaderlistview)
    PinnedHeaderListView pinnedHeaderListView;
    private boolean isScroll = true;
    private SortLeftAdapter adapter;
    //从服务器上获取数据（左边是字符串，用数组；右边有图片和字符串，需要使用对象）
    //所以左边数据仍然用数组表示，右边换成集合
    private String[] leftStr = new String[]{"数码产品","家用电器", "健身器材", "美食天地","虚拟充值","生活日用","玩具乐器","办公用品","家具建材","数码产品","家用电器", "健身器材", "美食天地","虚拟充值","生活日用","玩具乐器","办公用品","家具建材"};
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false};
    private String[][] rightStr = new String[][]{{"手机","笔记本","照相机","微单","摄像机"},
            {"手机4", "手机5","手机6","手机61"},
            {"手机7", "手机8"}, {"手机10","手机11","手机12"}, {"手机13","手机14","手机15"},
            {"手机a","手机s","手机d"}, {"手机f","手机g","手机h"},
            {"手机k","手机m","手机n"}, {"手机x","手机z","手机q"},{"手机","笔记本","照相机","微单","摄像机"},
            {"手机4", "手机5","手机6","手机61"},
            {"手机7", "手机8"}, {"手机10","手机11","手机12"}, {"手机13","手机14","手机15"},
            {"手机a","手机s","手机d"}, {"手机f","手机g","手机h"},
            {"手机k","手机m","手机n"}, {"手机x","手机z","手机q","手机z","手机q","手机z","手机q","手机z","手机q","手机z","手机q","手机z","手机q","手机z","手机q"}
    };

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initData() {
      final SortRightAdapter rightAdapter = new SortRightAdapter(baseActivity, leftStr, rightStr);
        pinnedHeaderListView.setAdapter(rightAdapter);
        adapter = new SortLeftAdapter(baseActivity, leftStr, flagArray);
        listviewLeft.setAdapter(adapter);
        listviewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;
                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                adapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += rightAdapter.getCountForSection(i) + 1;
                }
                pinnedHeaderListView.setSelection(rightSection);
                Log.w("rightSection","rightSection"+rightSection);

            }

        });

        pinnedHeaderListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedHeaderListView.getLastVisiblePosition() == (pinnedHeaderListView.getCount() - 1)) {
                            listviewLeft.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedHeaderListView.getFirstVisiblePosition() == 0) {
                            listviewLeft.setSelection(0);
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < rightStr.length; i++) {
                        if (i == rightAdapter.getSectionForPosition(pinnedHeaderListView.getFirstVisiblePosition())) {
                            flagArray[i] = true;
                            x = i;
                        } else {
                            flagArray[i] = false;
                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == listviewLeft.getLastVisiblePosition()) {
//                            z = z + 3;
                            listviewLeft.setSelection(z);
                        }
                        if (x == listviewLeft.getFirstVisiblePosition()) {
//                            z = z - 1;
                            listviewLeft.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            listviewLeft.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });

    }

    

    @Override
    protected void bindEvent() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SearchActivity.class);
            }
        });
    }
}
