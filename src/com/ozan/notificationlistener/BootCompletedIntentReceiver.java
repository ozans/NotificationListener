package com.ozan.notificationlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ozan.notificationlistener.ListenerService;
 
public class BootCompletedIntentReceiver extends BroadcastReceiver {
 @Override
 public void onReceive(Context context, Intent intent) {
  if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
	  Intent listenerService = new Intent(context, ListenerService.class);
	  context.startService(listenerService);
  }
 }
}

