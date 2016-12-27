package com.mj.cpfirebaseesportes.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.mj.cpfirebaseesportes.R;
import com.mj.cpfirebaseesportes.components.pickers.DatePickerFragment;
import com.mj.cpfirebaseesportes.components.pickers.TimePickerFragment;
import com.mj.cpfirebaseesportes.models.Evento;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFormFragment extends Fragment {

    public static final String TAG = "CREATE_EVENT_FORM_FRAGMENT";

    private EditText titleEditText;
    private EditText sportEditText;
    private EditText descriptionEditText;
    private EditText localEditText;
    private EditText personsEditText;
    private EditText valueEditText;
    private TextView dateTextView;
    private TextView hourTextView;

    private LatLng eventLatLng;

    private Evento evento;

    private Calendar datetime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_create_event_form, container, false);

        datetime = Calendar.getInstance();

        titleEditText = (EditText) v.findViewById(R.id.et_titulo);
        sportEditText = (EditText) v.findViewById(R.id.et_esporte);
        descriptionEditText = (EditText) v.findViewById(R.id.et_descricao);
        localEditText = (EditText) v.findViewById(R.id.et_local);
        personsEditText = (EditText) v.findViewById(R.id.et_pessoas);
        valueEditText = (EditText) v.findViewById(R.id.et_valor);

        dateTextView = (TextView) v.findViewById(R.id.tv_data);
        dateTextView.setText(CurrentDate());

        hourTextView = (TextView) v.findViewById(R.id.tv_hora);
        hourTextView.setText(CurretTime());

        hourTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        Bundle args = getArguments();
        if (args != null) {
            eventLatLng = new LatLng(args.getDouble("lat"), args.getDouble("lon"));
        }
        return v;
    }

    private String CurrentDate() {
        return datetime.get(Calendar.DATE) + "/" + (datetime.get(Calendar.MONTH) + 1) + "/" + datetime.get(Calendar.YEAR);
    }

    private String CurretTime() {
        return datetime.get(Calendar.HOUR_OF_DAY) + ":" + datetime.get(Calendar.MINUTE);
    }

    public void showTimePickerDialog() {
        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                datetime.set(Calendar.MINUTE, minute);
                hourTextView.setText(CurretTime());
            }
        }, datetime.get(Calendar.HOUR_OF_DAY), datetime.get(Calendar.MINUTE), false).show();
    }

    public void showDatePickerDialog() {
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datetime.set(Calendar.YEAR, year);
                datetime.set(Calendar.MONTH, month);
                datetime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateTextView.setText(CurrentDate());
            }
        }, datetime.get(Calendar.YEAR), datetime.get(Calendar.MONTH), datetime.get(Calendar.DAY_OF_MONTH)).show();
    }

    public Evento getEvento() {

        String titulo = titleEditText.getText().toString();
        String esporte = sportEditText.getText().toString();
        String descricao = descriptionEditText.getText().toString();
        String local = localEditText.getText().toString();
        Integer nPessoas = Integer.parseInt(personsEditText.getText().toString());
        Double valor = Double.parseDouble(valueEditText.getText().toString());

        evento = new Evento(titulo, esporte, descricao, local, eventLatLng.latitude, eventLatLng.longitude, datetime, nPessoas, valor);

        return evento;

    }

}
