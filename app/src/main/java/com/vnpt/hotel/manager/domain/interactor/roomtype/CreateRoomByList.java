package com.vnpt.hotel.manager.domain.interactor.roomtype;

import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomList;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomListResult;
import rx.Observable;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public interface CreateRoomByList {
  Observable<CreateRoomListResult> execute(CreateRoomList createRoomList);
}
