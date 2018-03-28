package com.vnpt.hotel.manager.ui.presenter.roomtype;

import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotel;
import com.vnpt.hotel.manager.domain.model.roomtype.RoomTypeFindByHotelResult;
import com.vnpt.hotel.manager.ui.view.roomtype.RoomTypeFindByHotelView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class RoomTypeFindByHotelPresenterImpl implements RoomTypeFindByHotelPresenter {
  RoomTypeFindByHotelView view;
  CompositeSubscription subscription;
  @Inject RoomTypeByHotel roomTypeByHotel;

  @Override public void setView(RoomTypeFindByHotelView view) {
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

  @Override public void roomTypeFindByHotel(final RoomTypeFindByHotel roomTypeFindByHotel) {
    subscription.add(roomTypeByHotel.execute(roomTypeFindByHotel)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<RoomTypeFindByHotelResult>() {
          @Override public void call(RoomTypeFindByHotelResult roomTypeFindByHotelResult) {
            if (roomTypeFindByHotelResult.getResponseCode() == 0) {
              view.onRoomTypeByHotelSuccess(roomTypeFindByHotelResult);
            } else {
              view.onRoomTypeByHotelFailed(roomTypeFindByHotelResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }
}
