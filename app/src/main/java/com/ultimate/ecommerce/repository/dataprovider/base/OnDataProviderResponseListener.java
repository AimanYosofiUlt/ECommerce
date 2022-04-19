package com.ultimate.ecommerce.repository.dataprovider.base;


import com.ultimate.ecommerce.repository.server.response.base.GenResponseObject;

public interface OnDataProviderResponseListener<T> {

    void onSuccess(T response);
     void onError(GenResponseObject result);

}
