package com.vnpt.hotel.manager.domain.repository.api.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public interface SaveFileHotelApi {
  @Multipart @POST("vnptline/rest/manager/hotel/saveFiles")
  Observable<CommonApiResult> SaveFileHotelApi(@Part("hotelId") RequestBody hotelId,
      @Part MultipartBody.Part[] pictures, @Part MultipartBody.Part businessLicense);
}
