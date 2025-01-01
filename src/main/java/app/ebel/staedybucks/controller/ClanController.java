package app.ebel.staedybucks.controller;

import app.ebel.staedybucks.dto.ClanDto;
import app.ebel.staedybucks.dto.UserDto;
import app.ebel.staedybucks.dto.request.CreateClanRqDto;
import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.staedybucks.service.ClanService;
import app.ebel.staedybucks.service.UserClanService;
import app.ebel.staedybucks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clans")
@RequiredArgsConstructor
public class ClanController {

    private final ClanService clanService;
    private final UserClanService userClanService;

    // 클랜 생성
    @PostMapping(value = "/create")
    public ResponseEntity<?> createClan(@Valid @RequestBody CreateClanRqDto dto) {
        Long clanId = userClanService.createClan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clanId);
    }

    // 클랜 가입
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterClanRqDto dto) {
        Long id = userClanService.userRegisterClan(dto);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    // 클랜 권한 확인
    @GetMapping(value = "/verifyRole")
    public ResponseEntity<?> checkRole(@RequestParam("clan") Long clanId,
                                       @RequestParam("user") Long userId) {

        String role = userClanService.verifyUserRole(clanId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @DeleteMapping(value = "/{clanId}/delete")
    public ResponseEntity<?> deleteClan(@PathVariable Long clanId,
                                        @RequestParam("user") Long userId) {
        boolean result = userClanService.deleteClan(clanId, userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping(value = "/{clanId}/members")
    public ResponseEntity<ClanMembersInfoRpDto> getMembers(@PathVariable Long clanId) {
        ClanMembersInfoRpDto result = clanService.getClanMemberInfo(clanId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
