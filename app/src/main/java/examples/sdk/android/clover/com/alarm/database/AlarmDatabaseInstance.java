package examples.sdk.android.clover.com.alarm.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Alarm.class}, version = 3)
public abstract class AlarmDatabaseInstance extends RoomDatabase{

  public abstract AlarmDao alarmDao();

  private static volatile AlarmDatabaseInstance INSTANCE;

  static AlarmDatabaseInstance getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (AlarmDatabaseInstance.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              AlarmDatabaseInstance.class, "alarm")
              .build();
        }
      }
    }
    return INSTANCE;
  }

}
