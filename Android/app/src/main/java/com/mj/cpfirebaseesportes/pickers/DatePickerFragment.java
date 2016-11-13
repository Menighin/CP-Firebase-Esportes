package com.mj.cpfirebaseesportes.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by munirsalim on 12/11/16.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private Calendar date;
    private TextView textView;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();

        int textViewId = getArguments().getInt("textView");
        textView = (TextView) getActivity().findViewById(textViewId);

        if(textView != null)
        {
            try {
                c.setTime(sdf.parse(textView.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DATE, day);

        textView.setText(sdf.format(date.getTime()));

    }

    public Calendar getDate()
    {
        return date;
    }
}
