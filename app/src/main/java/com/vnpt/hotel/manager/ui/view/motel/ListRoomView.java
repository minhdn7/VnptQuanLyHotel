package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by MinhDN on 28/3/2018.
 */

public interface ListRoomView extends View {
    void onListRoomSuccess(ListRoomResponse response);

    void onListRoomFailed(String message);

    void onListRoomError(Throwable e);
}
