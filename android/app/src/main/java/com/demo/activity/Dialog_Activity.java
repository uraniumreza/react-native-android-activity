package com.demo.activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.facebook.react.ReactActivity;

import android.widget.Button;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import java.util.Calendar;

public class Dialog_Activity extends ReactActivity {
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        this.setFinishOnTouchOutside(false);
        getWindow().addFlags(
                //WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG
                 //   WindowManager.LayoutParams.WRAP_CONTENT |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, mobileArray);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        Button reschedule = (Button) findViewById(R.id.reschedule);

        reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }

    protected void showTimePicker()
    {
        /**Time Picker*/
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Dialog_Activity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                // eReminderTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        //  mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


}
