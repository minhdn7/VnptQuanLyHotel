package com.vnpt.hotel.manager.domain.repository.api.motel;

import com.vnpt.hotel.manager.domain.model.motel.MotelFindByUser;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface GetListMotelApi {
  @POST("vnptline/rest/staff/hotel/findByUser") Observable<MotelModel> getListMotelApi(@Body
      MotelFindByUser findByUser);
}
