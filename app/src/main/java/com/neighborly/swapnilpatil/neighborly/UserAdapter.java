package com.neighborly.swapnilpatil.neighborly;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Users> userList;
    private static String serviceType;
    public final static String EXTRA_MESSAGE = "com.neighborly.swapnilpatil.neighborly.MESSAGE";
    public UserAdapter(List<Users> userList, String word) {
        this.userList = userList;
        serviceType = word;
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        Users ci = userList.get(i);
        userViewHolder.vName.setText(ci.name);
        //categoryViewHolder.vSurname.setText(ci.surname);
        //categoryViewHolder.vEmail.setText(ci.email);
        //categoryViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.user_cards, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName, textView;
        protected CardView cardView;
        //protected TextView vSurname;
        //protected TextView vEmail;
        //protected TextView vTitle;

        public UserViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            cardView = (CardView) v.findViewById(R.id.card_view);
            //textView = (TextView) v.findViewById(R.id.textView);
            //relativeLayout = (RelativeLayout) v.findViewById(R.id.cardList);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(v.getContext(),ProfileActivity.class);
                    String[] words = {vName.getText().toString(),serviceType};
                    intent.putExtra(EXTRA_MESSAGE,words);
                    v.getContext().startActivity(intent);
                    Log.v("UserAdapter", "Success");
                }
            });
            //vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            //vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            //vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}
