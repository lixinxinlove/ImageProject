package com.lixinxin.imageproject.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.lixinxin.imageproject.db.entity.User;

/**
 * Created by android on 2018/3/20.
 */

public class UserModel extends ViewModel {

    private User user;

    public User getUser() {
        if (user == null) {
            user = new User();
            user.setAddress("三里屯");
            user.setUserId(2);
            user.setPassword("123456");
            user.setLike("吃");
        }
        return user;
    }

}
