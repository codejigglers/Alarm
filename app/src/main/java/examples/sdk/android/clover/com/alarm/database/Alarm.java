package examples.sdk.android.clover.com.alarm.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "alarm")
public class Alarm  {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "id")
  private int id;

  @NonNull
  public int getId() {
    return id;
  }

  public void setId(@NonNull int id) {
    this.id = id;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public String getMinutes() {
    return minutes;
  }

  public void setMinutes(String minutes) {
    this.minutes = minutes;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  @ColumnInfo(name = "active")
  private Boolean isActive;

  @ColumnInfo(name = "minutes")
  private String minutes;

  @ColumnInfo(name = "hours")
  private String hours;

}
