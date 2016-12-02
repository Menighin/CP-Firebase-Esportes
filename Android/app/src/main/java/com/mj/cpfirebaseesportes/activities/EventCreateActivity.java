package com.mj.cpfirebaseesportes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mj.cpfirebaseesportes.R;
import com.mj.cpfirebaseesportes.models.Evento;
import com.mj.cpfirebaseesportes.pickers.DatePickerFragment;
import com.mj.cpfirebaseesportes.pickers.TimePickerFragment;

import java.util.Calendar;

public class EventCreateActivity extends BaseActivity {

    private EditText et_esporte;
    private EditText et_descricao;
    private EditText et_local;
    private EditText et_pessoas;
    private EditText et_valor;

    private TextView tv_data;
    private TextView tv_hora;

    TimePickerFragment timeFragment;
    DatePickerFragment dateFragment;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        et_esporte = (EditText) findViewById(R.id.et_esporte);
        et_descricao = (EditText) findViewById(R.id.et_descricao);
        et_local = (EditText) findViewById(R.id.et_local);
        et_pessoas = (EditText) findViewById(R.id.et_pessoas);
        et_valor = (EditText) findViewById(R.id.et_valor);

        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_data.setText(CurrentDate());

        tv_hora = (TextView) findViewById(R.id.tv_hora);
        tv_hora.setText(CurretTime());

        dateFragment = new DatePickerFragment();
        Bundle argsDate = new Bundle();
        argsDate.putInt("textView", R.id.tv_data);
        dateFragment.setArguments(argsDate);

        timeFragment = new TimePickerFragment();
        Bundle argsTime = new Bundle();
        argsTime.putInt("textView", R.id.tv_hora);
        timeFragment.setArguments(argsTime);

    }

    private String CurrentDate()
    {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
    }

    private String CurretTime()
    {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
    }

    public void onSavePressed(View v) {

        String esporte = et_esporte.getText().toString();
        String descricao = et_descricao.getText().toString();
        String local = et_local.getText().toString();
        Integer nPessoas = Integer.parseInt(et_pessoas.getText().toString());
        Double valor = Double.parseDouble(et_valor.getText().toString());

        Calendar time = timeFragment.getTime();
        Calendar data = dateFragment.getDate();

        data.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        data.set(Calendar.MINUTE, time.get(Calendar.MINUTE));

        Evento evento = new Evento(descricao, esporte, descricao, local, data, nPessoas, valor);
        createEvento(evento);

        Log.d("evento-create", "Clicou no botao de criar");
    }

    private void createEvento(Evento evento){

        DatabaseReference eventosRef = mDatabase.child("eventos");
        eventosRef.push().setValue(evento);
    }

    public void showTimePickerDialog(View v) {
        timeFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        dateFragment.show(getFragmentManager(), "datePicker");
    }


}
