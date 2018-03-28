package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;

import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface CreateMotel {
  Observable<CreateMotelApiResult> execute(CreateMotelModel createMotelModel);
}
