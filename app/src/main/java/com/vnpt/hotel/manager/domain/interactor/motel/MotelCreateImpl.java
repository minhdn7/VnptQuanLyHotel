package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;
import com.vnpt.hotel.manager.domain.repository.api.motel.CreateMotelApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class MotelCreateImpl implements CreateMotel {
  @Inject CreateMotelApi createMotelApi;

  @Override public Observable<CreateMotelApiResult> execute(CreateMotelModel createMotelModel) {
    return createMotelApi.createMotelApi(createMotelModel);
  }
}
