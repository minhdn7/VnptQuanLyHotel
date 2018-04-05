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
import com.vnpt.hotel.manager.domain.repository.api.motel.MotelApi;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class MotelDaoImpl implements MotelDao {
    @Inject
    MotelApi motelApi;

    @Override
    public Observable<Response<ListMotelOverViewResponse>> listHotelOverview(String token, ListMotelOverviewRequest request) {
        return motelApi.getListHotelOverView(token, request);
    }


    @Override
    public Observable<Response<ListBookingResponse>> listBooking(String token, ListBookingRequest request) {
        return motelApi.getListBooking(token, request);
    }

    @Override
    public Observable<Response<ListRoomResponse>> listRoom(String token, long hotelId) {
        return motelApi.getListRoom(token, hotelId);
    }

    @Override
    public Observable<Response<CommonApiResult>> cancelBooking(String token, long userId, long bookingId) {
        return motelApi.cancelBooking(token, userId, bookingId);
    }

    @Override
    public Observable<Response<UpdateBookingResponse>> updateBooking(String token, UpdateBookingRequest request) {
        return motelApi.updateBooking(token, request);
    }

    @Override
    public Observable<Response<RoomAvailableResponse>> getRoomAvailable(String token, Integer roomTypeId) {
        return motelApi.getRoomAvailable(token, roomTypeId);
    }

    @Override
    public Observable<Response<CheckInResponse>> checkIn(String token, CheckInRequest checkInRequest) {
        return motelApi.checkIn(token, checkInRequest);
    }
}
