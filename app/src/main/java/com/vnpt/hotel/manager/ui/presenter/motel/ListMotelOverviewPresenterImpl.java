package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.app.utils.StatusCode;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDaoImpl;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelOverviewView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 27/3/2018.
 */

public class ListMotelOverviewPresenterImpl implements ListMotelOverviewPresenter{
    private CompositeSubscription subscription;
    private ListMotelOverviewView listMotelOverviewView;
    @Inject
    MotelDao motelDao;
    @Override
    public void setView(ListMotelOverviewView view) {
        listMotelOverviewView = view;
    }

    @Override
    public void onViewCreate() {
        subscription = new CompositeSubscription();
    }

    @Override
    public void onViewStart() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public void getList(String token, ListMotelOverviewRequest request) {
        subscription.add(motelDao.listHotelOverview(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListMotelOverViewResponse>>() {
                    @Override
                    public void call(Response<ListMotelOverViewResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            listMotelOverviewView.onListMotelOverviewSuccess(response.body());
                        } else {
                            listMotelOverviewView.onListMotelOverviewFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        listMotelOverviewView.onListMotelOverviewError(e);
                    }
                }));
    }
}
