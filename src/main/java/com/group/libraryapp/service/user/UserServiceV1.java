package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository repository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.repository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        repository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return repository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        if (repository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        repository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (repository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        repository.deleteUser(name);
    }


}
