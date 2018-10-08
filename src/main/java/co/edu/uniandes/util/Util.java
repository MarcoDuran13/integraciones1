package co.edu.uniandes.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Clase utilitaria encargada de castear los valores retornados de las columnas
 * de la base de datos al tipo esperado
 * 
 * 
 *
 */
public class Util {

	private static final SimpleDateFormat FECHA = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

	public static Date getColumnDate_(Object column) {
		return column != null ? Util.getDate((String) column) : null;
	}

	public static XMLGregorianCalendar getColumnDate(Object column) {
		return column != null ? Util.getDate((Date) column) : null;
	}

	public static String getColumnString(Object column) {
		return column != null ? (String) column : "";
	}

	public static Double getColumnDouble(Object column) {
		return column != null ? (Double) column : null;
	}

	public static Float getColumnFloatFromString(Object column) {
		return column != null ? Float.valueOf((String) column) : null;
	}

	public static Float getColumnFloatFromBigDecimal(Object column) {
		return column != null ? ((BigDecimal) column).floatValue() : null;
	}

	public static Long getColumnLongFromString(Object column) {
		return column != null ? Long.valueOf((String) column) : null;
	}

	public static Integer getColumnInteger(Object column) {
		return column != null ? (Integer) column : null;
	}

	public static Integer getColumnIntegerFromString(Object column) {
		return column != null ? Integer.valueOf((String) column) : null;
	}

	public static Integer getColumnIntegerFromBigDecimal(Object column) {
		return column != null ? ((BigDecimal) column).intValue() : null;
	}

	public static Boolean getColumnBoolean(Object column) {
		return column != null ? (Boolean) column : null;
	}

	public static Boolean getColumnBooleanFromString(Object column) {
		return column != null ? Boolean.valueOf((String) column) : null;
	}

	public static XMLGregorianCalendar getDate(Date date) {
		try {
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(date);
			XMLGregorianCalendar xgcal = getDate(gcal);
			return xgcal;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(String date) {
		try {
			return FECHA.parse(date);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static Date getDate(Object date) {
		try {
			return FECHA.parse(FECHA.format(date));
		} catch (Exception e) {
			return null;
		}
		
	}

	public static XMLGregorianCalendar getDateCurrent() {
		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		XMLGregorianCalendar xgcal = getDate(gcal);
		return xgcal;
	}

	private static XMLGregorianCalendar getDate(GregorianCalendar gcal) {
		XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException e) {
		}
		return xgcal;
	}
}
