package com.vnpt.hotel.manager.ui.presenter.register;

import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.register.RegisterView;

/**
 * Created by LiKaLi on 3/2/2018.
 */

public interface RegisterPresenter extends Presenter<RegisterView> {
  void setView(RegisterView view);

  void register(NewUser newUser);
}
