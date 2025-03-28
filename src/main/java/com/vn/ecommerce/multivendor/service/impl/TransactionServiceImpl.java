package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.Order;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.modal.Transaction;
import com.vn.ecommerce.multivendor.repository.SellerRepository;
import com.vn.ecommerce.multivendor.repository.TransactionRepository;
import com.vn.ecommerce.multivendor.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {
        Seller seller = sellerRepository.findById(order.getSellerId()).get();

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}
