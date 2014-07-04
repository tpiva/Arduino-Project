package com.brothers.spendcontrol;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Intent intentList = new Intent(context, SpendsActivity.class);
	    PendingIntent pending = PendingIntent.getActivity(context, 0, intentList, 0);
		
		NotificationManager mNM;
		mNM = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		 
		Notification notification = new Notification(R.drawable.ic_launcher,"Test Alarm", System.currentTimeMillis());
		notification.setLatestEventInfo(context, "teste","This is a Test Alarm", pending);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
	    notification.defaults |= Notification.DEFAULT_SOUND; 
		mNM.notify(1, notification);
	}
}