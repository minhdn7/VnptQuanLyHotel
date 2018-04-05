package com.vnpt.hotel.manager.domain.repository.api.motel;

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

    @GET(ServiceUrl.CANCEL_BOOKING)
    public Observable<Response<CommonApiResult>> cancelBooking(@Header("token") String token,
                                                             @Query("userId") long userId,
                                                             @Query("bookingId") long bookingId);

    @POST(ServiceUrl.UPDATE_BOOKING)
    public Observable<Response<UpdateBookingResponse>> updateBooking(@Header("token") String token,
                                                                     @Body UpdateBookingRequest request);
    //end

    // room api
    @GET(ServiceUrl.GET_ROOM_AVAILABLE)
    public Observable<Response<RoomAvailableResponse>> getRoomAvailable(@Header("token") String token,
                                                                        @Query("roomTypeId") long roomTypeId);

    @POST(ServiceUrl.CHECK_IN_ROOM)
    public Observable<Response<CheckInResponse>> checkIn(@Header("token") String token,
                                                         @Body CheckInRequest request);
    // end
}
