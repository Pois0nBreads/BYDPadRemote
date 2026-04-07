package net.pois0nbread.bydpadremote.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

import net.pois0nbread.bydpadremote.R;
import net.pois0nbread.bydpadremote.util.NotificationHelper;

public class FloatingService extends Service {

    private FloatingWindowHelper floatingWindowHelper;
    private View rootView;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        floatingWindowHelper = new FloatingWindowHelper(this);
        rootView = LayoutInflater.from(this).inflate(R.layout.float_ac, null, false);
        floatingWindowHelper.addView(rootView, 0, 0, true, false);
        NotificationHelper.showNotification(this, "比亚迪遥控", "空调悬浮窗");


        View panel = rootView.findViewById(R.id.float_ac_panel);
        View icon = rootView.findViewById(R.id.float_ac_icon);
        rootView.findViewById(R.id.back_btn).setOnClickListener(v -> {
            panel.setVisibility(View.GONE);
            icon.setVisibility(View.VISIBLE);
        });
        rootView.setOnClickListener(v -> {
            panel.setVisibility(View.VISIBLE);
            icon.setVisibility(View.GONE);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        floatingWindowHelper.removeView(rootView);
        floatingWindowHelper.clear();
        floatingWindowHelper.destroy();
    }

}
