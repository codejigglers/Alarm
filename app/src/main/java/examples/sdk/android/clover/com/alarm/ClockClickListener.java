package examples.sdk.android.clover.com.alarm;

import examples.sdk.android.clover.com.alarm.database.Alarm;

public interface ClockClickListener {

  public void onTimeClick(String hour, String minute, Boolean isActive);

  public void updateDatabase(Alarm alarm);

  public void onItemAdded(long l);

}
