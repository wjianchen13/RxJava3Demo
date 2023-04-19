package com.example.rxjava3demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 组合操作符
 */
public class CombineActivity extends AppCompatActivity {
    private static final String TAG="RxJava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
    }


    public void merge(View v) {
        Observable<Integer> obs1 =Observable.just(1,2,3).subscribeOn(Schedulers.io());
        Observable<Integer> obs2 =Observable.just(4,5,6);
        Observable.merge(obs1, obs2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "merge:"+integer);
            }
        });
    }

    public void concat(View v) {
        Observable<Integer> obs1 =Observable.just(1,2,3).subscribeOn(Schedulers.io());
        Observable<Integer> obs2 =Observable.just(4,5,6);
        Observable.concat(obs1,obs2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "concat:"+integer);
            }

        });
    }

    public void zip(View v) {
        Observable<Integer> obs1 =Observable.just(1,2,3);
        Observable<String> obs2 =Observable.just("a","b","c");
        Observable.zip(obs1, obs2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return integer+s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "zip:"+s);
            }
        });
    }


    public void combineLastest(View v) {
        Observable<Integer> obs1 = Observable.just(1,2,3);
        Observable<String> obs2 = Observable.just("a","b","c");
        Observable.combineLatest(obs1, obs2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return integer+s;
            }
            
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "combineLastest:"+s);
            }
             
        });

    }

    /**
     * 
     * @param v
     */
    public void startWith(View v) {
        Observable.just(3, 4, 5).startWith(Observable.just(1))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.d(TAG, "startWith:"+integer);
                    }
                });
    }
}
