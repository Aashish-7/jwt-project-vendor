package com.jwtproject.auth.model;

import java.io.Serializable;

public record JwtResponse(String jwtToken) implements Serializable {

}
