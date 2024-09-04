package com.uzay.multiplerolebasedauthenticationapia.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfTokenController {

    @GetMapping("/public/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
       CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
       return csrfToken;
    }

}
