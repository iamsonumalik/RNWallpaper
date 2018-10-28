
package sonu.malik.wallpaper;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.io.IOException;

public class RNWallpaperModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private Callback mCallback = null;

    public RNWallpaperModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNWallpaper";
    }

    private void createResponse(String status, String msg, String url) {
        if (mCallback != null) {
            WritableMap map = Arguments.createMap();
            map.putString("status", status);
            map.putString("message", msg);
            map.putString("ImageUrl", url);
            mCallback.invoke(map);
            mCallback = null;
        }
    }

    @ReactMethod
    public void setWallpaper(final String url, Callback callback) {
        if (mCallback != null) {
            createResponse("error", "Current Context is not available", url);
            return;
        }
        mCallback = callback;
        Activity mActivity = getCurrentActivity();
        if (mActivity == null) {
            createResponse("error", "Current Activity is not available", url);
        } else {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(reactContext)
                            .load(url)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .priority(Priority.HIGH)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    try {
                                        WallpaperManager.getInstance(reactContext).setBitmap(resource);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            WallpaperManager.getInstance(reactContext).setBitmap(resource, null,true,WallpaperManager.FLAG_LOCK);
                                        }
                                        createResponse("success", "success", url);
                                    } catch (IOException e) {
                                        createResponse("error", e.getMessage(), url);
                                    }
                                }

                                @Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    super.onLoadFailed(e, errorDrawable);
                                    createResponse("error", e.getMessage(), url);
                                }
                            });
                }
            });
        }

    }

    @ReactMethod
    public void openImage(final String localFileURL){
        Uri uri = Uri.parse(localFileURL);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String mime = "*/*";
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        if (mimeTypeMap.hasExtension(
                MimeTypeMap.getFileExtensionFromUrl(uri.toString())))
            mime = mimeTypeMap.getMimeTypeFromExtension(
                    MimeTypeMap.getFileExtensionFromUrl(uri.toString()));
        intent.setDataAndType(uri,mime);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactContext.startActivity(intent);
    }
}