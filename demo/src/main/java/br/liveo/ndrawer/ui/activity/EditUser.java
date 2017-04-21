package br.liveo.ndrawer.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.io.Serializable;
import java.util.List;

import br.liveo.ndrawer.R;
import br.liveo.ndrawer.adapter.SimpleRecyclerAdapter;

public class EditUser extends AppCompatActivity implements OnClickListener, Serializable {

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	int SELECT_IMAGE = 100;
	int CAMERA_CAPTURE = 200;
	SharedPreferences sharedPreferences;
	ImageView profilepic;
	EditText name, number, trackerId, trackerPin;
	Button setGeofence, save;

	FrameLayout frameLayout;
	String imagePath;

	Bitmap bitmap;

	List<UserTracker> trackerUser;

	SimpleRecyclerAdapter familyTrackerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edituser);

		Initialization();

		ActionBar actionBar = getSupportActionBar();

		if (actionBar != null){
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setHomeButtonEnabled(true);
		}

		frameLayout.setOnClickListener(this);
		/*setGeofence.setOnClickListener(this);*/
		save.setOnClickListener(this);

		/*FamilyTrackerEditFarag.list.size();*/

		/*List<UserTracker> perks = (List<UserTracker>) getIntent().getSerializableExtra("data");
		Log.e("Data", perks.size()+"");*/









	}

	/*public EditUser(Context context, List<UserTracker> list){
		trackerUser = list;
		Log.e("ConstrauctorEdit", list.size()+"");
	}*/

	public void familyTrackerList(List<UserTracker> list){
		trackerUser = list;

		Log.e("Function List", trackerUser.size()+"");
	}

	private void Initialization() {
		// TODO Auto-generated method stub
		profilepic = (ImageView) findViewById(R.id.profilePic);
		name = (EditText) findViewById(R.id.trackerName_edituser);
		number = (EditText) findViewById(R.id.trackerName_edituser);
		trackerId = (EditText) findViewById(R.id.trackerId);
		trackerPin = (EditText) findViewById(R.id.trackerPin);
		frameLayout = (FrameLayout) findViewById(R.id.profileFrame);
		/*setGeofence = (Button) findViewById(R.id.geofenceedit);*/
		save = (Button) findViewById(R.id.save_geofenceEdit);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.profileFrame:
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
								startActivityForResult(Intent.createChooser(
										intent, "Select Source:"), SELECT_IMAGE);
							}
							dialog.dismiss();
						}
					});

			getImageFrom.show();

			break;

		/*case R.id.geofenceedit:

			if (name.getText().toString().matches("")
					|| number.getText().toString().matches("")
					|| trackerId.getText().toString().matches("")
					|| trackerPin.getText().toString().matches("")) {
				Toast.makeText(this, "Please fill the fields",
						Toast.LENGTH_SHORT).show();
			} else {
				sharedPreferences = getApplicationContext()
						.getSharedPreferences("Options", MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("name", name.getText().toString());
				editor.putString("number", number.getText().toString());
				editor.commit();
				*//*startActivity(new Intent(this, SetGeoFence.class));*//*
			}


			break;*/

		case R.id.save_geofenceEdit:
			if (name.getText().toString().matches("")
					|| number.getText().toString().matches("")
					|| trackerId.getText().toString().matches("")
					|| trackerPin.getText().toString().matches("")
					|| profilepic.getId() == 0 ) {
				Toast.makeText(this, "Please fill the fields",
						Toast.LENGTH_SHORT).show();
			} else {
				sharedPreferences = getApplicationContext()
						.getSharedPreferences("Options", MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("trackerName", name.getText().toString());
				editor.putString("number", number.getText().toString());
				editor.commit();




					/*trackerUser = (ArrayList<UserTracker>) getIntent().getSerializableExtra("array_list");*/



				int position= getIntent().getIntExtra("position", 0);

				Log.e("Position", position + "");

				Toast.makeText(getApplicationContext(), position + "After", Toast.LENGTH_SHORT).show();

				FamilyTrackerEditFrag_New.listData.set(position, new UserTracker("asd", "asd", "asd", name.getText().toString(),profilepic.getId()));



				Intent intent = new Intent(this, FamilyTrackerEditFrag_New.class);

				startActivity(intent);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);





			}
			break;

		}

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

				Picasso.with(this).load(selectedImage).resize(90, 90)
						.centerCrop().transform(new RoundedTransformation())
						.into(profilepic);


				Log.e("ProfilePic", profilepic.getId()+"");

			} else if (requestCode == CAMERA_CAPTURE) {

				Picasso.with(this).load(new File(imagePath)).resize(90, 90)
						.centerCrop().transform(new RoundedTransformation())
						.into(profilepic);

				if (bitmap != null && bitmap.isRecycled()) {
					bitmap.recycle();
				}

			}
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		startActivity(new Intent(this, FamilyTrackerEditFrag_New.class));
		overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				startActivity(new Intent(this, FamilyTrackerEditFrag_New.class));
				finish();
		}
		return true;
	}
}
