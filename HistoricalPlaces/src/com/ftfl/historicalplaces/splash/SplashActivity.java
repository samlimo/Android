package com.ftfl.historicalplaces.splash;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ftfl.historicalplaces.AddHistoricalActivity;
import com.ftfl.historicalplaces.HistoricalListActivity;
import com.ftfl.historicalplaces.database.Historical;
import com.ftfl.historicalplaces.database.HistoricalDataSource;
import com.ftfl.mylastvisiting.R;

public class SplashActivity extends Activity {
	HistoricalDataSource mDataSource = null;
	Historical mShoppingComplex = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mDataSource = new HistoricalDataSource(this);

		new Timer().schedule(new TimerTask() {
			public void run() {
				SplashActivity.this.runOnUiThread(new Runnable() {
					public void run() {

						if (mDataSource.isEmpty()) {
							startActivity(new Intent(SplashActivity.this,
									AddHistoricalActivity.class));
						} else {
							startActivity(new Intent(SplashActivity.this,
									HistoricalListActivity.class));

						}

						finish();
					}
				});
			}
		}, 2000);
	}

}
