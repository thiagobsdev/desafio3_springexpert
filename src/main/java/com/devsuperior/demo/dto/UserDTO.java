package com.devsuperior.demo.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.devsuperior.demo.entities.User;

import jakarta.validation.constraints.Email;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	
	@Email(message = "Digite um email válido")
	private String userName;
	
	Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO() {	
	}

	public UserDTO(Long id, String email) {
		this.id = id;
		this.userName = email;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		userName = entity.getUsername();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roles, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(roles, other.roles)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", userName=" + userName + ", roles=" + roles + "]";
	}


	
	
	
	
	

}
