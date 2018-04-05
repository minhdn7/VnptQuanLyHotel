package com.vnpt.hotel.manager.domain.repository;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class ServiceUrl {
    // quản lí nhà nghỉ
    public static final String LIST_HOTEL_OVERVIEW = "vnptline/rest/staff/hotel/list/overview";
    public static final String HOTEL_APPROVE = "vnptline/rest/admin/hotel/approve";
    public static final String HOTEL_CREATE = "vnptline/rest/manager/hotel/create";
    public static final String HOTEL_SAVE_AMENITIES = "vnptline/rest/manager/hotel/saveAmenities";
    public static final String HOTEL_SAVE_FILES = "vnptline/rest/manager/hotel/saveFiles";
    public static final String HOTEL_UPDATE = "vnptline/rest/manager/hotel/update";
    public static final String HOTEL_FIND_BY_USER = "vnptline/rest/staff/hotel/findByUser";

    // end

    // booking
    public static final String GET_LIST_BOOKING = "vnptline/rest/customer/booking/list";
    public static final String UPDATE_BOOKING = "vnptline/rest/customer/booking/update";
    public static final String CANCEL_BOOKING = "vnptline/rest/customer/booking/cancel";

    //end

    // room
    public static final String GET_LIST_ROOM = "vnptline/rest/staff/room/list/byHotel";
    public static final String GET_ROOM_AVAILABLE = "vnptline/rest/staff/room/available";
    public static final String CHECK_IN_ROOM = "vnptline/rest/staff/booking/checkin";
    // end

}
