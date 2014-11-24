package me.thomas.knowledge.utils;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimestampFormat extends Format {

	private static final long serialVersionUID = -4014009429596076337L;

	private SimpleDateFormat sdf;
	
	public TimestampFormat() {
		sdf = new SimpleDateFormat();
	}
	
	public TimestampFormat(String pattern) {
		sdf = new SimpleDateFormat(pattern);
	}

	public TimestampFormat(String pattern, Locale locale) {
		sdf = new SimpleDateFormat(pattern, locale);
	}
	
	public TimestampFormat(String pattern, DateFormatSymbols formatSymbols) {
		sdf = new SimpleDateFormat(pattern, formatSymbols);
	}
	
	public String format(Timestamp timestamp)
	{
		Date date = new Date(timestamp.getTime());
		
		return sdf.format(date);
	}
	
	public StringBuffer format(Timestamp timestamp, StringBuffer toAppendTo,
            FieldPosition fieldPosition)
	{
		Date date = new Date(timestamp.getTime());
		
		return sdf.format(date, toAppendTo, fieldPosition);
	}
	
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		
		if (obj instanceof Timestamp) {
			return format((Timestamp) obj, toAppendTo, pos);
		} else {
			return sdf.format(obj, toAppendTo, pos);
		}
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		return sdf.parseObject(source, pos);
	}

}
