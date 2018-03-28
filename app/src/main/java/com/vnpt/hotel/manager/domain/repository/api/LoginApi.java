package com.vnpt.hotel.manager.domain.repository.api;

import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.domain.model.UserLogin;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public interface LoginApi {
  @POST("vnptline/rest/user/login/") Observable<LoginUser> loginApi(@Body UserLogin userLogin);
}
