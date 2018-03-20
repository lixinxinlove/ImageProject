package com.lixinxin.imageproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lixinxin.imageproject.db.entity.User;

/**
 * Created by android on 2018/3/20.
 */

public class UserModel extends ViewModel {

    private User user;

    private MutableLiveData<User> liveData;

    public LiveData<User> getUser() {

        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        if (user == null) {
            user = new User();
            user.setAddress("三里屯");
            user.setUserId(2);
            user.setPassword("123456");
            user.setLike("吃");
        }
        liveData.setValue(user);
        return liveData;
    }

}
