package com.ultimate.ecommerce.repository.dataprovider.base;
import android.content.Context;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.creation.AppDatabase;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;
import com.ultimate.ecommerce.repository.server.response.base.GenResponseObject;
import com.ultimate.ecommerce.repository.server.response.base.Result;
import com.ultimate.ecommerce.utilities.CommonMethods;
import com.ultimate.ecommerce.utilities.NetworkUtil;
import com.yemensoft.yslibrary.ConnictionManger.YsRequestBuilder;
import com.yemensoft.yslibrary.ConnictionManger.YsResult;
import com.yemensoft.yslibrary.InterFaces.YsRequestFinishedListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


/**
 * Created by Youssif Hamdy on 2/16/2021.
 */
public abstract class BaseDataProvider {
    protected AppDatabase mDb;
    protected Context context;
    protected Executor executor = Executors.newSingleThreadExecutor();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseDataProvider(Context context) {
        this.context = context;
        this.mDb = AppDatabase.getInstance(context);
    }
    public static <T> void doRequest(Context context, YsRequestFinishedListener<GenResponseObject<T>> finishedListener, boolean showerrormsg, boolean showloading, String methodName, Object... requestPara) {
        YsRequestFinishedListener<GenResponseObject<T>> LS = new YsRequestFinishedListener<GenResponseObject<T>>() {
            @Override
            public void onRequestSuccess(GenResponseObject<T> tGenResponseObject) {
                if (finishedListener != null) {

                    if (tGenResponseObject.getResult().getErrorNumber() == 0) {
                        finishedListener.onRequestSuccess(GenResponseObject.success(tGenResponseObject.getData(), tGenResponseObject.result));
                    } else {
                        finishedListener.onRequestFailed(tGenResponseObject.getResult());
                    }
                }
            }

            @Override
            public void onRequestFailed(String s, int i) {
                if (finishedListener != null) {
                    if (s.toLowerCase().contains("Value <!DOCTYPE of type java.lang.String".toLowerCase())
                            || s.toLowerCase().contains("Value Status of type java.lang.String".toLowerCase())
                            || s.toLowerCase().contains("no internet")) {
                        s = context.getString(R.string.couldnt_reach_server);
                    }
                    finishedListener.onRequestFailed(CommonMethods.getErrorMessage(context, s, i), i);
                    // finishedListener.onRequestFailed(s, i);
                }
            }

            @Override
            public void onRequestFailed(YsResult ysResult) {
                if (finishedListener != null) {
                    if (ysResult.getErrorMessage().toLowerCase().contains("Value <!DOCTYPE of type java.lang.String".toLowerCase())
                            || ysResult.getErrorMessage().toLowerCase().contains("no internet")) {
                        ysResult.setErrorMessage(context.getString(R.string.couldnt_reach_server));
                    }
                    ysResult.setErrorMessage(CommonMethods.getErrorMessage(context, ysResult.getErrorMessage(), ysResult.getErrorNumber()));


                    try {
                        Result result = ((GenResponseObject) ysResult.ResponseObject).result;
                        finishedListener.onRequestSuccess(GenResponseObject.error(result, null));
                    } catch (Exception Ignored) {
                        finishedListener.onRequestFailed(ysResult);
                    }

                }
            }

            @Override
            public void onRetry() {
                doRequest(context, finishedListener, showerrormsg, showloading, methodName, requestPara);
            }
        };


        if (NetworkUtil.isConnectionAvailable(context)) {
            new YsRequestBuilder<GenResponseObject<T>>(context)
                    .setShowErrorMsgWithRetry(showerrormsg)
                    .setLoadingMessage("")
                    .setShowLoadingProgressDialog(showloading)
                    .setRequestFinishedListener(LS)
                    .seSetServiceApi(UltimateApi.class)
                    .doRequest(methodName, requestPara);
        } else {
            finishedListener.onRequestFailed(context.getString(R.string.no_internet_connection), -1);
            finishedListener.onRequestFailed(new Result(context.getString(R.string.no_internet_connection)));
        }

    }


}
