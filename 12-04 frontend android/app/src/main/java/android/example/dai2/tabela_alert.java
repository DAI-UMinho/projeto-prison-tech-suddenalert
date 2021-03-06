package android.example.dai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class tabela_alert extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.alerta_psicologo);
        ListView lista = (ListView) findViewById(R.id.alertas);
        ArrayList<AlertSituation> alerts = adicionarAlerts();
        ArrayAdapter adapter = new AlertAdapter(this, alerts);
        lista.setAdapter(adapter);
    }

    private ArrayList<AlertSituation> adicionarAlerts() {
        ArrayList<AlertSituation> alerts = new ArrayList<AlertSituation>();
        AlertSituation a = new AlertSituation("Médio", "Este recluso encontra-se instável.");
        alerts.add(a);
        a = new AlertSituation("Médio", "Este recluso encontra-se deprimido.");
        alerts.add(a);
        a = new AlertSituation("Alto", "Este recluso encontra-se deprimido e com atitudes violentas.");
        alerts.add(a);
        return alerts;
    }
    public void aceitar (View v) {
        startActivity(new Intent(this, android.example.dai2.fazer_documentos.class));
    }

}
