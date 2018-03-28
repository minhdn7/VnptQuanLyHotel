package com.vnpt.hotel.manager.ui.presenter;

import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public interface Presenter<T extends View> {
  void setView(T view);

  void onViewCreate();

  void onViewStart();

  void onViewResume();

  void onViewPause();

  void onViewStop();

  void onViewDestroy();
}
