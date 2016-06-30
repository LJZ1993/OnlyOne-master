package com.xunao.onlyone.ui.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.activity.SearchActivity;
import com.xunao.onlyone.ui.adapter.RecommendLeftAdapter;
import com.xunao.onlyone.ui.adapter.RecommendRightAdapter;
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
    @BindView(R.id.listview_recommend)
    ListView listviewRecommend;
    @BindView(R.id.pinnedheaderlistview)
    PinnedHeaderListView pinnedHeaderListView;
    private boolean isScroll = true;
    private RecommendLeftAdapter adapter;

    private String[] leftStr = new String[]{"数码产品","数码产品", "数码产品", "数码产品","数码产品","数码产品","数码产品","数码产品","数码产品"};
    private boolean[] flagArray = {true, false, false, false, false, false, false, false, false};
    private String[][] rightStr = new String[][]{{"手机", "手机", "手机"},
            {"手机", "手机","手机"},
            {"手机", "手机","手机"}, {"手机","手机","手机"}, {"手机","手机","手机"},
            {"手机","手机","手机"}, {"手机","手机","手机","手机"},
            {"手机","手机","手机","手机","手机","手机"}, {"手机","手机","手机","手机","手机","手机","手机","手机","手机"}
    };

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initData() {
      final  RecommendRightAdapter rightAdapter = new RecommendRightAdapter(baseActivity, leftStr, rightStr);
        pinnedHeaderListView.setAdapter(rightAdapter);
        adapter = new RecommendLeftAdapter(baseActivity, leftStr, flagArray);
        listviewRecommend.setAdapter(adapter);
        listviewRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                            listviewRecommend.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedHeaderListView.getFirstVisiblePosition() == 0) {
                            listviewRecommend.setSelection(0);
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
                        if (y == listviewRecommend.getLastVisiblePosition()) {
//                            z = z + 3;
                            listviewRecommend.setSelection(z);
                        }
                        if (x == listviewRecommend.getFirstVisiblePosition()) {
//                            z = z - 1;
                            listviewRecommend.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            listviewRecommend.setSelection(ListView.FOCUS_DOWN);
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
