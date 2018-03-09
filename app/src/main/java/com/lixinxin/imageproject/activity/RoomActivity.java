package com.lixinxin.imageproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lixinxin.imageproject.R;
import com.lixinxin.imageproject.app.App;
import com.lixinxin.imageproject.db.entity.User;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/activity/RoomActivity")
public class RoomActivity extends AppCompatActivity {

    private TextView textView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        textView = (TextView) findViewById(R.id.tv);
        user = new User();
        user.setUserId(1);
        user.setName("李晓娜");
        user.setSex(false);
        user.setAge(18);
        user.setPassword("10086");
        user.setPhone("17090408888");
        user.setAddress("北京三里屯soho");
    }


    public void find(View view) {


    }

    public void save(View view) {

        Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Exception {
                App.userDao.insertUser(user);
                emitter.onNext(user);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        textView.setText(user.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.setText("报错");
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RoomActivity.this, "save", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void update(View view) {
    }

    public void delete(View view) {
    }


}
