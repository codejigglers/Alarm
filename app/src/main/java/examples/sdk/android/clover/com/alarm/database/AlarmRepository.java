package examples.sdk.android.clover.com.alarm.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlarmRepository {

  private AlarmDao mAlarmInstance;
  private LiveData<List<Alarm>> mAllAlarms;
  private List<Alarm> als = new ArrayList<>();
  long l;

  boolean b;

  public AlarmRepository(Application application) {
    AlarmDatabaseInstance db = AlarmDatabaseInstance.getDatabase(application);
    mAlarmInstance = db.alarmDao();
    mAllAlarms = mAlarmInstance.getAllAlarm();
  }

  public LiveData<List<Alarm>> getAllWords() {
    return mAllAlarms;
  }

  public List<Alarm> getAllAlarmList() {
    new getAsyncTask(mAlarmInstance, als).execute();
    return als;
  }

  public LiveData<Alarm> getAlarm(int id) {
    return mAlarmInstance.getAlarm(id);
  }

  public void updateAlarm(Alarm alarm) {
    new updateAsyncTask(mAlarmInstance).execute(alarm);
  }

  public long insert(Alarm alarm) throws ExecutionException, InterruptedException {
    insertAsyncTask insertAsyncTask = new insertAsyncTask(mAlarmInstance, this);

    Long l = insertAsyncTask.execute(alarm).get();
    return l;
  }

  public long makeBlock() {
    return l;
  }

  private static class insertAsyncTask extends AsyncTask<Alarm, Long, Long> {

    private AlarmDao mAsyncTaskDao;
    private AlarmRepository alarmRepository;

    insertAsyncTask(AlarmDao dao, AlarmRepository alarmRepository) {

      mAsyncTaskDao = dao;
      this.alarmRepository = alarmRepository;
    }

    @Override
    protected Long doInBackground(final Alarm... params) {
      Long l = mAsyncTaskDao.insert(params[0]);
      alarmRepository.l = l;
      this.alarmRepository.l = l;
      alarmRepository.b=true;
      return l;
    }

    @Override
    protected void onPostExecute(Long a) {
      super.onPostExecute(a);
    }
  }

  private static class updateAsyncTask extends AsyncTask<Alarm, Void, Void> {

    private AlarmDao mAsyncTaskDao;

    updateAsyncTask(AlarmDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Alarm... params) {
      mAsyncTaskDao.updateAlarm(params[0]);
      return null;
    }
  }


  private static class getAsyncTask extends AsyncTask<Alarm, List<Alarm>, List<Alarm>> {

    private AlarmDao mAsyncTaskDao;
    private List<Alarm> als;

    getAsyncTask(AlarmDao dao, List<Alarm> als) {
      mAsyncTaskDao = dao;
      this.als = als;
    }

    @Override
    protected List<Alarm> doInBackground(final Alarm... params) {
      return mAsyncTaskDao.getAllAlarmList();
    }

    @Override
    protected void onPostExecute(List<Alarm> alarms) {
      als.clear();
      for (Alarm alarm : alarms) {
        als.add(alarm);
      }
    }
  }
}
