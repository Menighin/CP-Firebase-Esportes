package com.mj.cpfirebaseesportes.components.pickers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by munirsalim on 12/11/16.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private Calendar time;
    private TextView textView;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
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

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
        time.set(Calendar.MINUTE, minute);

        textView.setText(sdf.format(time.getTime()));
    }

    public Calendar getTime()
    {
        return time;
    }
}
