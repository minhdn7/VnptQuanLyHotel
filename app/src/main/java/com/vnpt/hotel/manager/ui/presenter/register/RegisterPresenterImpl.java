package com.vnpt.hotel.manager.ui.presenter.register;

import com.vnpt.hotel.manager.domain.interactor.register.RegisterNewUser;
import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import com.vnpt.hotel.manager.ui.presenter.BasePresenter;
import com.vnpt.hotel.manager.ui.view.register.RegisterView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class RegisterPresenterImpl extends BasePresenter implements RegisterPresenter {
  private RegisterView view;
  @Inject RegisterNewUser registerNewUser;

  @Override public void setView(RegisterView view) {
    this.view = view;
  }

  @Override public void register(NewUser newUser) {
    registerNewUser.execute(newUser)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<RegisterApiResult>() {
          @Override public void call(RegisterApiResult registerApiResult) {
            if (registerApiResult != null) {
              view.onRegisterComplete();
            } else {
              view.onRegisterError(registerApiResult);
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            view.onError();
          }
        });
  }
}
