package android.example.dai2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class fazer_documentos extends AppCompatActivity {
    private EditText identificacao, nome, gravidade, relatorioo, tituloRel;
    private String t_identific,t_nome, t_gravidade, t_relatorio, t_titulo;
    private boolean sucess = false, prenchido = true;
    Button regRelat;
    private int id_alerta = tabela_alert.id_alert;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fazer_documento);
       // t_identific = tabela_alert.numeroRecluso
        identificacao = (EditText) findViewById(R.id.numeroRecl);
        relatorioo = (EditText) findViewById(R.id.relatorio);
        tituloRel = (EditText) findViewById(R.id.tituloRel);
        nome = (EditText) findViewById(R.id.identificacao);
        gravidade = (EditText) findViewById(R.id.gravidaderelatorio);
        regRelat = (Button) findViewById(R.id.submeter);
        regRelat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        nome.setText(tabela_alert.nomeRecluso);
        identificacao.setText(String.valueOf(tabela_alert.numeroRecluso));
    }
    public void register(){
        intialize();
        if (!validate()){
            Toast.makeText(this, "Campos em falta!", Toast.LENGTH_SHORT).show();
        } else {
            CriarRelatorio criarRelatorio = new CriarRelatorio();
            criarRelatorio.execute();

            try {
                Thread.sleep(1000);
            }
            catch (Exception e){
                System.out.print("erro");
            }

            if (sucess == true) {
                Toast.makeText(this, "Relatório criado com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, android.example.dai2.Main2Activity.class));

            } else {
                Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  boolean validate(){
        boolean valid = true;
        if (t_gravidade.isEmpty()){
            gravidade.setError("Introduz a identificação");
            valid = false;
        }
        if (t_relatorio.isEmpty()){
            relatorioo.setError("Introduza um relatorio");
            valid = false;
        }
        if (t_titulo.isEmpty()){
            tituloRel.setError("Introduz um titulo");
            valid = false;
        }
        return valid;
    }
    public void intialize(){
        t_gravidade = gravidade.getText().toString().trim();
        t_relatorio = relatorioo.getText().toString().trim();
        t_titulo = tituloRel.getText().toString().trim();
    }
    private class CriarRelatorio extends AsyncTask<String, String, String>{
        String msg = "";
        String scan = MainActivity.scanValor;
        //String identificacao1 = identificacao.getText().toString();
        // String relatorio = relatorioo.getText().toString();
        int valor = 0;



        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (connection == null){
                    sucess = false;
                    msg = "Não foi possível realizar connection";
                } else {
                    String query1 = "SELECT id_recluse FROM Recluse WHERE numero_recluso like '" + tabela_alert.numeroRecluso + "';";
                    Statement statement1 = connection.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(query1);
                    while (resultSet1.next()) {
                        valor = resultSet1.getInt("id_recluse");

                        System.out.println(valor);

                    }
                    String query = "INSERT INTO Report (`report`, `scan`, `id_recluse`, `title`, `gravidade`, `id_alertsituation`) VALUES ('" + t_relatorio + "', '" + scan + "', '" + valor + "', '" + t_titulo + "', '" + t_gravidade + "', '" + id_alerta + "');";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);

                    String query2 = "UPDATE AlertSituation SET relatorio ='1' WHERE id_alertsituation='" + id_alerta + "'";
                    Statement statement2 = connection.createStatement();
                    statement2.executeUpdate(query2);
                    msg = "Inserido com sucesso";
                    sucess = true;

                }

                connection.close();
            } catch (Exception e){
                msg = "Connection correu mal";
                sucess = false;
            }
            return msg;
        }
    }
}