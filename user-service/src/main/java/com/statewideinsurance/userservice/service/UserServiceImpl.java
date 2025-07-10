package com.statewideinsurance.userservice.service;

import com.statewideinsurance.userservice.DTO.UserDto;
import com.statewideinsurance.userservice.model.User;
import com.statewideinsurance.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate ;
    @Autowired
    private RedisTemplate redisTemplate ;


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        // Try to get User from Redis cache
        User cachedUser = (User) redisTemplate.opsForHash().get("Users", "Users_" + id);
        if (cachedUser != null) {
            System.out.println("Fetched from Redis Cache");
            return cachedUser;
        }

        // Cache miss -- fetch from database
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        // Put User into Redis cache
        redisTemplate.opsForHash().put("Users", "Users_" + id, user);
        System.out.println("Fetched from DB and stored in Redis Cache");

        return user;
    }


    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}