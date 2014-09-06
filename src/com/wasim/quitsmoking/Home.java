package com.wasim.quitsmoking;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

	Button stats, links, settings, motivation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		stats = (Button) findViewById(R.id.buttonstats);
		stats.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Home.this, Stats.class);
				Home.this.startActivity(myIntent);
			}
		});


		settings = (Button) findViewById(R.id.buttonsettings);
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Home.this, Settings.class);
				Home.this.startActivity(myIntent);
			}
		});
		
		motivation = (Button) findViewById(R.id.buttonmotivation);
		motivation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(Home.this, Goal.class);
				Home.this.startActivity(myIntent);
			}
		});

	}





@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.home, menu);
	return true;
}

}
