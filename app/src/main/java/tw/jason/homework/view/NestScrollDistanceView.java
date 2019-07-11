package tw.jason.homework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class NestScrollDistanceView extends NestedScrollView {
    private OnNewScrollChangLisener mlisener;


    public NestScrollDistanceView(@NonNull Context context) {
        super(context);
    }

    public NestScrollDistanceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestScrollDistanceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnNewScrollChangLisener(OnNewScrollChangLisener mlisener) {
        this.mlisener = mlisener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mlisener != null){
            mlisener.onChang(l,t,oldl,oldt);
        }
    }

    public interface OnNewScrollChangLisener{
        void onChang(int l,int t,int oldl,int oldt);
    }
}
