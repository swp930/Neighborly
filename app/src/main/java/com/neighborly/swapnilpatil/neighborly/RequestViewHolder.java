package com.neighborly.swapnilpatil.neighborly;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.neighborly.swapnilpatil.neighborly.R;

/**
 * Created by swapnilpatil on 8/27/16.
 */
public class RequestViewHolder extends RecyclerView.ViewHolder{
    protected TextView vName;
    protected TextView vSurname;
    protected TextView vEmail;
    protected TextView vTitle;

    public RequestViewHolder(View v) {
        super(v);
        vName =  (TextView) v.findViewById(R.id.txtName);
    }
}

