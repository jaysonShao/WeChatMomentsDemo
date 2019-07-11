package tw.jason.homework.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import tw.jason.homework.R;

public class GlideUtils {
    private static RoundedCorners mroundedCorners = new RoundedCorners(10);


    public static void toNormal(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).apply(RequestOptions.errorOf(R.mipmap.error).diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)).into(imageView);
    }

    public static void toAround(Context context,String url,ImageView imageView){
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(mroundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false)
                .error(R.mipmap.error)).into(imageView);
    }
}
