package com.tandem6.housingfinance.account.ui;

import com.tandem6.housingfinance.account.application.JwtUserDetailsService;
import com.tandem6.housingfinance.account.application.dto.AccountDTO;
import com.tandem6.housingfinance.account.domain.Account;
import com.tandem6.housingfinance.account.ui.dto.JwtRequest;
import com.tandem6.housingfinance.account.ui.dto.JwtResponse;
import com.tandem6.housingfinance.common.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody AccountDTO user) throws Exception {
        Account account = userDetailsService.save(user);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(account.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.PUT)
    public ResponseEntity<?> refreshAuthenticationToken(
            @RequestHeader("Authorization") String token,
            @RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        if( token.startsWith("Bearer Token ") ){
            log.info(":::::::::: {}", token.substring(13));
            if( jwtTokenUtil.validateToken(token.substring(13), userDetails) )
            {
                final String newToken = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(newToken));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(token);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(token);
        }

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
