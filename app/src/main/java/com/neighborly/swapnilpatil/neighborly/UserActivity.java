package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class UserActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<String> userNames = new ArrayList<String>();
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);
        setContentView(R.layout.activity_user_cards);
        //addingLists();
        Intent intent = getIntent();
        String serviceType = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(serviceType);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        UserAdapter ca = new UserAdapter(createList(30),serviceType);
        recList.setAdapter(ca);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    private List<Users> createList(int size) {
        userNames.add("Donald Trump");
        userNames.add("Adam Johnson");
        userNames.add("Richard Nixon");
        userNames.add("Bill Clinton");
        userNames.add("Barack Obama");
        userNames.add("Abraham Lincoln");
        userNames.add("George Washington");
        /*for(int i = 0; i<7;i++)
        {
            userNames.add("User"+(i+1));
        }*/
        List<Users> result = new ArrayList<Users>();
        for (int i=0; i < userNames.size(); i++) {
            Users ci = new Users();
            ci.name = userNames.get(i);
            result.add(ci);

        }

        return result;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.7876, -122.3967);
        mMap.addMarker(new MarkerOptions().position(sydney).title("San Francisco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
