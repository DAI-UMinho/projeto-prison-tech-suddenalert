package android.example.dai2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.provider.ContactsContract;
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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private Button btnScan;
    private Button btnGetLocation;
    private TextView txLocation;
    private final int GPS_REQUEST = 200;
    private LocationManager locationManager;
    private ImageView imageView, imageView2;
    int[] imagens = {R.mipmap.aceite};
    public static String scanValor;
    private boolean sucess = false;
    private boolean isSucess = false;
    private String latitudePris;
    private String longitudePris;
    private String coordenadas;
    private ProgressDialog progressDialog1;



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
        imageView2 = (ImageView) findViewById(R.id.imageView11);

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
               // btnGetLocation.setEnabled(true);
            } else {
                alert("Scan Cancelado");
            }
            if (sucess == true) {
                Toast.makeText(getApplicationContext(), "Utilizador encontrado!", Toast.LENGTH_SHORT).show();
                btnGetLocation.setEnabled(true);
                imageView2.setImageResource(imagens[0]);
            } else {
                Toast.makeText(getApplicationContext(), "Utilizador não encontrado!", Toast.LENGTH_LONG).show();
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
        if (sucess == true) {
            txLocation.setText("Lat: " + location.getLatitude() + "\nLng: " + location.getLongitude());
            double latitude, longitude, longitudePrisao, latitudePrisao, distancia;
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            longitudePrisao = Double.parseDouble(longitudePris);
            latitudePrisao = Double.parseDouble(latitudePris);
            if (getDistanciaEntrePontosEmKm(latitude, longitude, latitudePrisao, longitudePrisao) < 999999999.00) {
                imageView.setImageResource(imagens[0]);
                isSucess = true;
            } else {
                alert("Localização inválida");

            }
        } else {
            Toast.makeText(getApplicationContext(), "Erro na Procura de Utilizador!", Toast.LENGTH_SHORT).show();
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
        if (sucess == true && isSucess==true) {
            startActivity(new Intent(this, android.example.dai2.Main2Activity.class));
        } else {
            if (sucess==false) {
                Toast.makeText(getApplicationContext(), "Erro na Procura de Utilizador!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Erro na Localização de Utilizador!", Toast.LENGTH_SHORT).show();
            }
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
                    String query = "SELECT name, location FROM Profile WHERE scan like '"+scanValor+"';";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String name = rs.getString("name");
                        System.out.println(name);

                        coordenadas = rs.getString("location");
                        String[] points = coordenadas.split("\\s*[,]\\s*");
                        latitudePris = points[0];
                        longitudePris = points[1];
                        System.out.println(latitudePris);
                        if (name.equals(null) || coordenadas.equals(false)) {
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
    private static double grausParaRadianos(double graus) {
        return graus * Math.PI / 180;
    }
    private static double getDistanciaEntrePontosEmKm(double lat1, double lon1, double lat2, double lon2) {
        int raioTerraKm = 6371;

        double dLat = grausParaRadianos(lat2-lat1);
        double dLon = grausParaRadianos(lon2-lon1);

        lat1 = grausParaRadianos(lat1);
        lat2 = grausParaRadianos(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distanciaEntrePontosKm= raioTerraKm * c;
        return distanciaEntrePontosKm;
    }
}

