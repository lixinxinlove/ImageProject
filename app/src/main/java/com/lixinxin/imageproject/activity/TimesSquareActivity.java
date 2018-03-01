package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

@Route(path = "/activity/TimesSquareActivity")
public class TimesSquareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_square);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);

    }
}
