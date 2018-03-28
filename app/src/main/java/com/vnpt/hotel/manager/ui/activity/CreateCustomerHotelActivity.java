package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.domain.model.customer.CustomerCreateRequest;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.presenter.customer.CreateCustomerPresenter;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.customer.CustomerView;
import javax.inject.Inject;

public class CreateCustomerHotelActivity extends BaseActivity implements BaseView, CustomerView {

  @Inject CreateCustomerPresenter customerPresenter;
  @Inject AppState appState;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.edt_name_customer) EditText edtNameCustomer;
  @BindView(R.id.edt_identity_customer) EditText edtIdentityCustomer;
  @BindView(R.id.edt_address_customer) EditText edtAddressCustomer;
  @BindView(R.id.edt_phone_customer) EditText edtPhoneCustomer;
  @BindView(R.id.btn_create_customer) TextView btnCreateCustomer;
  @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;
  CustomerCreateRequest customerRequest;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_customer_hotel);
    customerPresenter.setView(this);
    customerPresenter.onViewCreate();
    initView();
    addControls();
    addEvents();
  }

  @Override public void initView() {
    initToolbar();
  }

  @Override public void addControls() {
  }

  @Override public void addEvents() {

  }

  private void initToolbar() {
    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    tvToolbarTitle.setText("Thông tin pháp lý");
  }

  @OnClick(R.id.btn_create_customer) public void createCustomer() {
    // TODO submit data to server...
    if (edtNameCustomer.getText().toString().trim().isEmpty()
        || edtIdentityCustomer.getText()
        .toString()
        .trim()
        .isEmpty()
        || edtAddressCustomer.getText().toString().trim().isEmpty()
        || edtPhoneCustomer.getText().toString().trim().isEmpty()) {
      showToast("Vui lòng nhập các trường còn trống");
    } else {
      customerRequest = new CustomerCreateRequest();
      customerRequest.setName(edtNameCustomer.getText().toString().trim());
      customerRequest.setIdentity(edtIdentityCustomer.getText().toString().trim());
      customerRequest.setAddress(edtAddressCustomer.getText().toString().trim());
      customerRequest.setPhone(edtPhoneCustomer.getText().toString().trim());
      customerRequest.setUserId(appState.getUserId());

      showProgress();
      customerPresenter.createCustomer(customerRequest);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onCreateCustomerSuccess() {
    dismissProgress();
    showCallbacks();
  }

  @Override public void onCreateCustomerFailed(String message) {
    dismissProgress();
    showDialog(message);
  }

  @Override public void onCreateCustomerError(Throwable e) {
    dismissProgress();
    showToast(getString(R.string.str_ket_noi_mang_error));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    customerPresenter.onViewDestroy();
  }

  public void showCallbacks() {
    new MaterialDialog.Builder(this).title(getString(R.string.str_thong_bao))
        .content(R.string.str_cap_nhap_thong_tin_customer, true)
        .cancelable(false)
        .positiveText(getString(R.string.str_oke))
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            // TODO
            startNext(CreateCustomerHotelActivity.this, CreateAndEditHotelActivity.class);
          }
        })
        .show();
  }
}