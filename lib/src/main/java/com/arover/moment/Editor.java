package com.arover.moment;

import java.security.InvalidParameterException;
import java.util.Calendar;

/**
 * @author arover
 *         created at 8/26/16 23:32
 */

public class Editor {

    private Moment mMoment;
    private Calendar mCalendar;

    public Editor(Moment moment) {
        mMoment = moment;
        mCalendar = moment.getCalendar();
    }

    /**
     * add x unit to moment.
     *
     * @param x    0-n
     * @param unit time unit
     */
    private void set(int x, int unit) {
        switch (unit) {
            case MomentUnit.YEAR: {
                int year = mCalendar.get(Calendar.YEAR);
                mCalendar.set(Calendar.YEAR, year + x);
                break;
            }
            case MomentUnit.MONTH: {
                int months = x % 12;
                int year = x / 12;

                int month = mCalendar.get(Calendar.MONTH);
                if (month + x > Calendar.DECEMBER) {
                    mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) + 1);
                    mCalendar.set(Calendar.MONTH, (month + x) % 11);
                } else if (month + x < Calendar.JANUARY) {
                    mCalendar.set(Calendar.YEAR, mCalendar.get(Calendar.YEAR) - 1);
                    mCalendar.set(Calendar.MONTH, (month + x) % 11);
                } else {
                    mCalendar.set(Calendar.MONTH, (month + x));
                }
                break;
            }
            case MomentUnit.DAY:
                mCalendar.add(Calendar.DAY_OF_YEAR, x);
                break;
            case MomentUnit.HOUR:
                mCalendar.add(Calendar.HOUR, x);
                break;
            case MomentUnit.MINUTE:
                mCalendar.add(Calendar.MINUTE, x);
                break;
            case MomentUnit.SECOND:
                mCalendar.add(Calendar.SECOND, x);
                break;
            case MomentUnit.MILISECOND:
                mCalendar.add(Calendar.MILLISECOND, x);
                break;
        }
    }

    public void add(int n, int unit) {
        set(n, unit);
    }

    /**
     * minux x int time unit.
     *
     * @param n    the amount to minus to the field
     * @param unit time field/unit.
     */
    public void minus(int n, int unit) {
        set(-n, unit);
    }

    public void setFirstDayOfWeek(DayOfWeek dayOfWeek) {
//        mFirstDayOfWeek = dayOfWeek;
    }

    public void setMillisecond(long timeInMillis) {
        if (timeInMillis < 0)
            throw new InvalidParameterException("can't millisecond to " + timeInMillis);

        mCalendar.setTimeInMillis(timeInMillis);
    }

    public void setSecond(int sec) {
        if (sec < 0 || sec > 59)
            throw new InvalidParameterException("can't sec second to " + sec);
        mCalendar.set(Calendar.SECOND, sec);
    }

    public void setMinute(int min) {
        if (min < 0 || min > 59)
            throw new InvalidParameterException("can't sec minute to " + min);
        mCalendar.set(Calendar.MINUTE, min);
    }

    /**
     * @param hour [0,23]
     */
    public void setHour(int hour) {
        if (hour < 0 || hour > 23)
            throw new InvalidParameterException("can't sec hour to " + hour);
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
    }

    public void setDayOfMonth(int day) {
        if (day < 0 || day > 23)
            throw new InvalidParameterException("can't sec minute to " + day);

        mCalendar.set(Calendar.DAY_OF_MONTH, day);
    }

    public void setMonth(Month month) {
        mCalendar.set(Calendar.MONTH, month.ordinal());
    }

    /**
     * @param month [Calendar.JANUARY,Calendar.DECEMBER]
     */
    public void setMonth(int month) {
        mCalendar.set(Calendar.MONTH, month);
    }


    public Moment setBeginningOfDay() {
        Util.setTimeToBeginningOfDay(mCalendar);
        return mMoment;
    }

    public Moment setEndOfDay() {
        Util.setTimeToEndOfDay(mCalendar);
        return mMoment;
    }
}
