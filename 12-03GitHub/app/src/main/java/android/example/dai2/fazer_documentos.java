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
    private EditText identificacao, relatorioo;
    private String t_identific, t_relatorio;
    private boolean sucess = false, prenchido = true;
    Button regRelat;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_documentos);
        identificacao = (EditText) findViewById(R.id.identificacao);
        relatorioo = (EditText) findViewById(R.id.relatorio);
        regRelat = (Button) findViewById(R.id.button7);
        regRelat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
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
        if (t_identific.isEmpty()){
            identificacao.setError("Introduz a identificação");
            valid = false;
        }
        if (t_relatorio.isEmpty()){
            relatorioo.setError("Introduza um relatorio");
            valid = false;
        }
        return valid;
    }
    public void intialize(){
        t_identific = identificacao.getText().toString().trim();
        t_relatorio = relatorioo.getText().toString().trim();
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
                    //if (prenchido == true) {
                    String query1 = "SELECT COUNT(1) FROM Recluse WHERE id_recluse like '" + t_identific + "';";
                    Statement statement1 = connection.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(query1);
                    while (resultSet1.next()) {
                        valor = resultSet1.getInt("COUNT(1)");

                        System.out.println(valor);

                    }
                    if (valor == 1 ) {
                        String query = "INSERT INTO Report (`report`, `scan`, `id_recluse`) VALUES ('" + t_relatorio + "', '" + scan + "', '" + t_identific + "');";
                        Statement statement = connection.createStatement();
                        statement.executeUpdate(query);
                        msg = "Inserido com sucesso";
                        sucess = true;
                        // prenchido = true;
                        System.out.println(sucess);
                        //  System.out.println(prenchido);

                    } else {
                        sucess = false;
                        msg = "Identificação de Recluso inválida!";
                    }
                    //   }

                      //  System.out.println(valor);

                        //   }
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