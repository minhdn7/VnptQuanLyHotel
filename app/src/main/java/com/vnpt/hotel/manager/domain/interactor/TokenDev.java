package com.vnpt.hotel.manager.domain.interactor;

import com.vnpt.hotel.manager.domain.model.TokenDevApiResult;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface TokenDev {
  Observable<TokenDevApiResult> execute();
}
