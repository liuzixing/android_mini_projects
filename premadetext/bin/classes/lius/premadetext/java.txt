package lius.premadetext;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher{

	private EditText mName;
	private EditText mWhere;
	private TextView mtextView;
	private Button mSMS;
	private Button mEmail;
	private Button mAutofill;
	private Button mReset;
	private Button mFinish;
	private static final String s1 = "I tell you a funny thing!\nYesterday, I saw ";
	private static final String s2 = " dancing naked in the ";
	private static final String names[] = {"my friend","my classmate","my colleague"};
	private static final String locations[] = {"classroom","party","swimming pool"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mName = (EditText) findViewById(R.id.name);
		mWhere = (EditText) findViewById(R.id.where);
		mWhere.setVisibility(View.INVISIBLE);
		mtextView = (TextView) findViewById(R.id.textView1);
		mSMS = (Button) findViewById(R.id.sms);
		mEmail = (Button) findViewById(R.id.email);
		mAutofill = (Button) findViewById(R.id.autofill);
		mReset = (Button) findViewById(R.id.reset);
		mFinish = (Button) findViewById(R.id.finish);
		mName.addTextChangedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void showEditedText(View fiish){
		//show the edited view and change the visibility setting
		mtextView.setVisibility(View.VISIBLE);
		String name = mName.getText().toString();
		String where = mWhere.getText().toString();
		mtextView.setText(s1+name+s2+where+"!");
		mSMS.setVisibility(View.VISIBLE);
		mEmail.setVisibility(View.VISIBLE);
		mName.setVisibility(View.GONE);
		mWhere.setVisibility(View.GONE);
	}
	//send message
	public void sendSMS(View x){
		String name = mName.getText().toString();
		String where = mWhere.getText().toString();
		String message = s1+name+s2+where+"!";
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.fromParts("sms", "", null));
		intent.putExtra("sms_body", message);

		try {
			startActivity(intent);
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(),
					message, Toast.LENGTH_LONG)
					.show();
		}
	}
	public void onReset(View x){
		//change the visibility setting and set two editors to empty
		mSMS.setVisibility(View.GONE);
		mEmail.setVisibility(View.GONE);
		mName.setVisibility(View.VISIBLE);
		mName.setText("");
		mWhere.setText("");
		mWhere.setVisibility(View.VISIBLE);
		mtextView.setVisibility(View.INVISIBLE);
		mFinish.setVisibility(View.VISIBLE);
	}
	public void autofill(View x) {
		mtextView.setVisibility(View.VISIBLE);
		mtextView.setText(s1+names[(int)(Math.random()*2)]+s2+locations[(int)(Math.random()*2)]+"!");
		mSMS.setVisibility(View.VISIBLE);
		mEmail.setVisibility(View.VISIBLE);
		mName.setVisibility(View.INVISIBLE);
		mWhere.setVisibility(View.INVISIBLE);
		mFinish.setVisibility(View.INVISIBLE);
	}
	public void sendEmail(View X) {

		String name = mName.getText().toString();
		String where = mWhere.getText().toString();
		String message = s1+name+s2+where+"!";



		// To test unsupported schemes change "mailto" to "horseback"
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
		emailIntent.setData(Uri.fromParts("mailto",
				"", null));
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "important news");

		emailIntent.putExtra(Intent.EXTRA_TEXT, message);

		// Better .... use resolveActivity
		// We can check to see if there is a configured email client
		// BEFORE trying to start an activity
		// Using this test we could have prevented the user from ever opening
		// the survey...
		if (emailIntent.resolveActivity(getPackageManager()) == null) {
			Toast.makeText(getApplicationContext(),
					"Please configure your email client!", Toast.LENGTH_LONG)
					.show();
		} else {
			// Secondly, use a chooser will gracefully handle 0,1,2+ matching
			// activities
			startActivity(Intent.createChooser(emailIntent,
					"Please choose your email app!"));
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String name = s.toString();
		boolean valid = name.length() > 0;

		

		// Store true or false in the 'isVisible' boolean variable
		// true = the second editor is visible
		boolean isVisible = mWhere.getVisibility() == View.VISIBLE;
		if (isVisible == valid) {
			// No animation required if both values are true or both values are
			// false
			return;
		}
		// Declare the variable (the pointer!) The next line does NOT create a
		// new animation object - all we have is pointer so far.
		Animation anim;

		if (valid) {
			mWhere.setVisibility(View.VISIBLE);
			// Create a new animation object
			anim = AnimationUtils.makeInAnimation(this, true);
		} else {
			// Create a new animation object
			anim = AnimationUtils.makeOutAnimation(this, true);
			mWhere.setVisibility(View.INVISIBLE);
		}
		// Tell the view it's time to start animating
		mWhere.startAnimation(anim);
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}
