package com.example.androidproject.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

public class UserHolder extends RecyclerView.ViewHolder
{
    public TextView name, unseenMsg, lastMsg;
    public CardView card;
    public UserHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.user_name);
        card = itemView.findViewById(R.id.card_user);
        unseenMsg = itemView.findViewById(R.id.unSeenmsg);
        lastMsg = itemView.findViewById(R.id.lastMsg);


    }
}
