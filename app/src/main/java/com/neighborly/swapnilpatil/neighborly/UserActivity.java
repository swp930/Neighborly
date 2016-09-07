package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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


public class UserActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<String> userNames = new ArrayList<String>();
    public final static String EXTRA_MESSAGE_LATLNG = "com.neighborly.swapnilpatil.neighborly.MESSAGE.LatLng";
    public final static String EXTRA_MESSAGE_SERVICE = "com.neighborly.swapnilpatil.neighborly.MESSAGE.Service";
    private GoogleMap mMap;
    private LatLng latLng;
    private Drawer drawer;//Works without

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cards);
        Intent intent = getIntent();
        latLng = intent.getParcelableExtra(EXTRA_MESSAGE_LATLNG);
        //wrapper = intent.getParcelableExtra(EXTRA_MESSAGE);
        String serviceType = intent.getStringExtra(EXTRA_MESSAGE_SERVICE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(serviceType);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);//Will have to delete
        recList.setHasFixedSize(true);//Will have to delete
        LinearLayoutManager llm = new LinearLayoutManager(this);//Will have to delete
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        UserAdapter ca = new UserAdapter(createList(30),serviceType);
        recList.setAdapter(ca);
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
        System.out.println("Location: "+latLng.toString());
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng latLng2 = new LatLng(37.7876, -122.3967);
        LatLng location = new LatLng(37.7876, -122.3967);
        mMap.addMarker(new MarkerOptions().position(latLng).title("San Francisco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,6.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
    }

    public void moveDrawer(View view) {
        drawer.openDrawer();
    }
}
