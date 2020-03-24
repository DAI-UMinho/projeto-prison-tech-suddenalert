package android.example.dai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private Button btnScan;
    private Button btnGetLocation;
    private TextView txLocation;
    private final int GPS_REQUEST = 200;
    private LocationManager locationManager;
    private ImageView imageView;
    int[] imagens = {R.mipmap.aceite};
    private String scanValor;
    private boolean sucess = false;



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
        imageView = (ImageView) findViewById((R.id.imageView12));


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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        scanValor = result.getContents();
        if (result != null) {
            if (result.getContents() != null) {
                Login login = new Login();
                login.execute();
            } else {
                alert("Scan Cancelado");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
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
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        txLocation.setText("Lat: " + location.getLatitude() + "\nLng: " + location.getLongitude());
        double latitude, longitude, longitudePrisao, latitudePrisao, distancia;
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        longitudePrisao = 41.58;
        latitudePrisao = -8.46;
        distancia = Math.sqrt((longitudePrisao - longitude) * (longitudePrisao - longitude) + (latitudePrisao - latitude) * (latitudePrisao - latitude));
        String stringdouble = Double.toString(distancia);
        if (distancia < 9999.00) {
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

    public void entrar(View v) {
        if (sucess == true) {
            startActivity(new Intent(this, android.example.dai2.Main2Activity.class));
        } else {
            Toast.makeText(getApplicationContext(),
                    "Por favor leia o seu cartao QR", Toast.LENGTH_SHORT).show();
        }
    }

    private class Login extends AsyncTask<String, String, String> {
        String msg = "Profile não encontrado";
        ProgressDialog progressDialog;

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Synchronising", "Searching for user...", true);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(BD.getBdUrl(), BD.getUSER(), BD.getPASS());
                if (conn == null) {
                    sucess = false;
                } else {
                    //String query = "SELECT scan, name, location, color, id_type, points FROM Profile WHERE scan='"+scanValor+"';";
                    String query = "SELECT name FROM Profile WHERE scan like '"+scanValor+"';";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        if (name.equals(null)) {
                            sucess = false;
                        } else {
                            msg = "Utilizador encontrado!";
                            sucess = true;
                        }
                        System.out.println(sucess);
                    }
                    rs.close();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                sucess = false;
            }
            return msg;
        }
    }
}

