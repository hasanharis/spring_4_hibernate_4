package com.umarashfaq.tyrion.web.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public class TimestampConverter implements Converter<String, Timestamp>{
	
	public Timestamp convert(String source) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp t = null;
		try {
			Date d = df.parse(source);
			t = new Timestamp(d.getTime());
		} catch (ParseException e) {
			// logger.error(e, e);
		}
		return t;
	}

}
