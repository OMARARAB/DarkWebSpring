package com.example.DarkWebM.Config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
