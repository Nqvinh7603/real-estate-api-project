package site.rentofficevn.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import site.rentofficevn.config.TokenProvider;
import site.rentofficevn.model.dto.AuthToken;
import site.rentofficevn.model.dto.UserDTO;
import site.rentofficevn.service.UserService;

@RestController
public class UserAPI {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@GetMapping("api/users")
	public List<UserDTO> getStaff(@RequestParam(value = "buildingid", required = false) Long buildingId,
			@RequestParam(value = "role", required = false) String roleCode) {
		List<UserDTO> results = new ArrayList<>();
		return results;
	}
	@GetMapping("api/user")
	public List<UserDTO> findByRole(@RequestParam(value = "rolecode", required = false) String roleCode) {
		List<UserDTO> results = new ArrayList<>();
		userService.findByRole(roleCode);
		return results;
	}
	@PostMapping("/authentication")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userDTO.getUserName(),
						userDTO.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}
}
