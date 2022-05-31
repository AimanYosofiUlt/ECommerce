package com.ultimate.ecommerce.repository.repos.order;

import android.os.AsyncTask;

import com.ultimate.ecommerce.repository.local.user.UserDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.request.create_order.OrderProducts;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.create_order.CreateOrderResponse;
import com.ultimate.ecommerce.repository.server.response.get_order.GetOrderResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.GetUserOrdersResponse;
import com.ultimate.ecommerce.repository.server.response.get_user_orders.Order;
import com.ultimate.ecommerce.repository.server.response.refund_order.RefundOrderResponse;
import com.ultimate.ecommerce.repository.server.response.update_cart.UpdateCartData;

import java.util.List;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class OrderRepo extends BaseRepo {
    @Inject
    UserDao userDao;

    @Inject
    public OrderRepo() {
    }

    public void getOrders(ResponsesCallBack<GetUserOrdersResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getOrdersRequest(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.getUserOrders(request, tokenKey).enqueue(callBack);
        });

    }

    public void getOrderDetail(int orderId, ResponsesCallBack<GetOrderResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getGetOrderRequest(userDao.getUserId(), orderId);
            String tokenKey = userDao.getTokenKey();
            api.getOrder(request, tokenKey).enqueue(callBack);
        });
    }

    public void refundOrder(Order order, ResponsesCallBack<RefundOrderResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getOrdersRequest(userDao.getUserId());
            String tokenKey = userDao.getTokenKey();
            api.refundOrder(request, tokenKey).enqueue(callBack);
        });

    }

    public void createOrder(List<OrderProducts> orderProducts, ResponsesCallBack<CreateOrderResponse> callBack) {
        AsyncTask.execute(() -> {
            RequestBody request = BaseRequest.getCreateOrderRequest(userDao.getUserId(), orderProducts);
            api.createOrder(request).enqueue(callBack);
        });
    }
}
