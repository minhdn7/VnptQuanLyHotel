package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.domain.model.register.NewUser;
import com.vnpt.hotel.manager.domain.model.register.RegisterApiResult;
import com.vnpt.hotel.manager.ui.presenter.register.RegisterPresenter;
import com.vnpt.hotel.manager.ui.view.register.RegisterView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements RegisterView, View.OnClickListener {

  @BindView(R.id.edit_pass) EditText editPass;
  @BindView(R.id.edit_number_phone) EditText editNumberPhone;
  @BindView(R.id.btn_dang_ky) Button btnDangKy;
  private NewUser newUser;

  @Inject RegisterPresenter registerPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    registerPresenter.setView(this);
    registerPresenter.onViewCreate();
    btnDangKy.setOnClickListener(this);
  }

  @Override public void onRegisterComplete() {
    dismissProgress();
    showCallbacks();
  }

  @Override public void onRegisterError(RegisterApiResult registerApiResult) {
    dismissProgress();
    showToast(registerApiResult.getResponseMessage());
  }

  @Override public void onError() {
    dismissProgress();
    showToast("Vui lòng kiểm tra lại kết nối mạng của bạn!");
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_dang_ky:
        registerNewUser();

        break;
    }
  }

  private void registerNewUser() {
    if (!editNumberPhone.getText().toString().trim().isEmpty() && !editPass.getText()
        .toString()
        .trim()
        .isEmpty()) {
      showProgress();
      List<String> listRole = new ArrayList<>();
      listRole.add("MANAGER");
      newUser = new NewUser();
      newUser.setUsername(editNumberPhone.getText().toString().trim());
      newUser.setPassword(editPass.getText().toString().trim());
      newUser.setRoles(listRole);
      registerPresenter.register(newUser);
    }
  }

  public void showCallbacks() {
    new MaterialDialog.Builder(this).title(getString(R.string.str_thong_bao))
        .content(R.string.str_thong_bao_dang_ki_thanhcong, true)
        .cancelable(false)
        .positiveText(R.string.str_oke)
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            // TODO

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

          }
        })
        .show();
  }
}
