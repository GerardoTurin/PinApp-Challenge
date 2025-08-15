package trackapp.icube04backend.infrastructure.configuration.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // 1) Busco local por username o email
        trackapp.icube04backend.modules.auth_module.domain.models.User user =
                userRepository.findByUsername(usernameOrEmail);
        if (user == null) {
            user = userRepository.findByEmail(usernameOrEmail);
        }


        // 3) Mapeo roles de tu dominio a authorities
        var authorities = (user.getRoles() == null ? java.util.List.<org.springframework.security.core.GrantedAuthority>of()
                : user.getRoles().stream()
                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getName()))
                .toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),  // si preferís email como username, ajusta
                user.getPassword(),
                authorities
        );
    }
}
