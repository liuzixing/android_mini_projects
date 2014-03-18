package liuzixingplayer.playertest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer dontcallme;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Pickle","onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//dontcallme.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		Log.e("Pickle","onResume");
		dontcallme = MediaPlayer.create(this, R.raw.breathless);
		dontcallme.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e("Pickle","onResume");
		dontcallme.stop();
		dontcallme.release();
		super.onPause();
	}

	public void openFB(View v) {
		String url = "http://www.facebook.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	public void openBC(View v) {
		String url = "https://class.coursera.org/androidapps101-001/forum";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

}
