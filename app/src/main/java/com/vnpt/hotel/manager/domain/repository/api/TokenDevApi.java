package com.vnpt.hotel.manager.domain.repository.api;

import com.vnpt.hotel.manager.domain.model.TokenDevApiResult;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface TokenDevApi {
  @GET("vnptline/rest/token/get") Observable<TokenDevApiResult> TokenDevApi();
}
