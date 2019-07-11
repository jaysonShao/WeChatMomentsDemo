package tw.jason.homework.activity;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import tw.jason.homework.R;
import tw.jason.homework.adapter.TweetsAdapter;
import tw.jason.homework.base.BaseActivity;
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
        //bar hegiht
        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }

        mlist = new ArrayList<>();
        mlist.addAll(JSON.parseArray(BaseUtils.getJson("tweets.json",this),TweetsBean.class));
        //correct data 22
        for (int i = 0; i < mlist.size(); i++) {
            if (!"".equals(mlist.get(i).getError()) | !"".equals(mlist.get(i).get_$UnknownError23())){
                mlist.remove(i);
            }
        }

        madapter = new TweetsAdapter(this,mlist);
        mbinBing.recyclerView.setAdapter(madapter);
        mbinBing.recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        mbinBing.scroll.setOnNewScrollChangLisener(new NestScrollDistanceView.OnNewScrollChangLisener() {
            @Override
            public void onChang(int l, int t, int oldl, int oldt) {
                //When the status bar is halfway up the logo that need chang the bar of color and icon
                if (oldt + height + mbinBing.masterLogo.getMeasuredHeight() - mbinBing.backBt.getMeasuredHeight() - mbinBing.bar.getMeasuredHeight()  > -1){
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

}
