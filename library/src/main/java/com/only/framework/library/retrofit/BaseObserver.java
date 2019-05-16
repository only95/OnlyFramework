package com.only.framework.library.retrofit;

import android.util.Log;

import com.only.framework.library.retrofit.BaseResponse;
import com.only.framework.library.retrofit.RxExceptionUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * 数据返回统一处理  参考https://www.jianshu.com/p/ff619fea7e22
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<Response<BaseResponse<T>>> {
    private static final String TAG = "BaseObserver";
    @Override
    public void onNext(Response<BaseResponse<T>> tResponse) {
        Log.e(TAG,"code:==="+tResponse.code());
        Log.e(TAG,"errorBody===:"+tResponse.errorBody());
        Log.e(TAG,"Body===:"+tResponse.body());
        if(tResponse.code()==404){
            onFailure(new Exception("404"),"页面不存在");
            return;
        }
        BaseResponse<T> body = tResponse.body();
        Log.e(TAG,"BaseResponse:==="+body.getStatus());
        if(body.getStatus().trim().equals("success")){
            Log.e(TAG,"BaseResponse:==="+ body.getMessage());
            onSuccess(body.getData());
        }else if (body.getStatus().equals("error")){
            Log.e(TAG,"BaseResponse123:==="+ body.getMessage());
            onFailure(new Exception(body.getMessage()), body.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e,RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void onSuccess(T result);
    public abstract void onFailure(Throwable e, String errorMsg);

}
