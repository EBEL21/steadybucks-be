package app.ebel.steadybucks.security;

import app.ebel.steadybucks.dto.base.UserClanDto;
import app.ebel.steadybucks.dto.response.UserClanInfoRpDto;
import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.entity.UserClan;
import app.ebel.steadybucks.repository.base.UserClanRepository;
import app.ebel.steadybucks.repository.base.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class sbUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserClanRepository userClanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("유저 디테일 서비스 호출");

        User user = userRepository.findByLoginId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<UserClanDto> userClanDtos = userClanRepository.findUserClanByUserId(user.getId());
        return new sbUserDetails(user, userClanDtos);
    }
}
