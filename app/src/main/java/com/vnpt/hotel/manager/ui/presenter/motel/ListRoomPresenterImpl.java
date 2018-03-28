package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.app.utils.StatusCode;
import com.vnpt.hotel.manager.domain.interactor.motel.MotelDao;
import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.ui.view.motel.ListRoomView;

import javax.inject.Inject;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MinhDN on 28/3/2018.
 */

public class ListRoomPresenterImpl implements ListRoomPresenter{
    private CompositeSubscription subscription;
    private ListRoomView listRoomView;
    @Inject
    MotelDao motelDao;
    @Override
    public void setView(ListRoomView view) {
        listRoomView = view;
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
    public void getList(String token, Integer request) {
        subscription.add(motelDao.listRoom(token, request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<ListRoomResponse>>() {
                    @Override
                    public void call(Response<ListRoomResponse> response) {
                        if (response.body().getResponseCode() == StatusCode.RESPONSE_SUCCESS) {
                            listRoomView.onListRoomSuccess(response.body());
                        } else {
                            listRoomView.onListRoomFailed(response.body().getResponseMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        listRoomView.onListRoomError(e);
                    }
                }));
    }
}
