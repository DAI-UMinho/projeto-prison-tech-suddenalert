package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityPerfil extends AppCompatActivity {
    TextView localidade;
    TextView tipo, nome, nascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        localidade = (TextView) findViewById(R.id.localidaPerfil);
        tipo = (TextView) findViewById(R.id.tipoPerfil);
        nome = (TextView) findViewById(R.id.nomePerfil);
        nascimento = (TextView) findViewById(R.id.nascimentoPerfil);

    }

}
