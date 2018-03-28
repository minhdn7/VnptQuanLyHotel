package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.MotelModel;

import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface GetMotelList {
  Observable<MotelModel> execute(int userId,int pageIndex,int pageSize);
}
