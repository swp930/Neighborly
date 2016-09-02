package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<String> categoryNames = new ArrayList<String>();
    private List<Drawable> lists = new ArrayList<Drawable>();
    private GoogleMap mMap;
    private ListView mDrawerList;
    final String[] data = {"one", "two", "three"};
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);
        setContentView(R.layout.activity_cards);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,data);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList.setAdapter(adapter);
        /*mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });*/
        //mActivityTitle = getTitle().toString();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //addingLists();
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        CategoryAdapter ca = new CategoryAdapter(createList(30));
        recList.setAdapter(ca);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //addDrawerItems();
        //setupDrawer();
    }

    private List<Categories> createList(int size) {
        categoryNames.add("Plumbing");
        //Drawable drawablePlumbing = ContextCompat.getDrawable(getApplicationContext(),R.drawable.plumbingicon);
        //lists.add(drawablePlumbing);

        categoryNames.add("Car");
        //Drawable drawableCar = ContextCompat.getDrawable(getApplicationContext(),R.drawable.caricon);
        //lists.add(drawableCar);

        categoryNames.add("Medical");
        //Drawable drawableMedical = ContextCompat.getDrawable(getApplicationContext(),R.drawable.medicalicon);
        //lists.add(drawableMedical);

        categoryNames.add("Safety");
        //Drawable drawableSafety = ContextCompat.getDrawable(getApplicationContext(),R.drawable.safetyicon);
        //lists.add(drawableSafety);

        categoryNames.add("Electronics");
        //Drawable drawableElectronics = ContextCompat.getDrawable(getApplicationContext(),R.drawable.electronicsicon);
        //lists.add(drawableElectronics);

        categoryNames.add("Food/Groceries");
        //Drawable drawableFood = ContextCompat.getDrawable(getApplicationContext(),R.drawable.foodicon);
        //lists.add(drawableFood);

        categoryNames.add("Advice");
        //Drawable drawableAdvice = ContextCompat.getDrawable(getApplicationContext(),R.drawable.adviceicon);
        //lists.add(drawableAdvice);

        List<Categories> result = new ArrayList<Categories>();
        for (int i=0; i < categoryNames.size(); i++) {
            Categories ci = new Categories();
            ci.name = categoryNames.get(i);
            //ci.imageId = lists.get(i);
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


    public void moveDrawer(View view) {
        mDrawerLayout.openDrawer(Gravity.LEFT); //Works without
        //Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show(); //Works With
    }
}
