package com.knowledgehut.crm.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date unmarshal(String date) throws Exception {
        return dateFormat.parse(date);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return dateFormat.format(date);
    }
}
