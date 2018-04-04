package com.example.sachin.forecast;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class WeatherActivity extends AppCompatActivity {
    Typeface weatherfont;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }

    }


    //Menu to change the city
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather,menu);
        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_city) {

            // showInputDialog();

            try {
                Intent intent =
                        new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                .build(this);
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            } catch (GooglePlayServicesRepairableException e) {
                // TODO: Handle the error.
            } catch (GooglePlayServicesNotAvailableException e) {
                // TODO: Handle the error.
            }


        }

        else if (item.getItemId() == R.id.news)
        {
            startActivity(new Intent(WeatherActivity.this,News_Activity.class));
        }
        //respond to menu item selection
            return false;

    }





   // }
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
           if (resultCode == RESULT_OK) {
               Place place = PlaceAutocomplete.getPlace(this, data);
              // Log.i(TAG, "Place: " + place.getName());
               String p = "" + place.getName();
               changeCity(p);
           } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
               Status status = PlaceAutocomplete.getStatus(this, data);
               // TODO: Handle the error.
               //Log.i(TAG, status.getStatusMessage());

           } else if (resultCode == RESULT_CANCELED) {
               // The user canceled the operation.
           }
       }
   }


    public void changeCity(String city){
        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city);
        new CityPreference(this).setCity(city);

    }
}
