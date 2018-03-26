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

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.lixinxin.imageproject.app.App.userDao;
import static io.reactivex.Observable.create;

@Route(path = "/activity/RoomActivity")
public class RoomActivity extends AppCompatActivity {

    private TextView textView;
    private User user;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

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
        user.setLike("吃");
    }


    public void find(View view) {

        mDisposable.add(Observable.create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Exception {
                        List<User> users = userDao.query();
                        if (users != null) {
                            emitter.onNext(users.get(0));
                        } else {
                            emitter.onNext(null);
                        }
                        emitter.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                if (user != null) {
                                    textView.setText(user.toString() + "-----find");
                                } else {
                                    textView.setText("没有数据");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                textView.setText("throwable");
                            }
                        })
        );

    }

    public void save(View view) {

        create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Exception {
                long[] i = App.userDao.insertUser(user);
                if (i.length > 0) {
                    emitter.onNext(user);
                } else {
                    emitter.onError(new Throwable(""));
                }

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

        create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Exception {
                user.setAddress(user.getAddress() + "1607");
                int i = App.userDao.updateUser(user);
                if (i > 0) {
                    emitter.onNext(user);
                } else {
                    emitter.onError(new Throwable(""));
                }

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
                        Toast.makeText(RoomActivity.this, "update", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    public void delete(View view) {

        create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Exception {
                int i = App.userDao.deleteUser(user);
                if (i > 0) {
                    emitter.onNext(user);
                } else {
                    emitter.onError(new Throwable(""));
                }
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
                        textView.setText("删除成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.setText("报错");
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RoomActivity.this, "delete", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void count(View view) {
        create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Exception {
                long i = App.userDao.queryCount();
                if (i > 0) {
                    emitter.onNext(i);
                } else {
                    emitter.onError(new Throwable(""));
                }
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Long l) {
                        textView.setText("一共有" + l + "条数据");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.setText("报错");
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(RoomActivity.this, "delete", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void findMaybe(View view) {
        userDao.queryUserByMaybe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        textView.setText(users.get(0).toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.append("onError");
                    }

                    @Override
                    public void onComplete() {
                        textView.append("onComplete");
                    }
                });
    }

    public void findSingle(View view) {
        userDao.queryUserBySingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        textView.setText(users.get(0).toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.append("onError");
                    }
                });

    }

    public void findFlowable(View view) {

        //TODO Flowable 有bug  http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0726/8268.html
        userDao.queryUserByFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        textView.setText(users.get(0).toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        textView.append("onError");
                    }

                    @Override
                    public void onComplete() {
                        textView.append("onComplete");
                    }
                });

    }


    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear(); //防止内存泄漏
    }
}
