package com.ultimate.ecommerce.ui.fragment.category;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ultimate.ecommerce.R;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.category.CategoryRepo;
import com.ultimate.ecommerce.repository.server.response.base.ResponseState;
import com.ultimate.ecommerce.repository.server.response.base.ResponsesCallBack;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryData;
import com.ultimate.ecommerce.repository.server.response.get_categories.GetCategoryResponse;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import com.ultimate.ecommerce.utilities.state.CheckNetworkListener;
import com.ultimate.ecommerce.utilities.state.OnValidateListener;
import com.ultimate.ecommerce.utilities.state.StateUtil;

import java.util.List;

import javax.inject.Inject;

public class CategoryFragmentViewModel extends BaseViewModel {
    @Inject
    CategoryRepo categoryRepo;

    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<List<Category>> categoryMDL;

    @Inject
    public CategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        categoryMDL = new MutableLiveData<>();
    }

    public void validateGetCategory(Context context) {
        StateUtil
                .validate(new OnValidateListener() {
                    @Override
                    public boolean onValidate() {
                        return OnValidateListener.super.onValidate();
                    }
                })
                .checkNetwork(context, new CheckNetworkListener() {
                    @Override
                    public void onConnect() {
                        categoryRepo.getCategoriesFromApi(new ResponsesCallBack<GetCategoryResponse>() {
                            @Override
                            public void onSuccess(GetCategoryResponse response) {
                                for (GetCategoryData categoryData : response.getData()) {
                                    Category category = convertDataToCategory(categoryData);
                                    categoryRepo.addCategory(category);
                                }

                                getCategoryFromDatabase();
                            }

                            private Category convertDataToCategory(GetCategoryData categoryData) {
                                return new Category(categoryData.getId(), categoryData.getSlug()
                                        , categoryData.getTitle(), categoryData.getDescription()
                                        , categoryData.getImage(), categoryData.getParent()
                                        , categoryData.getCount(), categoryData.getGradientStartColor()
                                        , categoryData.getGradientEndColor());
                            }

                            @Override
                            public void onFailure(String state, String msg) {
                                responseStateMDL.setValue(ResponseState.failureState(msg));
                                getCategoryFromDatabase();
                            }
                        });
                    }

                    @Override
                    public void onDisconnect() {
                        responseStateMDL.setValue(ResponseState.failureState(context.getString(R.string.no_internet_connection)));
                        getCategoryFromDatabase();
                    }

                    private void getCategoryFromDatabase() {
                        AsyncTask.execute(() -> {
                            List<Category> categories = categoryRepo.getCategories();
                            categoryMDL.postValue(categories);
                        });
                    }
                });
    }
}

