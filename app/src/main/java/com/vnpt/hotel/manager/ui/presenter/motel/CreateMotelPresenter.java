package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.CreateMotelView;
import java.io.File;
import java.util.List;
import rx.Observable;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface CreateMotelPresenter extends Presenter<CreateMotelView> {
  void createMotel(CreateMotelModel createMotelModel, List<File> listFileHotel, File fileLicense);

  Observable<CommonApiResult> saveFileMotel(List<File> listFileHotel, File fileLicense,int hotelId);
}
