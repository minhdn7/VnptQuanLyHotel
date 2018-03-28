package com.vnpt.hotel.manager.domain.repository.api.roomtype;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.domain.model.roomtype.GetListAmenitiesResult;
import lombok.Getter;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomTypeApi {
  @POST("vnptline/rest/manager/roomtype/create")
  Observable<CreateRoomTypeResult> createRoomTypeApi(
      @Body CreateRoomType createStep1);

  @FormUrlEncoded @POST("vnptline/rest/manager/roomtype/saveAmenities")
  Observable<CommonApiResult> saveAmenitiesApi(@Field("roomTypeId") long roomTypeId,
      @Field("amenities") String amenities);

  @POST("vnptline/rest/manager/roomtype/price/create")
  Observable<CommonApiResult> createRoomTypePriceApi(
      @Body CreateRoomTypePrice createRoomTypePrice);

  @FormUrlEncoded
  @GET("vnptline/rest/manager/amenities/getlist")
  Observable<GetListAmenitiesResult> getListAmenites(@Query("type") String type);

  @Multipart @POST("vnptline/rest/manager/roomtype/saveFiles")
  Observable<CommonApiResult> SaveFileRoomTypeApi(@Part MultipartBody.Part[] pictures);
}
