package com.vnpt.hotel.manager.ui.presenter.customer;

import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import com.vnpt.hotel.manager.ui.presenter.Presenter;
import com.vnpt.hotel.manager.ui.view.customer.CustomerView;

/**
 * Created by LiKaLi on 3/6/2018.
 */

public interface CreateCustomerPresenter extends Presenter<CustomerView> {
void createCustomer(CustomerCreateRequest customerRequest);
}
