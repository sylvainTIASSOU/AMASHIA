package com.example.androidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.holder.MessageViewHolder;
import com.example.androidproject.memoryData.MemoryData;
import com.example.androidproject.model.ChatModel;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>
{
    Context context;
    List<ChatModel> messageList;
    String myMobile;

    public MessageAdapter(Context context, List<ChatModel> messageList) {
        this.context = context;
        this.messageList = messageList;
        this.myMobile = MemoryData.getMobile(context);

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.message_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position)
    {
        ChatModel model = messageList.get(position);


        if(model.getMobile().equals(myMobile))
        {
            holder.myLayout.setVisibility(View.VISIBLE);
            holder.commingLayout.setVisibility(View.GONE);

            holder.myMsg.setText(model.getMessage());
            holder.myMsgDate.setText(model.getDate()+" "+ model.getTime());
        }
        else {
            holder.myLayout.setVisibility(View.GONE);
            holder.commingLayout.setVisibility(View.VISIBLE);

            holder.commingMsg.setText(model.getMessage());
            holder.commingMsgDate.setText(model.getDate()+" "+ model.getTime());

        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public  void updateliste(List<ChatModel> chat)
    {
        this.messageList = chat;
    }
}
