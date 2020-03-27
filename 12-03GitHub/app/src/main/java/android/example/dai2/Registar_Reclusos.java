package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Registar_Reclusos extends AppCompatActivity {
    private ImageView imagem;
    private  final int GALERIA_IMAGENS = 1;
    private Button galeria;
    private final int PERMISSAO_REQUEST = 2;
    EditText nome, nascimento, piso, ala, doencas, entrada;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar__reclusos);
        nome = (EditText) findViewById(R.id.nomeRecluso);
        nascimento = (EditText) findViewById(R.id.nascimentoRecluso);
        piso = (EditText) findViewById(R.id.pisoRecluso);
        ala = (EditText) findViewById(R.id.alaRecluso);
        doencas = (EditText) findViewById(R.id.doencaRecluso);
        entrada = (EditText) findViewById(R.id.entradaRecluso);


        imagem = (ImageView)findViewById(R.id.imageView2);
        Button galeria = (Button)findViewById(R.id.button3);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Registar_Reclusos.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    } else {
                        ActivityCompat.requestPermissions(Registar_Reclusos.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                PERMISSAO_REQUEST);
                    }
                }
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(imagemGaleria);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// A permissão foi concedida. Pode continuar
            } else {
// A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
            return;
        }
    }

    public void btnAdicionarRecluso(View view){
        Send obj = new Send();
        obj.execute("");
    }

    private class Send extends AsyncTask<String,String,String> {
        String msg = "";
        String name = nome.getText().toString();
        String nasciment = nascimento.getText().toString();
        String pisoo = piso.getText().toString();
        String alaa = ala.getText().toString();
        String doenca = doencas.getText().toString();
        String entrad = entrada.getText().toString();



        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (conn == null){
                    msg = "Connection goes wrong";
                } else {
                     //String query = "INSERT INTO `Recluse` (`name`, `date_entry`, `date_left`) VALUES ('"+name+"', '"+entrad+"', '"+nasciment+"')";
                    String query = "INSERT INTO `Recluse` (`name`, `date_entry`, `birthday`, `floor`, `wing`, `disease`) VALUES ('"+name+"', '"+entrad+"', '"+nasciment+"', '"+pisoo+"', '"+alaa+"', '"+doenca+"');";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg = "Inserting Successfull!!!!";
                    System.out.println("ENTROU");

                }
                conn.close();
            } catch (Exception e){
                msg = "Connection goes wrong";
                e.printStackTrace();
            }
            return msg;
        }


    }

}
