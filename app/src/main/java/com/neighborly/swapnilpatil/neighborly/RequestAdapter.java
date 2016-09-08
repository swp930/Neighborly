package com.neighborly.swapnilpatil.neighborly;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<Requests> requestList;
    private static String serviceType;
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    public RequestAdapter(List<Requests> requestList, String word) {
        this.requestList = requestList;
        serviceType = word;
    }


    @Override
    public int getItemCount() {
        return requestList.size();
    }

    @Override
    public void onBindViewHolder(RequestViewHolder requestViewHolder, int i) {
        Requests ci = requestList.get(i);
        requestViewHolder.vName.setText(ci.name);
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.request_cards, viewGroup, false);
        return new RequestViewHolder(itemView);
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName, textView;
        protected CardView cardView;
        protected ImageView imageView;

        public RequestViewHolder(View v) {
            super(v);
            Intent intent = new Intent(v.getContext(),ProfileActivity.class);
            cardView = (CardView) v.findViewById(R.id.card_view2);
            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(v.getContext(),ProfileActivity.class);
                    String[] array = {"word1","word2"};
                    intent.putExtra(EXTRA_MESSAGE,array);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}
