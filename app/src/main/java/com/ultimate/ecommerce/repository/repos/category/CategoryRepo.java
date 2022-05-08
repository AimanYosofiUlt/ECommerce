package com.ultimate.ecommerce.repository.repos.category;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.tables.category.CategoryDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;

import java.util.List;

import javax.inject.Inject;

public class CategoryRepo extends BaseRepo {

    @Inject
    CategoryDao dao;

    @Inject
    public CategoryRepo() {
    }


    public void getCategoriesFromApi(ResponsesCallBack<GetCategoryResponse> responsesCallBack) {
        api.getCategories(BaseRequest.getBaseRequest())
                .enqueue(responsesCallBack);
    }

    public void addCategory(Category category) {
        AsyncTask.execute(() -> dao.insert(category));
    }

    public List<Category> getCategories() {
        return dao.getCategories();
    }
}
