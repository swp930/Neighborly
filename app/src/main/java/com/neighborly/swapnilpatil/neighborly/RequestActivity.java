package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.text.Text;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;


public class RequestActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<String> requestNames = new ArrayList<String>();
    public final static String EXTRA_MESSAGE_LATLNG = "com.neighborly.swapnilpatil.neighborly.MESSAGE.LatLng";
    public final static String EXTRA_MESSAGE_SERVICE = "com.neighborly.swapnilpatil.neighborly.MESSAGE.Service";
    public final static String EXTRA_MESSAGE_USER = "com.neighborly.swapnilpatil.neighborly.MESSAGE.User";
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    private GoogleMap mMap;
    private String user;
    private String serviceType;
    private LatLng latLng;
    private Drawer drawer;//Works without

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_cards);
        Intent intent = getIntent();
        latLng = intent.getParcelableExtra(EXTRA_MESSAGE_LATLNG);
        user = intent.getStringExtra(EXTRA_MESSAGE_USER);
        serviceType = intent.getStringExtra(EXTRA_MESSAGE_SERVICE);
        TextView textView = (TextView) findViewById(R.id.textView6);
        TextView textView2 = (TextView) findViewById(R.id.textView5);
        textView.setText(user);
        textView2.setText(serviceType);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        DrawerBuilder builder = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new SecondaryDrawerItem().withIcon(R.drawable.menu_icon1).withName(R.string.drawer_title1),
                        new SecondaryDrawerItem().withIcon(R.drawable.menu_icon2).withName(R.string.drawer_title2),
                        new SecondaryDrawerItem().withIcon(R.drawable.menu_icon3).withName(R.string.drawer_title3),
                        new SecondaryDrawerItem().withIcon(R.drawable.menu_icon4).withName(R.string.drawer_title4),
                        new SecondaryDrawerItem().withIcon(R.drawable.menu_icon5).withName(R.string.drawer_title5)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            if (drawerItem instanceof Nameable) {
                                //toolbar.setTitle(((Nameable)
                                //drawerItem).getNameRes());
                            }
                        }

                        return false;

                    }
                });
        drawer = builder.build();
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

    private List<Requests> createList(int size) {
        requestNames.add("Donald Trump");
        requestNames.add("Adam Johnson");
        requestNames.add("Richard Nixon");
        requestNames.add("Bill Clinton");
        requestNames.add("Barack Obama");
        requestNames.add("Abraham Lincoln");
        List<Requests> result = new ArrayList<Requests>();
        for (int i=0; i < requestNames.size(); i++) {
            Requests ci = new Requests();
            ci.name = requestNames.get(i);
            result.add(ci);
        }
        return result;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        System.out.println("Location: "+latLng.toString());
        mMap = googleMap;
        LatLng location = new LatLng(37.7876, -122.3967);
        mMap.addMarker(new MarkerOptions().position(latLng).title(serviceType));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,6.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
    }

    public void moveDrawer(View view) {
        drawer.openDrawer();
    }

    public void openActivity(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra(EXTRA_MESSAGE,user);
        startActivity(intent);
    }
}
