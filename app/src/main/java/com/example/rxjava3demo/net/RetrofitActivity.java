package com.example.rxjava3demo.net;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import com.example.rxjava3demo.IpServiceForPost;
import com.example.rxjava3demo.R;
import com.example.rxjava3demo.model.HttpResult;
import com.example.rxjava3demo.model.IpData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG="RxJava";
    private Subscription subscription;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }
    @Override
    protected void onResume() {
        super.onResume();
        postIpInformation("59.108.54.37");
    }
    @Override
    public void onStop() {
        super.onStop();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    private void postIpInformation(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IpServiceForPost ipService = retrofit.create(IpServiceForPost.class);
//        subscription=ipService.getIpMsg(ip).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HttpResult<IpData>>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(HttpResult<IpData> ipDataHttpResult) {
//                IpData data=ipDataHttpResult.getData();
//                Toast.makeText(getApplicationContext(), data.getCountry(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
