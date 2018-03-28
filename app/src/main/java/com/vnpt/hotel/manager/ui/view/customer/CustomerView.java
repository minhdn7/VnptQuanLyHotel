package com.vnpt.hotel.manager.ui.view.customer;

import com.vnpt.hotel.manager.ui.view.View;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public interface CustomerView extends View {
  void onCreateCustomerSuccess();

  void onCreateCustomerFailed(String message);

  void onCreateCustomerError(Throwable e);
}
