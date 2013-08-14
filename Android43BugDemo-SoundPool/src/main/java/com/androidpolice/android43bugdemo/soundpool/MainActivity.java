package com.androidpolice.android43bugdemo.soundpool;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private AudioManager audioManager;
	private SoundPool soundPool;
	private int windShieldSoundId;
	private int streamId;

	private Button btnPlay;
	private Button btnStop;
	private TextView txtAndroidVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    initControls();

	    audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	    soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
	    windShieldSoundId = soundPool.load(this, R.raw.windshield_hit_02, 1);
	    streamId = -1;
    }

	@Override
	protected void onPause() {
		super.onPause();

		stop();
		soundPool.release();
		soundPool = null;
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (audioManager == null)
			audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

		if (soundPool == null) {
			soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
			windShieldSoundId = soundPool.load(this, R.raw.windshield_hit_02, 1);
		}

		streamId = -1;
	}

	private void initControls() {
		btnPlay = (Button) findViewById(R.id.button_play);
		btnPlay.setOnClickListener(onClick_Play);

		btnStop = (Button) findViewById(R.id.button_stop);
		btnStop.setOnClickListener(onClick_Stop);

		txtAndroidVersion = (TextView) findViewById(R.id.txtAndroidVersion);
		txtAndroidVersion.setText(Build.VERSION.RELEASE);
	}

	private View.OnClickListener onClick_Play = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			play();
		}
	};

	private View.OnClickListener onClick_Stop = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			stop();
		}
	};

	private void play() {
		stop();

		try {
			float streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
			streamVolume = streamVolume / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

			streamId = soundPool.play(windShieldSoundId, streamVolume, streamVolume, 1, -1, 1f); // the -1 in the 5th parameter causes the sound to loop infinitely
		} catch (Exception ex) {
			Log.d("SoundPoolBug", ex.getMessage());
		}
	}

	private void stop() {
		if (streamId != -1) {
			soundPool.stop(streamId);
			streamId = -1;
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
