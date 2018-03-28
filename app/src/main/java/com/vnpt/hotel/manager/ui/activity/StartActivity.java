package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.ui.presenter.StartPresenter;
import com.vnpt.hotel.manager.ui.view.StartView;
import javax.inject.Inject;

public class StartActivity extends BaseActivity implements StartView {
  @Inject
  StartPresenter startPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
    startPresenter.setView(this);
    startPresenter.onViewCreate();
  }

  @Override public void onLoadUserSuccess() {
    startNext(MainActivity.class);
  }

  @Override public void onLoadUserFailed() {
    startNext(LoginActivity.class);
  }

  private void startNext(final Class<?> cls) {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        dismissProgress();
        Intent intent = new Intent(StartActivity.this, cls);
        startActivity(intent);
        finish();
      }
    }, 2000L);
  }
}
