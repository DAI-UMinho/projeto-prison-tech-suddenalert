package android.example.dai2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class documentos_guarda extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        setContentView(R.layout.documentos_entidades);
        ListView lista = (ListView) findViewById(R.id.lvdoc);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            Intent intent = new Intent(documentos_guarda.this,inicio_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_hor) {
            Intent intent = new Intent(documentos_guarda.this,horario_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_doc) {
            Intent intent = new Intent(documentos_guarda.this, tabela_meus_alertas.class);
            startActivity(intent);
        }else if (id == R.id.nav_perfil){
            Intent intent = new Intent(documentos_guarda.this, perfil_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_reclusos){
            Intent intent = new Intent(documentos_guarda.this,tabela_gua_reclusos.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


   /* public void ShowPopup3(View v){
        TextView txtclose;
        TextView pontos;
        TextView imprimir;
        TextView ala;
        ImageView imp;
        final RatingBar estrelas;
        ImageView submit;
        myDialog.setContentView(R.layout.acoesdoc);
        imp = (ImageView) myDialog.findViewById(R.id.imp);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        submit = (ImageView) myDialog.findViewById(R.id.check);
        pontos = (TextView) myDialog.findViewById(R.id.pontos);
        imprimir = (TextView) myDialog.findViewById(R.id.imprimir);
        estrelas = (RatingBar) myDialog.findViewById(R.id.ratingBar);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //manda imprimir o relatorio
            }
        });
       estrelas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
           @Override
           public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
               Toast.makeText(getApplicationContext(), String.valueOf(rating),Toast.LENGTH_LONG).show();
           }
       });

      submit.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               //guardar o raking
               alert("Pontos atribuídos:" + String.valueOf(estrelas.getRating()));
           }
       });
        myDialog.show();
    }*/
   public void ShowPopup(View v){
       TextView txtclose;
       Button btnSim;
       Button btnNao;
       myDialog.setContentView(R.layout.exitpopup);
       txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
       btnNao = (Button) myDialog.findViewById(R.id.btnNao);
       btnSim = (Button) myDialog.findViewById(R.id.btnSim);
       txtclose.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myDialog.dismiss();
           }
       });
       btnNao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myDialog.dismiss();
           }
       });
       btnSim.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sair(v);
               alert("Sessão terminada");
           }
       });
       myDialog.show();
   }

    public void sair (View v) {

        startActivity(new Intent(this, MainActivity.class));
    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
