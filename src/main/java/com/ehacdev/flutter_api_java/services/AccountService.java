package com.ehacdev.flutter_api_java.services;

import java.util.Map;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.datas.entities.User;

public interface AccountService {

     Account createAccountForUser(User user);

     Account debit(String phoneNumber, double amount);

     Account credit(String phoneNumber, double amount);

     Map<String, Account> validateAccounts(String senderPhoneNumber, String receiverPhoneNumber);

     Account getAccountByUser(String userId);

     Account findAccountByPhoneNumber(String phoneNumber);
}
