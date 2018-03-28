package com.vnpt.hotel.manager.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.utils.Config;
import com.vnpt.hotel.manager.app.utils.NetworkStateChanged;
import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.domain.repository.Preferences;
import com.vnpt.hotel.manager.ui.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pl.tajchert.nammu.Nammu;

/**
 * Created by LiKaLi on 1/25/2018.
 */

public class BaseActivity extends AppCompatActivity implements View {
  private Toast toast;
  MaterialDialog dialog;
  private AlertDialog alertDialog;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Nammu.init(this);
    injectDependencies();
  }

  @Override public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  public int dpToPx(int dp) {
    Resources r = getResources();
    return Math.round(
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
  }

  public void showToast(String message) {
    if (toast != null) {
      toast.cancel();
      toast = null;
    }
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
    toast.show();
  }

  @Override public void onLoadLoginUser(LoginUser loginUser) {

  }

  public void showProgress(String titleProgress) {
    if (dialog == null) {
      dialog = new MaterialDialog.Builder(this).title(titleProgress)
          .content(R.string.str_vui_long_doi)
          .progress(true, 0)
          .progressIndeterminateStyle(true)
          .cancelable(false)
          .show();
    }
  }

  public void showProgress() {
    if (dialog == null) {
      dialog = new MaterialDialog.Builder(this).content(R.string.str_vui_long_doi)
          .progress(true, 0)
          .progressIndeterminateStyle(true)
          .cancelable(false)
          .show();
    }
  }

  public void showDialog(String contentDialog) {
    MaterialDialog dialog = new MaterialDialog.Builder(this).title(R.string.str_thong_bao)
        .content(contentDialog)
        .positiveText(R.string.str_oke)
        .dismissListener(new DialogInterface.OnDismissListener() {
          @Override public void onDismiss(DialogInterface dialogInterface) {
            finish();
          }
        })
        .show();
  }

  public void showDialog(String contentDialog, final boolean isFinish) {
    MaterialDialog dialog = new MaterialDialog.Builder(this).title(R.string.str_thong_bao)
        .content(contentDialog)
        .positiveText(R.string.str_oke)
        .dismissListener(new DialogInterface.OnDismissListener() {
          @Override public void onDismiss(DialogInterface dialogInterface) {
            if (isFinish) {
              finish();
            }
          }
        })
        .show();
  }

  public void dismissProgress() {
    if (dialog != null && dialog.isShowing()) {
      dialog.dismiss();
      dialog = null;
    }
  }

  public void startNext(final Context mContext, final Class<?> cls) {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        dismissProgress();
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
        finish();
      }
    }, 2000L);
  }

  protected void injectDependencies() {
    ((ManagerApplication) getApplication()).inject(this);
  }

  private boolean isNetworkConnected() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    return cm.getActiveNetworkInfo() != null;
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(final NetworkStateChanged networkStateChanged) {

    if (networkStateChanged.isInternetConnected()) {

      if(alertDialog.isShowing()){
        alertDialog.dismiss();
      }

    } else {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mất kết nối mạng");
        builder.setMessage("Vui lòng kiểm tra lại kết nối");
        builder.setCancelable(false);
        builder.setPositiveButton("Thử lại", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
          }
        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new android.view.View.OnClickListener() {
          @Override
          public void onClick(android.view.View view) {
            if (isNetworkConnected()) {
              alertDialog.dismiss();
              finish();
              startActivity(getIntent());
            } else {
              Intent intent = new Intent(Settings.ACTION_SETTINGS);
              startActivityForResult(intent, 0);
            }
          }
        });
    }
  }
}
