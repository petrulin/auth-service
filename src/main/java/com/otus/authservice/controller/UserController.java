package com.otus.authservice.controller;

import com.otus.authservice.domain.response.*;
import com.otus.authservice.exception.ApplicationException;
import com.otus.authservice.domain.request.ChangePasswordRequest;
import com.otus.authservice.domain.request.CreateUserRequest;

import java.util.List;

import com.otus.authservice.service.UserService;
import com.otus.authservice.util.enums.ApplicationError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth-service/api/v1/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateUserResponse> addUser(@RequestBody CreateUserRequest request) {
		try {
			return new ResponseEntity<>(userService.createUser(request), HttpStatus.OK);
		} catch(Exception ex) {
			loggingCreateUserMethod(ex);
			return new ResponseEntity<>(new CreateUserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	@PostMapping(path = "users/edit", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CreateUserResponse> editUser(@RequestBody CreateUserRequest request) {
		try {
			return new ResponseEntity<>(userService.editUser(request), HttpStatus.OK);
		} catch(Exception ex) {
			loggingCreateUserMethod(ex);
			return new ResponseEntity<>(new CreateUserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	/**
	 * The endpoint is just for developers
	 * @param ids
	 * @return
	 */
	@DeleteMapping(path = "users", consumes = "application/json", produces = "application/json")
	public ResponseEntity<DeleteUserResponse> deleteUsers(@RequestBody List<Long> ids) {
		try {
			return new ResponseEntity<>(new DeleteUserResponse(userService.deleteUsers(ids)), HttpStatus.OK);
		} catch(Exception ex) {
			loggingUserEnableActionUserMethod(ex);
			return new ResponseEntity<>(new DeleteUserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	@PostMapping(path = "users/{userId}/{enableAction}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<EnableActionUserResponse> userEnableAction(@PathVariable("userId") Long userId, @PathVariable("enableAction") Boolean enableAction) {
		try {
			return new ResponseEntity<>(userService.userEnableAction(userId, enableAction), HttpStatus.OK);
		} catch(Exception ex) {
			loggingUserEnableActionUserMethod(ex);
			return new ResponseEntity<>(new EnableActionUserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	@GetMapping(path = "users", produces = "application/json")
	public ResponseEntity<GetUsersResponse> getUserList() {
		try {
			return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
		} catch(Exception ex) {
			loggingGetUserListMethod(ex);
			return new ResponseEntity<>(new GetUsersResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	@GetMapping(path = "users/{userId}", produces = "application/json")
	public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") Long userId) {
		try {
			return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
		} catch(ApplicationException ex) {
			loggingGetUserMethod(ex);
			return new ResponseEntity<>(new GetUserResponse(ex), HttpStatus.OK);
		} catch(Exception ex) {
			loggingGetUserMethod(ex);
			return new ResponseEntity<>(new GetUserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	@GetMapping(path = "users/username/{username}", produces = "application/json")
	public ResponseEntity<UserResponse> getUser(@PathVariable("username") String username) {
		try {
			return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
		} catch(Exception ex) {
			loggingGetUserMethod(ex);
			return new ResponseEntity<>(new UserResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}


	@PostMapping(path = "password", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
		try {
			return new ResponseEntity<>(userService.changePassword(request), HttpStatus.OK);
		} catch(Exception ex) {
			loggingChangePasswordMethod(ex);
			return new ResponseEntity<>(new ChangePasswordResponse(ApplicationError.AUTH_SERVICE_INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	private void loggingUserEnableActionUserMethod(Exception ex) {
		log.error("::AuthService:: enableActionUser error with message -> {}", ex.getLocalizedMessage());
		log.error("::AuthService:: enableActionUser error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
	}

	private void loggingCreateUserMethod(Exception ex) {
		log.error("::AuthService:: createUser error with message -> {}", ex.getLocalizedMessage());
		log.error("::AuthService:: createUser error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
	}

	private void loggingGetUserListMethod(Exception ex) {
		log.error("::AuthService:: getUserList error with message -> {}", ex.getLocalizedMessage());
		log.error("::AuthService:: getUserList error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
	}

	private void loggingGetUserMethod(Exception ex) {
		log.error("::AuthService::   getUser error with message -> {}", ex.getLocalizedMessage());
		log.error("::AuthService:: getUser error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
	}

	private void loggingChangePasswordMethod(Exception ex) {
		log.error("::AuthService:: changePassword error with message -> {}", ex.getLocalizedMessage());
		log.error("::AuthService:: changePassword error with stackTrace -> {}", ExceptionUtils.getStackTrace(ex));
	}

}
