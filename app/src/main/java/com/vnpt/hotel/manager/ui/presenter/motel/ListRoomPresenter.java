package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.ListRoomView;

/**
 * Created by MinhDN on 28/3/2018.
 */

public interface ListRoomPresenter extends Presenter<ListRoomView> {
    void getList(String token, Integer hotelId);
}
