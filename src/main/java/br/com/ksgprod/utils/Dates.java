package br.com.ksgprod.utils;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {
	
	public static final String YYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	private static final String INITIAL_TIMESTAMP = "T00:00:00";
	private static final String FINAL_TIMESTAMP = "T23:59:59";
	
	public static String formatDate(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYY_MM_DD_T_HH_MM_SS);
		return dateTime.format(formatter);
	}
	
	public static LocalDateTime getInitialTimestampOfDay(LocalDate localDate) {
		if (isNull(localDate)) {
			return null;
		}
		CharSequence fromText = localDate.toString() + INITIAL_TIMESTAMP;
		return LocalDateTime.parse(fromText, DateTimeFormatter.ISO_DATE_TIME);
	}

	public static LocalDateTime getFinalTimestampOfDay(LocalDate localDate) {
		CharSequence fromText = localDate.toString() + FINAL_TIMESTAMP;
		return LocalDateTime.parse(fromText, DateTimeFormatter.ISO_DATE_TIME);
	}

}
