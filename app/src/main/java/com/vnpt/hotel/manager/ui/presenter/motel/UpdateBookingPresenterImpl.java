package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.app.utils.StatusCode;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.request.motel.UpdateBookingRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.UpdateBookingResponse;
import com.vnpt.hotel.manager.ui.view.motel.CancelBookingView;
import com.vnpt.hotel.manager.ui.view.motel.UpdateBookingView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 30/3/2018.
 */

public class UpdateBookingPresenterImpl implements UpdateBookingPresenter{
    private CompositeSubscription subscription;
    private UpdateBookingView updateBookingView;
    @Inject
    MotelDao motelDao;


    @Override
    public void setView(UpdateBookingView view) {
       this.updateBookingView = view;
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
    public void updateBooking(String token, UpdateBookingRequest request) {
        subscription.add(motelDao.updateBooking(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<UpdateBookingResponse>>() {
                    @Override
                    public void call(Response<UpdateBookingResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            updateBookingView.onUpdateBookingSuccess(response.body());
                        } else {
                            updateBookingView.onUpdateBookingFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        updateBookingView.onUpdateBookingError(e);
                    }
                }));
    }
}
