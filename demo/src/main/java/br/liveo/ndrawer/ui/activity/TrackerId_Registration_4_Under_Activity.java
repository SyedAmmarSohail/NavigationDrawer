package br.liveo.ndrawer.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.liveo.ndrawer.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class TrackerId_Registration_4_Under_Activity extends AppCompatActivity implements
		OnClickListener {

	EditText trackerId, code;
	Button next;
	Intent ourIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trackerid_registration_4_under_activity);

		initialize();

		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null){
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}

		next.setOnClickListener(this);
		/*getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);*/
	}

	private void initialize() {
		// TODO Auto-generated method stub
		trackerId = (EditText) findViewById(R.id.trackerId_registration4);
		code = (EditText) findViewById(R.id.codeText_registration4);

		next = (Button) findViewById(R.id.next_registration4);

	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (trackerId.getText().toString().matches("")
				|| code.getText().toString().matches("")) {
			Toast.makeText(this, "Enter Related Fields", Toast.LENGTH_SHORT)
					.show();

		} else {
			Intent ourIntent = new Intent(TrackerId_Registration_4_Under_Activity.this,
					TrackerName_Registration_5_Under_Activity.class);
			startActivity(ourIntent);
			overridePendingTransition(R.anim.enter, R.anim.exit);

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
