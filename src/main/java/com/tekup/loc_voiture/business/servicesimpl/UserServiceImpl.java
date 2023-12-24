package com.tekup.loc_voiture.business.servicesimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekup.loc_voiture.business.iservices.IUserService;
import com.tekup.loc_voiture.dao.entities.User;
import com.tekup.loc_voiture.dao.repositories.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public String saveUser(User user) {
		String passwd = user.getPassword();
		String encodedPasswod = passwordEncoder.encode(passwd);
		user.setPassword(encodedPasswod);
		user.setRoles(List.of("ADMIN"));
		user = userRepo.save(user);
		return user.getId();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<User> opt = userRepo.findUserByEmail(email);

		org.springframework.security.core.userdetails.User springUser = null;

		if (opt.isEmpty()) {
			throw new UsernameNotFoundException("User with email: " + email + " not found");
		}
		User user = opt.get();
		List<String> roles = user.getRoles();
		Set<GrantedAuthority> ga = new HashSet<>();
		for (String role : roles) {
			ga.add(new SimpleGrantedAuthority(role));
		}

		springUser = new org.springframework.security.core.userdetails.User(
				opt.get().getName(),
				user.getPassword(),
				ga);
		return springUser;
	}
	// Other Approach: Using Lambda & Stream API Of Java 8
	/*
	 * public UserDetails loadUserByUsername(String email)
	 * throws UsernameNotFoundException {
	 * 
	 * Optional<User> opt = userRepo.findUserByEmail(email);
	 * 
	 * if(opt.isEmpty())
	 * throw new UsernameNotFoundException("User with email: " +email
	 * +" not found !");
	 * else {
	 * User user = opt.get();
	 * return new org.springframework.security.core.userdetails.User(
	 * user.getEmail(),
	 * user.getPassword(),
	 * user.getRoles()
	 * .stream()
	 * .map(role-> new SimpleGrantedAuthority(role))
	 * .collect(Collectors.toSet())
	 * );
	 * }
	 * 
	 * }
	 */

}