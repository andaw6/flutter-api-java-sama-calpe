package com.ehacdev.flutter_api_java.services;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.datas.entities.User;

public interface AccountService {

     Account createAccountForUser(User user);
}
