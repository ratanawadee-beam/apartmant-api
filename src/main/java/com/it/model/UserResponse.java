package com.it.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String userUsername;
	private String userPassword;
	private String userTitle;
	private String userName;
	private String userLasname;
	private String userBirthday;
	private String userIdcard;
	private String userPhone;
	private String userGender;
	private String userAddress;
	private String userEmail;
	private String RoleId;
	private String zipCode;
	private RoleResponse role;
	private RentResponse rent;
	private InvoiceResponse invoice;

}
