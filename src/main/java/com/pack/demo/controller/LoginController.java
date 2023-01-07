package com.pack.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pack.demo.entity.JWTRequest;
import com.pack.demo.entity.JWTResponse;
import com.pack.demo.entity.StatusModel;
import com.pack.demo.exceptions.UsernameNotFoundException;
import com.pack.demo.service.UserService;
import com.pack.demo.util.JWTUtil;
import com.pack.demo.util.Paths;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	public AuthenticationManager authenticationManger;	
	
	@Autowired
	public UserDetailsService userDetailsService; 
	
	@Autowired
	public UserService userModelService;
	
	@Autowired
	public JWTUtil jwtUtil;
	
	@PostMapping(value = Paths.LOGIN)
	public ResponseEntity<StatusModel> generateToken(@RequestBody JWTRequest jwtRequest){
		try {
			authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(
					jwtRequest.getUserName(),jwtRequest.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());	
			String token = jwtUtil.generateToken(userDetails);
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.OK,"no error","/login",new JWTResponse(token)),HttpStatus.OK);
		}
		catch(UsernameNotFoundException ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.FORBIDDEN,ex.getMessage(),"/login","no data"),HttpStatus.FORBIDDEN);
		}	
		catch(Exception ex) {
			return new ResponseEntity<>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,ex.getMessage(),"/login","no data"),HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
