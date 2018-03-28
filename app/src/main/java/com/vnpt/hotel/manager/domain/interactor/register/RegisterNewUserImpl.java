package com.vnpt.hotel.manager.domain.interactor.register;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import com.vnpt.hotel.manager.domain.repository.api.register.RegisterApi;
import java.lang.reflect.Type;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public class RegisterNewUserImpl implements RegisterNewUser {
  @Inject RegisterApi registerApi;

  @Override public Observable<RegisterApiResult> execute(NewUser newUser) {
    return registerApi.registerNewUser(newUser);
  }
}
