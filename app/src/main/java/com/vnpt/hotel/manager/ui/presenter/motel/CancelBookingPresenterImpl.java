package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.app.utils.StatusCode;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.ui.view.motel.CancelBookingView;


import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 30/3/2018.
 */

public class CancelBookingPresenterImpl implements CancelBookingPresenter{
    private CompositeSubscription subscription;
    private CancelBookingView cancelBookingView;
    @Inject
    MotelDao motelDao;
    @Override
    public void setView(CancelBookingView view) {
        cancelBookingView = view;
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
    public void cancelBooking(String token, Integer userId, Integer bookingId) {
        subscription.add(motelDao.cancelBooking(token, userId, bookingId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<CommonApiResult>>() {
                    @Override
                    public void call(Response<CommonApiResult> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            cancelBookingView.onCancelBookingSuccess(response.body());
                        } else {
                            cancelBookingView.onCancelBookingFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        cancelBookingView.onCancelBookingError(e);
                    }
                }));
    }
}
