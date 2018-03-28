package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by MinhDN on 28/3/2018.
 */

public interface ListBookingView extends View {
    void onListBookingSuccess(ListBookingResponse response);

    void onListBookingFailed(String message);

    void onListBookingError(Throwable e);
}
