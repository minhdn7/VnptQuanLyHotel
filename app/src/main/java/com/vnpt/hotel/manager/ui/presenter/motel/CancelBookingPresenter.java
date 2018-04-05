package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.model.request.motel.UpdateBookingRequest;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.CancelBookingView;
import com.vnpt.hotel.manager.ui.view.motel.UpdateBookingView;

/**
 * Created by MinhDN on 30/3/2018.
 */

public interface CancelBookingPresenter extends Presenter<CancelBookingView> {
    void cancelBooking(String token, Integer userId, Integer bookingId);
}
