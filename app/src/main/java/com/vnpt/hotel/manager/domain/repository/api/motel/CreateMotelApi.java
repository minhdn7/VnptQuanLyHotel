package com.vnpt.hotel.manager.domain.repository.api.motel;

import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface CreateMotelApi {
  @POST("vnptline/rest/manager/hotel/create")
  Observable<CreateMotelApiResult> createMotelApi(@Body CreateMotelModel createMotelModel);
}
