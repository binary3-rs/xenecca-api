package com.xenecca.api.dto.request;

import javax.validation.constraints.NotNull;

import com.xenecca.api.model.type.MessageStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMessageStatusDTO {

	@NotNull
	MessageStatus _status;
}
