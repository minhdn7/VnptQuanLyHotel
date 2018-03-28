package com.vnpt.hotel.manager.domain.interactor.cutomer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import com.vnpt.hotel.manager.domain.repository.api.customer.CreateCustomerApi;
import java.lang.reflect.Type;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public class CreateCustomerImpl implements CreateCustomer {
  @Inject CreateCustomerApi customerApi;

  @Override public Observable<CustomerCheckApiResult> execute(CustomerCreateRequest customerCreate) {
    return customerApi.createCustomerApi(customerCreate);
  }
}
