package com.ultimate.ecommerce.ui.fragment.category;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.ultimate.ecommerce.repository.local.tables.category.Category;
import com.ultimate.ecommerce.repository.repos.category.CategoryRepo;
import com.ultimate.ecommerce.ui.base.BaseViewModel;
import java.util.List;
import javax.inject.Inject;

public class CategoryFragmentViewModel extends BaseViewModel {
    @Inject
    CategoryRepo categoryRepo;

    LiveData<List<Category>> categoriesLiveData;

    @Inject
    public CategoryFragmentViewModel(@NonNull Application application, CategoryRepo categoryRepo) {
        super(application);
        categoriesLiveData = categoryRepo.getCategories();
    }
}
