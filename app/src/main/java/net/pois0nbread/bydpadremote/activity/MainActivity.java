package net.pois0nbread.bydpadremote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.os.Build;
import android.os.Bundle;

import net.pois0nbread.bydpadremote.R;
import net.pois0nbread.bydpadremote.service.FloatingService;
import net.pois0nbread.bydpadremote.util.AutoBootHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initBidModules();
        Intent intent = new Intent(this, FloatingService.class);

        if (Build.VERSION.SDK_INT >= 26) {
            AutoBootHelper.startForegroundService(this, intent);
        } else {
            startService(intent);
        }

    }

    private void initBidModules() {

    }
}
