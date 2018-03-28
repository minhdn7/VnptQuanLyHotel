package com.vnpt.hotel.manager.domain.repository.api.register;

import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public interface RegisterApi {
  @POST("vnptline/rest/user/registration") Observable<RegisterApiResult> registerNewUser(@Body NewUser newUser);
}
