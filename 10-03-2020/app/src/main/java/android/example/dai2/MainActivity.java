package android.example.dai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Button btnScan;
    private Button btnGetLocation;
    private TextView txLocation;
    private final int GPS_REQUEST = 100;
    private LocationManager locationManager;
    private TextView dist;
    private ImageView imageView;
    int[] imagens ={R.mipmap.aceite};
    int counter = 3;
    private Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan = (Button) findViewById(R.id.btnScan);
        final Activity activity = this;
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan Camara");
                integrator.setCameraId(0);
                integrator.initiateScan();
            }


        });
        btnGetLocation = findViewById(R.id.btnGetLocation);
        txLocation = findViewById(R.id.txLocation);
        dist = findViewById(R.id.dist);
        imageView = (ImageView) findViewById((R.id.imageView12));

        btnEntrar = (Button) findViewById(R.id.btnEntrar);




        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, GPS_REQUEST);
                } else {
                    getLocation();
                }
            }
        });
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnGetLocation.getText().toString().equals(true) &&
                        btnScan.getText().toString().equals(true)) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    counter--;

                    if (counter == 0) {
                        btnEntrar.setEnabled(false);
                    }
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                alert(result.getContents());
         /*       AlertDialog.Builder construtorAlerta;
                construtorAlerta = new AlertDialog.Builder(this);
                construtorAlerta.setTitle("O seu nome é...");
                construtorAlerta.setMessage(result.getContents());
                AlertDialog meuAlerta = construtorAlerta.create();
                meuAlerta.show();*/
            }else{
                alert("Scan Cancelado");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case GPS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getLocation() {
        try{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

             locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        txLocation.setText("Lat: " + location.getLatitude()+"\nLng: " + location.getLongitude());
        double latitude, longitude, longitudePrisao,latitudePrisao, distancia;
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        longitudePrisao = 41.58;
        latitudePrisao = -8.46;
        distancia = Math.sqrt((longitudePrisao-longitude)*(longitudePrisao-longitude) + (latitudePrisao-latitude)*(latitudePrisao-latitude));
        String stringdouble = Double.toString(distancia);
        dist.setText(stringdouble);
        if (distancia < 170.00){
            btnScan.setEnabled(true);
            imageView.setImageResource(imagens[0]);
        }
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void entrar (View v) {
        if (btnGetLocation.getText().toString().equals(true) &&
                btnScan.getText().toString().equals(true)) {
            Toast.makeText(getApplicationContext(),
                    "Redirecting...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, android.example.dai2.Main2Activity.class));
        } else {
            Toast.makeText(getApplicationContext(),
                    "Wrong Credentials", Toast.LENGTH_SHORT).show();

            counter--;

            if (counter == 0) {
                btnEntrar.setEnabled(false);
            }
        }
    }

    public void verificarScan(View v){
        
    }
}
