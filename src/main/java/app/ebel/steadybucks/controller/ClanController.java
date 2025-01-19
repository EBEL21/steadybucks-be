package app.ebel.steadybucks.controller;

import app.ebel.steadybucks.aop.annotation.RequiresClanRole;
import app.ebel.steadybucks.dto.request.AddInterestRqDto;
import app.ebel.steadybucks.dto.request.CreateClanRqDto;
import app.ebel.steadybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.steadybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.steadybucks.dto.response.ClanInterestRpDto;
import app.ebel.steadybucks.dto.response.ClanListRpDto;
import app.ebel.steadybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.steadybucks.service.base.ClanService;
import app.ebel.steadybucks.service.base.UserClanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clans")
@RequiredArgsConstructor
public class ClanController {

    private final ClanService clanService;
    private final UserClanService userClanService;

    // 클랜 목록 조회
    @GetMapping("/list")
    public ResponseEntity<ClanListRpDto> getAllClans() {
        ClanListRpDto clanList = clanService.getAllClans();
        return ResponseEntity.ok(clanList);
    }

    // 클랜 생성
    @PostMapping(value = "/create")
    public ResponseEntity<?> createClan(@RequestParam String name, @AuthenticationPrincipal Long userId) {
        Long clanId = userClanService.createClan(name, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(clanId);
    }

    // 클랜 가입
    @PostMapping(value = "/{clanId}/register")
    public ResponseEntity<?> registerUser(@PathVariable Long clanId, @AuthenticationPrincipal Long userId) {
        Long id = userClanService.userRegisterClan(clanId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    // 클랜 권한 확인
    @GetMapping(value = "/verifyRole")
    public ResponseEntity<?> checkRole(@RequestParam("clan") Long clanId,
                                       @AuthenticationPrincipal Long userId) {

        String role = userClanService.verifyUserRole(clanId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @DeleteMapping(value = "/{clanId}/delete")
    @RequiresClanRole("CAPTAIN")
    public ResponseEntity<?> deleteClan(@PathVariable Long clanId,
                                        @AuthenticationPrincipal Long userId) {
        boolean result = userClanService.deleteClan(clanId, userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping(value = "/{clanId}/members")
    public ResponseEntity<ClanMembersInfoRpDto> getMembers(@PathVariable Long clanId) {
        ClanMembersInfoRpDto result = clanService.getClanMemberInfo(clanId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/{clanId}/interests")
    public ResponseEntity<ClanInterestRpDto> getClanInterest(@PathVariable Long clanId) {
        ClanInterestRpDto result = clanService.getClanInterest(clanId);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{clanId}/interests/{interestId}/members")
    public ResponseEntity<ClanInterestFollowersRpDto> getClanInterestFollowers(@PathVariable Long clanId, @PathVariable Long interestId) {
        ClanInterestFollowersRpDto result = clanService.getClanInterestFollowers(clanId, interestId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{clanId}/interests/add")
    @RequiresClanRole("CAPTAIN")
    public ResponseEntity<?> addClanInterest(@PathVariable Long clanId, @Valid @RequestBody AddInterestRqDto addInterestRqDto, @AuthenticationPrincipal Long userId) {
        Long result = clanService.addClanInterest(clanId, addInterestRqDto, userId);
        return ResponseEntity.ok(result);
    }
}
