package com.ozan.notificationlistener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		finish();

		Intent listenerService = new Intent(getBaseContext(), ListenerService.class);
		startService(listenerService);
	}

}
