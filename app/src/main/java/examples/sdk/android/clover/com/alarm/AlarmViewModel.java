package examples.sdk.android.clover.com.alarm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import examples.sdk.android.clover.com.alarm.database.Alarm;
import examples.sdk.android.clover.com.alarm.database.AlarmRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlarmViewModel extends AndroidViewModel {

  private AlarmRepository alarmRepository;
  private LiveData<List<Alarm>> mAllAlarm;


  public AlarmViewModel(@NonNull Application application) {
    super(application);
    alarmRepository = new AlarmRepository(application);

  }

  public LiveData<List<Alarm>> getAllAlarm() {
    mAllAlarm = alarmRepository.getAllWords();
    return mAllAlarm;
  }

  public long insert(Alarm alarm) {
    long l = 0;
    try {
      l = alarmRepository.insert(alarm);
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mAllAlarm = alarmRepository.getAllWords();
    return l;
  }

  public LiveData<Alarm> getAlarm(int id) {
    return alarmRepository.getAlarm(id);
  }

  public void updateAlarm(Alarm alarm) {
    alarmRepository.updateAlarm(alarm);
    mAllAlarm = alarmRepository.getAllWords();
  }

  public List<Alarm> getAllAlarmList() {
    return alarmRepository.getAllAlarmList();
  }
}
