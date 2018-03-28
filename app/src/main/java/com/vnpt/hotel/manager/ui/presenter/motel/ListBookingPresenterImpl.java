package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.app.utils.StatusCode;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.model.request.motel.ListBookingRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListBookingResponse;
import com.vnpt.hotel.manager.ui.view.motel.ListBookingView;


import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListBookingPresenterImpl implements ListBookingPresenter{
    private CompositeSubscription subscription;
    private ListBookingView listBookingView;
    @Inject
    MotelDao motelDao;
    @Override
    public void setView(ListBookingView view) {
        listBookingView = view;
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
    public void getList(String token, ListBookingRequest request) {
        subscription.add(motelDao.listBooking(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListBookingResponse>>() {
                    @Override
                    public void call(Response<ListBookingResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            listBookingView.onListBookingSuccess(response.body());
                        } else {
                            listBookingView.onListBookingFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        listBookingView.onListBookingError(e);
                    }
                }));
    }
}
