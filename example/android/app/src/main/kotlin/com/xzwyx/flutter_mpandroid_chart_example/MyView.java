package com.xzwyx.flutter_mpandroid_chart_example;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

public class MyView implements PlatformView , MethodChannel.MethodCallHandler {

    public MyView(Context context, BinaryMessenger messenger, int id, Map<String, Object> params) {
        TextView myNativeView = new TextView(context);
        myNativeView.setText("我是来自Android的原生TextView");
        this.myNativeView = myNativeView;
        if (params.containsKey("myContent")) {
            String myContent = (String) params.get("myContent");
            myNativeView.setText(myContent);
        }

        MethodChannel methodChannel = new MethodChannel(messenger, "plugins.nightfarmer.top/myview_" + id);
        methodChannel.setMethodCallHandler(this);
    }
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        // 在接口的回调方法中可以接收到来自Flutter的调用
        if ("setText".equals(methodCall.method)) {
            String text = (String) methodCall.arguments;
            myNativeView.setText(text);
            result.success(null);
        }
    }
    private final TextView myNativeView;
    @Override
    public View getView() {
        return myNativeView;
    }

    @Override
    public void dispose() {

    }
}
