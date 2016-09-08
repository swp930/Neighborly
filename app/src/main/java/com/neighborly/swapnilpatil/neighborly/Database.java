package com.neighborly.swapnilpatil.neighborly;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnilpatil on 9/7/16.
 */
public class Database {

    public List<DataElement> dataElements;

    public Database()
    {
        dataElements = new ArrayList<DataElement>();
        dataElements.add(new DataElement("Need help with plumbing!",new LatLng(37.7876, -122.3967),"Donald Trump"));
        dataElements.add(new DataElement("Need jumper cables",new LatLng(37.783004, -122.444117),"Adam Johnson"));
        dataElements.add(new DataElement("Pack of Bandages",new LatLng(37.869433133014546, -122.27645874023438),"Richard Nixon"));
        dataElements.add(new DataElement("Safety",new LatLng(37.68599392939966, -122.47215270996094),"Bill Clinton"));
        dataElements.add(new DataElement("Computer troubleshooting issues",new LatLng(37.90736658145496, -122.54150390625),"Barack Obama"));
        dataElements.add(new DataElement("Counseling needed",new LatLng(37.63598495426961, -122.41928100585938),"Abraham Lincoln"));
    }

    public List<String> getCategoryList()
    {
        List<String> names;
        names = new ArrayList<String>();
        for(int i = 0; i< dataElements.size();i++)
        {
            names.add(dataElements.get(i).word);
        }
        return names;
    }

    public List<LatLng> getLatList()
    {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        for(int i = 0; i< dataElements.size();i++)
        {
            latLngs.add(dataElements.get(i).latLng);
        }
        return latLngs;
    }

    public String getUser(int i)
    {
        return dataElements.get(i).user;
    }
}
