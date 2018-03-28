package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.repository.api.motel.SaveFileHotelApi;
import javax.inject.Inject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public class SaveFileHotelImpl implements SaveFileHotel {
  @Inject SaveFileHotelApi saveFileHotelApi;
  @Override
  public Observable<CommonApiResult> execute(RequestBody hotelId, MultipartBody.Part[] pictures,
      MultipartBody.Part businessLicense) {
    return saveFileHotelApi.SaveFileHotelApi(hotelId, pictures, businessLicense);
  }
}
