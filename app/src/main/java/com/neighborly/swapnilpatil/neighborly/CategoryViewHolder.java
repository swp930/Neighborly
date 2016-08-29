package com.neighborly.swapnilpatil.neighborly;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neighborly.swapnilpatil.neighborly.R;

/**
 * Created by swapnilpatil on 8/27/16.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder{
    protected TextView vName;
    //protected ImageView vImage;

    //protected TextView vSurname;
    //protected TextView vEmail;
    //protected TextView vTitle;
    //protected CardView cardView;
    protected RelativeLayout relativeLayout;

    public CategoryViewHolder(View v) {
        super(v);
        vName =  (TextView) v.findViewById(R.id.txtName);
        /*v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.v("CategoryViewHolder", "nigga we made it");
            }
        });*/
        //vSurname = (TextView)  v.findViewById(R.id.txtSurname);
        //vEmail = (TextView)  v.findViewById(R.id.txtEmail);
        //vTitle = (TextView) v.findViewById(R.id.title);
    }
}
