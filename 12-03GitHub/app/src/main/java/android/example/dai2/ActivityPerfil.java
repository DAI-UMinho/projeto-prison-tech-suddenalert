package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ActivityPerfil extends AppCompatActivity {
    TextView localidade;
    TextView tipo, nome, nascimento, pontos;
   private String scan = MainActivity.scanValor;
     private String localizacao, name, typeUser, nascimento1;
    int tipo1, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        localidade = (TextView) findViewById(R.id.localidaPerfil);
        tipo = (TextView) findViewById(R.id.tipoPerfil);
        nome = (TextView) findViewById(R.id.nomePerfil);
        nascimento = (TextView) findViewById(R.id.nascimentoPerfil);
        pontos = (TextView) findViewById(R.id.pontosPerfil);

        CarregaDados carregaDados = new CarregaDados();
        carregaDados.execute();

        System.out.println(typeUser);

        /*localidade.setText(localizacao);
        tipo.setText(typeUser);
        nome.setText(name);
        nascimento.setText(nascimento1);
       // pontos.setText(points);*/
    }

    private class CarregaDados extends AsyncTask<String,String,String> {
        boolean success = false;
        String msg = "";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ActivityPerfil.this, "Synchronising", "Searching for user data...", true);
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

}
