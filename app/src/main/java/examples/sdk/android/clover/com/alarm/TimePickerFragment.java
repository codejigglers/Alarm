package examples.sdk.android.clover.com.alarm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment {

  Button cancel;
  ClockClickListener clockClickListener;

  @SuppressLint("ValidFragment")
  public TimePickerFragment(ClockClickListener clockClickListener) {
    this.clockClickListener = clockClickListener;
  }

  Button setAlarm;
  TimePicker timePicker;
  int hour;
  int minute;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    GradientDrawable gradientDrawable = new GradientDrawable();
    LayoutInflater inflater = getActivity().getLayoutInflater();


    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    View view = inflater.inflate(R.layout.fragment_time_picker, null);
    cancel = view.findViewById(R.id.cancelAlarm);
    setAlarm = view.findViewById(R.id.setAlarm);
    timePicker = view.findViewById(R.id.timepicker);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      hour = timePicker.getHour();
      minute = timePicker.getMinute();
    }

    cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
      }
    });

    setAlarm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clockClickListener.onTimeClick(String.valueOf(hour), String.valueOf(minute), true);
        Toast.makeText(getContext(), "Alarm Set", Toast.LENGTH_LONG).show();
        dismiss();
      }
    });

    builder.setView(view);

    timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
      @Override
      public void onTimeChanged(TimePicker timePicker, int i, int i1) {
        hour = i;
        minute = i1;
      }
    });

    return builder.create();
  }

}
