package com.lxy.pink.utils;

import android.content.ContentUris;
import android.graphics.Bitmap;
import android.net.Uri;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;

/**
 * Created by homelajiang on 2016/9/7 0007.
 */
public class MediaHelper {
    public static Uri getCoverUri(long albumId, long songId){
        Uri uri ;
        if(albumId<0){
            uri = Uri.parse("content://media/external/audio/media/"
                    + songId + "/albumart");
        }else {
            uri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),albumId);
        }
        return uri;
    }

    public static Bitmap getBitmapFromFresco(Uri uri) {

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequest imageRequest = ImageRequest.fromUri(uri);
        DataSource<CloseableReference<CloseableImage>> dataSource =
                imagePipeline.fetchImageFromBitmapCache(imageRequest, CallerThreadExecutor.getInstance());
        try {
            CloseableReference<CloseableImage> imageReference = dataSource.getResult();
            if (imageReference != null) {
                try {
                    CloseableBitmap image = (CloseableBitmap) imageReference.get();
                    // do something with the image
                    Bitmap loadedImage = image.getUnderlyingBitmap();
                    if (loadedImage != null) {
                        return loadedImage;
                    } else {
                        return null;
                    }
                } finally {
                    CloseableReference.closeSafely(imageReference);
                }
            }
        } finally {
            dataSource.close();
        }
        return null;
    }
}
