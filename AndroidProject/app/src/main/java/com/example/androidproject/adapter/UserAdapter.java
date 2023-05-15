package com.example.androidproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.holder.UserHolder;
import com.example.androidproject.interfaces.SelectUser;
import com.example.androidproject.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder>
{
    Context context;
    List<UserModel> userModels;
    SelectUser selectUser;

    public UserAdapter(Context context, List<UserModel> userModels, SelectUser selectUser) {
        this.context = context;
        this.userModels = userModels;
        this.selectUser = selectUser;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(context).inflate(R.layout.user_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, @SuppressLint("RecyclerView") int position)
    {
        UserModel  userModel = userModels.get(position);

        holder.name.setText(userModel.getName());
        holder.lastMsg.setText(userModel.getLastMsg());


        if(userModel.getUnseenMsg()==0)
        {
            //on rend le vue invisible
            holder.unseenMsg.setVisibility(View.GONE);
        }
        else
        {
            //on rend la vue visible
            holder.unseenMsg.setVisibility(View.VISIBLE);
            holder.unseenMsg.setText(userModel.getUnseenMsg()+"");
        }



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectUser.onItemClicked(userModels.get(position));
            }
        });

    }

    public void updateUser( List<UserModel> userModels)
    {
        this.userModels = userModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }
}
