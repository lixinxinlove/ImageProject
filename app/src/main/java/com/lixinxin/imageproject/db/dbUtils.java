package com.lixinxin.imageproject.db;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by android on 2018/3/26.
 */

public class dbUtils {

    public static <T, V> Observable<V> save(T t, V v) {
        return Observable.create(new ObservableOnSubscribe<V>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<V> emitter) throws Exception {

            }
        });
    }


}
