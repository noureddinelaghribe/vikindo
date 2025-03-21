//package Controlar;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//
//import java.util.List;
//
//public class ImageMerger {
//
//    public static Bitmap drawableToBitmap(Context context, int drawableId) {
//        return BitmapFactory.decodeResource(context.getResources(), drawableId);
//    }
//
//
//    public static Bitmap mergeBitmapsSideBySide(List<Bitmap> bitmapList) {
//        if (bitmapList == null || bitmapList.isEmpty()) {
//            return null; // or throw an IllegalArgumentException
//        }
//
//        // Calculate total width and maximum height
//        int totalWidth = 0;
//        int maxHeight = 0;
//        for (Bitmap bmp : bitmapList) {
//            totalWidth += bmp.getWidth();
//            if (bmp.getHeight() > maxHeight) {
//                maxHeight = bmp.getHeight();
//            }
//        }
//
//        // Create the output bitmap with calculated dimensions
//        Bitmap combined = Bitmap.createBitmap(totalWidth, maxHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(combined);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//
//        // Draw each bitmap side by side
//        int currentX = 0;
//        for (Bitmap bmp : bitmapList) {
//            canvas.drawBitmap(bmp, currentX, 0, paint);
//            currentX += bmp.getWidth();
//        }
//
//        return combined;
//    }
//
//
//}









//
//
//package Controlar;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.util.TypedValue;
//
//import androidx.annotation.NonNull;
//
//import com.bumptech.glide.load.Key;
//import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
//import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
//
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ImageMerger extends BitmapTransformation {
//
//    private static final int IMAGES_PER_ROW = 10; // عدد الصور لكل صف
//    private static final int TARGET_WIDTH_DP = 35; // العرض المطلوب بالـ dp
//    private static final int TARGET_HEIGHT_DP = 50; // الارتفاع المطلوب بالـ dp
//
//    private final List<Integer> drawableIds;
//    private final Context context;
//
//    public ImageMerger(Context context, List<Integer> drawableIds) {
//        this.context = context.getApplicationContext();
//        this.drawableIds = drawableIds;
//    }
//
//    @Override
//    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform,
//                               int outWidth, int outHeight) {
//        List<Bitmap> bitmapList = new ArrayList<>();
//        int targetWidthPx = dpToPx(TARGET_WIDTH_DP);
//        int targetHeightPx = dpToPx(TARGET_HEIGHT_DP);
//
//        for (int id : drawableIds) {
//            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), id);
//            Bitmap resizedBmp = Bitmap.createScaledBitmap(bmp, targetWidthPx, targetHeightPx, true);
//            bitmapList.add(resizedBmp);
//        }
//
//        int rowCount = (int) Math.ceil((double) bitmapList.size() / IMAGES_PER_ROW);
//        int totalWidth = IMAGES_PER_ROW * targetWidthPx;
//        int totalHeight = rowCount * targetHeightPx;
//
//        Bitmap merged = pool.get(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
//        if (merged == null) {
//            merged = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
//        }
//
//        Canvas canvas = new Canvas(merged);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//
//        int x = 0, y = 0;
//        for (int i = 0; i < bitmapList.size(); i++) {
//            canvas.drawBitmap(bitmapList.get(i), x, y, paint);
//            x += targetWidthPx;
//            if ((i + 1) % IMAGES_PER_ROW == 0) {
//                x = 0;
//                y += targetHeightPx;
//            }
//        }
//        return merged;
//    }
//
//    private int dpToPx(int dp) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
//                context.getResources().getDisplayMetrics());
//    }
//
//    @Override
//    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//        messageDigest.update("MergeTransformation".getBytes(Key.CHARSET));
//        for (int id : drawableIds) {
//            messageDigest.update(String.valueOf(id).getBytes(Key.CHARSET));
//        }
//    }
//}













package com.example.aslsignlanguage.Controlar;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.aslsignlanguage.R;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class ImageMerger extends BitmapTransformation {

    private static final int IMAGES_PER_ROW = 10; // عدد الصور لكل صف
    private static final int TARGET_WIDTH_DP = 35; // العرض المطلوب بالـ dp
    private static final int TARGET_HEIGHT_DP = 50; // الارتفاع المطلوب بالـ dp

    private final List<Integer> drawableIds;
    private final Context context;

    public ImageMerger(Context context, List<Integer> drawableIds) {
        this.context = context.getApplicationContext();
        this.drawableIds = drawableIds;
    }

    public static Bitmap resizeImage(Context context,int drawableId){

        int targetWidth = 100; // العرض المطلوب
        int targetHeight = 150; // الارتفاع المطلوب
        // تحميل الصورة الأصلية مع إعادة القياس لتجنب استهلاك الذاكرة
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);

        // إعادة تغيير الحجم مع الحفاظ على النسبة (Aspect Ratio)
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                originalBitmap,
                targetWidth,
                targetHeight,
                false // إذا كنت تريد تنعيم الصورة (Filtering)
        );

        return resizedBitmap ;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform,
                               int outWidth, int outHeight) {
        List<Bitmap> bitmapList = new ArrayList<>();
        int targetWidthPx = dpToPx(TARGET_WIDTH_DP);
        int targetHeightPx = dpToPx(TARGET_HEIGHT_DP);

        for (int id : drawableIds) {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), id);
            Bitmap resizedBmp = Bitmap.createScaledBitmap(bmp, targetWidthPx, targetHeightPx, true);
            bitmapList.add(resizedBmp);
        }

        int rowCount = (int) Math.ceil((double) bitmapList.size() / IMAGES_PER_ROW);
        int totalWidth = IMAGES_PER_ROW * targetWidthPx;
        int totalHeight = rowCount * targetHeightPx;

        Bitmap merged = pool.get(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        if (merged == null) {
            merged = Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(merged);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        int x = 0, y = 0;
        for (int i = 0; i < bitmapList.size(); i++) {
            canvas.drawBitmap(bitmapList.get(i), x, y, paint);
            x += targetWidthPx;
            if ((i + 1) % IMAGES_PER_ROW == 0) {
                x = 0;
                y += targetHeightPx;
            }
        }
        return merged;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update("MergeTransformation".getBytes(Key.CHARSET));
        for (int id : drawableIds) {
            messageDigest.update(String.valueOf(id).getBytes(Key.CHARSET));
        }
    }
}


