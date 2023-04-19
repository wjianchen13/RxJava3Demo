package com.example.rxjava3demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 过滤操作符
 */
public class FilterActivity extends AppCompatActivity {
    private static final String TAG="RxJava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        //filter();
        //elementAt();
        //distinct();
        //skip();
        //take();
        //ignoreElements();
        //throttleFirst();
//        throttleWithTimeOut();


    }

    public void filter(View v) {
        Observable.just(1, 2, 3, 4).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "filter:" + integer);
            }
        });
    }


    public void elementAt(View v) {
        Observable.just(1, 2, 3, 4).elementAt(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "elementAt:" + integer);
            }
        });
    }
    
    public void distinct(View v) {
        Observable.just(1, 2, 2, 3, 4, 1).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "distinct:" + integer);
            }
        });
    }

    public void skip(View v) {
        Observable.just(1, 2, 3, 4, 5, 6).skip(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "skip:" + integer);
            }
        });
    }
    
    public void take(View v) {
        Observable.just(1, 2, 3, 4, 5, 6).take(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "take:" + integer);
            }
        });
    }
    
    public void ignoreElements(View v) {
        Observable.just(1, 2, 3, 4).ignoreElements().subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("wangshu", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wangshu", "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("wangshu", "onNext");
            }
        });
    }

    public void throttleFirst(View v) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<10;i++){
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "throttleFirst:"+integer);
            }
        });
    }


    public void throttleWithTimeOut(View v) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext(i);
                    int sleep = 100;
                    if (i % 3 == 0) {
                        sleep = 300;
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }

    }).throttleWithTimeout(200,TimeUnit.MILLISECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "throttleWithTimeOut:"+integer);
            }
        });
    }

}
