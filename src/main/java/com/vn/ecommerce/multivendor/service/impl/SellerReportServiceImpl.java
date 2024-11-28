package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.modal.SellerReport;
import com.vn.ecommerce.multivendor.repository.SellerReportRepository;
import com.vn.ecommerce.multivendor.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());

        if (sellerReport == null) {
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);

            return sellerReportRepository.save(newReport);
        }

        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
