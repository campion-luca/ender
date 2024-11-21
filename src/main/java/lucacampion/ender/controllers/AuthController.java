package lucacampion.ender.controllers;

import lucacampion.ender.payloads.UserLoginDTO;
import lucacampion.ender.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDTO body){
        return this.authService.checkCredentialsAndGenerateToken(body);
    }
}
