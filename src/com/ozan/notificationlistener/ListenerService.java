package com.ozan.notificationlistener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class ListenerService extends Service {

	private ServerSocket serverSocket;
	private Thread serverThread;
	 
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int mNotificationId = 001;
			
	public void putNotification(String addr, String str) {
		
		NotificationCompat.Builder mBuilder =
			    new NotificationCompat.Builder(this)
			    .setSmallIcon(R.drawable.ic_launcher)
			    .setContentTitle("From "+addr)
			    .setStyle(new NotificationCompat.BigTextStyle()
                .bigText(str))
			    .setContentText(str);
		
		mNotificationId+=1;
		
		NotificationManager mNotifyMgr = 
		        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNotifyMgr.notify(mNotificationId, mBuilder.build()); 
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("MyService", "Received start id " + startId + ": " + intent);
		
		Toast.makeText(getBaseContext(), "Notification ListenerService is started",
	              Toast.LENGTH_LONG).show();
		
		this.serverThread = new Thread(new ServerThread());
		this.serverThread.start();
		
		return START_STICKY;
	}
	
	class ServerThread implements Runnable {
		public void run() {
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(25000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!Thread.currentThread().isInterrupted()) {
				try {
					socket = serverSocket.accept();
					String remoteAddr=socket.getRemoteSocketAddress().toString();
					String wholeText="";
					try {
						BufferedReader input;
						input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						wholeText="";
						while(input.ready()) {
							String read = input.readLine();
							System.out.println(read);
							wholeText+=read+"\n";
						}
						new ShowNotificationThread(remoteAddr,wholeText).start();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public class ShowNotificationThread extends Thread {
		public String notificationStr, addr;
		public ShowNotificationThread(String remoteAddr,String str) {
			addr=remoteAddr;
			notificationStr=str;
		}
		public void run () {
			Log.d("MyService", "ShowNotificationThread ran with text: " + notificationStr);
			putNotification(addr,notificationStr);
		}
	}
	

}
