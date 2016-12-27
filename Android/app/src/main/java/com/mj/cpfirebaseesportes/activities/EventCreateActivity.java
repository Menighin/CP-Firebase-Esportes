package com.mj.cpfirebaseesportes.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.event.Event;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mj.cpfirebaseesportes.R;
import com.mj.cpfirebaseesportes.fragments.CreateEventFormFragment;
import com.mj.cpfirebaseesportes.fragments.MapPinFragment;
import com.mj.cpfirebaseesportes.fragments.interfaces.FragmentCallback;
import com.mj.cpfirebaseesportes.models.Evento;
import com.mj.cpfirebaseesportes.components.pickers.DatePickerFragment;
import com.mj.cpfirebaseesportes.components.pickers.TimePickerFragment;

import java.util.Calendar;

public class EventCreateActivity extends BaseActivity implements FragmentCallback {

    private EditText et_esporte;
    private EditText et_descricao;
    private EditText et_local;
    private EditText et_pessoas;
    private EditText et_valor;
    private FrameLayout fragmentHolder;

    private TextView tv_data;
    private TextView tv_hora;

    TimePickerFragment timeFragment;
    DatePickerFragment dateFragment;

    private DatabaseReference mDatabase;

    private MapPinFragment mapPinFragment;
    private CreateEventFormFragment formFragment;

    private FloatingActionButton fab;

    private LatLng eventLatLng;

    private boolean fabSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fragmentHolder = (FrameLayout) findViewById(R.id.event_create_fragment_holder);
        mapPinFragment = new MapPinFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.event_create_fragment_holder, mapPinFragment).commit();

        fab = (FloatingActionButton) findViewById(R.id.fab_create_event);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fabSave) {
                    formFragment = new CreateEventFormFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    Bundle args = new Bundle();
                    args.putDouble("lat", eventLatLng.latitude);
                    args.putDouble("lon", eventLatLng.longitude);
                    formFragment.setArguments(args);

                    transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_down);

                    transaction.addToBackStack(null);
                    transaction.replace(R.id.event_create_fragment_holder, formFragment);

                    transaction.commit();

                    fabSave = true;
                } else {

                    Evento e = formFragment.getEvento();

                    createEvento(e);

                    Toast.makeText(EventCreateActivity.this, "Save! " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void createEvento(Evento evento){
        DatabaseReference eventosRef = mDatabase.child("eventos");
        eventosRef.push().setValue(evento);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fabSave = false;
    }

    @Override
    public void fragmentCallback(String tag, Object object) {
        if (tag.equals(MapPinFragment.TAG)) {
            eventLatLng = (LatLng) object;
        } else if (tag.equals(CreateEventFormFragment.TAG)) {

        }
    }
}
