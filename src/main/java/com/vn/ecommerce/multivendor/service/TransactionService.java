package com.vn.ecommerce.multivendor.service;

import com.vn.ecommerce.multivendor.modal.Order;
import com.vn.ecommerce.multivendor.modal.Seller;
import com.vn.ecommerce.multivendor.modal.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransaction();
}
