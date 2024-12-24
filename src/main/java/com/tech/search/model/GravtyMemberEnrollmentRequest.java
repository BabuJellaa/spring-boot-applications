package com.tech.search.model;

import com.google.gson.annotations.SerializedName;

public class GravtyMemberEnrollmentRequest {
	private String mobile;
	private String gender;
	private String salutation;
	private int country;
	@SerializedName("date_of_birth")
	private String dateOfBirth;
	private String addressLine1;
	private String addressLine2;
	private ExtraData extraData;
	private User user;
	@SerializedName("enrollment_touchpoint")
	private String enrollmentTouchpoint;
	@SerializedName("enrollment_channel")
	private String enrollmentChannel;
	@SerializedName("enrolling_sponsor")
	private String enrollingSponsor;
	@SerializedName("enrolling_location")
	private String enrollingLocation;
}

class ExtraData{
	@SerializedName("epicure_type")
	private String epicureType;
	private String domicile;
	private String state;
	@SerializedName("country_code")
	private String countryCode;
}

class User{
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	private String email;
}
