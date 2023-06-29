package com.devsuperior.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projections.UserDetailsProjection;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value = """
			SELECT tb_user.user_name AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
			FROM tb_user
			INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
			INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
			WHERE tb_user.user_name = :email
		""")
List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

Optional<User> findByUserName(String userName);

}