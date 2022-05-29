package com.ultimate.ecommerce.repository.repos.category;

import android.os.AsyncTask;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.local.tables.category.CategoryDao;
import com.ultimate.ecommerce.repository.repos.base.BaseRepo;
import com.ultimate.ecommerce.repository.server.request.base.BaseRequest;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryData;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;

import java.util.List;

import javax.inject.Inject;

public class CategoryRepo extends BaseRepo {

    @Inject
    CategoryDao dao;

    @Inject
    public CategoryRepo() {
    }


    public void getCategoriesFromApi(ResponsesCallBack<GetCategoryResponse> callBack) {
        api.getCategories(BaseRequest.getBaseRequest())
                .enqueue(new ResponsesCallBack<GetCategoryResponse>() {
                    @Override
                    public void onSuccess(GetCategoryResponse response) {
                        List<GetCategoryData> dataList = response.getData();
                        for (GetCategoryData responseData : dataList) {
                            Category category = convertResponseToCategory(responseData);
                            addCategory(category);
                        }
                        callBack.onSuccess(response);
                    }

                    private Category convertResponseToCategory(GetCategoryData responseData) {
                        return new Category(responseData.getId()
                                , responseData.getSlug()
                                , responseData.getTitle()
                                , responseData.getDescription()
                                , responseData.getImage()
                                , responseData.getParent()
                                , responseData.getCount()
                                , responseData.getGradientStartColor()
                                , responseData.getGradientEndColor());
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        callBack.onFailure(state, msg);
                    }
                });
    }

    public void addCategory(Category category) {
        AsyncTask.execute(() -> dao.insert(category));
    }

    public List<Category> getCategories() {
        return dao.getCategories();
    }
}
