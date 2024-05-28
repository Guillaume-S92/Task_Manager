package com.portfolio.TaskManager.service;

import com.portfolio.TaskManager.dto.UserDto;
import com.portfolio.TaskManager.model.User;

public interface UserService {

    User save(UserDto userDto);
}
