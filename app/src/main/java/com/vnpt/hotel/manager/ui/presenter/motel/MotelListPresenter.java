package com.vnpt.hotel.manager.ui.presenter.motel;

import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelView;

/**
 * Created by LiKaLi on 3/8/2018.
 */

public interface MotelListPresenter extends Presenter<ListMotelView> {
  void getListMotel(int userId,int pageIndex,int pageSize);
}
