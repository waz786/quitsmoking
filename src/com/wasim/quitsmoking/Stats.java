package com.wasim.quitsmoking;

import java.text.DecimalFormat;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Stats extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statslayout);

		TextView quitDate = (TextView) findViewById(R.id.quitDateView); // shows date/time when user quit
		TextView cigarettesNotSmoked = (TextView) findViewById(R.id.notSmokedView);
		TextView moneySaved = (TextView) findViewById(R.id.moneySavedView);

		SharedPreferences myPrefs = getSharedPreferences(Settings.PREFS_NAME, 0);
		// Get day the user entered
		int pString = myPrefs.getInt("day", -1);
		String d = Integer.toString(pString);

		// Get month the user entered
		int qString = myPrefs.getInt("month", -1) + 1;
		String e = Integer.toString(qString);

		// Get year the user entered
		int rString = myPrefs.getInt("year", -1);
		String f = Integer.toString(rString);

		// Get hour user entered
		long quithr = myPrefs.getLong("quitHour", -1);
		int quitTimeHour = (int) quithr;

		// Get min user entered
		long quitmin = myPrefs.getLong("quitMin", -1);
		int quitTimeMin = (int) quitmin;

		// The date difference between when user quit and todays date 
		DateTime past = new DateTime(rString, qString, pString, quitTimeHour,
				quitTimeMin);
		DateTime today = new DateTime();
		Duration duration = new Interval(past, today).toDuration();
		int days = duration.toStandardDays().getDays();
		int hours = duration.toStandardHours().getHours() - days * 24;
		int minutes = duration.toStandardMinutes().getMinutes() - days * 24
				* 60 - hours * 60;
		String aday = Integer.toString(days);
		String ahours = Integer.toString(hours);
		String amins = Integer.toString(minutes);
		quitDate.setText(aday + " days " + ahours + " hours and " + amins
				+ " minutes ago");

		// Retrieve how many cigarettes smoked a day and multiply by days for Cigarettes not smoked
		double cal = 24.0/(myPrefs.getInt("smokedCigaretes", -1));
		int cigsNotSmoked = (int) (duration.toStandardHours().getHours()/cal);
		DecimalFormat nf = new DecimalFormat("#");
		String totalNotSmoked = (nf.format(cigsNotSmoked));
		cigarettesNotSmoked.setText((totalNotSmoked));
		
		// Money saved - get cost of box/ amount of cigs in each box and multiply by days
		int cigsInBox = myPrefs.getInt("cigaretteBox", -1);
		float boxCost = myPrefs.getFloat("cigaretteCost", -1);
		String currency = myPrefs.getString("currency", null);
		float moneySavedCalc = (boxCost/cigsInBox) * cigsNotSmoked;
		// Sets the total as 2 decimal places
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		String total = (df.format(moneySavedCalc));
		moneySaved.setText(currency + " " + total);

	}

}
