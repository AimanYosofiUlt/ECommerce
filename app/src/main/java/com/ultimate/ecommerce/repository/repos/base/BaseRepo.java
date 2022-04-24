package com.ultimate.ecommerce.repository.repos.base;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.server.remote.UltimateApi;

import javax.inject.Inject;

/**
 * Created by Youssif Hamdy on 2/16/2021.
 */
public abstract class BaseRepo {
    @Inject
    protected UltimateApi api;
}
