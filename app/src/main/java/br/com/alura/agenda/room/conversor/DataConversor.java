package br.com.alura.agenda.room.conversor;

import java.util.Calendar;

import androidx.room.TypeConverter;

public class DataConversor{

	@TypeConverter
	public Long paraLong(Calendar dataEmCalendar){
		if(dataEmCalendar != null)
			return dataEmCalendar.getTimeInMillis();
		else return null;
	}

	@TypeConverter
	public Calendar paraCalendar(Long dataEmLong){
		Calendar calendar = Calendar.getInstance();
		if(dataEmLong != null){
			calendar.setTimeInMillis(dataEmLong);
			return calendar;
		} else return null;
	}

}
