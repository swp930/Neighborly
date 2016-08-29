package com.neighborly.swapnilpatil.neighborly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends Activity {

    private List<String> profileNames = new ArrayList<String>();
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);
        setContentView(R.layout.activity_profile);
        //addingLists();
        Intent intent = getIntent();
        String[] words = intent.getStringArrayExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(words[0]);
        TextView textView1 = (TextView) findViewById(R.id.textView3);
        textView1.setText(words[1]);

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ProfileAdapter ca = new ProfileAdapter(createList(7));
        recList.setAdapter(ca);

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
            //ci.name = profileNames.get(i);
            ci.name= profileNames.get(i);
            result.add(ci);
        }
        return result;
    }
}