package minhhn.blog.controller;

import minhhn.blog.authentication.JwtRequest;
import minhhn.blog.authentication.JwtResponse;
import minhhn.blog.authentication.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/authenticate")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;

  public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping
  public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
    this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()));
    return ResponseEntity.ok(this.jwtTokenUtil.generateToken(authenticationRequest));
  }

}
