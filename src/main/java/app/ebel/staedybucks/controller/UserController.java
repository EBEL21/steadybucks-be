package app.ebel.staedybucks.controller;

import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.UserStockDto;
import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.request.UserTransactionRqDto;
import app.ebel.staedybucks.dto.response.*;
import app.ebel.staedybucks.enums.TransactionType;
import app.ebel.staedybucks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping(value = "/", name = "유저 목록 조회")
    public ResponseEntity<UserListRpDto> getAllUsers() {
        UserListRpDto userInfoRpDtoList = userService.getAllUserInfo();
        return ResponseEntity.ok(userInfoRpDtoList);

    }

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

    @PostMapping(value = "/interests/add")
    public ResponseEntity<?> addUserInterest(@Valid @RequestBody AddInterestRqDto addInterestRqDto) {
        Long interestId = userService.addUserInterest(addInterestRqDto);
        return ResponseEntity.ok(interestId);
    }

    @PostMapping(value = "/{userId}/interests/{interestId}/follow")
    public ResponseEntity<?> followInterest(@PathVariable Long userId, @PathVariable Long interestId) {
        Long result = userService.followClanInterest(userId, interestId);
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "/{userId}/get-interest")
    public ResponseEntity<UserInterestRpDto> getUserInterest(@PathVariable Long userId) {
        UserInterestRpDto userInterest = userService.getUserInterest(userId);
        return ResponseEntity.ok(userInterest);
    }

    @PostMapping(value = "/{userId}/stocks/transaction")
    public ResponseEntity<UserStockDto> buyStock(@Valid @RequestBody UserTransactionRqDto userTransactionRqDto) {
        if (userTransactionRqDto.getType() == TransactionType.BUY) {
            UserStockDto result = userService.buyStock(userTransactionRqDto);
            return ResponseEntity.ok(result);
        } else if(userTransactionRqDto.getType() == TransactionType.SELL) {
            UserStockDto result = userService.sellStock(userTransactionRqDto);
            return ResponseEntity.ok(result);
        } else {
            throw new IllegalArgumentException("Invalid Transaction Type");
        }
    }

    @GetMapping(value = "/{userId}/stocks/list")
    public ResponseEntity<UserStockListRpDto> getUserStock(@PathVariable Long userId) {
        UserStockListRpDto dto = userService.getUserStockList(userId);
        return ResponseEntity.ok(dto);
    }

}
