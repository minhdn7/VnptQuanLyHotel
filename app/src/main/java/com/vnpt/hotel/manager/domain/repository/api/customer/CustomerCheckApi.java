package com.vnpt.hotel.manager.domain.repository.api.customer;

import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LiKaLi on 3/7/2018.
 */

public interface CustomerCheckApi {
  @GET("vnptline/rest/manager/customer/get/byuser")
  Observable<CustomerCheckApiResult> customerCheckApi(@Query("userId") int userId);
}
