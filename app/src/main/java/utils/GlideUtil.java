package utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiyou3g.thefriendsofthecity.R;


public class GlideUtil {

    public static void loadImag(Context context, ImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .error(R.mipmap.defualt_image)
                .into(imageView);
    }

    public static void loadMainImag(Context context, ImageView imageView,String url){
        Glide.with(context)
                .load(url)
                .error(R.mipmap.book)
                .into(imageView);
    }
}
