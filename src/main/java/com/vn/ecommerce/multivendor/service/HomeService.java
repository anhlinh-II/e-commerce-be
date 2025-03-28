package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Home;
import com.vn.ecommerce.multivendor.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
