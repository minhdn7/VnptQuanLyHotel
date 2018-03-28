package com.vnpt.hotel.manager.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseActivity;
import com.vnpt.hotel.manager.domain.model.MessageEvent;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelApiResult;
import com.vnpt.hotel.manager.domain.model.motel.CreateMotelModel;
import com.vnpt.hotel.manager.domain.model.motel.MotelModel;
import com.vnpt.hotel.manager.domain.repository.AppState;
import com.vnpt.hotel.manager.ui.adapter.ImageHotelAdapter;
import com.vnpt.hotel.manager.ui.presenter.motel.CreateMotelPresenter;
import com.vnpt.hotel.manager.ui.view.BaseView;
import com.vnpt.hotel.manager.ui.view.motel.CreateMotelView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class CreateAndEditHotelActivity extends BaseActivity
    implements View.OnClickListener, BaseView, CreateMotelView {
  private static final String PHOTOS_KEY = "image_photos_list";
  @BindView(R.id.recycler_image_hotel) RecyclerView recyclerImageHotel;
  @BindView(R.id.iv_hotel_license) ImageView ivHotelLicense;
  @BindView(R.id.iv_delete_license) ImageView ivDeleteLicense;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tv_toolbar_title) TextView tvToolbarTitle;
  @BindView(R.id.btn_register_hotel) TextView btnRegisterHotel;
  @BindView(R.id.edt_name_hotel) EditText edtNameHotel;
  @BindView(R.id.edt_location_hotel) EditText edtLocationHotel;
  private ArrayList<File> photosHotel = new ArrayList<>();
  private File licenseHotel;
  private ImageHotelAdapter imagesAdapter;
  @Inject CreateMotelPresenter createMotelPresenter;
  @Inject AppState appState;
  private CreateMotelApiResult createMotelApiResult;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_and_edit_hotel);
    photosHotel.add(new File("Empty"));
    if (savedInstanceState != null) {
      photosHotel = (ArrayList<File>) savedInstanceState.getSerializable(PHOTOS_KEY);
    }
    initView();
    addEvents();
    checkGalleryAppAvailability();
    createMotelPresenter.setView(this);
    createMotelPresenter.onViewCreate();
  }

  private void checkGalleryAppAvailability() {
    if (!EasyImage.canDeviceHandleGallery(this)) {
      //Device has no app that handles gallery intent
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable(PHOTOS_KEY, photosHotel);
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
        if (type == 0) {
          onPhotosHotelReturned(imageFiles);
        } else if (type == 1) {
          onPhotosLicenseReturned(imageFiles);
        }
      }

      @Override public void onCanceled(EasyImage.ImageSource source, int type) {
        //Cancel handling, you might wanna remove taken photo if it was canceled
        if (source == EasyImage.ImageSource.CAMERA) {
          File photoFile = EasyImage.lastlyTakenButCanceledPhoto(CreateAndEditHotelActivity.this);
          if (photoFile != null) photoFile.delete();
        }
      }
    });
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.iv_delete_license:
        ivDeleteLicense.setVisibility(View.GONE);
        Picasso.with(this)
            .load(R.drawable.ic_add_a_photo_black_48dp)
            .fit()
            .centerCrop()
            .into(ivHotelLicense);
        break;
      case R.id.iv_hotel_license:
        EasyImage.openChooserWithGallery(CreateAndEditHotelActivity.this, "Chon anh: ", 1);
        break;
      case R.id.btn_register_hotel:
        createHotel();
        break;
    }
  }

  private void createHotel() {
    showProgress(getString(R.string.str_dang_tai_du_lieu));
    CreateMotelModel motelModel = new CreateMotelModel();
    motelModel.setHotelName(edtNameHotel.getText().toString().trim());
    motelModel.setAddress(edtLocationHotel.getText().toString().trim());
    motelModel.setCustomerId(appState.getCreateCustomer());
    motelModel.setUserId(appState.getUserId());
    motelModel.setHotelType("MOTEL");
    createMotelPresenter.createMotel(motelModel, photosHotel, licenseHotel);
  }

  @Override public void initView() {
    initToolbar();
    initRecycleview();
    initPhotoImage();
  }

  @Override public void addControls() {

  }

  @Override public void addEvents() {
    ivHotelLicense.setOnClickListener(this);
    ivDeleteLicense.setOnClickListener(this);
    btnRegisterHotel.setOnClickListener(this);
    imagesAdapter.setmOnClickListener(new ImageHotelAdapter.MyOnClickListener() {
      @Override public void onClickItemView(int position) {
        EasyImage.openChooserWithGallery(CreateAndEditHotelActivity.this, "Chọn ảnh :", 0);
      }

      @Override public void onClickDeleteView(int position) {
        imagesAdapter.removeAt(position);
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

  private void initToolbar() {
    setSupportActionBar(toolbar);
    // Remove default title text
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    // set icon
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    tvToolbarTitle.setText("Thông tin nhà nghỉ");
  }

  private void initRecycleview() {
    imagesAdapter = new ImageHotelAdapter(this, photosHotel);
    recyclerImageHotel.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerImageHotel.setHasFixedSize(true);
    recyclerImageHotel.setAdapter(imagesAdapter);
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

  private void onPhotosHotelReturned(List<File> returnedPhotos) {
    photosHotel.addAll(photosHotel.size() - 1, returnedPhotos);
    imagesAdapter.notifyDataSetChanged();
    recyclerImageHotel.scrollToPosition(photosHotel.size() - 1);
  }

  private void onPhotosLicenseReturned(List<File> returnedPhotos) {
    ivDeleteLicense.setVisibility(View.VISIBLE);
    licenseHotel = returnedPhotos.get(0);
    Picasso.with(this).load(returnedPhotos.get(0)).fit().centerCrop().into(ivHotelLicense);
  }

  @Override protected void onDestroy() {
    // Clear any configuration that was done!
    EasyImage.clearConfiguration(this);
    super.onDestroy();
  }

  public void showDialogRegiter(String contentDialog) {
    new MaterialDialog.Builder(this).title(R.string.str_thong_bao)
        .cancelable(false)
        .content(contentDialog)
        .positiveText(R.string.str_oke)
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            dialog.dismiss();
            MessageEvent messageEvent = new MessageEvent();
            MotelModel.Hotels hotels = new MotelModel.Hotels();
            hotels.setHotelName(createMotelApiResult.getHotelName());
            hotels.setAddress(createMotelApiResult.getAddress());
            hotels.setHotelId(createMotelApiResult.getHotelId());

            messageEvent.setHotelModel(hotels);
            EventBus.getDefault().postSticky(messageEvent);
            Intent intent =
                new Intent(CreateAndEditHotelActivity.this, ListHotelRoomActivity.class);
            startActivity(intent);
            finish();
          }
        })
        .show();
  }

  @Override public void onCreateMotelComplete(CreateMotelApiResult motelApiResult) {
    dismissProgress();
    createMotelApiResult = motelApiResult;
    showDialogRegiter("Cập nhật thông tin nhà nghỉ thành công!");
  }

  @Override public void onCreateMotelError(String message) {
    dismissProgress();
  }

  @Override public void onError() {
    dismissProgress();
  }
}
