package app.ebel.staedybucks.service.impl;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.response.UserClanInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInfoRpDto;
import app.ebel.staedybucks.entity.User;
import app.ebel.staedybucks.repository.UserRepository;
import app.ebel.staedybucks.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long registerUser(UserDto userDto) {
        User user = userDto.toEntity();
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public Long deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
        return user.getId();
    }

    @Override
    public UserInfoRpDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return new UserInfoRpDto(user);
    }

    @Override
    public UserClanInfoRpDto getUserRegisteredClan(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return new UserClanInfoRpDto(user);
    }
}
