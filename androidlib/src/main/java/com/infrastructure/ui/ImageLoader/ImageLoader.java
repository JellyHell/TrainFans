package com.infrastructure.ui.ImageLoader;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by Administrator on 2016/8/15.
 */
public class ImageLoader {

    /**
     *直接显示图片
     * */
    public static void simpleLoad(String imgURL, SimpleDraweeView view) {
        view.setImageURI(Uri.parse(imgURL));
    }

    /**
     * 显示渐进式图片
     * */
    public static void  progressiveLoad(String imgURL,SimpleDraweeView view)
    {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgURL))
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(view.getController())
                .build();
        view.setController(controller);
    }

    /**
     * 显示动图
     * */
    public static void showAnimatImg(String imgURL,SimpleDraweeView view)
    {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(imgURL))
                .setAutoPlayAnimations(true)
                .build();
        view.setController(controller);
    }
}
