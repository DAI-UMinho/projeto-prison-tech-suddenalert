package android.example.dai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class tabela_reclusos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static ArrayList<ClassListReclusos> itemArrayList;
    private SyncData.MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean sucess = false;
    Dialog myDialog, editarRec, progress, elimina;
    Button verDados;
    int posicao, id_recluso;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabela_reclusos);
        //ArrayList<Reclusos> reclusos = adicionarReclusos();
        // ArrayAdapter adapter = new ListarReclusos(this, reclusos);
        //   lista.setAdapter(adapter);
        verDados = (Button) findViewById(R.id.button4);
        myDialog = new Dialog(this);
        editarRec = new Dialog(this);
        progress = new Dialog(this);
        elimina = new Dialog(this);


        listView = (ListView) findViewById(R.id.lvRdir);
        itemArrayList = new ArrayList<ClassListReclusos>();

        final SyncData orderData = new SyncData();
        orderData.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) (listView.getItemAtPosition(position).toString());
                System.out.println(selectedFromList);


                int selectedItemPosition = listView.getItemAtPosition(position).hashCode();

                System.out.println(selectedItemPosition);

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDetalles = new Intent(view.getContext(), Call.Details.class);
                visorDetalles.putExtra("ala", itemArrayList.get(position).alaRec);
                visorDetalles.putExtra("doenca", itemArrayList.get(position).doencaRec);
                visorDetalles.putExtra("piso", itemArrayList.get(position).pisoRec);
            }
        });*/



          /*      listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(tabela_reclusos.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Dados")
                        .setMessage("Doenças:" + itemArrayList.get(which_item).doencaRec)
                        .setMessage("Ala:" + itemArrayList.get(which_item).alaRec)
                        .setMessage("Piso:" + itemArrayList.get(which_item).pisoRec);
                return true;
            }
        });*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





    /*private ArrayList<Reclusos> adicionarReclusos() {
        ArrayList<Reclusos> reclusos = new ArrayList<Reclusos>();
        Reclusos r = new Reclusos("João","A", "Demência", "1" , R.mipmap.preso1);
                reclusos.add(r);
        r = new Reclusos("Antonio","B", "Demência", "2", R.mipmap.preso2);
        reclusos.add(r);
        return reclusos;
    }*/
    /*public void entrarr (View v) {
        startActivity(new Intent(this, android.example.dai2.Registar_Reclusos.class));
    }*/

    private class SyncData extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Connection turn un error";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
             progress = ProgressDialog.show(tabela_reclusos.this, "Lista de reclusos", "A carregar...", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (conn == null) {
                    sucess = false;
                } else {
                    String query = "SELECT id_recluse, name, disease, wing, floor, photo, birthday FROM Recluse WHERE deleted like 0";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null) {
                        while (rs.next()) {
                            try {
                                itemArrayList.add(new ClassListReclusos(rs.getInt("id_recluse"), rs.getString("name"), rs.getString("disease"), rs.getString("wing"), rs.getString("floor"), rs.getString("photo"), rs.getString("birthday")));
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
            Toast.makeText(tabela_reclusos.this, msg + "", Toast.LENGTH_LONG).show();
            if (sucess == false) {
            } else {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, tabela_reclusos.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex) {

                }
            }
        }

        public class MyAppAdapter extends BaseAdapter {
            public class ViewHolder {
                TextView nome, pisoo, alaa, doencaa, nascimento;
                ImageView imageView;

            }

            public List<ClassListReclusos> recluseList;

            public Context context;
            ArrayList<ClassListReclusos> arrayList;

            private MyAppAdapter(List<ClassListReclusos> apps, Context context) {
                this.recluseList = apps;
                this.context = context;
                arrayList = new ArrayList<ClassListReclusos>();
                arrayList.addAll(recluseList);

            }

            @Override
            public int getViewTypeCount() {
                return super.getViewTypeCount();
            }

            @Override
            public int getCount() {
                return recluseList.size();
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
                    rowView = inflater.inflate(R.layout.linha, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.nome = (TextView) rowView.findViewById(R.id.nome);
                   /* viewHolder.doencaa = (TextView) rowView.findViewById(R.id.doencas1);
                    viewHolder.alaa = (TextView) rowView.findViewById(R.id.ala1);
                    viewHolder.pisoo = (TextView) rowView.findViewById(R.id.piso1);
                    viewHolder.nascimento = (TextView) rowView.findViewById(R.id.nascimento1);*/
                    viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imagem);
                    rowView.setTag(viewHolder);


                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.nome.setText(recluseList.get(position).getNomeRec() + "");
                /*viewHolder.doencaa.setText(recluseList.get(position).getDoencaRec() + "");
                viewHolder.alaa.setText(recluseList.get(position).getAlaRec() + "");
                viewHolder.pisoo.setText(recluseList.get(position).getPisoRec() + "");
                viewHolder.nascimento.setText(recluseList.get(position).getNascimento()+ "");*/
                Picasso.get().load(recluseList.get(position).getImg()).into(viewHolder.imageView);


                return rowView;


            }
        }

    }

    public void entrarRegRec(View v) {
        startActivity(new Intent(this, android.example.dai2.Registar_Reclusos.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            Intent intent = new Intent(tabela_reclusos.this,inicio_diretor.class);
            startActivity(intent);
        }else if (id == R.id.nav_hor) {

                    startActivity(new Intent(tabela_reclusos.this, tabela_horario.class));

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
                    startActivity(new Intent(tabela_reclusos.this, documentos_diretor.class));
                }
            });
            his.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(tabela_reclusos.this, historico.class));
                }
            });
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });
            myDialog.show();
        }else if (id == R.id.nav_perfil){
            Intent intent = new Intent(tabela_reclusos.this,perfil_diretor.class);
            startActivity(intent);
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
                    startActivity(new Intent(tabela_reclusos.this, Main3Activity.class));
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
                    startActivity(new Intent(tabela_reclusos.this, tabela_reclusos.class));
                }
            });
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(tabela_reclusos.this, Registar_Reclusos.class));
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
                startActivity(new Intent(tabela_reclusos.this, tabela_guarda.class));
            }
        });
        psicologos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(tabela_reclusos.this, tabela_psicologo.class));
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
    }
    public void verData(View view) {
        posicao = listView.getPositionForView(view);
        System.out.println(posicao);
        TextView txtclose;
        TextView doencas;
        TextView piso;
        TextView ala;
        TextView nascimento;
        myDialog.setContentView(R.layout.vermaisdirpopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        doencas = (TextView) myDialog.findViewById(R.id.doencas1);
        piso = (TextView) myDialog.findViewById(R.id.piso1);
        ala = (TextView) myDialog.findViewById(R.id.ala1);
        nascimento = (TextView) myDialog.findViewById(R.id.nascimento1);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        doencas.setText(itemArrayList.get(posicao).getDoencaRec());
        piso.setText(itemArrayList.get(posicao).getPisoRec());
        ala.setText(itemArrayList.get(posicao).getAlaRec());
        nascimento.setText(itemArrayList.get(posicao).getNascimento());
        id_recluso = itemArrayList.get(posicao).getId_recluse();
        System.out.println(id_recluso);
        myDialog.show();
    }

    public void alteraDados_rec(View view) {
        // posicao = listView.getPositionForView(view);
        TextView txtclose;
        ImageView txtAlterar;
        EditText nome;
        EditText doencas;
        EditText piso;
        EditText ala;
        editarRec.setContentView(R.layout.alterarpopup);
        txtclose = (TextView) editarRec.findViewById(R.id.txtclose);
        txtAlterar = (ImageView) editarRec.findViewById(R.id.imageView18);
        nome = (EditText) editarRec.findViewById(R.id.alteraNome);
        doencas = (EditText) editarRec.findViewById(R.id.alteraDoencas);
        piso = (EditText) editarRec.findViewById(R.id.alteraPiso);
        ala = (EditText) editarRec.findViewById(R.id.alteraAla);
        doencas.setText(itemArrayList.get(posicao).getDoencaRec());
        piso.setText(itemArrayList.get(posicao).getPisoRec());
        ala.setText(itemArrayList.get(posicao).getAlaRec());
        nome.setText(itemArrayList.get(posicao).getNomeRec());
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarRec.dismiss();
            }
        });
        txtAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlterarDadosRec alterarDadosRec = new AlterarDadosRec();
                alterarDadosRec.execute();
            guardar(v);            }
        });

        editarRec.show();
    }
    public void guardar(View v){
        TextView txtclose;
        progress.setContentView(R.layout.guardarpopup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                alert("Sucesso!!");
            }
        }, 4000);
        txtclose = (TextView) progress.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.dismiss();
            }
        });
        progress.show();
    }

    private class AlterarDadosRec extends AsyncTask<String, String, String> {
        String msg = "";
        EditText nome = (EditText) editarRec.findViewById(R.id.alteraNome);
        EditText doencas = (EditText) editarRec.findViewById(R.id.alteraDoencas);
        EditText piso = (EditText) editarRec.findViewById(R.id.alteraPiso);
        EditText ala = (EditText) editarRec.findViewById(R.id.alteraAla);
        String t_nome = nome.getText().toString().trim();
        String t_doencas = doencas.getText().toString().trim();
        String t_piso = piso.getText().toString().trim();
        String t_ala = ala.getText().toString().trim();

        @Override
        protected String doInBackground(String... strings) {
            try {
                // System.out.println(id_recluso);
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (connection == null) {
                    msg = "Não foi possível realizar connection";
                } else {
                    String query = "UPDATE Recluse SET name = '" + t_nome + "', floor = '" + t_piso + "', wing = '" + t_ala + "', disease = '" + t_doencas + "' WHERE id_recluse = '"+id_recluso+"';";
                    System.out.println(query);
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    msg = "Atualizado com sucesso";
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return msg;
        }
    }

public void eliminarRecluso (View view){
    TextView txtclose;
    ImageView eliminar;
    elimina.setContentView(R.layout.eliminar_r);
    txtclose = (TextView) elimina.findViewById(R.id.txtclose);
    eliminar = (ImageView) elimina.findViewById(R.id.imageView21);
    txtclose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            elimina.dismiss();
        }
    });
    eliminar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            eliminaRecluso(view);
        }
    });
    elimina.show();
}

    public void eliminaRecluso (View view) {
        EliminarRecluso eliminarRecluso = new EliminarRecluso();
        eliminarRecluso.execute();
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            System.out.println(e);
        }
        myDialog.dismiss();
        startActivity(new Intent(this, tabela_reclusos.class));
        tabela_reclusos.this.finish();
    }
    private class EliminarRecluso extends AsyncTask<String, String, String>{
        String msg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (connection == null){
                    msg = "Connect failed";
                } else {
                    System.out.println(posicao);
                    String query = "UPDATE Recluse SET deleted='1' where id_recluse = '"+ itemArrayList.get(posicao).getId_recluse()+ "'";
                    Statement preparedStatement = connection.createStatement();
                    System.out.println(query);
                    preparedStatement.executeUpdate(query);
                    System.out.println("1");
                }
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return msg;
        }
    }

}

