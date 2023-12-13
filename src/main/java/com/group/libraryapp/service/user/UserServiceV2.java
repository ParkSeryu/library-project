package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;을 해준다. (트랜잭션 시작)
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional // 트랜잭션, 메소드내의 내용은 한몸이다.
    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));
//        throw new IllegalArgumentException(); // 이러면 롤백되어야함
    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        // 이걸 활용하면 예외를 던진다.

        // select * from user where id = ?;
        // Optional<User>

        user.updateName(request.getName());
//        userRepository.save(user); // 영속성 컨텍스트로 생략 가능
        //객체를 업데이트 해주고
        //save 메소드를 호출하면 자동으로 UPDATE SQL이 날라가게 된다.
    }

    @Transactional
    public void deleteUser(String name) {
        // SELECT * FROM user WHERE name = ?
        // user가 있으면 user 나오고 없으면 null 나온다
        // User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        if(!userRepository.existsByName(name)){
            throw new IllegalArgumentException();
        }

        User user = userRepository.findByName(name).orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
}
