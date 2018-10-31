package examples.sdk.android.clover.com.alarm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import examples.sdk.android.clover.com.alarm.database.Alarm;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TimeHolder> {

  List<Alarm> data;
  ClockClickListener clockClickListener;

  public RecyclerAdapter(List<Alarm> alarmObjects, ClockClickListener clockClickListener) {
    this.clockClickListener = clockClickListener;
    this.data = alarmObjects;
  }

  @NonNull
  @Override
  public TimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerobject, viewGroup, false);

    TimeHolder timeHolder = new TimeHolder(view);
    return timeHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull TimeHolder timeHolder, int i) {
    timeHolder.tv.setText(data.get(i).getHours()+":"+data.get(i).getMinutes());
    timeHolder.aSwitch.setChecked(data.get(i).getActive());
    timeHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Alarm alarm = data.get(i);
        alarm.setActive(b);
        clockClickListener.updateDatabase(alarm);
      }
    });
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public static class TimeHolder extends RecyclerView.ViewHolder {

    TextView tv;
    Switch aSwitch;

    public TimeHolder(@NonNull View itemView) {
      super(itemView);

      tv = itemView.findViewById(R.id.time);
      aSwitch = itemView.findViewById(R.id.toggleButton);
    }
  }
}
