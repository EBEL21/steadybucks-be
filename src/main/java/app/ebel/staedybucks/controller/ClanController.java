package app.ebel.staedybucks.controller;

import app.ebel.staedybucks.dto.request.AddInterestRqDto;
import app.ebel.staedybucks.dto.request.CreateClanRqDto;
import app.ebel.staedybucks.dto.request.UserRegisterClanRqDto;
import app.ebel.staedybucks.dto.response.ClanInterestFollowersRpDto;
import app.ebel.staedybucks.dto.response.ClanInterestRpDto;
import app.ebel.staedybucks.dto.response.ClanListRpDto;
import app.ebel.staedybucks.dto.response.ClanMembersInfoRpDto;
import app.ebel.staedybucks.service.base.ClanService;
import app.ebel.staedybucks.service.base.UserClanService;
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

    // 클랜 목록 조회
    @GetMapping("/")
    public ResponseEntity<ClanListRpDto> getAllClans() {
        ClanListRpDto clanList = clanService.getAllClans();
        return ResponseEntity.ok(clanList);
    }

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

    @PostMapping(value = "/{clanId}/interests")
    public ResponseEntity<ClanInterestRpDto> getClanInterest(@PathVariable Long clanId) {
        ClanInterestRpDto result = clanService.getClanInterest(clanId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/{clanId}/interests/{interestId}/members")
    public ResponseEntity<ClanInterestFollowersRpDto> getClanInterestFollowers(@PathVariable Long clanId, @PathVariable Long interestId) {
        ClanInterestFollowersRpDto result = clanService.getClanInterestFollowers(clanId, interestId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/interests/add")
    public ResponseEntity<?> addClanInterest(@Valid @RequestBody AddInterestRqDto addInterestRqDto) {
        Long result = clanService.addClanInterest(addInterestRqDto);
        return ResponseEntity.ok(result);
    }
}
