package ru.job4j.design.srp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAdapter extends XmlAdapter<String, Calendar> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Calendar unmarshal(String s) throws Exception {
        synchronized (dateFormat) {
            return (Calendar) dateFormat.parseObject(s);
        }
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        synchronized (dateFormat) {
            return dateFormat.format(calendar.getTime());
        }
    }
}
