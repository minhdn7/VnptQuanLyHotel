package com.vnpt.hotel.manager.ui.presenter.customer;

import com.vnpt.hotel.manager.domain.interactor.cutomer.CreateCustomer;
import com.vnpt.hotel.manager.domain.interactor.cutomer.CustomerCheck;
import com.vnpt.hotel.manager.domain.model.CommonApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCheckApiResult;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.view.customer.CustomerView;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public class CreateCustomerPresenterImpl implements CreateCustomerPresenter {

  @Inject CreateCustomer createCustomer;
  @Inject AppState appState;
  private CustomerView view;
  private CompositeSubscription subscription;

  @Override public void setView(CustomerView view) {
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

  @Override public void createCustomer(CustomerCreateRequest customerRequest) {
    subscription.add(createCustomer.execute(customerRequest)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<CustomerCheckApiResult>() {
          @Override public void call(CustomerCheckApiResult customerCheckApiResult) {
            if (customerCheckApiResult.getResponseCode() == 0) {
              appState.putCreateCustomer(customerCheckApiResult.getCustomerId());
              view.onCreateCustomerSuccess();
            } else {
              view.onCreateCustomerFailed(customerCheckApiResult.getResponseMessage());
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable e) {
            view.onCreateCustomerError(e);
          }
        }));
  }
}
