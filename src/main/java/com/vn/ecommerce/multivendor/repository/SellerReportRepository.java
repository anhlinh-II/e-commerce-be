package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long> {
    SellerReport findBySellerId(Long sellerId);

}
