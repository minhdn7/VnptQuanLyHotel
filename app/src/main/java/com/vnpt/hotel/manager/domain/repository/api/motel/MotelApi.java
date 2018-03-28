package com.vnpt.hotel.manager.domain.repository.api.motel;

import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.domain.repository.ServiceUrl;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by MinhDN on 27/3/2018.
 */

public interface MotelApi {


    // hotel api
    @POST(ServiceUrl.LIST_HOTEL_OVERVIEW)
    public Observable<Response<ListMotelOverViewResponse>> getListHotelOverView(@Header("token") String token,
                                                                       @Body ListMotelOverviewRequest request);

    // end

    // booking api
    @POST(ServiceUrl.GET_LIST_BOOKING)
    public Observable<Response<ListBookingResponse>> getListBooking(@Header("token") String token,
                                                                          @Body ListBookingRequest request);

    //end

    // booking api
    @GET(ServiceUrl.GET_LIST_ROOM)
    public Observable<Response<ListRoomResponse>> getListRoom(@Header("token") String token,
                                                              @Query("hotelId") long hotelId);

    //end
}
