package com.kali.services.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kali.services.auth.entity.User;
import com.kali.services.auth.repository.UserRepository;

/**
 * User credentials service.
 * 
 * @author kali
 *
 */
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", userName));
		}
	}

	public User findAccountByUsername(String username) throws UsernameNotFoundException {
		Optional<User> account = userRepository.findByUsername(username);
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
		}
	}

	public User registerUser(User account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return userRepository.save(account);
	}

	@Transactional
	public void removeAuthenticatedAccount() throws UsernameNotFoundException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = findAccountByUsername(username);
		userRepository.deleteById(user.getId());
	}

}
