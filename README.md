Android43BugDemo-SoundPool
==========================

Sample code to demonstrate an Android 4.3 bug in SoundPool. The bug being demonstrated is only present in Android 4.3. A fix is expected with the release of 4.3.1.

To be used as described in [Bug Watch - Android 4.3 - Automatic Looping Of Sounds With SoundPool Is Broken, May Cause Some Games And Apps To Be Eerily Quiet](http://www.androidpolice.com/2013/08/12/bug-watch-several-2013-nexus-7-units-suffer-from-erratic-and-jumpy-multi-touch-android-team-investigating/)

NOTE: This project was built with Android Studio v0.2.3

Steps to replicate the bug:

1.  Build this project and deploy it to a device running Android 4.3
2.  Run the app
3.  Tap the button labeled 'Play'

Expected result on *Android 4.3*: The sound will play once.
Expected result on *any other version*: The sound will play continuously.
