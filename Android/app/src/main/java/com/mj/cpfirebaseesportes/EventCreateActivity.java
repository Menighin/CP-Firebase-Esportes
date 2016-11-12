package com.mj.cpfirebaseesportes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class EventCreateActivity extends AppCompatActivity {

    private EditText et_esporte;
    private EditText et_local;
    private EditText et_data;
    private EditText et_hora;
    private EditText et_pessoas;
    private EditText et_valor;

    private Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

    }

    public void onCreatePressed() {
        Log.d("evento-create", "Clicou no botao de criar");
    }


}
