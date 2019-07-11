package tw.jason.homework.activity;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import tw.jason.homework.R;
import tw.jason.homework.adapter.TweetsAdapter;
import tw.jason.homework.base.BaseActivity;
import tw.jason.homework.bean.AdapterCommentsBean;
import tw.jason.homework.bean.AdapterContentBean;
import tw.jason.homework.bean.AdapterImgBean;
import tw.jason.homework.bean.AdapterTimeBean;
import tw.jason.homework.bean.AdapterTypeBean;
import tw.jason.homework.bean.ProfileBean;
import tw.jason.homework.bean.TweetsBean;
import tw.jason.homework.databinding.ActivityMainBinding;
import tw.jason.homework.utils.BaseUtils;
import tw.jason.homework.view.NestScrollDistanceView;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mbinBing;
    private ProfileBean mprofileBean;
    private TweetsAdapter madapter;
    private List<TweetsBean> mlist;
    private String TAG = "MainActivity";
    private int height = 0; // bar height
    private boolean mbarShow = false; //  bar status
    private List<AdapterTypeBean> madapterList;
    private int mshowCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Transparent status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        mbinBing = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mprofileBean = new ProfileBean();
        mbinBing.setPrfilebean(mprofileBean);
        initstatusbarHegiht();
        removeBadData();
        initRecyclerView();
        initScrollLisener();
        initRefresh();

    }

    private void initRefresh() {
        mbinBing.smartlayout.setEnableScrollContentWhenLoaded(false);
        mbinBing.smartlayout.setEnableNestedScroll(true);
        mbinBing.smartlayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadTweetsData(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadTweetsData(false);
            }
        });
    }

    /**
     * When the status bar is halfway up the user logo that need chang the bar of color and icon
     */
    private void initScrollLisener() {
        mbinBing.scroll.setOnNewScrollChangLisener(new NestScrollDistanceView.OnNewScrollChangLisener() {
            @Override
            public void onChang(int l, int t, int oldl, int oldt) {
                if (oldt + height + mbinBing.masterLogo.getMeasuredHeight() - mbinBing.backBt.getMeasuredHeight()
                        - mbinBing.bar.getMeasuredHeight()  > -1){ // This height should be changed
                    if (mbarShow == false){
                        //show
                        mbarShow = true;
                        mbinBing.navigationBar.setBackgroundColor(getResources().getColor(R.color.color_E2E1E1));
                        mbinBing.moments.setText("Momens");
                        mbinBing.backBt.setBackgroundResource(R.mipmap.back_black);
                        mbinBing.sendBt.setBackgroundResource(R.mipmap.camera_black);
                    }
                }else {
                    if (mbarShow == true){
                        //reset
                        mbarShow = false;
                        mbinBing.navigationBar.setBackgroundColor(Color.TRANSPARENT);
                        mbinBing.moments.setText("");
                        mbinBing.backBt.setBackgroundResource(R.mipmap.back_white);
                        mbinBing.sendBt.setBackgroundResource(R.mipmap.camera_white);
                    }
                }
            }
        });
    }

    /**
     * inint about recyclerview
     */
    private void initRecyclerView() {
        madapterList = new ArrayList<>();
        madapter = new TweetsAdapter(this,madapterList);
        mbinBing.recyclerView.setAdapter(madapter);
        mbinBing.recyclerView.setLayoutManager(new GridLayoutManager(this,1){
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 10;
            }
        });
        mbinBing.recyclerView.setHasFixedSize(true);
        mbinBing.recyclerView.setItemViewCacheSize(15);
        mbinBing.recyclerView.setDrawingCacheEnabled(true);
        mbinBing.recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        loadTweetsData(false);
    }

    /**
     * Break the data down into holders
     */
    private void loadTweetsData(boolean more) {
        if (!more) {
            madapterList.clear();
            mshowCount = 0;
        }
        int start = mshowCount;
        int size = mlist.size() - mshowCount >= 5 ? 5 : mlist.size() - mshowCount;
        mshowCount += size ;
        for (int i = start; i < mshowCount ; i++) {
            // user + content  0
            madapterList.add(new AdapterContentBean(mlist.get(i).getContent(),mlist.get(i).getSender()));
//            //img one 1 or img many 2
            if (mlist.get(i).getImages().size()  == 1) {
                madapterList.add(new AdapterImgBean(mlist.get(i).getImages()));
            }else if (mlist.get(i).getImages().size() > 1){
                madapterList.add(new AdapterImgBean(mlist.get(i).getImages(),2));
            }
            // time  type = 3
            madapterList.add(new AdapterTimeBean());
            //comments   4
            if (mlist.get(i).getComments().size() > 0) {
                madapterList.add(new AdapterCommentsBean(mlist.get(i).getComments(), mlist.get(i).getSender()));
            }
            // line  type = 5
            madapterList.add(new AdapterTypeBean(5));
        }


        if (size != 0) {
            if (more) {
                mbinBing.smartlayout.finishLoadMore(0);
            } else {
                mbinBing.smartlayout.finishRefresh();
            }
            if (madapter != null) {
                if (start == 0){
                    madapter.notifyDataSetChanged();
                }else {
                    madapter.notifyItemChanged(start, mshowCount);
                }
            }
        }else {
            mbinBing.smartlayout.finishLoadMoreWithNoMoreData();
        }
    }

    /**
     * remove bad data
     */
    private void removeBadData() {
        mlist = new ArrayList<>();
        mlist.addAll(JSON.parseArray(BaseUtils.getJson("tweets.json",this), TweetsBean.class));
        //load correct data
        for (int i = 0; i < mlist.size(); i++) {
            if (!"".equals(mlist.get(i).getError()) | !"".equals(mlist.get(i).get_$UnknownError23())){
                mlist.remove(i);
                i--;
            }
            if (mlist.size() == i)
                break;
        }
    }

    /**
     * bar hegiht
     */
    private void initstatusbarHegiht() {
        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
    }

}
