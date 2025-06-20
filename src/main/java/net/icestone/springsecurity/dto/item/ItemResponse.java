package net.icestone.springsecurity.dto.item;

import net.icestone.springsecurity.common.ItemState;

public record ItemResponse(String id, String data, String userId, ItemState itemState) {}
