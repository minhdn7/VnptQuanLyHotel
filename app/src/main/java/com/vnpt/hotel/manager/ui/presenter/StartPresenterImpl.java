package com.vnpt.hotel.manager.ui.presenter;

import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.view.StartView;
import javax.inject.Inject;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class StartPresenterImpl implements StartPresenter {
  @Inject AppState appState;

  private StartView startView;
  public StartPresenterImpl() {}
  @Override public void setView(StartView view) {
    this.startView = view;
  }

  @Override public void onViewCreate() {
    if (!appState.getTokenFireBase().isEmpty()
        && !appState.getUserNumber().isEmpty()
        && !appState.getUserPassword().isEmpty()) {
      startView.onLoadUserSuccess();
    } else {
      startView.onLoadUserFailed();
    }
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

  }
}
