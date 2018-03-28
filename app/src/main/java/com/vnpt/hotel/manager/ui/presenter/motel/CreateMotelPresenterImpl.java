package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.interactor.motel.CreateMotel;
import com.vnpt.hotel.manager.domain.interactor.motel.SaveFileHotel;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.view.motel.CreateMotelView;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public class CreateMotelPresenterImpl implements CreateMotelPresenter {
  @Inject CreateMotel createMotel;
  @Inject SaveFileHotel saveFileHotel;
  @Inject AppState appState;
  private CompositeSubscription subscription;
  CreateMotelView view;
  CreateMotelApiResult createMotelResultApi;

  @Override public void setView(CreateMotelView view) {
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

  @Override
  public void createMotel(CreateMotelModel createMotelModel, final List<File> listFileHotel,
      final File fileLicense) {
    subscription.add(createMotel.execute(createMotelModel)
        .flatMap(new Func1<CreateMotelApiResult, Observable<CommonApiResult>>() {
          @Override
          public Observable<CommonApiResult> call(CreateMotelApiResult createMotelApiResult) {
            if (createMotelApiResult.getResponseCode() == 0) {
              createMotelResultApi = createMotelApiResult;
            }
            return saveFileMotel(listFileHotel, fileLicense,createMotelApiResult.getHotelId());
          }
        })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CommonApiResult>() {
          @Override public void call(CommonApiResult commonApiResult) {
            if (commonApiResult.getResponseCode() == 0) {
              view.onCreateMotelComplete(createMotelResultApi);
            } else {
              view.onCreateMotelError(commonApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable e) {
            view.onError();
          }
        }));
  }

  @Override
  public Observable<CommonApiResult> saveFileMotel(List<File> listFileHotel, File fileLicense,int intHotelId) {
    RequestBody hotelId =
        RequestBody.create(MediaType.parse("text/plain"), String.valueOf(intHotelId));

    RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"), fileLicense);
    MultipartBody.Part propertyImagePart =
        MultipartBody.Part.createFormData("businessLicense", fileLicense.getName(), propertyImage);

    MultipartBody.Part[] listImagesHotels = new MultipartBody.Part[listFileHotel.size()];

    for (int index = 0; index < listFileHotel.size()-1; index++) {
      RequestBody picturesBody =
          RequestBody.create(MediaType.parse("image/*"), listFileHotel.get(index));
      listImagesHotels[index] =
          MultipartBody.Part.createFormData("pictures", listFileHotel.get(index).getName(),
              picturesBody);
    }
    return saveFileHotel.execute(hotelId, listImagesHotels, propertyImagePart);
  }
}
