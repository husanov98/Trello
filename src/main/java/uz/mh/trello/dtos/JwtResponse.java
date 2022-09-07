package uz.mh.trello.dtos;

public record JwtResponse(String accessToken,String refreshToken,String tokenType) {
}
