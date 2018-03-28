package com.vnpt.hotel.manager.app;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.LoginUser;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by LiKaLi on 1/25/2018.
 */

public abstract class BaseFragment extends Fragment implements com.vnpt.hotel.manager.ui.view.View {
  private Toast toast;
  MaterialDialog dialog;
  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectDependencies();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(getFragmentLayout(), container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injectViews(view);
  }

  @Override public void onLoadLoginUser(LoginUser loginUser) {

  }

  protected abstract int getFragmentLayout();

  private void injectViews(final View view) {
    ButterKnife.bind(this, view);
  }
  /**
   * Converting dp to pixel
   */
  public int dpToPx(int dp) {
    Resources r = getResources();
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
  }
  protected void injectDependencies() {
    ((ManagerApplication) getActivity().getApplication()).inject(this);
  }
  protected void showProgress() {
    if (getActivity() instanceof BaseActivity) {
      ((BaseActivity) getActivity()).showProgress();
    }
  }
  protected void dismissProgress() {
    if (getActivity() instanceof BaseActivity) {
      ((BaseActivity) getActivity()).dismissProgress();
    }
  }

  public void showDialog(String contentDialog) {
    MaterialDialog dialog = new MaterialDialog.Builder(getActivity()).title(R.string.str_thong_bao)
            .content(contentDialog)
            .positiveText(R.string.str_oke)
            .dismissListener(new DialogInterface.OnDismissListener() {
              @Override public void onDismiss(DialogInterface dialogInterface) {
                  dialogInterface.dismiss();
              }
            })
            .show();
  }
}
