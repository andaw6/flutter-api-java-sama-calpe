package com.ehacdev.flutter_api_java.services;

import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.web.dto.request.ClientRegisterRequestDTO;

public interface UserService {

    User createClient(ClientRegisterRequestDTO request);

    User getUserByPhoneNumber(String phoneNumer);
}
