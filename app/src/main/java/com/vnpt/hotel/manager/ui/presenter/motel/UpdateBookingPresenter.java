package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.model.request.motel.UpdateBookingRequest;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.UpdateBookingView;

/**
 * Created by MinhDN on 30/3/2018.
 */

public interface UpdateBookingPresenter extends Presenter<UpdateBookingView> {
    void updateBooking(String token, UpdateBookingRequest request);
}
