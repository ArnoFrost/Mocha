package tech.arno.mocha.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/7/8
 *     desc  :
 * </pre>
 */
public class BindingImageView extends AppCompatImageView {
    public BindingImageView(Context context) {
        super(context);
    }

    public BindingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageUrl(String imageUrl) {
        setImageUrl(this, imageUrl, false);
    }

    @BindingAdapter(value = {"image_url", "isCircle"})
    public static void setImageUrl(BindingImageView view, String imageUrl, boolean isCircle) {
        RequestBuilder<Drawable> builder = Glide.with(view).load(imageUrl);

        if (isCircle) {
            builder.transform(new CircleCrop());
        }

        //防止图片过大进行尺寸优化
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            builder.override(layoutParams.width, layoutParams.height);
        }

        builder.into(view);
    }
}