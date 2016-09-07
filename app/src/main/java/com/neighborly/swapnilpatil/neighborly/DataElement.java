package com.neighborly.swapnilpatil.neighborly;

import android.provider.ContactsContract;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by swapnilpatil on 9/7/16.
 */
public class DataElement {
    public String word;
    public LatLng latLng;

    public DataElement(String word, LatLng latLng)
    {
        this.word = word;
        this.latLng = latLng;
    }
}
