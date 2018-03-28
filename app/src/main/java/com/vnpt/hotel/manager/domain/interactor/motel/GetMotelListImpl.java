package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.motel.MotelFindByUser;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.domain.repository.api.motel.GetListMotelApi;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class GetMotelListImpl implements GetMotelList {
  @Inject GetListMotelApi getListMotelApi;
  @Override public Observable<MotelModel> execute(int userId,int pageIndex,int pageSize) {
    MotelFindByUser findByUser = new MotelFindByUser();
    findByUser.setUserId(userId);
    findByUser.setPageIndex(pageIndex);
    findByUser.setPageSize(pageSize);
    return getListMotelApi.getListMotelApi(findByUser);
  }
}
