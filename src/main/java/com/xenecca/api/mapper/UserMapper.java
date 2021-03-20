package com.xenecca.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xenecca.api.dto.request.NewUserDTO;
import com.xenecca.api.dto.response.UserDTO;
import com.xenecca.api.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDTO mapToDTO(User user);

	List<UserDTO> mapToDTOList(Iterable<User> users);
	
	User mapToEntity(NewUserDTO userData);
}
