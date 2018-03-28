package com.vnpt.hotel.manager.domain.repository.api.customer;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import java.util.Map;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public interface CreateCustomerApi {
  @POST("vnptline/rest/manager/customer/create")
  Observable<CustomerCheckApiResult> createCustomerApi(@Body CustomerCreateRequest newCustomer);
}
