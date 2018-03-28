package com.vnpt.hotel.manager.ui.presenter;

import com.vnpt.hotel.manager.domain.interactor.Login;
import com.vnpt.hotel.manager.domain.interactor.TokenDev;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CustomerCheck;
import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.model.TokenDevApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.domain.repository.LoginUserToken;
import com.vnpt.hotel.manager.ui.view.LoginView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

  private LoginView loginView;
  @Inject LoginUserToken loginUserToken;
  @Inject AppState appState;
  @Inject Login login;
  @Inject TokenDev tokenDev;
  @Inject CustomerCheck customerCheck;
  private CompositeSubscription subscription;

  @Override public void setView(LoginView view) {
    loginView = view;
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

  @Override public void login(final String phoneNumber, final String password) {
    subscription.add(login.execute(String.valueOf(phoneNumber), password)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<LoginUser>() {
          @Override public void call(LoginUser loginResult) {
            if (loginResult.getResponseCode() == 0) {
              loginUserToken.put(loginResult.getTokenId());
              storeLoginResult(phoneNumber, password, loginResult);
            } else {
              loginView.onLoginFailed(loginResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable e) {
            loginView.onLoginError(e);
          }
        }));
  }

  @Override
  public void storeLoginResult(String phoneNumber, String password, final LoginUser loginUser) {
    appState.putUserNumber(phoneNumber);
    appState.putUserPassword(password);
    appState.putTokenId(loginUser.getTokenId());
    appState.putUserId(loginUser.getUserId());
    subscription.add(customerCheck.execute(loginUser.getUserId())
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CustomerCheckApiResult>() {
          @Override public void call(CustomerCheckApiResult customerCheckApiResult) {
            if (customerCheckApiResult.getResponseCode() == 0) {
              if (customerCheckApiResult.getCustomerId() != null) {
                appState.putCreateCustomer(customerCheckApiResult.getCustomerId());
              } else {
                appState.putCreateCustomer(-1);
              }
              loginView.onLoginSuccess(loginUser);
            } else {
              loginView.onLoginFailed(customerCheckApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable e) {
            loginView.onLoginError(e);
          }
        }));
  }
}
