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

public class MenuDoctor extends AppCompatActivity
{
    private DrawerLayout layout;
    private CardView chatBox, rendez_vous;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_doctor);

        chatBox = (CardView) findViewById(R.id.chatBoxDoctor);
        rendez_vous = (CardView) findViewById(R.id.rendez_vous);
        layout = (DrawerLayout) findViewById(R.id.drawerLayoutMedical);

        chatBox.setOnClickListener(actionChatBox);
        rendez_vous.setOnClickListener(actionRendezVous);
    }


    /*
     * if we clik on chatbox
     * */

    View.OnClickListener actionChatBox = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuDoctor.this, UserList.class);
            startActivity(intent);

        }
    };

    View.OnClickListener actionRendezVous = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MenuDoctor.this, ProgramerRendezVous.class);
            startActivity(intent);
        }
    };



// Affichage de l'icon sur le menu de la page
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