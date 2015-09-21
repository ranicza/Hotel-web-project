package com.epam.te.hotel.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * Class for creating users tags.
 * Gets today's date.
 */
public class TodayTag extends TagSupport{

	private static final long serialVersionUID = 5270025173437426570L;
	
	private String mFormat;

	/**
	 * Sets format.
	 * @param format
	 */
	public void setFormat(String format) {
		this.mFormat = format;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			Date today = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat(mFormat);
			out.print(dateFormatter.format(today));

		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
