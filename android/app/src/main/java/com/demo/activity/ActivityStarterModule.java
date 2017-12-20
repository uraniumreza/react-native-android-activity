package com.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import android.app.AlarmManager;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.Context;

import android.widget.Toast;
import java.util.Calendar;

/**
 * Expose Java to JavaScript.
 */
class ActivityStarterModule extends ReactContextBaseJavaModule {

    private static DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = null;

    ActivityStarterModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public void initialize() {
        super.initialize();
        eventEmitter = getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from JavaScript.
     */
    @Override
    public String getName() {
        return "ActivityStarter";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("MyEventName", "MyEventValue");
        return constants;
    }

    @ReactMethod
    void navigateToExample(int x, int y) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            //service();
            notification(x,y) ;
            Intent intent = new Intent(activity, ExampleActivity.class);
            activity.startActivity(intent);
        }
    }

    @ReactMethod
    void dialNumber(@Nonnull String number) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            activity.startActivity(intent);
        }
    }

    @ReactMethod
    void getActivityName(@Nonnull Callback callback) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            callback.invoke(activity.getClass().getSimpleName());
        }
    }

    @ReactMethod
    void getActivityNameAsPromise(@Nonnull Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            promise.resolve(activity.getClass().getSimpleName());
        }
    }

    @ReactMethod
    void callJavaScript() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            MainApplication application = (MainApplication) activity.getApplication();
            ReactNativeHost reactNativeHost = application.getReactNativeHost();
            ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();
            ReactContext reactContext = reactInstanceManager.getCurrentReactContext();

            if (reactContext != null) {
                CatalystInstance catalystInstance = reactContext.getCatalystInstance();
                WritableNativeArray params = new WritableNativeArray();
                params.pushString("Hello, JavaScript!");
                catalystInstance.callFunction("JavaScriptVisibleToJava", "alert", params);
            }
        }
    }

    /**
     * To pass an object instead of a simple string, create a {@link WritableNativeMap} and populate it.
     */
    static void triggerAlert(@Nonnull String message) {
        eventEmitter.emit("MyEventValue", message);
    }
      public void service() {
              Intent intent = new Intent( getReactApplicationContext(), MyAlarmReceiver.class);
              final PendingIntent pIntent = PendingIntent.getBroadcast(getReactApplicationContext(), MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
              long firstMillis = System.currentTimeMillis(); //first run of alarm is immediately //
              int intervalMillis = 1 * 3 * 1000; //3 Second
              AlarmManager alarm = (AlarmManager) getReactApplicationContext().getSystemService(Context.ALARM_SERVICE);
              alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
          }
          private void notification(int h ,int m ){
                  //Locale alocal = new Locale

                  Calendar calendar = Calendar.getInstance();
                  // we can set time by open date and time picker dialog
                  calendar.set(Calendar.HOUR_OF_DAY, h);
                  calendar.set(Calendar.MINUTE,m);
                  calendar.set(Calendar.SECOND, 0);

                  //Toast.makeText(this,  showTime(h,m), Toast.LENGTH_SHORT).show();
                  Intent intent1 = new Intent( getReactApplicationContext(), MyAlarmReceiver.class);
                  final PendingIntent pIntent = PendingIntent.getBroadcast(getReactApplicationContext(), MyAlarmReceiver.REQUEST_CODE,  intent1 , PendingIntent.FLAG_UPDATE_CURRENT);

                  AlarmManager alarm = (AlarmManager) getReactApplicationContext().getSystemService(Context.ALARM_SERVICE);
                                  alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pIntent);



              }
}
