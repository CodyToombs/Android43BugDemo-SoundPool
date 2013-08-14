Android43BugDemo-SoundPool
==========================

Sample code to demonstrate an Android 4.3 bug in SoundPool. The bug being demonstrated is only present in Android 4.3. A fix is expected with the release of 4.3.1.

To be used as described in ***ARTICLE LINK HERE***

NOTE: This project was built with Android Studio v0.2.3

Steps to replicate the bug:

1.  Build this project and deploy it to a device running Android 4.3
2.  Run the app
3.  Tap the button labeled 'Play'

Expected result on *Android 4.3*: The sound will play once.
Expected result on *any other version*: The sound will play continuously.