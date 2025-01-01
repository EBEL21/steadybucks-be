package app.ebel.staedybucks.controller;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.response.UserClanInfoRpDto;
import app.ebel.staedybucks.dto.response.UserInfoRpDto;
import app.ebel.staedybucks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        Long userId = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @DeleteMapping(value = "/{userId}/delete")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId) {
        Long id = userService.deleteUser(userId);
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/{userId}/info")
    public ResponseEntity<UserInfoRpDto> getUserInfo(@PathVariable Long userId) {
        UserInfoRpDto rpDto = userService.getUserInfo(userId);
        return ResponseEntity.ok(rpDto);
    }

    @GetMapping(value = "/{userId}/clans")
    public ResponseEntity<UserClanInfoRpDto> getUserRegisteredClan(@PathVariable Long userId) {
        UserClanInfoRpDto rpDto = userService.getUserRegisteredClan(userId);
        return ResponseEntity.ok(rpDto);
    }

}
