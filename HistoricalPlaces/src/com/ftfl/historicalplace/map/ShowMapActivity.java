package com.ftfl.historicalplace.map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.ftfl.mylastvisiting.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapActivity extends Activity {

	// latitude and longitude
	public long latitute;
	public long longitude;
	public LatLng BCC_HOME_LAT_LNG = new LatLng(latitute, longitude);
	Marker myMarker;
	// Google Map
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_map);

		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Function to load map. If map is not created it will Create it
	private void initilizeMap() {

		try {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// Setting my current Location
			googleMap.setMyLocationEnabled(true);

			// For Detail Information Show
			myMarker = googleMap.addMarker(new MarkerOptions()
					.position(BCC_HOME_LAT_LNG)
					.title("BCC LICT FTFL ANDROID")
					.snippet("BCC LAB 2")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));

			// Move the camera instantly to BCC with a zoom of 20x
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
					BCC_HOME_LAT_LNG, 20));

			// Zoom in, Animating the Camera
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);

			// BLUE color icon set on Marker
			myMarker.setIcon(BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
		} catch (Exception e) {
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

}