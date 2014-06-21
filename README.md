NotificationListener
====================

![Screenshot](screenshot.png?raw=true)

Simple Android socket listener service which starts automatically during boot-up to listen for messages on a certain port and show them as Notifications

The idea was to be able to send messages to my mobile via the Linux command line about the status of cron jobs, or certain events taking place so that I'm still informed even while on the move.

This is my 2nd ever Android application, so I am sure there are things which can be improved. Please let me know if you come across anything.

Client Usage
====================

Use your favorite utility/language to establish a data stream to the destination port 25000 and send the text, that's it!

	echo "mesg" | netcat -w 1 x.y.z.w 25000
	
	echo "mesg" | socat -4 - tcp:x.y.z.w:25000

TODO
====================
* A challenge-response protocol to authenticate the user before accepting notifications
* Notification categories
* Notification history
* ...
* Try out GCM (Google Cloud Messaging) for Android

I got to know about GCM while I was almost finished with the application. In retrospect, perhaps it would be easier and more reliable to implement it via this system which would be accessible at times where my mobile won't be, and would offer a queuing system to deliver messages when the mobile device can actually be accessed.

On the other hand, everything would go through Google's servers then, so perhaps I should setup such a system myself :)







