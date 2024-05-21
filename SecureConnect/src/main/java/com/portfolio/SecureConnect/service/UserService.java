package com.portfolio.SecureConnect.service;

import com.portfolio.SecureConnect.dto.UserDto;
import com.portfolio.SecureConnect.model.User;

public interface UserService {

    User save(UserDto userDto);
}
