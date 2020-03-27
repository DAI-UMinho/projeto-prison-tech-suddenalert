package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class tabela_reclusos extends AppCompatActivity {
    private ArrayList<ClassListReclusos> itemArrayList;
    private SyncData.MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean sucess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_reclusos);
        //ArrayList<Reclusos> reclusos = adicionarReclusos();
       // ArrayAdapter adapter = new ListarReclusos(this, reclusos);
     //   lista.setAdapter(adapter);

        listView = (ListView) findViewById(R.id.lvR);
        itemArrayList = new ArrayList<ClassListReclusos>();

        SyncData orderData = new SyncData();
        orderData.execute();
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
            progress = ProgressDialog.show(tabela_reclusos.this, "Synchronising", "ListView Loading wait...", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if(conn == null){
                    sucess = false;
                } else {
                    String query = "SELECT name, disease, wing, floor, photo FROM Recluse";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs != null){
                        while (rs.next()){
                            try {
                                itemArrayList.add(new ClassListReclusos(rs.getString("name"), rs.getString("disease"), rs.getString("wing"), rs.getString("floor"), rs.getString("photo")));
                            } catch (Exception ex){
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
            if (sucess == false){
            }else {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, tabela_reclusos.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                }catch (Exception ex){

                }
            }
        }
        public class MyAppAdapter extends BaseAdapter{
            public class ViewHolder{
                TextView nome, pisoo, alaa, doencaa;
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
                if (rowView == null){
                    LayoutInflater inflater = getLayoutInflater();
                    rowView = inflater.inflate(R.layout.linha, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.nome = (TextView) rowView.findViewById(R.id.nomeLRec);
                    viewHolder.doencaa = (TextView) rowView.findViewById(R.id.doencasLRec);
                    viewHolder.alaa = (TextView) rowView.findViewById(R.id.alaLRec);
                    viewHolder.pisoo = (TextView) rowView.findViewById(R.id.pisoLRec);
                    viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imagemRec);
                    rowView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.nome.setText(recluseList.get(position).getNomeRec() + "");
                viewHolder.doencaa.setText(recluseList.get(position).getDoencaRec() + "");
                viewHolder.alaa.setText(recluseList.get(position).getAlaRec() + "");
                viewHolder.pisoo.setText(recluseList.get(position).getPisoRec() + "");
                Picasso.get().load(recluseList.get(position).getImg()).into(viewHolder.imageView);

                return rowView;

            }
        }
    }
    public void entrarRegRec (View v) {
        startActivity(new Intent(this, android.example.dai2.Registar_Reclusos.class));
    }
}
