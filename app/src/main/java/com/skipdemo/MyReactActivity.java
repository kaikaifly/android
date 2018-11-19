package com.skipdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

/**
 * author : dukai
 * date  : 2018/11/7
 * describe:
 */
public class MyReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle") //可远程地址
                .setJSMainModulePath("index")
                .addPackage(new MainReactPackage())//如果为true，则会启用诸如JS重新加载和调试之类的开发人员选项.反之打包
                .setUseDeveloperSupport(true)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        //'AwesomeProject'==>index.android.js 页面内注册名称，可根据自己随意调整
        Intent intent = this.getIntent();
        //从前一个MainActivity获取参数
        String componentName = intent.getStringExtra("componentName");
        Bundle bundle=new Bundle();
        bundle.putString("componentName",componentName);
        //将参数传递到RN端
        mReactRootView.startReactApplication(mReactInstanceManager, "skipdemo", bundle);

        setContentView(mReactRootView);
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}
