package com.wasim.quitsmoking;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Settings extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";

	public int cigSmoked, cigBox;
	private float cigCost;
	EditText cigSmokedText, cigBoxText, cigCostText;
	TimePicker quitTimeText;
	DatePicker quitYearText;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settinglayout);

		submit = (Button) findViewById(R.id.buttonsubmit);
		cigSmokedText = (EditText) findViewById(R.id.cigsmoked);
		cigBoxText = (EditText) findViewById(R.id.cigbox);
		cigCostText = (EditText) findViewById(R.id.cigcost);
		quitYearText = (DatePicker) findViewById(R.id.quityear);
		quitTimeText = (TimePicker) findViewById(R.id.quittime);
		submit.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();

				try {
					cigSmoked = Integer.parseInt(cigSmokedText.getText()
							.toString());
					cigBox = Integer.parseInt(cigBoxText.getText().toString());
					cigCost = Float
							.parseFloat(cigCostText.getText().toString());
				} catch (Exception e) {

				} finally {

					if (cigSmokedText.getText().toString().length() > 0
							|| cigBoxText.getText().toString().length() > 0
							|| cigCostText.getText().toString().length() > 0) {

						editor.putInt("smokedCigaretes", cigSmoked);
						editor.putInt("cigaretteBox", cigBox);
						editor.putFloat("cigaretteCost", cigCost);

						int day = quitYearText.getDayOfMonth();
						int month = quitYearText.getMonth();
						int year = quitYearText.getYear();
						editor.putInt("day", day);
						editor.putInt("month", month);
						editor.putInt("year", year);
						
						long timeHour = quitTimeText.getCurrentHour();
						long timeMins = quitTimeText.getCurrentMinute();
						editor.putLong("quitHour", timeHour);
						editor.putLong("quitMin", timeMins);
						editor.commit();

						Context context = getApplicationContext();
						CharSequence text = "Saved!";
						int duration = Toast.LENGTH_SHORT;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();

						Intent myIntent = new Intent(Settings.this, Home.class);
						Settings.this.startActivity(myIntent);

					} else {

						Toast.makeText(
								getApplicationContext(),
								"Did NOT save settings, please fill in all fields",
								Toast.LENGTH_SHORT).show();
					}

				}
			}
		});

	}
}
