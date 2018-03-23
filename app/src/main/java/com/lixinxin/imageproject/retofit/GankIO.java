package com.lixinxin.imageproject.retofit;

import com.lixinxin.imageproject.db.entity.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by android on 2017/7/11.
 */

public interface GankIO {

    @GET("{count}/{page}")
    Observable<User> getGirls(@Path("count") int count, @Path("page") int page);

}
