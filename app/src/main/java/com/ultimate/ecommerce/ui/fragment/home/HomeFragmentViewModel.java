package com.ultimate.ecommerce.ui.fragment.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.category.CategoryRepo;
import com.ultimate.ecommerce.repository.repos.homepage.HomePageRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryData;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageData;
import com.ultimate.ecommerce.repository.server.response.homepage.HomePageResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class HomeFragmentViewModel extends BaseViewModel {
    @Inject
    HomePageRepo homePageRepo;

    private static final String TAG = "HomeFragmentViewModel";

    @Inject
    CategoryRepo categoryRepo;

    LiveData<List<Category>> categoriesLiveData;
    MutableLiveData<HomePageData> homePageDataMDL;
    MutableLiveData<ResponseState> homepageResponseMDL;
    MutableLiveData<ResponseState> categoriesMDL;
    MutableLiveData<ResponseState> getCategoriesResponseMDL;


    @Inject
    public HomeFragmentViewModel(@NonNull Application application, CategoryRepo categoryRepo) {
        super(application);
//        categoriesLiveData = categoryRepo.getCategories();
        homePageDataMDL = new MutableLiveData<>();
        homepageResponseMDL = new MutableLiveData<>();
        categoriesMDL = new MutableLiveData<>();
        getCategoriesResponseMDL = new MutableLiveData<>();
    }

    public void getHomePageData() {
        homePageRepo.getHomePageData(new ResponsesCallBack<HomePageResponse>() {
            @Override
            public void onSuccess(HomePageResponse response) {
                HomePageData data = response.getData();
                homePageDataMDL.setValue(data);
            }

            @Override
            public void onFailure(String state, String msg) {
                homepageResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }

    public void getCategory() {
        categoryRepo.getCategoriesFromApi(new ResponsesCallBack<GetCategoryResponse>() {
            @Override
            public void onSuccess(GetCategoryResponse response) {
                getCategoriesResponseMDL.setValue(ResponseState.successState());
            }

            @Override
            public void onFailure(String state, String msg) {
                getCategoriesResponseMDL.setValue(ResponseState.failureState(msg));
            }
        });
    }
}
