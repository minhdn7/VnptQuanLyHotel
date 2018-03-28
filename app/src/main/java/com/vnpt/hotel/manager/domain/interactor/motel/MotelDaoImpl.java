package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
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
}
