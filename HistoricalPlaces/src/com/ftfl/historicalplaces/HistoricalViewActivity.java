package com.ftfl.historicalplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ftfl.historicalplaces.database.Historical;
import com.ftfl.historicalplaces.database.HistoricalDataSource;
import com.ftfl.mylastvisiting.R;

public class HistoricalViewActivity extends Activity {

	TextView tvName = null;
	TextView tvPurpose = null;
	TextView tvAddress = null;
	TextView tvLatitude = null;
	TextView tvLongitude = null;
	TextView tvStartDay = null;
	TextView tvEndDay = null;
	TextView tvNotes = null;

	HistoricalDataSource dataSource = null;
	Historical place = null;

	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historical_view);

		tvName = (TextView) findViewById(R.id.viewName);
		tvPurpose = (TextView) findViewById(R.id.viewPurpose);
		tvAddress = (TextView) findViewById(R.id.viewAddress);
		tvLatitude = (TextView) findViewById(R.id.viewLatitude);
		tvLongitude = (TextView) findViewById(R.id.viewLongitude);
		tvStartDay = (TextView) findViewById(R.id.viewStartDay);
		tvEndDay = (TextView) findViewById(R.id.viewEndDay);
		tvNotes = (TextView) findViewById(R.id.viewNotes);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("mId");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			dataSource = new HistoricalDataSource(this);
			place = dataSource.singlePlaceData(mLId);

			String mName = place.getmHistoricalPlace();
			String mPurpose = place.getmDescription();
			String mAddress = place.getmAddress();
			String mLatitude = place.getmLatitute();
			String mLongitude = place.getmLongitude();
			String mStartDay = place.getmWeeklyCloseDay();
			String mEndDay = place.getmDailyVisitTime();
			String mNotes = place.getmDescription();

			// set the value of database to the text field.

			tvName.setText(mName);
			tvPurpose.setText(mPurpose);
			tvAddress.setText(mAddress);
			tvLatitude.setText(mLatitude);
			tvLongitude.setText(mLongitude);
			tvStartDay.setText(mStartDay);
			tvEndDay.setText(mEndDay);
			tvNotes.setText(mNotes);

		}
	}

}
