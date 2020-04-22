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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class historico extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Dialog myDialog;
    ListView listView;
    public static ArrayList<Historicos> historicosArrayList;
    private SyncData.MyAppAdapter myAppAdapter;
    private boolean sucess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDialog = new Dialog(this);
        setContentView(R.layout.historico);
        listView = (ListView) findViewById(R.id.lvhis);
        historicosArrayList = new ArrayList<Historicos>();

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

        SyncData syncData = new SyncData();
        syncData.execute();

    }

    private class SyncData extends AsyncTask<String, String, String>{
        String msg = "Internet/DB_Connection turn un error";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(historico.this, "Lista de reclusos", "A carregar...", true);
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (conn == null) {
                    sucess = false;
                } else {
                    String query = "Select Profile.name, Historico.acao, Historico.data, Historico.motivo, Historico.tipo from Historico inner join Profile  on Historico.scan = Profile.scan ;";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null) {
                        while (rs.next()) {
                            try {
                                historicosArrayList.add(new Historicos(rs.getString("name"), rs.getString("data"), rs.getString("acao"), rs.getString("tipo"), rs.getString("motivo")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        sucess = true;
                    } else {
                        msg = "No Data Found";
                        sucess = false;
                    }

                }
                     String query1 = "Select Recluse.name, Historico.acao, Historico.data, Historico.motivo, Historico.tipo from Historico inner join Recluse  on Historico.id_recluse = Recluse.numero_recluso ;";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query1);
                    if (rs != null) {
                        while (rs.next()) {
                            try {
                                historicosArrayList.add(new Historicos(rs.getString("name"), rs.getString("data"), rs.getString("acao"), rs.getString("tipo"), rs.getString("motivo")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        msg = "Found";
                        sucess = true;
                    } else {
                        msg = "No Data Found";
                        sucess = false;
                    }


            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                sucess = false;
            }
            return msg;
        }
        @Override
        protected void onPostExecute(String s) {
            progress.dismiss();
            Toast.makeText(historico.this, msg + "", Toast.LENGTH_LONG).show();
            if (sucess == false) {
            } else {
                try {
                    myAppAdapter = new MyAppAdapter(historicosArrayList, historico.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex) {

                }
            }
        }
        public class MyAppAdapter extends BaseAdapter {
            public class ViewHolder {
                TextView nome, acao, motivo, data, tipo ;


            }

            public List<Historicos> historicosList;

            public Context context;
            ArrayList<Historicos> arrayList;

            private MyAppAdapter(List<Historicos> apps, Context context) {
                this.historicosList = apps;
                this.context = context;
                arrayList = new ArrayList<Historicos>();
                arrayList.addAll(historicosList);

            }

            @Override
            public int getViewTypeCount() {
                return super.getViewTypeCount();
            }

            @Override
            public int getCount() {
                return historicosList.size();
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
                ViewHolder viewHolder = null;


                if (rowView == null) {
                    LayoutInflater inflater = getLayoutInflater();
                    rowView = inflater.inflate(R.layout.linhahist, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.nome = (TextView) rowView.findViewById(R.id.nomePessoa);
                    viewHolder.data = (TextView) rowView.findViewById(R.id.datahis);
                    viewHolder.acao = (TextView) rowView.findViewById(R.id.acao);
                    viewHolder.tipo = (TextView) rowView.findViewById(R.id.tipo);
                    viewHolder.motivo = (TextView) rowView.findViewById(R.id.motivo);
                    rowView.setTag(viewHolder);


                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.nome.setText(historicosList.get(position).getNomePessoa() + "");
                viewHolder.data.setText(historicosList.get(position).getData() + "");
                viewHolder.acao.setText(historicosList.get(position).getAcao());
                viewHolder.tipo.setText(historicosList.get(position).getTipo());
                viewHolder.motivo.setText(historicosList.get(position).getMotivo());
                return rowView;


            }
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
            Intent intent = new Intent(historico.this,inicio_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_hor) {
            TextView txtclose;
            Button listahor;
            Button meuhor;
            myDialog.setContentView(R.layout.horariospopup);
            txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
            listahor = (Button) myDialog.findViewById(R.id.listahor);
            meuhor = (Button) myDialog.findViewById(R.id.meuhor);
            listahor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, tabela_horario.class));
                }
            });
            meuhor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, horario_diretor.class));
                }
            });
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.show();
        }else if (id == R.id.nav_doc) {
            TextView txtclose;
            Button listarel;
            Button his;
            myDialog.setContentView(R.layout.relatoriospopup);
            txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
            listarel = (Button) myDialog.findViewById(R.id.listarel);
            his = (Button) myDialog.findViewById(R.id.his);
            listarel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, documentos_diretor.class));
                }
            });
            his.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, historico.class));
                }
            });
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.show();
        }else if (id == R.id.nav_entidades){
            TextView txtclose;
            Button listagem;
            Button registo;
            myDialog.setContentView(R.layout.entidadesinicio);
            txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
            listagem = (Button) myDialog.findViewById(R.id.listagem);
            registo = (Button) myDialog.findViewById(R.id.registo);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            listagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirEntidades(v);
                }
            });
            registo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, Main3Activity.class));
                }
            });
            myDialog.show();
        }else if (id == R.id.nav_reclusos){
            TextView txtclose;
            Button listarec;
            Button reg;
            myDialog.setContentView(R.layout.reclusospopup);
            txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
            listarec = (Button) myDialog.findViewById(R.id.listarec);
            reg = (Button) myDialog.findViewById(R.id.reg);
            listarec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, tabela_reclusos.class));
                }
            });
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(historico.this, Registar_Reclusos.class));
                }
            });
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.show();
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirEntidades(View v){
        TextView txtclose;
        Button guardas;
        Button psicologos;
        myDialog.setContentView(R.layout.entidadespopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        guardas = (Button) myDialog.findViewById(R.id.guardas);
        psicologos = (Button) myDialog.findViewById(R.id.psicologos);
        guardas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(historico.this, tabela_guarda.class));
            }
        });
        psicologos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(historico.this, tabela_psicologo.class));
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
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
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

}
