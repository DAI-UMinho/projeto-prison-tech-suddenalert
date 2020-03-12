package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class tabela_reclusos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_reclusos);
        ListView lista = (ListView) findViewById(R.id.lvR);
        ArrayList<Reclusos> reclusos = adicionarReclusos();
        ArrayAdapter adapter = new ListarReclusos(this, reclusos);
        lista.setAdapter(adapter);
    }

    private ArrayList<Reclusos> adicionarReclusos() {
        ArrayList<Reclusos> reclusos = new ArrayList<Reclusos>();
        Reclusos r = new Reclusos("João","A", "Demência", "1" , R.mipmap.preso1);
                reclusos.add(r);
        r = new Reclusos("Antonio","B", "Demência", "2", R.mipmap.preso2);
        reclusos.add(r);
        return reclusos;
    }
    public void entrarr (View v) {
        startActivity(new Intent(this, android.example.dai2.Registar_Reclusos.class));
    }
}
