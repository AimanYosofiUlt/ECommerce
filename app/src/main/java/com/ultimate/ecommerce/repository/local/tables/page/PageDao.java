package com.ultimate.ecommerce.repository.local.tables.page;

import androidx.room.Dao;
import androidx.room.Query;

import com.ultimate.ecommerce.repository.local.creation.BaseDao;

@Dao
public interface PageDao extends BaseDao<Page> {
    @Query("SELECT * FROM Page WHERE id = " + Page.CONTACT_US_ID)
    Page getContactUsPage();

    @Query("SELECT * FROM Page WHERE id = " + Page.ABOUT_US_ID)
    Page getAboutUsPage();

    @Query("SELECT * FROM Page WHERE id = " + Page.HELP_US_ID)
    Page getHelpPage();
}
