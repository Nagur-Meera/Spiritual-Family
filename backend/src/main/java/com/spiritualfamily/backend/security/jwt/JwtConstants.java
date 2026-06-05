package com.spiritualfamily.backend.security.jwt;

public class JwtConstants {

    // NOTE: For production, load this from environment/config and rotate regularly.
    public static final String SECRET =
            "SPIRITUAL_FAMILY_CHANGE_ME_REPLACE_WITH_ENV_SECRET_0123456789ABCDEF";

    public static final long JWT_EXPIRATION =
            86400000;
}