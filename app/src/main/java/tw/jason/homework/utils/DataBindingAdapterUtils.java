package tw.jason.homework.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapterUtils {

    @BindingAdapter("normalImg")
    public static void setNormalImg(ImageView img,String url){
        GlideUtils.toNormal(img.getContext(),url,img);
    }

    @BindingAdapter("aroundImg")
    public static void setAroundImg(ImageView img,String url){
        GlideUtils.toAround(img.getContext(),url,img);
    }
}
