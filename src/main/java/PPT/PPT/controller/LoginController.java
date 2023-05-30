package PPT.PPT.controller;


import PPT.PPT.domain.dto.member.LoginForm;
import PPT.PPT.domain.entity.Member;
import PPT.PPT.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm form) {

        try {
            String token = loginService.login(form);
            return ResponseEntity.ok().body(token);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("회원을 찾을 수 없습니다.");

        }
    }

    @PostMapping("/test")
    public String test() {
        return "테스트 통과!";
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body(null);
    }
}
