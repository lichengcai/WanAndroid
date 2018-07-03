package wanandroid.cc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

public class AppStartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int hasReadContactsPermission = checkSelfPermission(
                    android.Manifest.permission.READ_PHONE_STATE);
            if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                //这里就会弹出对话框
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 10000);
            } else {
                gotoMainTab();
            }
        } else {
            gotoMainTab();
        }
    }

    private void gotoMainTab() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AppStartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
}
