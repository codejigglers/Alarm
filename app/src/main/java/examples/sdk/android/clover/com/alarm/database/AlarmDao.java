package examples.sdk.android.clover.com.alarm.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AlarmDao {

  @Query("SELECT * from alarm ORDER BY id ASC")
  LiveData<List<Alarm>> getAllAlarm();

  @Query("SELECT * from alarm ORDER BY id ASC")
  List<Alarm> getAllAlarmList();

  @Insert
  long insert(Alarm note);

  @Query("SELECT * FROM alarm WHERE id = :ids")
  LiveData<Alarm> getAlarm(int ids);

  @Update
  void updateAlarm(Alarm alarm);

}
