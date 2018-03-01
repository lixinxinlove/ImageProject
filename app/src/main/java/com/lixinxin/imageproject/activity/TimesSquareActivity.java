package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Route(path = "/activity/TimesSquareActivity")
public class TimesSquareActivity extends AppCompatActivity {
    private CalendarPickerView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_square);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 3);

        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime()).withSelectedDate(today);
        setCalendarStyle();


    }

    private void setCalendarStyle() {
        MyDatePickerDecorator decorator = new MyDatePickerDecorator(this);
        List<CalendarCellDecorator> d = new ArrayList<>();
        d.add(decorator);
        calendar.setDecorators(d);

        calendar.setOnInvalidDateSelectedListener(new CalendarPickerView.OnInvalidDateSelectedListener() {
            @Override
            public void onInvalidDateSelected(Date date) {

            }
        });

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
               // getOpenTime(DateTimeUtils.timeForDate(date.getTime(), DateTimeUtils.yyyy_MM_dd));
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


    }
}
