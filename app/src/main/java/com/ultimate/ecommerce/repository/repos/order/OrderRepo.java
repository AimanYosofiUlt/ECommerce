package com.ultimate.ecommerce.repository.repos.order;

import android.os.AsyncTask;

import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.repository.server.response.refund_order.RefundOrderResponse;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class OrderRepo extends BaseRepo {
    @Inject
    UserDao userDao;

    @Inject
    public OrderRepo() {
    }

    public void getOrders(String userId,ResponsesCallBack<GetUserOrdersResponse> callBack) {
//        RequestBody request = BaseRequest.getOrdersRequest(userId);
        //todo remove test code
        RequestBody request = BaseRequest.getOrdersRequest("15259");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                String tokenKey = userDao.getTokenKey();
                //todo remove test code
                String tokenKey = "ed3e8e2829fe213e8f370f7a173f5ef0bbc2358d128500d7c3c0ddfbceb6e98c";
                api.getUserOrders(request,tokenKey).enqueue(callBack);
            }
        });

    }

    public void getOrderDetail(String userId, int orderId, ResponsesCallBack<GetOrderResponse> callBack) {
//        RequestBody request = BaseRequest.getGetOrderRequest(userId, orderId);
        //todo remove test code
        RequestBody request = BaseRequest.getGetOrderRequest("15259", orderId);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                String tokenKey = userDao.getTokenKey();
                //todo remove test code
                String tokenKey = "ed3e8e2829fe213e8f370f7a173f5ef0bbc2358d128500d7c3c0ddfbceb6e98c";
                api.getOrder(request,tokenKey).enqueue(callBack);
            }
        });
    }

    public void refundOrder(Order order, ResponsesCallBack<RefundOrderResponse> callBack) {
        //        RequestBody request = BaseRequest.getOrdersRequest(userId);
        //todo remove test code
        RequestBody request = BaseRequest.getRefundOrderRequest(15259);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                String tokenKey = userDao.getTokenKey();
                //todo remove test code
                String tokenKey = "ed3e8e2829fe213e8f370f7a173f5ef0bbc2358d128500d7c3c0ddfbceb6e98c";
                api.refundOrder(request,tokenKey).enqueue(callBack);
            }
        });

    }
}
