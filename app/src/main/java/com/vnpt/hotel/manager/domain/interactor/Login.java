package com.vnpt.hotel.manager.domain.interactor;

import com.vnpt.hotel.manager.domain.model.LoginUser;
import rx.Observable;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public interface Login {
  Observable<LoginUser> execute(String phoneNumber, String password);
}
