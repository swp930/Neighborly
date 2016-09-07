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
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<String> categoryNames = new ArrayList<String>();
    private List<LatLng> locationNames = new ArrayList<LatLng>();
    private GoogleMap mMap;
    final String[] data = {"one", "two", "three"};
    private Drawer drawer;//Works without

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,data);
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
        /////Works without
    }

    private List<String> getList()
    {
        List<String> names = new ArrayList<String>();
        names.add("Need help with plumbing!");
        names.add("Need jumper cables");
        names.add("Pack of Bandages");
        names.add("Safety");
        names.add("Computer troubleshooting issues");
        names.add("Counseling needed");
        return names;
    }

    private List<LatLng> getLatList()
    {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(37.7876, -122.3967));
        latLngs.add(new LatLng(37.783004, -122.444117));
        latLngs.add(new LatLng(37.869433133014546, -122.27645874023438));
        latLngs.add(new LatLng(37.68599392939966, -122.47215270996094));
        latLngs.add(new LatLng(37.90736658145496, -122.54150390625));
        latLngs.add(new LatLng(37.63598495426961, -122.41928100585938));
        return latLngs;
    }

    private List<Categories> createList(int size) {
        categoryNames = getList();
        locationNames = getLatList();
        List<Categories> result = new ArrayList<Categories>();
        for (int i=0; i < categoryNames.size(); i++) {
            Categories ci = new Categories();
            ci.name = categoryNames.get(i);
            ci.latLng = locationNames.get(i);
            result.add(ci);
        }
        return result;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sanfran = new LatLng(37.7876, -122.3967);
        List<LatLng> list = getLatList();
        for(int i = 0; i<list.size();i++)
        {
            mMap.addMarker(new MarkerOptions().position(list.get(i)).title(categoryNames.get(i)));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfran,9.0f));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.7876, -122.3967), 12.0f));
    }


    public void moveDrawer(View view) {
        drawer.openDrawer();
    }
}
