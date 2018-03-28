package com.vnpt.hotel.manager.domain.repository;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public class LoginUserToken {
  public static final String PREF_KEY_LOGIN_TOKEN = "jp.drjoy.app.LOGIN_TOKEN";

  private Preferences preferences;

  public LoginUserToken(Preferences preferences) {
    this.preferences = preferences;
  }

  public String get() {
    return preferences.getString(PREF_KEY_LOGIN_TOKEN,"");
  }

  public void put(String tokenUser) {
    preferences.putString(PREF_KEY_LOGIN_TOKEN, tokenUser);
  }

  public void remove() {
    preferences.remove(PREF_KEY_LOGIN_TOKEN);
  }
}
