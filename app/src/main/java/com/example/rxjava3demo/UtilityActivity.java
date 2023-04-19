package com.example.rxjava3demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * 辅助/错误操作符
 */
public class UtilityActivity extends AppCompatActivity {
    private static final String TAG="RxJava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
    }
    
    public void delay(View v) {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Throwable {
                Long currentTime=System.currentTimeMillis()/1000;
                emitter.onNext(currentTime);
            }
            
        }).delay(2,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable{
                Log.d(TAG, "delay:"+(System.currentTimeMillis()/1000-aLong));
            }
        });
    }


    public void doOnNext(View v) {
        Observable.just(1,2)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.d(TAG,"call:" + integer);
                    }

                }).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        
                    }

                    @Override
                    public void onNext(Integer item) {
                        Log.d(TAG,"onNext:" + item);
                    }
                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG,"Error:" + error.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onCompleted");
                    }
                });

    }

    public void subscribeOn(View v) {
        Observable<Integer> obs= Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG,"Observable:" + Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onComplete();
            }

        });
//        obs.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.d(TAG,"Observer:" + Thread.currentThread().getName());
//            }
//        });
    }
    
    public void timeout(View v) {
        Observable<Integer> obs = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i=0;i<4;i++){
                    try {
                        Thread.sleep(i * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
            
        }).timeout(200,TimeUnit.MILLISECONDS,Observable.just(10,11));
        obs.subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "timeout:" + integer);
            }

        });
    }




}
