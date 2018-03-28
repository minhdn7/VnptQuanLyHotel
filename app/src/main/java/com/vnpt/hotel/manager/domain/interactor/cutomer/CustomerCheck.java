package com.vnpt.hotel.manager.domain.interactor.cutomer;

import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import rx.Observable;

/**
 * Created by LiKaLi on 3/7/2018.
 */

public interface CustomerCheck {
  Observable<CustomerCheckApiResult> execute(int userId);
}
