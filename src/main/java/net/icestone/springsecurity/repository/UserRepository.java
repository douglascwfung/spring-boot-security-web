package net.icestone.springsecurity.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.icestone.springsecurity.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

  Optional<UserEntity> findByUsername(String username);

  @Query(
      """
      SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u
          WHERE net.icestone.springsecurity.common.Role.ROLE_ADMIN MEMBER OF u.roles
      """)
  boolean isAnyAdminExist();
}
