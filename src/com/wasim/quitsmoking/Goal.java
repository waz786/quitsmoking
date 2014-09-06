package com.wasim.quitsmoking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Goal extends Activity {
	private static int RESULT_LOAD_IMAGE = 1;
	private static boolean hasPic;
	private String picName = "";
	private ImageView imageView;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goallayout);
		Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
		buttonLoadImage.setText("Change goal");
		final SharedPreferences myPrefs = getSharedPreferences(
				Settings.PREFS_NAME, 0);

		if (isSet() == true) {

			imageView = (ImageView) findViewById(R.id.imgView);
			String a = myPrefs.getString("pictureName", null);
			imageView.setImageBitmap(BitmapFactory.decodeFile(a));

		} else {
			buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
			buttonLoadImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					//hasPic = true;
					Intent i = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					startActivityForResult(i, RESULT_LOAD_IMAGE);
					;
				}
			});

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaColumns.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picName = cursor.getString(columnIndex);
			cursor.close();
			imageView = (ImageView) findViewById(R.id.imgView);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picName));

			SharedPreferences myPrefs = getSharedPreferences(
					Settings.PREFS_NAME, 0);
			SharedPreferences.Editor editor = myPrefs.edit();
			editor.putString("pictureName", picName);
			editor.commit();
			hasPic = true;
			Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
			buttonLoadImage.setText("Change goal");
		}
	}

	/**
	 * Checks if picture has been uploaded
	 * 
	 * @return
	 */
	public boolean isSet() {
		return hasPic;
	}

}