package com.ftfl.historicalplaces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ftfl.historicalplaces.database.Historical;
import com.ftfl.historicalplaces.database.HistoricalDataSource;
import com.ftfl.mylastvisiting.R;

public class AddHistoricalActivity extends Activity implements OnClickListener,
		OnTimeSetListener {
	Button btns_save = null;

	EditText etPlace = null;
	EditText etDescription = null;
	EditText etAddress = null;
	EditText etDistrict = null;
	EditText etWeeklyClosedDay = null;
	EditText etDailyVisitTime = null;
	EditText etLatitude = null;
	EditText etLongitude = null;

	String mtPlace = "";
	String mtDescription = "";
	String mtAddress = "";
	String mtDistrict = "";
	String mtWeeklyClosedDay = "";
	String mtDailyVisitTime = "";
	String mtLatitude = "";
	String mtLongitude = "";

	TextView tvItem = null;

	String mStrPlaceID = "";
	String mID = "";
	Long mLId;
	HistoricalDataSource placeDataSource = null;
	Historical UpdatePlace = null;

	String mActivityCurrentDate = "";
	String mActivityProfileId = "";
	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	String mCurrentDate = "";
	Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_historical);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
				Locale.getDefault());
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);

		etPlace = (EditText) findViewById(R.id.addName);
		etDescription = (EditText) findViewById(R.id.addPurpose);
		etAddress = (EditText) findViewById(R.id.addAddress);
		etLatitude = (EditText) findViewById(R.id.addLatitude);
		etLongitude = (EditText) findViewById(R.id.addLongitude);
		etDistrict = (EditText) findViewById(R.id.addStartDay);
		etWeeklyClosedDay = (EditText) findViewById(R.id.addEndDay);
		etDailyVisitTime = (EditText) findViewById(R.id.addNotes);
		btns_save = (Button) findViewById(R.id.btnSave);

		etWeeklyClosedDay.setOnClickListener(this);
		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("mId");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			placeDataSource = new HistoricalDataSource(this);
			UpdatePlace = placeDataSource.singlePlaceData(mLId);

			String mtPlace = UpdatePlace.getmHistoricalPlace();
			String mtDescription = UpdatePlace.getmDescription();
			String mtAddress = UpdatePlace.getmAddress();
			String mtDistrict = UpdatePlace.getmDistrict();
			String mtWeeklyClosedDay = UpdatePlace.getmWeeklyCloseDay();
			String mtDailyVisitTime = UpdatePlace.getmDailyVisitTime();
			String mtLatitude = UpdatePlace.getmLatitute();
			String mtLongitude = UpdatePlace.getmLongitude();

			// set the value of database to the text field.

			etPlace.setText(mtPlace);
			etDescription.setText(mtDescription);
			etAddress.setText(mtAddress);
			etLatitude.setText(mtDistrict);
			etLongitude.setText(mtWeeklyClosedDay);
			etDistrict.setText(mtDailyVisitTime);
			etWeeklyClosedDay.setText(mtLatitude);
			etDailyVisitTime.setText(mtLongitude);

			/*
			 * change button mName
			 */
			btns_save.setText("Update");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_place, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub

		mSetHour = hourOfDay;
		mSetMinute = minute;
		int hour = 0;
		String st = "";
		if (hourOfDay > 12) {
			hour = hourOfDay - 12;
			st = "PM";
		}

		else if (hourOfDay == 12) {
			hour = hourOfDay;
			st = "PM";
		}

		else if (hourOfDay == 0) {
			hour = hourOfDay + 12;
			st = "AM";
		} else {
			hour = hourOfDay;
			st = "AM";
		}
		etWeeklyClosedDay.setText(new StringBuilder().append(hour)
				.append(" : ").append(minute).append(" ").append(st));

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = null;
		int mHour;
		int mMinute;
		switch (v.getId()) {

		case R.id.addStartDay:
			// Process to get Current Time
			mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
			mMinute = mCalendar.get(Calendar.MINUTE);

			// Launch Time Picker Dialog

			TimePickerDialog tpd = new TimePickerDialog(this, this, mHour,
					mMinute, false);
			tpd.show();
			break;

		case R.id.btnSave:

			String mtPlace = "";
			String mtDescription = "";
			String mtAddress = "";
			String mtDistrict = "";
			String mtWeeklyClosedDay = "";
			String mtDailyVisitTime = "";
			String mtLatitude = "";
			String mtLongitude = "";

			mtPlace = etPlace.getText().toString();
			mtDescription = etDescription.getText().toString();
			mtAddress = etAddress.getText().toString();
			mtDistrict = etDistrict.getText().toString();
			mtLongitude = etLongitude.getText().toString();
			mtLatitude = etLatitude.getText().toString();
			mtDailyVisitTime = etDailyVisitTime.getText().toString();
			mtWeeklyClosedDay = etWeeklyClosedDay.getText().toString();

			Historical place = new Historical();
			place.setmHistoricalPlace(mtPlace);
			place.setmDescription(mtDescription);
			place.setmAddress(mtAddress);
			place.setmDistrict(mtDistrict);
			place.setmWeeklyCloseDay(mtWeeklyClosedDay);
			place.setmDailyVisitTime(mtDailyVisitTime);
			place.setmLatitute(mtLatitude);
			place.setmLongitude(mtLongitude);

			/*
			 * if update is needed then update otherwise submit
			 */
			if (mID != null) {

				mLId = Long.parseLong(mID);

				placeDataSource = new HistoricalDataSource(this);

				if (placeDataSource.updateData(mLId, place) == true) {
					toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_SHORT);
					toast.show();
					startActivity(new Intent(AddHistoricalActivity.this,
							HistoricalListActivity.class));
					finish();
				} else {
					toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				placeDataSource = new HistoricalDataSource(this);
				if (placeDataSource.insert(place) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_SHORT);
					toast.show();

					startActivity(new Intent(AddHistoricalActivity.this,
							HistoricalListActivity.class));

					// finish();
				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
			break;
		}

	}
}
