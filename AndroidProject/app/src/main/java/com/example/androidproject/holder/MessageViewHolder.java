package com.example.androidproject.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

public class MessageViewHolder extends RecyclerView.ViewHolder
{
     public  TextView commingMsg, myMsg, commingMsgDate, myMsgDate;
     public LinearLayout myLayout, commingLayout;

    public MessageViewHolder(@NonNull View itemView)
    {
        super(itemView);

        commingMsg = itemView.findViewById(R.id.commingMsg);
        myMsg  = itemView.findViewById(R.id.myMsg);
        commingMsgDate = itemView.findViewById(R.id.commingMsgDate);
        myMsgDate  = itemView.findViewById(R.id.myMsgDate);
        commingLayout = itemView.findViewById(R.id.commingLayout);
        myLayout = itemView.findViewById(R.id.myLayout);
    }

}
