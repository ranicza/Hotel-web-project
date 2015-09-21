package com.epam.te.hotel.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * Class for creating users tags.
 * Calculates total cost for user's order.
 */
public class TotalCostTag extends TagSupport {
	
	private static final long serialVersionUID = -4223365137779077499L;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TOTAL_COST = "totalCost";

	// days spending in the hotel
	private String delta;

	//cost per day
	private String costPerDay;

	/**
	 * Sets days spending in the hotel.
	 * @param delta (amount of days)
	 */
	public void setDelta(String delta) {
		this.delta = delta;
	}

	/**
	 * Sets cost for one day spending in the hotel.
	 * @param costPerDay
	 */
	public void setCostPerDay(String costPerDay) {
		this.costPerDay = costPerDay;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			int totalCost = Integer.parseInt(delta)* Integer.parseInt(costPerDay);
			JspWriter out = pageContext.getOut();
			pageContext.getRequest().setAttribute(TOTAL_COST, totalCost);
			out.print(totalCost);
		} catch (IOException e) {
			throw new JspException(e.getMessage(), e);
		}
		return SKIP_BODY;
	}
}
