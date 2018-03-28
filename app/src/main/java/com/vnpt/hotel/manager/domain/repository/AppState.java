package com.vnpt.hotel.manager.domain.repository;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class AppState {
  public static final String PREF_KEY_USER_PASS_WORD = "com.vnpt.hotel.manager.USER_PASS_WORD";
  public static final String PREF_KEY_USER_PHONE_NUMBER =
      "com.vnpt.hotel.manager.USER_PHONE_NUMBER";
  public static final String PREF_KEY_USER_ROLES = "com.vnpt.hotel.manager.USER_ROLES";
  public static final String PREF_KEY_USER_TOKEN_FIRE_BASE = "com.vnpt.hotel.manager.TOKEN_FIRE_BASE";
  public static final String PREF_KEY_USER_TOKEN_ID = "com.vnpt.hotel.manager.TOKEN_ID";
  public static final String PREF_KEY_USER_ID = "com.vnpt.hotel.manager.USER_ID";
  public static final String PREF_KEY_CREATE_ROOM_TYPE = "com.vnpt.hotel.manager.CREATE_ROOM_TYPE";
  public static final String PREF_KEY_CREATE_CUSTOMER = "com.vnpt.hotel.manager.CREATE_CREATE_CUSTOMER";
  private Preferences preferences;

  public AppState(Preferences preferences) {
    this.preferences = preferences;
  }

  public String getUserNumber() {
    return preferences.getString(PREF_KEY_USER_PHONE_NUMBER, "");
  }
  public void putUserNumber(String phoneNumber) {
    preferences.putString(PREF_KEY_USER_PHONE_NUMBER, phoneNumber);
  }
  public String getUserPassword() {
    return preferences.getString(PREF_KEY_USER_PASS_WORD, "");
  }
  public void putUserPassword(String passWord) {
    preferences.putString(PREF_KEY_USER_PASS_WORD, passWord);
  }
  public String getUserRoles() {
    return preferences.getString(PREF_KEY_USER_ROLES, "");
  }
  public void putUserRoles(String roles) {
    preferences.putString(PREF_KEY_USER_ROLES, roles);
  }
  public String getTokenFireBase() {
    return preferences.getString(PREF_KEY_USER_TOKEN_FIRE_BASE, "");
  }
  public void putTokenFireBase(String roles) {
    preferences.putString(PREF_KEY_USER_TOKEN_FIRE_BASE, roles);
  }
  public String getTokenId() {
    return preferences.getString(PREF_KEY_USER_TOKEN_ID, "");
  }
  public void putTokenId(String tokenId) {
    preferences.putString(PREF_KEY_USER_TOKEN_ID, tokenId);
  }
  public int getUserId() {
    return preferences.getInt(PREF_KEY_USER_ID, 0);
  }
  public void putUserId(int userId) {
    preferences.putInt(PREF_KEY_USER_ID, userId);
  }

  public boolean getCreateRoomType() {
    return preferences.getBoolean(PREF_KEY_CREATE_ROOM_TYPE, false);
  }

  public void putCreateRoomType(boolean customer) {
    preferences.putBoolean(PREF_KEY_CREATE_ROOM_TYPE, customer);
  }
  public int getCreateCustomer() {
    return preferences.getInt(PREF_KEY_CREATE_CUSTOMER, 0);
  }

  public void putCreateCustomer(int customer) {
    preferences.putInt(PREF_KEY_CREATE_CUSTOMER, customer);
  }

}
