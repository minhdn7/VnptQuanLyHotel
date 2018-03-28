package com.vnpt.hotel.manager.app.http.interceptor;

import com.vnpt.hotel.manager.domain.repository.LoginUserToken;
import java.io.IOException;
import java.util.HashSet;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiKaLi on 3/9/2018.
 */

public class AddCookieInterceptor implements Interceptor {
  private LoginUserToken loginUserToken;

  public AddCookieInterceptor(LoginUserToken loginUserCookies) {
    this.loginUserToken = loginUserCookies;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();
    builder.addHeader("Content-Type", "application/json");
    String tokenUser = loginUserToken.get();
    if (tokenUser.length() > 0) {
      builder.addHeader("token", tokenUser.trim());
    }
    return chain.proceed(builder.build());
  }
}
