package br.liveo.ndrawer.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.liveo.ndrawer.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class TrackerName_Registration_5_Under_Activity extends AppCompatActivity implements
		OnClickListener {

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	int SELECT_IMAGE = 100;
	int CAMERA_CAPTURE = 200;
	AlertDialog levelDialog;

	SharedPreferences sharedPreferences;
	Button next;
	EditText trackerName, trackerNumber;

	ImageView profileImage;

	String imagePath;

	Bitmap bitmap;
	FrameLayout profileFrame;

	static List<UserTracker> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trackername_registration_5_under_activity);

		initialization();

		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null){
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}
		list = new ArrayList<UserTracker>();
		next.setOnClickListener(this);
		profileImage.setOnClickListener(this);
		/*getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);*/
	}

	private void initialization() {
		// TODO Auto-generated method stub

		next = (Button) findViewById(R.id.next_registration5);
		trackerName = (EditText) findViewById(R.id.trackerName_registration5);
		trackerNumber = (EditText) findViewById(R.id.trackerNumber_registration5);
		profileImage = (ImageView) findViewById(R.id.trackerProfile_pic);
		profileFrame = (FrameLayout) findViewById(R.id.addTrackerPhoto);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivity(new Intent(this, TrackerId_Registration_4_Under_Activity.class));
			finish();
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {

			case R.id.next_registration5:
			if (trackerName.getText().toString().matches("")
					|| trackerNumber.getText().toString().matches("")) {
				Toast.makeText(this, "Please Enter Related Fields",
						Toast.LENGTH_SHORT).show();
			} else {
				sharedPreferences = getApplicationContext().getSharedPreferences(
						"Options", MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("trackerName", trackerName.getText().toString());
				editor.putString("trackerMobile", trackerNumber.getText()
						.toString());
				editor.commit();

				Log.e("profile", profileImage.toString() + "");

				if (FamilyTrackerEditFrag_New.listData != null) {

					FamilyTrackerEditFrag_New.listData.add(new UserTracker("abc", "abc", "abc", trackerName.getText().toString(), profileImage.getId()));

				}
				else{
					FamilyTrackerEditFrag_New.listData = new ArrayList<UserTracker>();
					FamilyTrackerEditFrag_New.listData.add(new UserTracker("abc", "abc", "abc", trackerName.getText().toString(), profileImage.getId()));

				}


				Intent i = new Intent(TrackerName_Registration_5_Under_Activity.this,
						Complete_Registration_6_Under_Activity.class);
				startActivity(i);
				overridePendingTransition(R.anim.enter, R.anim.exit);
			}
				break;

			case R.id.trackerProfile_pic:
				chooseFrom();

				break;
		}

	}

	private void chooseFrom() {
		// TODO Auto-generated method stub
		AlertDialog.Builder getImageFrom = new AlertDialog.Builder(this);
		getImageFrom.setTitle("Select Source:");
		final CharSequence[] opsChars = { "Select Image From Gallery",
				"Capture Image From Camera" };
		getImageFrom.setItems(opsChars,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 1) {
							String imagePath1 = Environment
									.getExternalStorageDirectory()
									+ File.separator + "";
							File dir_image2 = new File(imagePath1);
							dir_image2.mkdirs();

							File tmpFile = new File(dir_image2, ""
									+ System.currentTimeMillis());
							imagePath = tmpFile.getPath();
							Uri imageFileUri = Uri.fromFile(tmpFile);

							Intent intent = new Intent(
									MediaStore.ACTION_IMAGE_CAPTURE);
							intent.putExtra(MediaStore.EXTRA_OUTPUT,
									imageFileUri);
							startActivityForResult(intent, CAMERA_CAPTURE);
						} else if (which == 0) {
							Intent intent = new Intent();
							intent.setType("image/*");
							intent.setAction(Intent.ACTION_GET_CONTENT);
							startActivityForResult(Intent.createChooser(intent,
									"Select Source:"), SELECT_IMAGE);
						}
						dialog.dismiss();
					}
				});

		getImageFrom.show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_IMAGE) {

				Uri selectedImage = data.getData();
				String[] filePath = { MediaStore.Images.Media.DATA };
				Cursor c = getContentResolver().query(selectedImage, filePath,
						null, null, null);
				c.moveToFirst();
				int columnIndex = c.getColumnIndex(filePath[0]);
				String picturePath = c.getString(columnIndex);
				c.close();

				Picasso.with(this).load(selectedImage).resize(120, 120)
						.centerCrop().transform(new RoundedTransformation())
						.into(profileImage);
				Picasso.with(this).load(selectedImage).resize(120, 120)
						.centerCrop().transform(new RoundedTransformation())
						.into(profileImage);


			} else if (requestCode == CAMERA_CAPTURE) {

				Picasso.with(this).load(new File(imagePath)).resize(90, 90)
						.centerCrop().transform(new RoundedTransformation())
						.into(profileImage);

				if (bitmap != null && bitmap.isRecycled()) {
					bitmap.recycle();
				}

			}
		}

	}



}
