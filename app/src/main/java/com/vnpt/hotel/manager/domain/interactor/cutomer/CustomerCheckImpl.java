package com.vnpt.hotel.manager.domain.interactor.cutomer;

import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.repository.api.customer.CustomerCheckApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/7/2018.
 */

public class CustomerCheckImpl implements CustomerCheck {
  @Inject CustomerCheckApi customerCheckApi;
  @Override public Observable<CustomerCheckApiResult> execute(int userId) {

    return customerCheckApi.customerCheckApi(userId);
  }
}
