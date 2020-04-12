package android.example.dai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    EditText nome;
    EditText data;
    Dialog myDialog;
    Button registar;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registar_ent3);
        myDialog = new Dialog(this);
        rg= (RadioGroup) findViewById(R.id.rgroup);
        final EditText data = (EditText) findViewById(R.id.data);
        final EditText nome = (EditText) findViewById(R.id.editText);
        Button registar = (Button) findViewById(R.id.button10);
        data.addTextChangedListener(Mask.insert("##/##/####", data));
        data.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data.setText("");
                }
                return false;
            }

    });
        nome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    nome.setText("");
                }
                return false;
            }
        });

    }
    public void ShowPopup3(View v){
        TextView txtclose;
        myDialog.setContentView(R.layout.registopopup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                alert("Sucesso!!");
            }
        }, 7000);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }


    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }


    public void rbclick(View v){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(radiobuttonid);
        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_SHORT).show();
    }
   /* public void registar (View v) {
       nome = (EditText) findViewById(R.id.editText);
        data = (EditText) findViewById(R.id.data);
        if (nome.getText().length() == 0) {//como o tamanho é zero é nulla aresposta
            nome.setError("Campo vazio");}
        else {
            if (data.getText().length() == 0 || data.getText().length() < 8) {//como o tamanho é zero é nulla aresposta
                data.setError("Preencha corretamente");}
            else{
                //startActivity(new Intent(this, Main2Activity.class));
                ShowPopup3(v);}
        }

    }*/
}
