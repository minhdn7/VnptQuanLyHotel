package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by MinhDN on 30/3/2018.
 */

public interface CancelBookingView extends View {
    void onCancelBookingSuccess(CommonApiResult response);

    void onCancelBookingFailed(String message);

    void onCancelBookingError(Throwable e);
}
