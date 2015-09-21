package com.epam.te.hotel.tags;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * Class for creating users tags.
 * Calculates delta (the difference) between the day of user's arrival
 * and the day of departure.
 */
public class DeltaDateTag extends TagSupport{
	
	private static final long serialVersionUID = 4808422578218608810L;

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String DELTA = "delta";

	private String dateDeparture;

	private String dateArrival;

	/**
	 * Sets the day of user's departure.
	 * @param dateDeparture
	 */
	public void setDateDeparture(String dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	/**
	 * Sets the day of user's arrival.
	 * @param dateArrival
	 */
	public void setDateArrival(String dateArrival) {
		this.dateArrival = dateArrival;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			long delta;
			delta = (getTime(dateDeparture) - getTime(dateArrival)) / (1000 * 60 * 60 * 24);

			pageContext.getRequest().setAttribute(DELTA, delta);
		} catch (ParseException e) {
			throw new JspException("Error: " + e + e.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * Gets the time.
	 * @param date
	 * @return date in long type
	 * @throws ParseException
	 */
	private long getTime(String date) throws ParseException {
		DateFormat formater = new SimpleDateFormat(DATE_FORMAT);
		long dateTime = formater.parse(date).getTime();
		return dateTime;
	}
}
