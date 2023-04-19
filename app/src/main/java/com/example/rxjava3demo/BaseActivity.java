package com.example.rxjava3demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 基础使用
 */
public class BaseActivity extends AppCompatActivity {
 
    private static final String TAG = BaseActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    
    public void onTest(View v) {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

//        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
//                emitter.onNext("杨影枫");
//                emitter.onNext("月眉儿");
//                emitter.onComplete();
//            }
//        });

        Observable observable = Observable.just("杨影枫", "月眉儿");
        
        observable.subscribe(observer);
        
    }

}
