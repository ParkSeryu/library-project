package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public void saveUser(UserCreateRequest request) {
        repository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers(){
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