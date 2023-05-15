package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidproject.adapter.DoctorListAdapter;
import com.example.androidproject.adapter.MessageAdapter;
import com.example.androidproject.memoryData.MemoryData;
import com.example.androidproject.model.ChatModel;
import com.example.androidproject.model.DoctorListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatPage extends AppCompatActivity
{
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ImageButton backbtn, btn_send;

    private TextView fullName;
    private EditText messageEdit;
    SimpleDateFormat simpleDateFormat, simpleTimeFormat;

    private  String chatKey;
    String userMobile ;
    String commingMobile;
    List<ChatModel> messageListe = new ArrayList<ChatModel>();
    String getMsg, getMobile;
    Date date;

    private  MessageAdapter messageAdapter;
    private boolean loadFirstTime = true;
    String name;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        //recuperation des donné envoyer la l'intention
         name = this.getIntent().getExtras().getString("name");
        commingMobile = this.getIntent().getExtras().getString("mobile");
        chatKey = this.getIntent().getExtras().getString("chatkey");

        userMobile = MemoryData.getMobile(this);

        //recuperation des vues
        fullName = (TextView) findViewById(R.id.fullName);
        backbtn = (ImageButton) findViewById(R.id.back_msg);
        messageEdit = (EditText) findViewById(R.id.edit_message);
        btn_send = (ImageButton)findViewById(R.id.btn_send);

        //modifier le nom
        fullName.setText(name);

        // association du buton au listener
        backbtn.setOnClickListener(backAction);
        btn_send.setOnClickListener(sendAction);

        //messageAdapter = new MessageAdapter(getApplicationContext(), messageListe);
        recyclerView = findViewById(R.id.recyclerView);
        //messageListe.add(new ChatModel("salut","21-02-2022","12:22","9854","sylvain"));
        messageAdapter = new MessageAdapter(getApplicationContext(), messageListe);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        //generate chat clé chatKey

        chageMessage();
    }
//enregistrement des messages
    View.OnClickListener sendAction = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            String message = messageEdit.getText().toString();
            String time = String.valueOf(System.currentTimeMillis()).substring(0,10);

            //MemoryData.saveLastMsg(time, chatKey, getApplicationContext());

            database.child("chat").child(chatKey).child("user_1").setValue(userMobile);
            database.child("chat").child(chatKey).child("user_2").setValue(commingMobile);

            database.child("chat").child(chatKey).child("message").child(time).child("msg").setValue(message);
            database.child("chat").child(chatKey).child("message").child(time).child("mobile").setValue(userMobile);

            messageEdit.setText("");
           chageMessage();
            //messageAdapter.updateliste(messageListe);

        }
    };

    View.OnClickListener backAction = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ChatPage.this, UserList.class);
            startActivity(intent);
            finish();
        }
    };

    public void  chageMessage()
    {
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (chatKey.isEmpty())
                {
                    chatKey="1";
                    if (snapshot.hasChild("chat"))
                    {
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount()+1);
                    }
                }


                if (snapshot.hasChild("chat"))
                {
                    if(snapshot.child("chat").child(chatKey).hasChild("message"))
                    {
                        messageListe.clear();

                        for (DataSnapshot messageSnapshot: snapshot.child("chat").child(chatKey).child("message").getChildren())
                        {
                            if(messageSnapshot.hasChild("msg") && messageSnapshot.hasChild("mobile"))
                            {
                                String messageTimeStamps = messageSnapshot.getKey();
                                getMobile = messageSnapshot.child("mobile").getValue(String.class);
                                getMsg = messageSnapshot.child("msg").getValue(String.class);

                                //generer la data
                                Timestamp timestamp = new Timestamp(Long.parseLong(messageTimeStamps));
                                date = new Date(timestamp.getTime());


                                simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

                                //messageEdit.setText("message1");
                                ChatModel chatModel = new ChatModel(getMsg, simpleDateFormat.format(date) ,simpleTimeFormat.format(date),getMobile, name);
                                messageListe.add(chatModel);

                                if(loadFirstTime || Long.parseLong(messageTimeStamps)> Long.parseLong(MemoryData.getLastMsg(getApplicationContext(), chatKey)))
                                {
                                    loadFirstTime = false;
                                    MemoryData.saveLastMsg(messageTimeStamps, chatKey, getApplicationContext());
                                    messageAdapter.updateliste(messageListe);
                                    recyclerView.scrollToPosition(messageListe.size()-1);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}