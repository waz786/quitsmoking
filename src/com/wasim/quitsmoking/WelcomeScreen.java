package com.wasim.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class WelcomeScreen extends Activity {

	private int ds, fib;
	private float c;
	private EditText dailySmoke, fagsInBox, cost, currency;
	private DatePicker smokeDate;
	private Button start;
	private TimePicker quitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcomescreenlayout);

		final SharedPreferences settings = getSharedPreferences(
				Settings.PREFS_NAME, 0);
		int check = settings.getInt("smokedCigaretes", -99);
		if (check != -99) {
			Intent myIntent = new Intent(WelcomeScreen.this, Home.class);
			WelcomeScreen.this.startActivity(myIntent);
		}

		start = (Button) findViewById(R.id.buttonStart);
		dailySmoke = (EditText) findViewById(R.id.dailysmoke);
		fagsInBox = (EditText) findViewById(R.id.fagsinbox);
		cost = (EditText) findViewById(R.id.boxprice);
		smokeDate = (DatePicker) findViewById(R.id.datestop);
		quitTime = (TimePicker) findViewById(R.id.initialquittime);
		currency = (EditText) findViewById(R.id.currencyText);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SharedPreferences.Editor editor = settings.edit();
				ds = Integer.parseInt(dailySmoke.getText().toString());
				editor.putInt("smokedCigaretes", ds);
				//editor.commit();

				fib = Integer.parseInt(fagsInBox.getText().toString());
				editor.putInt("cigaretteBox", fib);
				//editor.commit();

				c = Float.parseFloat((cost.getText().toString()));
				editor.putFloat("cigaretteCost", c);
				//editor.commit();

				int day = smokeDate.getDayOfMonth();
				int month = smokeDate.getMonth();
				int year = smokeDate.getYear();
				editor.putInt("day", day);
				editor.putInt("month", month);
				editor.putInt("year", year);
				//editor.commit();
				
				long timeHour = quitTime.getCurrentHour();
				long timeMins = quitTime.getCurrentMinute();
				editor.putLong("quitHour", timeHour);
				editor.putLong("quitMin", timeMins);
				
				String currencyValue = currency.getText().toString();
				editor.putString("currency", currencyValue);
				editor.commit();

				Intent myIntent = new Intent(WelcomeScreen.this, Home.class);
				WelcomeScreen.this.startActivity(myIntent);
			}

		});

	}
}
