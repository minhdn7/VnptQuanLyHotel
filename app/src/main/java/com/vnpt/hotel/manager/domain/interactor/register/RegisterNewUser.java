package com.vnpt.hotel.manager.domain.interactor.register;

import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import rx.Observable;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public interface RegisterNewUser {
  Observable<RegisterApiResult> execute(NewUser newUser);
}
