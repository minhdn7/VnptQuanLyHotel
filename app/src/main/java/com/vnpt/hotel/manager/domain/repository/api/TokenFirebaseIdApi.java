package com.vnpt.hotel.manager.domain.repository.api;

import rx.Observable;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public interface TokenFirebaseIdApi {
  Observable<String> generate();
}
