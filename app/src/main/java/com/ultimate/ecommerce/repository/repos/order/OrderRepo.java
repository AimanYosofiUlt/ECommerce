package com.ultimate.ecommerce.repository.repos.order;

import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class OrderRepo extends BaseRepo {
    @Inject
    UserDao userDao;

    @Inject
    public OrderRepo() {
    }

    public void getOrders(ResponsesCallBack<GetUserOrdersResponse> callBack) {
        String userId = userDao.getUserId();
        RequestBody request = BaseRequest.getOrdersRequest(userId);
        api.getUserOrders(request).enqueue(callBack);
    }
}
