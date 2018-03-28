package com.vnpt.hotel.manager.domain.repository.api;

import com.google.firebase.iid.FirebaseInstanceId;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by LiKaLi on 3/5/2018.
 */

public class TokenFirebaseIdApiImpl implements TokenFirebaseIdApi {

  @Override public Observable<String> generate() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override public void call(Subscriber<? super String> subscriber) {
        try {
          String tokenFirebaseId = firebaseInstanceId();
          if (!tokenFirebaseId.isEmpty()) {
            subscriber.onNext(tokenFirebaseId);
          } else {
            subscriber.onError(new Throwable("Token Firebase Empty"));
          }
        } catch (Throwable e) {
          subscriber.onError(e);
        }
        subscriber.onCompleted();
      }
    });
  }

  private String firebaseInstanceId() {
    String token = FirebaseInstanceId.getInstance().getToken();
    return token;
  }
}
