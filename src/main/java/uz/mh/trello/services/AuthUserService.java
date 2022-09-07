package uz.mh.trello.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.mh.trello.config.security.UserDetails;
import uz.mh.trello.domains.AuthUser;
import uz.mh.trello.dtos.JwtResponse;
import uz.mh.trello.dtos.LoginRequest;
import uz.mh.trello.dtos.RefreshTokenRequest;
import uz.mh.trello.dtos.UserRegisterDto;
import uz.mh.trello.repository.AuthUserRepository;
import uz.mh.trello.utils.jwt.RefreshTokenService;
import uz.mh.trello.utils.jwt.TokenService;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Service
public class AuthUserService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService accessTokenService;

    private final TokenService refreshTokenService;

    public AuthUserService(AuthUserRepository authUserRepository,
                           PasswordEncoder passwordEncoder,
                           @Lazy AuthenticationManager authenticationManager,
                           @Qualifier("accessTokenService") TokenService accessTokenService,
                           @Qualifier("refreshTokenService") TokenService refreshTokenService) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
    }

    public UserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> exception = () ->
                new UsernameNotFoundException("Bad credentials");
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(exception);
        return new UserDetails(authUser);
    }

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = accessTokenService.generateToken(userDetails);
        String refreshToken = refreshTokenService.generateToken(userDetails);
        AuthUser authUser = userDetails.authUser();
        authUser.setLastLoggedTime(LocalDateTime.now());
        authUserRepository.save(authUser);
        return new JwtResponse(accessToken, refreshToken, "Bearer");
    }

    public JwtResponse refreshToken(RefreshTokenRequest request) {
        return null;
    }

    @SneakyThrows
    @Transactional
    public AuthUser register(UserRegisterDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setUsername(dto.getUsername());
        authUser.setEmail(dto.getEmail());
        authUserRepository.save(authUser);
        return authUser;
    }
}
