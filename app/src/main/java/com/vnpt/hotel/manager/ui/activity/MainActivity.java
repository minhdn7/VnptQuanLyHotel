package com.vnpt.hotel.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.domain.model.request.motel.ListMotelOverviewRequest;
import com.vnpt.hotel.manager.domain.model.response.motel.ListMotelOverViewResponse;
import com.vnpt.hotel.manager.ui.fragment.HotelFragment;
import com.vnpt.hotel.manager.ui.fragment.ListStaffFragment;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListMotelOverviewPresenterImpl;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.motel.ListMotelOverviewView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class MainActivity extends BaseActivity
    implements BaseView, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

  @BindView(R.id.nav_view) NavigationView navigationView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.frame_layout_content) FrameLayout frameContent;
  @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;

  LinearLayout menuHotel, menuEmployees, btnLogout, menu_notifications;



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    addEvents();
  }

  @Override public void initView() {

    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close) {
          /** Called when a drawer has settled in a completely closed state. */
          public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            // Do whatever you want here
          }

          /** Called when a drawer has settled in a completely open state. */
          public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            // Do whatever you want
          }
        };
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    View headerView = getLayoutInflater().inflate(R.layout.nav_header_home, navigationView, false);
    navigationView.addHeaderView(headerView);
    menuHotel = (LinearLayout) headerView.findViewById(R.id.menu_hotel);
    menuEmployees = (LinearLayout) headerView.findViewById(R.id.menu_employees);
    btnLogout = (LinearLayout) headerView.findViewById(R.id.btnLogout);
    menu_notifications = (LinearLayout) headerView.findViewById(R.id.menu_notifications);
    gotoHotelFragment();
  }

  @Override public void addControls() {

  }

  @Override public void addEvents() {
    menuHotel.setOnClickListener(this);
    menu_notifications.setOnClickListener(this);
    menuEmployees.setOnClickListener(this);
    btnLogout.setOnClickListener(this);
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onClick(View view) {
    Fragment fragment = null;
    Class fragmentClass = null;
    switch (view.getId()) {
      case R.id.menu_hotel:
        fragmentClass = HotelFragment.class;
        break;
      case R.id.menu_employees:
        fragmentClass = ListStaffFragment.class;
        break;
      case R.id.menu_notifications:
        break;
      case R.id.btnLogout:
        showDialogLogout("Bạn thực sự muốn thoát ứng dụng");
        drawerLayout.closeDrawers();
        return;
    }
    try {
      fragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.frame_layout_content, fragment).commit();
    drawerLayout.closeDrawers();
  }

  private void gotoHotelFragment() {
    tvToolbarTitle.setText("Quản lý nhà nghỉ");
    Fragment fragment = new HotelFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.frame_layout_content, fragment).commit();
  }

  public void showDialogLogout(String contentDialog) {
    new MaterialDialog.Builder(this).title(R.string.str_thong_bao)
        .content(contentDialog)
        .positiveText(R.string.str_oke)
        .negativeText(R.string.str_cancel)
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            dialog.dismiss();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
              @Override
              public void run() {
                dismissProgress();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
              }
            }, 2000L);
          }
        })
        .onNegative(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            dialog.dismiss();
          }
        })
        .show();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
    // Better check that an event was actually posted before
    if(stickyEvent != null) {
      // "Consume" the sticky event
      EventBus.getDefault().removeStickyEvent(stickyEvent);
      // Now do something with it
    }
  }

  @Override
  public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 0) {
      getFragmentManager().popBackStack();
    } else {
//      super.onBackPressed();
    }
  }

}
