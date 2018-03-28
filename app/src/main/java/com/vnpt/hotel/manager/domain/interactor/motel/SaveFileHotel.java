package com.vnpt.hotel.manager.domain.interactor.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public interface SaveFileHotel {
  Observable<CommonApiResult> execute(RequestBody hotelId,
      MultipartBody.Part[] pictures, MultipartBody.Part businessLicense);
}
