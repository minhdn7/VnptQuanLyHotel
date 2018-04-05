package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.domain.model.request.motel.CheckInRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.UpdateBookingRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.CheckInResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.RoomAvailableResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.UpdateBookingResponse;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 27/3/2018.
 */

public interface MotelDao {
    /**
     * @param token {@link String}
     * @param request {@link ListMotelOverviewRequest}
     */
    Observable<Response<ListMotelOverViewResponse>> listHotelOverview(String token, ListMotelOverviewRequest request);
    Observable<Response<ListBookingResponse>> listBooking(String token, ListBookingRequest request);
    Observable<Response<ListRoomResponse>> listRoom(String token, long hotelId);
    Observable<Response<CommonApiResult>> cancelBooking(String token, long userId, long bookingId);
    Observable<Response<UpdateBookingResponse>> updateBooking(String token, UpdateBookingRequest request);
    Observable<Response<RoomAvailableResponse>> getRoomAvailable(String token, Integer roomTypeId);
    Observable<Response<CheckInResponse>> checkIn(String token, CheckInRequest checkInRequest);
}
