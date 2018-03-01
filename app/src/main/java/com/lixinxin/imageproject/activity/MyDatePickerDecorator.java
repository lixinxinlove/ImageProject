package com.lixinxin.imageproject.activity;

import android.content.Context;
import android.graphics.Color;

import com.lixinxin.imageproject.R;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by android on 2018/3/1.
 */

public class MyDatePickerDecorator implements CalendarCellDecorator {

    private Context context;

    public MyDatePickerDecorator(Context context) {
        this.context = context;
    }

    @Override
    public void decorate(CalendarCellView cellView, Date date) {

        if (!cellView.isCurrentMonth()) {
            cellView.getDayOfMonthTextView().setText("");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1 || week == 7) {
            //星期天设置字体颜色
            cellView.getDayOfMonthTextView().setTextColor(Color.parseColor("#e94b4a"));
        }

        if (cellView.isSelectable()) {
            if (cellView.isSelected()) {
                cellView.setBackground(context.getDrawable(R.mipmap.ic_launcher_round));
            } else {
                cellView.setBackground(null);
            }
        } else {
            cellView.setBackground(null);
        }
    }
}
