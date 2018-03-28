package com.vnpt.hotel.manager.domain.interactor;

import com.vnpt.hotel.manager.domain.model.TokenDevApiResult;
import com.vnpt.hotel.manager.domain.repository.api.TokenDevApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class TokenDevImpl implements TokenDev {
  @Inject TokenDevApi tokenDevApi;
  @Override public Observable<TokenDevApiResult> execute() {
    return tokenDevApi.TokenDevApi();
  }
}
