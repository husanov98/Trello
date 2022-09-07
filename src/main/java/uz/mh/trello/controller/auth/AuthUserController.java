package uz.mh.trello.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.trello.controller.ApiController;
import uz.mh.trello.domains.AuthUser;
import uz.mh.trello.dtos.JwtResponse;
import uz.mh.trello.dtos.LoginRequest;
import uz.mh.trello.dtos.RefreshTokenRequest;
import uz.mh.trello.dtos.UserRegisterDto;
import uz.mh.trello.response.ApiResponse;
import uz.mh.trello.services.AuthUserService;

import javax.validation.Valid;


@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login",produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest request){
        return new ApiResponse<JwtResponse>(service.login(request));
    }

    @GetMapping(value = PATH + "/auth/refresh",produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody RefreshTokenRequest request){
        return new ApiResponse<>(service.refreshToken(request));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<AuthUser> register(@Valid @RequestBody UserRegisterDto dto){
        return new ApiResponse<>(service.register(dto));
    }
}
