package com.vnpt.hotel.manager.ui.presenter.roomtype;

import com.vnpt.hotel.manager.domain.interactor.roomtype.CreateRoomByList;
import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomList;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomListResult;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import com.vnpt.hotel.manager.ui.view.roomtype.CreateRoomByListView;
import javax.inject.Inject;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomByListPresenterImpl implements CreateRoomByListPresenter {
  CreateRoomByListView view;
  CompositeSubscription subscription;
  @Inject CreateRoomByList createRoomByList;


  @Override public void setView(CreateRoomByListView view) {
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

  @Override public void createRoomByList(CreateRoomList createRoomList) {
    subscription.add(createRoomByList.execute(createRoomList)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CreateRoomListResult>() {
          @Override public void call(CreateRoomListResult createRoomListResult) {
            if (createRoomListResult.getResponseCode() == 0) {
              view.onCreateRoomByListSuccess(createRoomListResult);
            } else {
              view.onCreateRoomByListFailed(createRoomListResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }


}
