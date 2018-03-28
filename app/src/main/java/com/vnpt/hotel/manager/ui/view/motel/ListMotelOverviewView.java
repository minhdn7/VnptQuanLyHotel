package com.vnpt.hotel.manager.ui.view.motel;

import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by MinhDN on 27/3/2018.
 */

public interface ListMotelOverviewView extends View {
    void onListMotelOverviewSuccess(ListMotelOverViewResponse response);

    void onListMotelOverviewFailed(String message);

    void onListMotelOverviewError(Throwable e);
}
