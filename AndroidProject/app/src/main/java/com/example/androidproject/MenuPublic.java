package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuPublic extends AppCompatActivity
{
    private ImageButton closeBtn;
    private DrawerLayout layout;
    private CardView chatBox;
    public String name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_public);

        //name = getIntent().getExtras().getString("nameDoctor");

        chatBox = (CardView) findViewById(R.id.chatBoxPublic);
        //closeBtn = (ImageButton) findViewById(R.id.btn_close_menu);
        layout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //Mise en place de l'icone

        chatBox.setOnClickListener(actionChatBox);

       /* closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.openDrawer(Gravity.NO_GRAVITY);
            }
        });*/

    }

    /*
    * if we clik on chatbox
    * */

    View.OnClickListener actionChatBox = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuPublic.this, UserList.class);
            startActivity(intent);

        }
    };






   /* public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }

    //gestion du click sur l'icon du menu

    public boolean onOptionsItemSelected(MenuItem item)
    {


        switch (item.getItemId())
        {
            case R.id.account_menu:
                layout.openDrawer(Gravity.RIGHT);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }*/
}