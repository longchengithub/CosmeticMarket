package com.market.view.activity.base;//package com.market.view.activity.base;
//
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.design.widget.TabLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.market.R;
//import com.market.view.activity.SearchActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by Administrator on 2017/1/8 0008.
// */
//
//public class SearchResult extends AppCompatActivity {
//    @BindView(R.id.search_iv)
//    ImageView searchIv;
//    @BindView(R.id.search_et)
//    EditText searchEt;
//    @BindView(R.id.search_cancel)
//    TextView searchCancel;
//    @BindView(R.id.tabLayout)
//    TabLayout tabLayout;
//    @BindView(R.id.result_rv)
//    RecyclerView resultRv;
//    @BindView(R.id.activity_search)
//    LinearLayout activitySearch;
//    private List<String> TabItems;
//    private List<Integer> TabDrawbles;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.activity_search);
//        ButterKnife.bind(this);
//        initTab();
//    }
//
//    public void initTab() {
//        TabItems = new ArrayList<>();
//        TabItems.add("  销  量");
//        TabItems.add("  价  格");
//        TabItems.add(" 好评度");
//        TabItems.add("上架时间");
//        TabDrawbles = new ArrayList<>();
//        TabDrawbles.add(R.drawable.nav_button_press_nor);
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setCustomView(getTabView(i));
//            if (i == 0)
//                ((TextView) tab.getCustomView()).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.nav_button_press_down, 0);
//        }
//    }
//
//    private View getTabView(int position) {
////        View view = LayoutInflater.from(SearchResult.this).inflate(R.layout.tab_item, null);
//        View.inflate(SearchActivity)
//        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
//        tv.setText(TabItems.get(position));
//        return tv;
//    }
//}
