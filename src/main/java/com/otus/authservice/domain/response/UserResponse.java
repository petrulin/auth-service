
package com.otus.authservice.domain.response;

import com.otus.authservice.util.enums.ApplicationError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends AResponse {

	private String username;
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String mobilePhone;

	public UserResponse(ApplicationError errorContent) {
		super(errorContent.getErrorCode(), errorContent.getMessage());
	}

}
