package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends Activity {

    private List<String> profileNames = new ArrayList<String>();
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    private Drawer drawer;//Works without

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setRating(Float.parseFloat("3.5"));
        Intent intent = getIntent();
        String word = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(word);
        TextView textView2 = (TextView) findViewById(R.id.textView3);
        textView2.setText("(408)123-4567");
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ProfileAdapter ca = new ProfileAdapter(createList(7));
        recList.setAdapter(ca);

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

    private List<Profiles> createList(int size) {
        profileNames.add("COMMENTS:");
        profileNames.add("Excellent Service!");
        profileNames.add("I am very happy with this service!");
        profileNames.add("10/10 would recommend to a friend");
        profileNames.add("Am very satisfied");
        profileNames.add("Another one");
        profileNames.add("Could be better");
        List<Profiles> result = new ArrayList<Profiles>();
        for (int i=0; i < profileNames.size(); i++) {
            Profiles ci = new Profiles();
            ci.name= profileNames.get(i);
            result.add(ci);
        }
        return result;
    }

    public void moveDrawer(View view) {
        drawer.openDrawer();
    }
}