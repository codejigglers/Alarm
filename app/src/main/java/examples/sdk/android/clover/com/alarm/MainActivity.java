package examples.sdk.android.clover.com.alarm;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import examples.sdk.android.clover.com.alarm.database.Alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClockClickListener {

  public static final String TAGS = "VARDAN";
  TimePickerFragment timePickerFragment;
  private RecyclerView mRecyclerView;
  private RecyclerView.Adapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  List<Alarm> data = new ArrayList<>();
  FloatingActionButton fl1;
  FloatingActionButton fl2;
  FloatingActionButton fl3;
  FloatingActionButton fl4;
  AlarmViewModel alarmViewModel;
  RecyclerAdapter recyclerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);


    recyclerAdapter = new RecyclerAdapter(data, this);


    alarmViewModel.getAllAlarm().observe(this, listAlarm -> {
      data.clear();
      for (Alarm alarm : listAlarm) {
        data.add(alarm);
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        data.sort(new Comparator<Alarm>() {
          @Override
          public int compare(Alarm alarm, Alarm t1) {
            if (Integer.parseInt(alarm.getHours()) > Integer.parseInt(t1.getHours())) {
              return 1;
            } else if (Integer.parseInt(alarm.getHours()) < Integer.parseInt(t1.getHours())) {
              return -1;
            }
            return 0;
          }
        });
      }

      if (mRecyclerView.getAdapter() == null) {
        mRecyclerView.setAdapter(recyclerAdapter);
      }
    });

    Button b = findViewById(R.id.button);
    timePickerFragment = new TimePickerFragment(this);


    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

    fl1 = findViewById(R.id.fl1);
    fl2 = findViewById(R.id.floatingActionButton);
    fl3 = findViewById(R.id.floatingActionButton2);
    fl4 = findViewById(R.id.floatingActionButton3);

    fl1.setImageBitmap(textAsBitmap("15 Min", 80, Color.WHITE));
    fl2.setImageBitmap(textAsBitmap("30 Min", 80, Color.WHITE));
    fl3.setImageBitmap(textAsBitmap("1 Hour", 80, Color.WHITE));
    fl4.setImageBitmap(textAsBitmap("2 Hour", 80, Color.WHITE));

    fl1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (fl1.getBackgroundTintList().toString().equals(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)).toString())) {
          fl1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        } else {
          fl1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
        }
      }
    });

    fl2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (fl2.getBackgroundTintList().toString().equals(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)).toString())) {
          fl2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        } else {
          fl2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
        }
      }
    });

    fl3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (fl3.getBackgroundTintList().toString().equals(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)).toString())) {
          fl3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        } else {
          fl3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
        }
      }
    });

    fl4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (fl4.getBackgroundTintList().toString().equals(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)).toString())) {
          fl4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        } else {
          fl4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_primary)));
        }
      }
    });


    b.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        timePickerFragment.show(getSupportFragmentManager(), TAGS);
      }
    });
  }


  @Override
  protected void onResume() {
    super.onResume();

  }

  public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setTextSize(textSize);
    paint.setColor(textColor);
    paint.setTextAlign(Paint.Align.LEFT);
    float baseline = -paint.ascent(); // ascent() is negative
    int width = (int) (paint.measureText(text) + 0.0f); // round
    int height = (int) (baseline + paint.descent() + 0.0f);
    Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

    Canvas canvas = new Canvas(image);
    canvas.drawText(text, 0, baseline, paint);
    return image;
  }

  public long insertAlarm(Alarm alarm) {
    long l = alarmViewModel.insert(alarm);
    Log.i("VARDAAAAAN", String.valueOf(l));
    return l;
  }

  @Override
  public void onTimeClick(String hour, String minute, Boolean isActive) {
    Alarm a = new Alarm();
    a.setHours(hour);
    a.setMinutes(minute);
    a.setActive(isActive);
    long l = insertAlarm(a);
    onItemAdded(l);
  }

  @Override
  public void updateDatabase(Alarm alarm) {
    alarmViewModel.updateAlarm(alarm);
  }

  @Override
  public void onItemAdded(long l) {
    alarmViewModel.getAllAlarm().observe(this, listAlarm -> {
      data.clear();
      for (Alarm alarm : listAlarm) {
        data.add(alarm);
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        data.sort(new Comparator<Alarm>() {
          @Override
          public int compare(Alarm alarm, Alarm t1) {
            if (Integer.parseInt(alarm.getHours()) > Integer.parseInt(t1.getHours())) {
              return 1;
            } else if (Integer.parseInt(alarm.getHours()) < Integer.parseInt(t1.getHours())) {
              return -1;
            }
            return 0;
          }
        });
      }
      int i = 0;
      for (Alarm alarm : data) {
        if (alarm.getId() == l) {
          mRecyclerView.getLayoutManager().scrollToPosition(i);
          break;
        }
        i += 1;
      }
      mRecyclerView.setAdapter(recyclerAdapter);

      final int o = i;
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          View viewItem = mRecyclerView.getLayoutManager().getChildAt(0);
          int v = mRecyclerView.getLayoutManager().getItemCount();
          viewItem.findViewById(R.id.toReplace).setVisibility(View.VISIBLE);
          mRecyclerView.getLayoutManager().scrollToPosition(o);
        }
      }, 1);
    });





  }
}
