package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.ui.presenter.LoginPresenter;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity
    implements BaseView, LoginView, View.OnClickListener {

  @Inject LoginPresenter loginPresenter;

  @BindView(R.id.edt_number_phone) EditText edtNumberPhone;
  @BindView(R.id.txt_pass_word) EditText edtPassWord;
  @BindView(R.id.btn_dang_nhap) Button btnDangNhap;
  @BindView(R.id.btnForgotPassword) Button btnForgotPassword;
  @BindView(R.id.btnRegister) Button btnRegister;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initView();
    addControls();
    addEvents();
  }

  @Override public void onLoginSuccess(LoginUser loginUser) {
    dismissProgress();
    AppDef.TOKEN_USER = loginUser.getTokenId();
    AppDef.USER_ID = loginUser.getUserId();
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

  @Override public void onLoginFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onLoginError(Throwable e) {
    dismissProgress();
    showToast("Vui lòng kiểm tra lại kết nối mạng của bạn!");
  }

  @Override protected void onStart() {
    super.onStart();
    loginPresenter.onViewStart();
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }

  }

  @Override protected void onResume() {
    super.onResume();
    loginPresenter.onViewResume();
  }

  @Override protected void onPause() {
    super.onPause();
    loginPresenter.onViewPause();
  }

  @Override protected void onStop() {
    super.onStop();
    loginPresenter.onViewStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    loginPresenter.onViewDestroy();
    dismissProgress();
    EventBus.getDefault().unregister(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_dang_nhap:
        if (!edtNumberPhone.getText().toString().trim().isEmpty() && !edtPassWord.getText()
            .toString()
            .trim()
            .isEmpty()) {
          showProgress(getString(R.string.str_dang_tai_du_lieu));
          loginPresenter.login(edtNumberPhone.getText().toString().trim(),
              edtPassWord.getText().toString().trim());
        } else {
          showToast("Số điện thoại và mật khẩu không được để trống");
        }
        break;
      case R.id.btnRegister:
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        break;
    }
  }

  @Override public void initView() {
    loginPresenter.setView(this);
    loginPresenter.onViewCreate();
  }

  @Override public void addControls() {

  }

  @Override public void addEvents() {
    btnDangNhap.setOnClickListener(this);
    btnRegister.setOnClickListener(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onMessageEvent(MessageEvent event) {
    edtNumberPhone.setText(event.getUserLogin().getUsernameLogin());
    edtPassWord.setText(event.getUserLogin().getPasswordLogin());
    showProgress();
    loginPresenter.login(edtNumberPhone.getText().toString().trim(),
        edtPassWord.getText().toString().trim());
    /* Do something */
  }
}
