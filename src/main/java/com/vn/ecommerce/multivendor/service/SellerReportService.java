package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.modal.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
