package uz.mh.trello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mh.trello.domains.AuthUser;
import uz.mh.trello.response.ApiResponse;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public ApiResponse<List<AuthUser>> getAll() {

        return new ApiResponse<List<AuthUser>>(List.of(AuthUser.builder()
                .username("JOhn")
                .password("123")
                .lastLoggedTime(LocalDateTime.now())
                .email("john.@gmail.com")
                .build()), 2);

    }
}
