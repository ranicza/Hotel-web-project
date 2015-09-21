package com.epam.te.hotel.controller;
/**
 *Contains constant strings with the paths to jsp pages.
 */
public final class JspPageName {
	
	//Common pages
	public final static String INDEX = "/index.jsp";
	public final static String ERROR = "/jsp/error.jsp";
	public final static String LOGIN = "/jsp/common/login.jsp";
	public final static String REGISTRATION = "/jsp/common/registration.jsp";
	
	//Pages for client
	public final static String CLIENT_CABINET = "/jsp/client/client_cabinet.jsp";
	public final static String CLIENT_BILL = "/jsp/client/client_bill.jsp";		
	public final static String CLIENT_ORDER = "/jsp/client/client_order.jsp";
	public final static String FIND_APARTMENT = "/jsp/client/find_apartment.jsp";
	public final static String FREE_APARTMENT = "/jsp/client/freeRoom.jsp";
	
	//Page for user (admin & client)
	public final static String CHANGE_USER_INFO = "/jsp/client/change_user_info.jsp";
	
	//Pages for admin
	public final static String ADMIN_PAGE = "/jsp/admin/admin.jsp";
	public final static String ROOM_ADMINISTRATION = "/jsp/admin/room_administration.jsp";
	public final static String ORDER_ADMINISTRATION = "/jsp/admin/order_administration.jsp";
	public final static String USER_ADMINISTRATION = "/jsp/admin/user_administration.jsp";
	public final static String CHANGE_APARTMENT_DATA = "/jsp/admin/change_apartment_data.jsp";
	public final static String ADD_APARTMENT = "/jsp/admin/add_apartment.jsp";


	private JspPageName() {}
}
