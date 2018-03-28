package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelOverviewView;

import rx.Observable;

/**
 * Created by MinhDN on 27/3/2018.
 */

public interface ListMotelOverviewPresenter extends Presenter<ListMotelOverviewView> {

    void getList(String token, ListMotelOverviewRequest request);
}
