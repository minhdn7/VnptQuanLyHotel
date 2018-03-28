package com.vnpt.hotel.manager.domain.interactor.cutomer;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import rx.Observable;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public interface CreateCustomer {
  Observable<CustomerCheckApiResult> execute(CustomerCreateRequest customerCreate);
}
