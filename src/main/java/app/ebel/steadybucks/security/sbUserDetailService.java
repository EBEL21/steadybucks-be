package app.ebel.steadybucks.security;

import app.ebel.steadybucks.entity.User;
import app.ebel.steadybucks.repository.base.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class sbUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));;
        return new sbUserDetails(user);
    }
}
