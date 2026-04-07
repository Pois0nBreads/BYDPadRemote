package net.pois0nbread.bydpadremote.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import net.pois0nbread.bydpadremote.BuildConfig;
import net.pois0nbread.bydpadremote.R;
import net.pois0nbread.bydpadremote.util.PermissionChecker;


public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkPermission();
        findViewById(R.id.activity_splash_check_bt).setOnClickListener(v -> checkPermission());
    }

    private void checkPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "浮窗权限未授予", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        if (!PermissionChecker.isSystemPermissionGranted(getBaseContext())) {
            Toast.makeText(this, "系统权限未授予", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, PermissionChecker.SYSTEM_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            return;
        }
        if (PermissionChecker.isBydAutoPermissionGranted(getBaseContext()) || BuildConfig.DEBUG) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "车辆权限未授予", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, PermissionChecker.BYD_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        }
    }


}
