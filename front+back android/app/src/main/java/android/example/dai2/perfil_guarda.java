package android.example.dai2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class perfil_guarda extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView localidade;
    TextView tipo, nome, nascimento, pontos;
     private String scan = MainActivity.scanValor;
     private String localizacao, name, typeUser, nascimento1;
    int tipo1, points;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_guarda);

        myDialog = new Dialog(this);
        localidade = (TextView) findViewById(R.id.textView14);
        tipo = (TextView) findViewById(R.id.textView12);
        nome = (TextView) findViewById(R.id.textView13);
        nascimento = (TextView) findViewById(R.id.datanascimento);
        pontos = (TextView) findViewById(R.id.textView9);


        CarregaDados carregaDados = new CarregaDados();
        carregaDados.execute();




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private class CarregaDados extends AsyncTask<String,String,String> {
        boolean success = false;
        String msg = "";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(perfil_guarda.this, "Synchronising", "Searching for user data...", true);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            localidade.setText(localizacao);
            tipo.setText(typeUser);
            nome.setText(name);
            nascimento.setText(nascimento1);
            pontos.setText(String.valueOf(points));
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (connection ==null){
                    success = false;
                } else {
                    String query = "SELECT location, id_type, name, birthday, points FROM Profile WHERE scan like '"+scan+"'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()){
                        System.out.println("Cegou");
                        localizacao = resultSet.getString("location");
                        System.out.println("Cegou1");
                        tipo1 = resultSet.getInt("id_type");
                        name = resultSet.getString("name");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        nascimento1 = dateFormat.format(resultSet.getDate("birthday"));
                        points = resultSet.getInt("points");
                        if(tipo1 <=  3 && tipo1 > 0){
                            String query1 = "SELECT type FROM Type WHERE id_type like '"+tipo1+"'";
                            Statement statement1 = connection.createStatement();
                            ResultSet resultSet1 = statement1.executeQuery(query1);
                            while (resultSet1.next()) {
                                typeUser = resultSet1.getString("type");
                                success = true;
                            }
                        } else {
                            success=false;
                            msg = "Não foi possivel encontrar dados do Utilizador";
                        }
                    }
                }
            } catch (SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
            return msg;
        }
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
        if (id == R.id.ajuda){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            Intent intent = new Intent(perfil_guarda.this,inicio_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_hor) {
            Intent intent = new Intent(perfil_guarda.this,horario_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_doc) {
            Intent intent = new Intent(perfil_guarda.this,tabela_meus_alertas.class);
            startActivity(intent);
        }else if (id == R.id.nav_perfil){
            Intent intent = new Intent(perfil_guarda.this,perfil_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_reclusos){
            Intent intent = new Intent(perfil_guarda.this,tabela_gua_reclusos.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
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
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    public void sair (View v) {

        startActivity(new Intent(this, MainActivity.class));
    }

}
