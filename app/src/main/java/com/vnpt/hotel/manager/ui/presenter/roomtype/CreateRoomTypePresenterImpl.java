package com.vnpt.hotel.manager.ui.presenter.roomtype;

import com.vnpt.hotel.manager.domain.interactor.roomtype.RoomTypeCreate;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.domain.model.roomtype.GetListAmenitiesResult;
import com.vnpt.hotel.manager.ui.view.roomtype.CreateRoomTypeView;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/12/2018.
 */

public class CreateRoomTypePresenterImpl implements CreateRoomTypePresenter {
  CreateRoomTypeView view;
  CompositeSubscription subscription;
  @Inject RoomTypeCreate roomTypeCreate;
  CreateRoomTypeResult roomTypeResult;

  @Override public void setView(CreateRoomTypeView view) {
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

  @Override public void createRoomType(CreateRoomType createRoomType,final List<File> listImages) {
    subscription.add(roomTypeCreate.createRoomType(createRoomType)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CreateRoomTypeResult>() {
          @Override public void call(CreateRoomTypeResult createRoomTypeResult) {
            if (createRoomTypeResult.getResponseCode() == 0) {
              roomTypeResult = createRoomTypeResult;
              uploadImageRoomType(listImages);
            } else {
              view.onCreateRoomTypeApiFailed(createRoomTypeResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }

  private void uploadImageRoomType(List<File> imagesRoomtype) {
    MultipartBody.Part[] listImagesHotels = new MultipartBody.Part[imagesRoomtype.size()];

    for (int index = 0; index < imagesRoomtype.size() - 1; index++) {
      RequestBody picturesBody =
          RequestBody.create(MediaType.parse("image/*"), imagesRoomtype.get(index));
      listImagesHotels[index] =
          MultipartBody.Part.createFormData("pictures", imagesRoomtype.get(index).getName(),
              picturesBody);
    }
    subscription.add(roomTypeCreate.saveFileRoomType(listImagesHotels)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CommonApiResult>() {
          @Override public void call(CommonApiResult commonApiResult) {
            if (commonApiResult.getResponseCode() == 0) {
              view.onCreateRoomTypeApiSuccess(roomTypeResult);
            } else {
              view.onCreateRoomTypeApiFailed(commonApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }

  @Override public void saveAmenities(long roomTypeId, String amenities) {
    subscription.add(roomTypeCreate.saveAmenities(roomTypeId,amenities)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CommonApiResult>() {
          @Override public void call(CommonApiResult commonApiResult) {
            if (commonApiResult.getResponseCode() == 0) {
              view.onSaveAmenitiesApiSuccess();
            } else {
              view.onSaveFileRoomTypeApiFailed(commonApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }

  @Override public void createRoomTypePriceApi(CreateRoomTypePrice createRoomTypePrice) {
    subscription.add(roomTypeCreate.createRoomTypePrice(createRoomTypePrice)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CommonApiResult>() {
          @Override public void call(CommonApiResult commonApiResult) {
            if (commonApiResult.getResponseCode() == 0) {
              view.onCreateRoomTypePriceApiSuccess();
            } else {
              view.onCreateRoomTypePriceApiFailed(commonApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }

  @Override public void getListAmenities(String typeRoom) {
    subscription.add(roomTypeCreate.getAmenitiesRoomType(typeRoom)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<GetListAmenitiesResult>() {
          @Override public void call(GetListAmenitiesResult getListAmenitiesResult) {
            if (getListAmenitiesResult.getResponseCode() == 0) {
              view.onGetAmenitiesApiSuccess(getListAmenitiesResult.getAmenities());
            } else {
              view.onGetAmenitiesApiFailed(getListAmenitiesResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError(throwable);
          }
        }));
  }
}
