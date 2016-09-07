package com.neighborly.swapnilpatil.neighborly;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static List<Categories> categoryList;
    public final static String EXTRA_MESSAGE_SERVICE = "com.neighborly.swapnilpatil.neighborly.MESSAGE.Service";
    public final static String EXTRA_MESSAGE_LATLNG = "com.neighborly.swapnilpatil.neighborly.MESSAGE.LatLng";

    public CategoryAdapter(List<Categories> categoryList) {
        this.categoryList = categoryList;
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static LatLng findLatLng(String word)
    {
        for (int i = 0; i<categoryList.size();i++)
        {
            if(categoryList.get(i).name.equals(word))
            {
                System.out.println("Dis shit Found");
                return categoryList.get(i).latLng;
            }
        }
        System.out.println("Dis shit Not found");
        return categoryList.get(0).latLng;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {
        Categories ci = categoryList.get(i);
        categoryViewHolder.vName.setText(ci.name);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cards, viewGroup, false);

        return new CategoryViewHolder(itemView);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected RelativeLayout relativeLayout;
        protected CardView cardView;
        protected LatLng latLng = new LatLng(37.7876, -122.3967);

        public CategoryViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            cardView = (CardView) v.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(v.getContext(),UserActivity.class);
                    latLng = findLatLng(vName.getText().toString());
                    intent.putExtra(EXTRA_MESSAGE_LATLNG,latLng);
                    intent.putExtra(EXTRA_MESSAGE_SERVICE,vName.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}