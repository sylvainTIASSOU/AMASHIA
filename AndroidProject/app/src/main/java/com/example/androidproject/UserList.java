package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidproject.adapter.UserAdapter;
import com.example.androidproject.interfaces.SelectUser;
import com.example.androidproject.memoryData.MemoryData;
import com.example.androidproject.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserList extends AppCompatActivity implements SelectUser
{
    private RecyclerView recyclerView;
    String number;
    String chatKey;
    List<UserModel> userModels = new ArrayList<>();
    String name;
    UserAdapter userAdapter;
    int unseenMsg = 0;
    String lasMsg = "";
    private boolean dataset = false;


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView = (RecyclerView) findViewById(R.id.user_recycler);

        userAdapter = new UserAdapter(getApplicationContext(),userModels, UserList.this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       // recyclerView.setAdapter(new UserAdapter(getApplicationContext(), userModels, this));

        ProgressDialog prog = new ProgressDialog(this);
        prog.setCancelable(false);
        prog.setMessage("chargement...");
        prog.show();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                userModels.clear();
                unseenMsg =0;
                lasMsg = "";
                chatKey ="";
                for (DataSnapshot dataSnapshot: snapshot.child("user").getChildren())
                {

                    String userMode = dataSnapshot.child("userMode").getValue(String.class);
                    number = dataSnapshot.getKey();
                    dataset = false;

                        //on recuper la liste des patient
                        if(!number.equals(MemoryData.getMobile(UserList.this)) && !userMode.equals(MemoryData.getUser(UserList.this)))
                        {

                            name = dataSnapshot.child("name").getValue(String.class);

                            prog.dismiss();

                            database.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot1)
                                {
                                    int getChatCount = (int) snapshot1.getChildrenCount();

                                    if(getChatCount > 0)
                                    {
                                        for (DataSnapshot dataSnapshot1 : snapshot1.getChildren())
                                        {
                                            String chatKey1 = dataSnapshot1.getKey();
                                            chatKey = chatKey1;

                                            if (dataSnapshot1.hasChild("user_1") && dataSnapshot1.hasChild("user_2") && dataSnapshot1.hasChild("message"))
                                            {
                                                String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                                String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                                if ((getUserOne.equals(number) || getUserTwo.equals(MemoryData.getMobile(getApplicationContext()))) && (getUserOne.equals(MemoryData.getMobile(getApplicationContext())) || getUserTwo.equals(number)))
                                                {
                                                    //recupération de la derniere message
                                                    for (DataSnapshot chatSnapshot : dataSnapshot1.child("message").getChildren())
                                                    {
                                                        Long getMsgKey = Long.valueOf(chatSnapshot.getKey());
                                                        Long getLastSeenMsg = Long.valueOf(MemoryData.getLastMsg(getApplicationContext(), chatKey1));

                                                        lasMsg = chatSnapshot.child("msg").getValue(String.class);
                                                        if (getMsgKey > getLastSeenMsg)
                                                        {
                                                            //on increment
                                                            unseenMsg ++;
                                                        }
                                                    }

                                                }
                                            }

                                            //recuperation des utilisateur

                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    prog.dismiss();
                                }
                            });

                            if (!dataset)
                            {
                                dataset=true;
                                UserModel model = new UserModel(name, number, chatKey, unseenMsg, lasMsg);
                                userModels.add(model);
                                userAdapter.updateUser(userModels);
                            }



                        }

               /*     if(MemoryData.getUser(UserList.this).equals("public"))
                    {
                        number = dataSnapshot.child("user").getKey();
                        //on recupert la liste des docteur
                        if(!number.equals(MemoryData.getMobile(UserList.this)) && !userMode.equals(MemoryData.getUser(UserList.this)))
                        {
                            name = dataSnapshot.child("name").getValue(String.class);
                            database.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot1)
                                {
                                    int getChatCount = (int) snapshot1.getChildrenCount();

                                    if(getChatCount > 0)
                                    {
                                        for (DataSnapshot dataSnapshot1 : snapshot1.getChildren())
                                        {
                                            String chatKey1 = dataSnapshot1.getKey();
                                            chatKey = chatKey1;
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            //prog.dismiss();
                            UserModel model = new UserModel(name,number, chatKey);
                            userModels.add(model);
                            userAdapter.updateUser(userModels);
                            recyclerView.setAdapter(userAdapter);

                        }
                    }*/

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //prog.dismiss();
            }
        });
    }

    @Override
    public void onItemClicked(UserModel userModel)
    {
        String nom = userModel.getName();
        String numero = userModel.getMobile();
        String key = userModel.getChatKey();
        Toast.makeText(this, nom+" "+numero, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), ChatPage.class);
        intent.putExtra("name", nom);
        intent.putExtra("mobile", numero);
        intent.putExtra("chatkey", key);


        startActivity(intent);
    }
}