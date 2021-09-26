package com.it.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRespons {
	
	private static final long serialVersionUID = 1L;

private Integer conId;
private String conName;
private String conPhone;
private String conIext;
private String roomId;
private String userId;

}

