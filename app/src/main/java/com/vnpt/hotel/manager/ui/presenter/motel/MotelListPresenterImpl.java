package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.interactor.motel.GetMotelList;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelView;

import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class MotelListPresenterImpl implements MotelListPresenter {
  @Inject GetMotelList getMotelList;
  ListMotelView view;
  private CompositeSubscription subscription;

  @Override public void setView(ListMotelView view) {
    this.view = view;
  }

  @Override public void onViewCreate() {
    subscription = new CompositeSubscription();
  }

  @Override public void onViewStart() {

  }

  @Override public void onViewResume() {

  }

  @Override public void onViewPause() {

  }

  @Override public void onViewStop() {

  }

  @Override public void onViewDestroy() {
    subscription.unsubscribe();
  }

  @Override public void getListMotel(int userId,int pageIndex,int pageSize) {
    subscription.add(getMotelList.execute(userId,pageIndex,pageSize)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<MotelModel>() {
          @Override public void call(MotelModel listMotel) {
            view.onGetListMotelComplete(listMotel);
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable e) {
            view.onError();
          }
        }));
  }
}
