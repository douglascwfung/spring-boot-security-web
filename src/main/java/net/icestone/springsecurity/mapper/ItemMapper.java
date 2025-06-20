package net.icestone.springsecurity.mapper;

import org.mapstruct.Mapper;

import net.icestone.springsecurity.dto.item.ItemResponse;
import net.icestone.springsecurity.entity.ItemEntity;

@Mapper(componentModel = "spring")
public interface ItemMapper {

  ItemResponse toResponse(ItemEntity itemEntity);
}
