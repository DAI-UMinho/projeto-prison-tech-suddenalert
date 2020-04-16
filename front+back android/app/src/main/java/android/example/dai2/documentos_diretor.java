package android.example.dai2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class documentos_diretor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog myDialog;
    public static ArrayList<Documentos> documentosArrayList;
    private documentos_diretor.SyncDataDoc.MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean sucess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        setContentView(R.layout.documentos_diretor);
        listView = (ListView) findViewById(R.id.lvdoc);

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

        SyncDataDoc syncDataDoc = new SyncDataDoc();
        syncDataDoc.execute();

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
            Intent intent = new Intent(documentos_diretor.this,inicio_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_hor) {
            Intent intent = new Intent(documentos_diretor.this, horario_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_doc) {
            Intent intent = new Intent(documentos_diretor.this, documentos_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_perfil){
            Intent intent = new Intent(documentos_diretor.this,perfil_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_guardas){
            Intent intent = new Intent(documentos_diretor.this,tabela_guarda.class);
            startActivity(intent);
        }else if (id == R.id.nav_psicologos){
            Intent intent = new Intent(documentos_diretor.this,tabela_psicologo.class);
            startActivity(intent);
        }else if (id == R.id.nav_reclusos){
            Intent intent = new Intent(documentos_diretor.this, tabela_reclusos.class);
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
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    public void entrarhist (View v){
        startActivity(new Intent(this, android.example.dai2.historico.class));
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

    public void sair (View v) {

        startActivity(new Intent(this, MainActivity.class));
    }
    private class SyncDataDoc extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Connection turn un error";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(documentos_diretor.this, "Synchronising", "ListView Loading wait...", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                System.out.println("qq1ui");
                if (conn == null) {
                    sucess = false;
                } else {
                    String query = "Select  Report.idReport, Report.title, Profile.name, Report.date, Report.gravidade, Profile.email , Report.report from Report inner join Profile on Report.scan = Profile.scan;";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    System.out.println("ali");
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            try {
                                documentosArrayList.add(new Documentos(resultSet.getInt("idReport"), resultSet.getString("title"), resultSet.getString("name"), resultSet.getString("date"), resultSet.getString("gravidade"), resultSet.getString("email"), resultSet.getString("report")));
                                System.out.println("1");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        sucess = true;
                    } else {
                        msg = "Data not found";
                        sucess = false;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            Toast.makeText(documentos_diretor.this, msg + "", Toast.LENGTH_LONG).show();
            if (sucess == false) {
            } else {
                try {
                    myAppAdapter = new documentos_diretor.SyncDataDoc.MyAppAdapter(documentosArrayList, documentos_diretor.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex) {

                }
            }
        }
        public class MyAppAdapter extends BaseAdapter {
            public class ViewHolder {
                TextView titulo, id, data, email, gravidade;
            }

            public List<Documentos> reportList;

            public Context context;
            ArrayList<Documentos> arrayList;

            private  MyAppAdapter(List<Documentos> apps, Context context){
                this.reportList = apps;
                this.context = context;
                arrayList = new ArrayList<Documentos>();
                arrayList.addAll(reportList);
            }

            @Override
            public int getCount() {
                return reportList.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View rowView = convertView;
                documentos_diretor.SyncDataDoc.MyAppAdapter.ViewHolder viewHolder = null;
                if (rowView == null){
                    LayoutInflater inflater = getLayoutInflater();
                    rowView = inflater.inflate(R.layout.linhadoc, parent, false);
                    viewHolder = new documentos_diretor.SyncDataDoc.MyAppAdapter.ViewHolder();
                    viewHolder.titulo = (TextView) rowView.findViewById(R.id.nomeRel);
                    viewHolder.id = (TextView) rowView.findViewById(R.id.nomeEnt);
                    viewHolder.data = (TextView) rowView.findViewById(R.id.data);
                    viewHolder.email = (TextView) rowView.findViewById(R.id.email);
                    viewHolder.gravidade = (TextView) rowView.findViewById(R.id.gravidade);
                    rowView.setTag(viewHolder);
                } else {
                    viewHolder = (documentos_diretor.SyncDataDoc.MyAppAdapter.ViewHolder) convertView.getTag();
                }
                viewHolder.titulo.setText(reportList.get(position).getNomeRel());
                viewHolder.id.setText(reportList.get(position).getNomeEn());
                viewHolder.data.setText(reportList.get(position).getData());
                viewHolder.email.setText(reportList.get(position).getemail());
                viewHolder.gravidade.setText(reportList.get(position).getgravidade());
                return rowView;
            }
        }
    }
    public void lerRelatorio(View view){
        int posicao = listView.getPositionForView(view);
        TextView txtclose;
        TextView relatorio;
        myDialog.setContentView(R.layout.vermaispopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        relatorio = (TextView) myDialog.findViewById(R.id.); 
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        relatorio.setText(documentosArrayList.get(posicao).getRelatorio());
        myDialog.show();
    }

}
