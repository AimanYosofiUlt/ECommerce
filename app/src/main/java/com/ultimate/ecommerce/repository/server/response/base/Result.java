package com.ultimate.ecommerce.repository.server.response.base;

 import com.yemensoft.yslibrary.ConnictionManger.YsResult;

/**
 * Created by Abubaker on 06/12/2016.
 */

public class Result extends YsResult {
    public static final int APP_DFLT_ERROR_NO = -1;
    public Result(String errorMessage) {
        setErrorMessage(errorMessage);
        setErrorNumber(APP_DFLT_ERROR_NO);

    }
    public Result(YsResult ysResult) {
        setErrorMessage(ysResult.getErrorMessage());
        setErrorNumber(ysResult.getErrorNumber());

    }
    public Result(int ErrorNumber) {
        setErrorNumber(ErrorNumber);
    }
}
