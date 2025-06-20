package net.icestone.springsecurity.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.icestone.springsecurity.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {

  Page<ItemEntity> findByUserId(String userId, Pageable pageable);
}
