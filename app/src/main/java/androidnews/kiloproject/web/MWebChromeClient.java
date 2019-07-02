package androidnews.kiloproject.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.blankj.utilcode.util.ConvertUtils;

import androidnews.kiloproject.activity.AboutActivity;

/**
 * 自定义实现WebChromeClient对象
 */
public class MWebChromeClient extends WebChromeClient {
    AboutActivity aboutActivity = null;

    public MWebChromeClient(Activity activity) {
        if (activity instanceof AboutActivity) {
            aboutActivity = (AboutActivity) activity;
        }
    }

    /**
     * 当webview加载进度变化时回调该方法
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (aboutActivity != null) {
            if (newProgress == 100) {
                changeColor(view);
                aboutActivity.progressBar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
                aboutActivity.progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                aboutActivity.progressBar.setProgress(newProgress);//设置进度值
            }
        }
    }

    /**
     * 当加载到H5页面title的时候回调该方法
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    /**
     * 当接收到icon的时候回调该方法
     */
    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    /**
     * 当H5页面调用js的Alert方法的时候回调该方法
     */
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    /**
     * 当H5页面调用js的Confirm方法的时候回调该方法
     */
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    /**
     * 当H5页面调用js的Prompt方法的时候回调该方法
     */
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    protected void changeColor(WebView web) {
        Bitmap bitmap = ConvertUtils.view2Bitmap(web);
        int pixel = bitmap.getPixel(3, 3);
        int redValue = Color.red(pixel);
        int greenValue = Color.green(pixel);
        int blueValue = Color.blue(pixel);
        int alphaValue = Color.alpha(pixel);
        boolean isWhite = false;
        if (redValue >= 223 && greenValue >= 223 && blueValue >= 223)
            isWhite = true;
        int color = Color.argb(alphaValue, redValue, greenValue, blueValue);
        String colorStr = "0x" + Integer.toHexString(color);
    }
}