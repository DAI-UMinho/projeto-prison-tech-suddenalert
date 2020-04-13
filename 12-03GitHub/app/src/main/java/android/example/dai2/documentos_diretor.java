package android.example.dai2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class documentos_diretor extends AppCompatActivity {
    public static  ArrayList<ClassListDocumentos> itemArrayList;
    private documentos_diretor.SyncDataDoc.MyAppAdapter myAppAdapter;
    private ListView listView;
    private boolean sucess = false;
    Dialog verRelatorio, classificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_diretor);
        verRelatorio = new Dialog(this);
        classificar = new Dialog(this);

        listView = (ListView) findViewById(R.id.lvdoc);
        itemArrayList = new ArrayList<ClassListDocumentos>();

        System.out.println("Aqui");
        final documentos_diretor.SyncDataDoc orderData = new documentos_diretor.SyncDataDoc();
        orderData.execute();
        System.out.println("123");
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
                    String query = "SELECT idReport, report, scan, date, id_recluse, title FROM Report";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    System.out.println("ali");
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            try {
                                itemArrayList.add(new ClassListDocumentos(resultSet.getInt("idReport"), resultSet.getString("scan"), resultSet.getString("report"), resultSet.getString("date"), resultSet.getString("id_recluse"), resultSet.getString("title")));
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
                    myAppAdapter = new documentos_diretor.SyncDataDoc.MyAppAdapter(itemArrayList, documentos_diretor.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex) {

                }
            }
        }
        public class MyAppAdapter extends BaseAdapter {
            public class ViewHolder {
                TextView titulo, id, data;
            }

            public List<ClassListDocumentos> reportList;

            public Context context;
            ArrayList<ClassListDocumentos> arrayList;

            private  MyAppAdapter(List<ClassListDocumentos> apps, Context context){
                this.reportList = apps;
                this.context = context;
                arrayList = new ArrayList<ClassListDocumentos>();
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
                    viewHolder.titulo = (TextView) rowView.findViewById(R.id.nomeRe);
                    viewHolder.id = (TextView) rowView.findViewById(R.id.nomeEnt);
                    viewHolder.data = (TextView) rowView.findViewById(R.id.data);
                    rowView.setTag(viewHolder);
                } else {
                    viewHolder = (documentos_diretor.SyncDataDoc.MyAppAdapter.ViewHolder) convertView.getTag();
                }
                viewHolder.titulo.setText(reportList.get(position).getTitulo());
                viewHolder.id.setText(reportList.get(position).getId_recluse());
                viewHolder.data.setText(reportList.get(position).getData());
                return rowView;
            }
        }
    }
    /*public void lerRelatorio(View view){
        int posicao = listView.getPositionForView(view);
        TextView txtclose, relatorio;
        verRelatorio.setContentView(R.layout.);
        txtclose = (TextView) verRelatorio.findViewById(R.id);
        relatorio = (TextView) verRelatorio.findViewById(R.id);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verRelatorio.dismiss();
            }
        });
        relatorio.setText(itemArrayList.get(posicao).getReport());
        verRelatorio.show();
    }*/
}
