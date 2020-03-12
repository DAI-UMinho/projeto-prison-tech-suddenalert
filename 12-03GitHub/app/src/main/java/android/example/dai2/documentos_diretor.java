package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class documentos_diretor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_diretor);
        ListView lista = (ListView) findViewById(R.id.lvdoc);
        ArrayList<Documentos> documentos = adicionarDocumentos();
        ArrayAdapter adapter = new ListarDocumentos(this, documentos);
        lista.setAdapter(adapter);
    }

    private ArrayList<Documentos> adicionarDocumentos() {
        ArrayList<Documentos> documentos = new ArrayList<Documentos>();
        Documentos d = new Documentos("Incidente","Joao", "24/10/2019");
        documentos.add(d);
        d = new Documentos("Normal","Antonio","13/1/2020");
        documentos.add(d);
        return documentos;
    }
}
