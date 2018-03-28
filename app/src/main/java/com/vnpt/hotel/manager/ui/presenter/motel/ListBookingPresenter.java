package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.ListBookingView;


/**
 * Created by MinhDN on 28/3/2018.
 */

public interface ListBookingPresenter extends Presenter<ListBookingView> {
    void getList(String token, ListBookingRequest request);
}
