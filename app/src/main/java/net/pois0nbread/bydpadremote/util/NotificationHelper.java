package net.pois0nbread.bydpadremote.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.util.Log;


import net.pois0nbread.bydpadremote.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationHelper {

    private static String TAG = NotificationHelper.class.getName();

    public static void showNotification(Service context, String Title, String msg) {
        String channelId = context.getPackageName();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        //反射
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = newNotificationBuilder(context, channelId);
        } else {
            builder = new Notification.Builder(context);
        }
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(Title).setSubText(msg).build();
        //反射
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel(manager, channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
        }
        context.startForeground(1, notification);
    }

    private static void createNotificationChannel(NotificationManager manager, String id, CharSequence name, int importance) {
        try {
            Class<?> aClass = Class.forName("android.app.NotificationChannel");
            Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, CharSequence.class, int.class);
            Object instance = constructor.newInstance(id, name, importance);

            Class<?> clz = Class.forName("android.app.NotificationManager");
//            Method method = clz.getDeclaredMethod("createNotificationChannel", NotificationChannel.class);
            Method method = clz.getDeclaredMethod("createNotificationChannel", aClass);
            method.setAccessible(true);
            method.invoke(manager, instance);
//            System.out.println();
            Log.i(TAG, "createNotificationChannel success");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            Log.e(TAG, "createNotificationChannel failed", e);
        }
    }

    private static Notification.Builder newNotificationBuilder(Context context, String channelId) {
        try {
            Class<?> clz = Class.forName("android.app.Notification$Builder");
            Constructor<?> constructor = clz.getDeclaredConstructor(Context.class, String.class);
            constructor.setAccessible(true);
            Object obj = constructor.newInstance(context, channelId);
            Log.i(TAG, "newNotificationBuilder success");
            return (Notification.Builder) obj;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            Log.e(TAG, "newNotificationBuilder failed", e);
        }
        return new Notification.Builder(context);
    }
}