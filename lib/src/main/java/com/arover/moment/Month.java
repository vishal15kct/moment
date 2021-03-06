package com.arover.moment;

/**
 * Created by minstrel on 8/18/16.
 */
public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST,
    SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public int index() {
        return ordinal();
    }

    public int number() {
        return ordinal() + 1;
    }

    public static Month from(int i) {
        return new Month[]{
                    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
                    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
                }[i];
    }
}
