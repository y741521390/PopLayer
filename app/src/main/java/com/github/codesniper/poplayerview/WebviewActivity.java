package com.github.codesniper.poplayerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.github.codesniper.poplayer.PopLayerView;
import com.github.codesniper.poplayer.config.LayerConfig;
import com.github.codesniper.poplayer.custom.PopWebView;
import com.github.codesniper.poplayer.pop.PopManager;
import com.github.codesniper.poplayer.pop.Popi;
import com.github.codesniper.poplayer.strategy.concreate.DialogLayerStrategyImpl;
import com.github.codesniper.poplayer.strategy.concreate.WebViewLayerStrategyImpl;

import static com.github.codesniper.poplayer.config.LayerConfig.COUNTDOWN_CANCEL;
import static com.github.codesniper.poplayer.config.LayerConfig.TRIGGER_CANCEL;

/**
 * 还有bug
 */
public class WebviewActivity extends AppCompatActivity {

    //我需要加载自己的url 满足
    //我可以任选jsbridge方案还是第三方方案 还是原生 不满足
    //我可以自定义注入的对象名 满足
    //我可以自定义配置webview 满足
    //可不可以自定义X5webview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_subject);
    }


    /**
     * 普通弹窗无需纳入管理
     *
     * @param view
     */
    public void showNormalPop(View view) {
        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.dialog5);
        mLayerView.onShow();
    }



    /**
     * 显示延迟弹窗 倒数5s
     * @param view
     */
    public void showDealyPop(View view) {
        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.dialog3);

        Popi delayPop = new Popi.Builder()
                .setmPopId(1)
                .setmPriority(2)
                .setmCancelType(COUNTDOWN_CANCEL)
                .setMaxShowTimeLength(5)
                .setLayerView(mLayerView)
                .build();

        PopManager.getInstance(this).pushToQueue(delayPop);
        PopManager.getInstance(this).showNextPopi();
    }



    /**
     * 限定活动时间
     * @param view
     */
    public void showEventPop(View view) {
        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.dialog4);

        Popi eventPop = new Popi.Builder()
                .setmPopId(2)
                .setmPriority(4)
                .setmCancelType(COUNTDOWN_CANCEL)
                .setMaxShowTimeLength(5)
                .setmBeginDate(154885802L)//开始时间 2019-01-30 22:20:28
                .setmEndDate(1559666666)//结束时间 2019-01-31 22:20:28
                .setLayerView(mLayerView)
                .build();

        PopManager.getInstance(this).pushToQueue(eventPop);
        PopManager.getInstance(this).showNextPopi();
    }



    /**
     * 限定次数5次
     * @param view
     */
    public void showTimePop(View view) {

        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.dialog6);

        Popi timePop = new Popi.Builder()
                .setmPopId(3)
                .setmPriority(1)
                .setmCancelType(TRIGGER_CANCEL)
                .setMaxShowTimeLength(5)
                .setMaxShowCount(5)
                .setLayerView(mLayerView)
                .build();

        PopManager.getInstance(this).pushToQueue(timePop);
        PopManager.getInstance(this).showNextPopi();
    }


    public void showPriorityPop(View view){


        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.dialog6);

        Popi eventPop = new Popi.Builder()
                .setmPopId(2)
                .setmPriority(4)
                .setmCancelType(COUNTDOWN_CANCEL)
                .setMaxShowTimeLength(5)
                .setLayerView(mLayerView)
                .build();



        PopLayerView mLayerView1 = new PopLayerView(this,LayerConfig.dialog5);

        Popi timePop = new Popi.Builder()
                .setmPopId(3)
                .setmPriority(1)
                .setmCancelType(TRIGGER_CANCEL)
                .setMaxShowTimeLength(5)
                .setMaxShowCount(5)
                .setLayerView(mLayerView1)
                .build();





        PopManager.getInstance(this).pushToQueue(timePop);
        PopManager.getInstance(this).pushToQueue(eventPop);
        PopManager.getInstance(this).showNextPopi();
    }


    /**
     * 显示红包雨弹窗
     * @param view
     */
    public void showRpRain(View view) {
        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.redPocketScheme);
        mLayerView.onShow();
    }

    private PopWebView webView;

    /**
     * 显示JS
     * @param view
     */
    public void showJS(View view) {
        PopLayerView mLayerView = new PopLayerView(this,LayerConfig.jsTest);
        webView= (PopWebView) mLayerView.getiPop();
        mLayerView.onShow();
    }

//
//    private void initWebview(WebView myWebView) {
//        //可以在这里添加自己业务的原生js交互方案
//        myWebView.addJavascriptInterface(new TestJs(), "test");
//        //可以拿到webviewsetting进行设置
//        myWebView.getSettings().setAppCacheEnabled(true);
//        //Poplayer默认设置了webclient 可以根据
//        myWebView.setWebChromeClient(new UserChromeClient());
//        myWebView.setWebViewClient(new UserWebviewClient());
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(webView!=null){
            webView.destroy();
        }
    }
}
