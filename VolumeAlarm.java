package com.example.raghavanpc.volumealarm;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import android.media.AudioManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class VolumeAlarm extends AppCompatActivity {

    public static AlarmManager alarmMgr;
    protected static PendingIntent alarmIntent;
    static Context context;
    Button set;
    Button ok;
    static Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_alarm);
        context=VolumeAlarm.this;
        final TimePicker tm=(TimePicker)findViewById(R.id.timePicker);
        alarmMgr=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(VolumeAlarm.this,MyReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY,10);
//        calendar.set(Calendar.MINUTE,00);
//        AudioManager am = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//                calendar.set(Calendar.AM_PM,tm.get);
    }
     
//    public class MyReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    }
}
class timePickerDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
 VolumeAlarm v;
static Context con;
    


    public Dialog onCreateDialog(Bundle bundle)
    {
        int hour=v.calendar.get(Calendar.HOUR_OF_DAY);
        int minute=v.calendar.get(Calendar.MINUTE);
        con=getActivity();
        return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        v.alarmMgr.set(AlarmManager.RTC_WAKEUP, v.calendar.getTimeInMillis(), v.alarmIntent);
    }
    static TimePickerDialog.OnTimeSetListener s;
    public static void showTimePickerDialog(View v) {
        TimePickerDialog newFragment = new TimePickerDialog(con,s,Calendar.HOUR,Calendar.MINUTE,true);
      //  newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}

