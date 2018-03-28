package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;

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
}
