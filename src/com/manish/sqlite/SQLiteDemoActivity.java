package com.manish.sqlite;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class SQLiteDemoActivity extends Activity {
	ArrayList<Contact> imageArry = new ArrayList<Contact>();
	ContactImageAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DataBaseHandler db = new DataBaseHandler(this);
		// get image from drawable
		Bitmap image = BitmapFactory.decodeResource(getResources(),
				R.drawable.facebook);

		// convert bitmap to byte
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte imageInByte[] = stream.toByteArray();
		/**
		 * CRUD Operations
		 * */
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		db.addContact(new Contact("FaceBook", imageInByte));
		// display main List view bcard and contact name

		// Reading all contacts from database
		List<Contact> contacts = db.getAllContacts();
		for (Contact cn : contacts) {
			String log = "ID:" + cn.getID() + " Name: " + cn.getName()
					+ " ,Image: " + cn.getImage();

			// Writing Contacts to log
			Log.d("Result: ", log);
			//add contacts data in arrayList
			imageArry.add(cn);

		}
		adapter = new ContactImageAdapter(this, R.layout.screen_list,
				imageArry);
		ListView dataList = (ListView) findViewById(R.id.list);
		dataList.setAdapter(adapter);

	}

}