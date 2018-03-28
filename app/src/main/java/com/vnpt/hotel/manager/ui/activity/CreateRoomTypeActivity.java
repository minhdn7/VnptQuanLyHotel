package com.vnpt.hotel.manager.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.anton46.stepsview.StepsView;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomType;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypePrice;
import com.vnpt.hotel.manager.domain.model.roomtype.CreateRoomTypeResult;
import com.vnpt.hotel.manager.domain.model.roomtype.UtilityModel;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.adapter.ImageHotelAdapter;
import com.vnpt.hotel.manager.ui.adapter.UtilityAdapter;
import com.vnpt.hotel.manager.ui.presenter.roomtype.CreateRoomTypePresenter;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.roomtype.CreateRoomTypeView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class CreateRoomTypeActivity extends BaseActivity
    implements BaseView, View.OnClickListener, CreateRoomTypeView {
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tv_toolbar_title) TextView toolbarTitle;
  @BindView(R.id.stepsView) StepsView mStepsCreateRoomType;
  @BindView(R.id.btn_next_step) TextView btnNextStep;
  @BindView(R.id.btn_preview_step) TextView btnPreviewStep;
  @BindView(R.id.ll_step1) LinearLayout llStep1;
  @BindView(R.id.ll_step2) LinearLayout llStep2;
  @BindView(R.id.ll_step3) LinearLayout llStep3;
  @BindView(R.id.edt_name_room_type) EditText edtNameRoomType;
  @BindView(R.id.edt_description_type) EditText edtDescriptionType;
  @BindView(R.id.edt_number_person_room) EditText edtNumberPersonRoom;
  @BindView(R.id.recycler_image_hotel_roomtype) RecyclerView recyclerImageHotelRoomType;
  @BindView(R.id.recycler_utility) RecyclerView recyclerUtility;
  @BindView(R.id.iv_add_utility) ImageView ivAddUtility;
  @BindView(R.id.edt_money_day) EditText edt_money_day;
  @BindView(R.id.edt_hour_late_day) EditText edtHourLateDay;
  @BindView(R.id.edit_money_late_day) EditText editMoneyLateDay;
  @BindView(R.id.edt_hour_late_day_100_money) EditText edtHourLateDay100money;
  @BindView(R.id.edt_hour_first) EditText edtHourFirst;
  @BindView(R.id.edt_money_hour_first) EditText edtMoneyHourFirst;
  @BindView(R.id.edt_money_next_hour) EditText edtMoneyNextHour;
  private final String[] labels = { "Bước 1", "Bước 2", " Bước 3" };
  private ImageHotelAdapter imagesAdapter;
  private UtilityAdapter utilityAdapter;
  private ArrayList<File> photosHotel = new ArrayList<>();
  private ArrayList<UtilityModel> utilitysList = new ArrayList<>();
  @Inject AppState appState;
  @Inject CreateRoomTypePresenter createRoomTypePresenter;
  String listAmenities;
  CreateRoomTypeResult createRoomTypeResult;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_room_type);
    initView();
    addControls();
    addEvents();
    createRoomTypePresenter.setView(this);
    createRoomTypePresenter.onViewStart();
  }

  @Override public void initView() {
    initToolbar();
    initStepView();
    initRecycleview();
    initPhotoImage();
  }

  @Override public void addControls() {
    gotoNextSceen(0);
  }

  @Override public void addEvents() {
    btnNextStep.setOnClickListener(this);
    btnPreviewStep.setOnClickListener(this);
    imagesAdapter.setmOnClickListener(new ImageHotelAdapter.MyOnClickListener() {
      @Override public void onClickItemView(int position) {
        EasyImage.openChooserWithGallery(CreateRoomTypeActivity.this, "Chọn ảnh :", 0);
      }

      @Override public void onClickDeleteView(int position) {
        imagesAdapter.removeAt(position);
      }
    });
    ivAddUtility.setOnClickListener(this);
  }

  private void initToolbar() {
    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbarTitle.setText("Thêm loại phòng");
  }

  private void initStepView() {
    mStepsCreateRoomType.setLabels(labels)
        .setBarColorIndicator(ContextCompat.getColor(this, R.color.md_black))
        .setProgressColorIndicator(ContextCompat.getColor(this, R.color.colorPrimary))
        .setLabelColorIndicator(ContextCompat.getColor(this, R.color.md_black))
        .setCompletedPosition(0)
        .drawView();
  }

  private void initRecycleview() {
    photosHotel.add(new File("Empty"));
    utilitysList.add(new UtilityModel("Điều hòa"));
    utilitysList.add(new UtilityModel("Bình nóng lạnh"));
    imagesAdapter = new ImageHotelAdapter(this, photosHotel);
    recyclerImageHotelRoomType.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerImageHotelRoomType.setHasFixedSize(true);
    recyclerImageHotelRoomType.setAdapter(imagesAdapter);
    utilityAdapter = new UtilityAdapter(this, utilitysList);
    recyclerUtility.setLayoutManager(new GridLayoutManager(this, 2));
    recyclerUtility.setHasFixedSize(true);
    recyclerUtility.setAdapter(utilityAdapter);
  }

  private void initPhotoImage() {
    int permissionCheck =
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      Nammu.askForPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
          new PermissionCallback() {
            @Override public void permissionGranted() {
              //Nothing, this sample saves to Public gallery so it needs permission
            }

            @Override public void permissionRefused() {
              finish();
            }
          });
    }

    EasyImage.configuration(this)
        .setImagesFolderName("Album HotelImage")
        .setCopyTakenPhotosToPublicGalleryAppFolder(true)
        .setCopyPickedImagesToPublicGalleryAppFolder(true)
        .setAllowMultiplePickInGallery(true);

    checkGalleryAppAvailability();
  }

  private void checkGalleryAppAvailability() {
    if (!EasyImage.canDeviceHandleGallery(this)) {
      //Device has no app that handles gallery intent
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
      @Override
      public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
        //Some error handling
        e.printStackTrace();
      }

      @Override
      public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
        onPhotosHotelReturned(imageFiles);
      }

      @Override public void onCanceled(EasyImage.ImageSource source, int type) {
        //Cancel handling, you might wanna remove taken photo if it was canceled
        if (source == EasyImage.ImageSource.CAMERA) {
          File photoFile = EasyImage.lastlyTakenButCanceledPhoto(CreateRoomTypeActivity.this);
          if (photoFile != null) photoFile.delete();
        }
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void onPhotosHotelReturned(List<File> returnedPhotos) {
    photosHotel.addAll(photosHotel.size() - 1, returnedPhotos);
    imagesAdapter.notifyDataSetChanged();
    recyclerImageHotelRoomType.scrollToPosition(photosHotel.size() - 1);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_next_step:
        if (mStepsCreateRoomType.getCompletedPosition() == 0) {

          callApiCreateRoomType();
          //mStepsCreateRoomType.setCompletedPosition(1).drawView();
          //gotoNextSceen(1);
        } else if (mStepsCreateRoomType.getCompletedPosition() == 1) {
          callApiSaveAmenities();
          //gotoNextSceen(2);
          //mStepsCreateRoomType.setCompletedPosition(2).drawView();
        } else if (mStepsCreateRoomType.getCompletedPosition() == 2) {
          callApiCreatePriceRoomType();
          //showProgress(getString(R.string.str_dang_tai_du_lieu));
          //new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
          //  @Override public void run() {
          //    dismissProgress();
          //    appState.putCreateRoomType(true);
          //    showCallbacks();
          //  }
          //}, 2000L);
        }
        break;
      case R.id.btn_preview_step:
        if (mStepsCreateRoomType.getCompletedPosition() == 1) {
          gotoNextSceen(0);
          mStepsCreateRoomType.setCompletedPosition(0).drawView();
        } else if (mStepsCreateRoomType.getCompletedPosition() == 2) {
          gotoNextSceen(1);
          mStepsCreateRoomType.setCompletedPosition(1).drawView();
        }
        break;
      case R.id.iv_add_utility:
        showPopupAddUtility();
        break;
    }
  }

  private void callApiCreatePriceRoomType() {
    showProgress();
    CreateRoomTypePrice roomTypePrice = new CreateRoomTypePrice();
    roomTypePrice.setRoomTypeId(createRoomTypeResult.getRoomTypeId());
    roomTypePrice.setUserId(appState.getUserId());
    roomTypePrice.setPriceDay(Integer.valueOf(edt_money_day.getText().toString().trim()));
    roomTypePrice.setPriceHour(Integer.valueOf(edtHourFirst.getText().toString().trim()));
    roomTypePrice.setOverTimePrice(Integer.valueOf(edtMoneyHourFirst.getText().toString().trim()));
    createRoomTypePresenter.createRoomTypePriceApi(roomTypePrice);
  }

  private void callApiSaveAmenities() {
    if (createRoomTypeResult != null) {
      showProgress();
      if (utilitysList != null && utilitysList.size() > 0) {
        for (int i = 0; i < utilitysList.size(); i++) {
          if (utilitysList.get(i).isCheckedUtility()) {
            listAmenities += utilitysList.get(i).getNameUtility() + " ,";
          }
        }
      }
      createRoomTypePresenter.saveAmenities(createRoomTypeResult.getRoomTypeId(), listAmenities);
    }
  }

  private void callApiCreateRoomType() {

    showProgress();
    CreateRoomType createRoomType = new CreateRoomType();
    createRoomType.setHotelId(1);
    createRoomType.setDescription(edtDescriptionType.getText().toString().trim());
    createRoomType.setMaxPerson(Integer.valueOf(edtNumberPersonRoom.getText().toString().trim()));
    createRoomType.setName(edtNameRoomType.getText().toString().trim());
    createRoomTypePresenter.createRoomType(createRoomType, photosHotel);
  }

  private void showPopupAddUtility() {
    new MaterialDialog.Builder(this).title("Thêm tiện ích")
        .content("Nhập tên tiện ích :")
        .inputType(InputType.TYPE_CLASS_TEXT
            | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
            | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
        .positiveText("Oke")
        .input("Ví dụ : Wifi, điều hòa ... ", "", false, new MaterialDialog.InputCallback() {
          @Override public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            utilitysList.add(new UtilityModel(input.toString(),true));
            utilityAdapter.notifyDataSetChanged();
          }
        })
        .show();
  }

  private void gotoNextSceen(int position) {
    switch (position) {
      case 0:
        btnNextStep.setText("Tiếp tục");
        btnPreviewStep.setVisibility(View.INVISIBLE);
        llStep1.setVisibility(View.VISIBLE);
        llStep2.setVisibility(View.GONE);
        llStep3.setVisibility(View.GONE);
        break;
      case 1:

        btnNextStep.setText("Tiếp tục");
        btnPreviewStep.setVisibility(View.VISIBLE);
        llStep1.setVisibility(View.GONE);
        llStep2.setVisibility(View.VISIBLE);
        llStep3.setVisibility(View.GONE);
        break;
      case 2:
        llStep1.setVisibility(View.GONE);
        llStep2.setVisibility(View.GONE);
        llStep3.setVisibility(View.VISIBLE);
        btnNextStep.setText("Kết thúc");
        break;
    }
  }

  @Override protected void onDestroy() {
    // Clear any configuration that was done!
    EasyImage.clearConfiguration(this);
    super.onDestroy();
  }

  private void gotoCreateRoomHotel() {
    Intent intent = new Intent(this, CreateRoomHotelActivity.class);
    startActivity(intent);
    finish();
  }

  public void showCallbacks() {
    new MaterialDialog.Builder(this).title("Thông báo")
        .content(R.string.str_tao_loai_phong, true)
        .positiveText("Oke")
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            // TODO
            gotoCreateRoomHotel();
          }
        })
        .show();
  }

  @Override public void onCreateRoomTypeApiSuccess(CreateRoomTypeResult createRoomTypeResult) {
    this.createRoomTypeResult = createRoomTypeResult;
    dismissProgress();
    mStepsCreateRoomType.setCompletedPosition(1).drawView();
    gotoNextSceen(1);
    showProgress();
    createRoomTypePresenter.getListAmenities("ROOM");
  }

  @Override public void onCreateRoomTypeApiFailed(String message) {
    dismissProgress();
  }

  @Override public void onSaveAmenitiesApiSuccess() {
    dismissProgress();
    mStepsCreateRoomType.setCompletedPosition(1).drawView();
    gotoNextSceen(2);
  }

  @Override public void onSaveAmenitiesApiFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onGetAmenitiesApiSuccess(List<String> amenities) {
    dismissProgress();
    if (amenities != null && amenities.size() > 0) {
      for (int i = 0; i < amenities.size(); i++) {
        UtilityModel utilityModel = new UtilityModel(amenities.get(i));
        utilitysList.add(utilityModel);
      }
      utilityAdapter.notifyDataSetChanged();
    }
  }

  @Override public void onGetAmenitiesApiFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onCreateRoomTypePriceApiSuccess() {
    dismissProgress();
    showCallbacks();
  }

  @Override public void onCreateRoomTypePriceApiFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onSaveFileRoomTypeApiFailed(String message) {
    dismissProgress();
    showToast(message);
  }

  @Override public void onError(Throwable e) {
    dismissProgress();
    showToast(getString(R.string.str_ket_noi_mang_error));
  }
}
