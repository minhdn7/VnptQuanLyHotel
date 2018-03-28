package com.vnpt.hotel.manager.domain.interactor;

import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.model.TokenDevApiResult;
import com.vnpt.hotel.manager.domain.model.UserLogin;
import com.vnpt.hotel.manager.domain.repository.LoginUserToken;
import com.vnpt.hotel.manager.domain.repository.api.LoginApi;
import com.vnpt.hotel.manager.domain.repository.api.TokenFirebaseIdApi;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class LoginImpl implements Login {
  @Inject TokenDev tokenDev;
  @Inject TokenFirebaseIdApi tokenFirebaseIdApi;
  @Inject LoginApi loginApi;
  @Inject LoginUserToken loginUserToken;

  @Override public Observable<LoginUser> execute(final String phoneNumber, final String password) {
    return tokenDev.execute().concatMap(new Func1<TokenDevApiResult, Observable<String>>() {
      @Override public Observable<String> call(TokenDevApiResult tokenDevApiResult) {
        if (tokenDevApiResult.getResponseCode() == 0) {
          loginUserToken.put(tokenDevApiResult.getToken());
        }
        return tokenFirebaseIdApi.generate();
      }
    }).concatMap(new Func1<String, Observable<LoginUser>>() {
      @Override public Observable<LoginUser> call(String tokenFireBaseId) {
        UserLogin userLogin = new UserLogin();
        userLogin.setPassword(password);
        userLogin.setUsername(phoneNumber);
        userLogin.setTokenFirebase(tokenFireBaseId);
        return loginApi.loginApi(userLogin);
      }
    });
  }
}
