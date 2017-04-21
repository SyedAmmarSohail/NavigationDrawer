package br.liveo.ndrawer.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import br.liveo.ndrawer.R;

public class Complete_Registration_6_Under_Activity extends AppCompatActivity implements OnClickListener{
	
	SharedPreferences sharedPreferences;

	TextView userName,trackerName;
	Button next;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.complete_registration_6_under_activity);
		
		initialization();

		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null){
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}
		
		sharedPreferences=getApplication().getSharedPreferences("Options", MODE_PRIVATE);
		String name = sharedPreferences.getString("name", "No Name Define");
		String TrackerName = sharedPreferences.getString("trackerName", "No Name Define");

		trackerName.setText(TrackerName);
		
		next.setOnClickListener(this);
	}

	private void initialization() {
		// TODO Auto-generated method stub
		/*userName = (TextView) findViewById(R.id.username_registration6);*/
		next = (Button) findViewById(R.id.next_registration6);
		trackerName = (TextView) findViewById(R.id.trackerName_registration6);
			
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent nextIntent = new Intent(this, MainActivity.class);
		startActivity(nextIntent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				startActivity(new Intent(this, TrackerName_Registration_5_Under_Activity.class));
				finish();
		}
		return true;
	}

}
