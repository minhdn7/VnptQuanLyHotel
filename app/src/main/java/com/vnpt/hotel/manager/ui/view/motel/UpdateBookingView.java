package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.response.motel.UpdateBookingResponse;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by MinhDN on 30/3/2018.
 */

public interface UpdateBookingView extends View {
    void onUpdateBookingSuccess(UpdateBookingResponse response);

    void onUpdateBookingFailed(String message);

    void onUpdateBookingError(Throwable e);
}
