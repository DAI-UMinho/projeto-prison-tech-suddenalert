package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class fazer_documentos extends AppCompatActivity {
    private  EditText identificacao, relatorioo;
    private boolean sucess = false, prenchido = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_documentos);
        identificacao = (EditText) findViewById(R.id.identificacao);
        relatorioo = (EditText) findViewById(R.id.relatorio);

    }

    public void btnCriarRelatorio(View view){
        CriarRelatorio criarRelatorio = new CriarRelatorio();
        criarRelatorio.execute();
        if (sucess == true && prenchido == true){
            Toast.makeText(getApplicationContext(), "Relatório Inserido com Sucesso!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, android.example.dai2.Main2Activity.class));
        } else {
           // if (prenchido == false) {
                Toast.makeText(getApplicationContext(), "É necessário preencher todos os campos!", Toast.LENGTH_LONG).show();
           /* } else {
                Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, android.example.dai2.fazer_documentos.class));
            }*/
        }

    }
    private class CriarRelatorio extends AsyncTask<String, String, String>{
        String msg = "";
        String scan = MainActivity.scanValor;
        String identificacao1 = identificacao.getText().toString();
        String relatorio = relatorioo.getText().toString();
        int valor;



        @Override
        protected String doInBackground(String... strings) {
            try {
                if (identificacao1.equals("") || relatorio.equals("")) {
                    prenchido = false;
                }

                System.out.println(relatorio);
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (connection == null){
                    sucess = false;
                    msg = "Não foi possível realizar connection";
                } else {
                    if (prenchido == true) {
                        String query1 = "SELECT COUNT(1) FROM Recluse WHERE id_recluse like '" + identificacao1 + "';";
                        Statement statement1 = connection.createStatement();
                        ResultSet resultSet1 = statement1.executeQuery(query1);
                        System.out.println("ficou aqui");
                        while (resultSet1.next()) {
                            valor = resultSet1.getInt("COUNT(1)");
                            System.out.println(valor);
                        }
                        if (valor > 0 && valor < 2) {
                            System.out.println("ainda nao");
                            String query = "INSERT INTO Report (`report`, `scan`, `id_recluse`) VALUES ('" + relatorio + "', '" + scan + "', '" + identificacao1 + "');";
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);
                            System.out.println("inserido");
                            msg = "Inserido com sucesso";
                            // Toast.makeText(getApplicationContext(), "Criado com Sucesso!", Toast.LENGTH_SHORT);
                            sucess = true;
                            prenchido = true;
                        } else {
                            sucess = false;
                            msg = "Identificação de Recluso inválida!";
                            System.out.println("CONA");
                            //  Toast.makeText(getApplicationContext(), "Erro ao identificar Recluso!", Toast.LENGTH_SHORT);
                        }
                    }
                }

                connection.close();
            } catch (Exception e){
                msg = "Connection correu mal";
                //Toast.makeText(getApplicationContext(), "Não foi possível comunicar com a BD!", Toast.LENGTH_SHORT);
                sucess = false;
            }
            return msg;
        }
    }
}
