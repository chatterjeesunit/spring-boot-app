package com.dev.springdemo.auth.jwt;

import com.dev.springdemo.auth.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(properties="spring.main.lazy-initialization=true", classes = {JWTUtil.class})
class JWTUtilTest {

    final String VALID_JWT_WITH_LONG_EXPIRY = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI2ZGQ1MmFlZS1kZGJhLTRiYjgtYTM2YS1iMTc2ZTQ3NmMyMDEiLCJzdWIiOiJzdW5pdGNoYXR0ZXJqZWVAdGhvdWdodHdvcmtzLmNvbSIsImlzcyI6IlNwcmluZyBCb290IEFwcCIsImlhdCI6MTU5ODExNjE0NSwiZXhwIjoyNDYyMTE2MTQ1LCJGaXJzdE5hbWUiOiJTdW5pdCIsIkxhc3ROYW1lIjoiQ2hhdHRlcmplZSJ9.a0h5_LdFVnxc_R0uzPHrrcQojBU99izdxAxu1ZjabxfhI0osXTNlsVD7oM4sLgmFDACyOicJ41VR2och1xyiZQ";
    final String JWT_SIGNED_WITH_DIFFERENT_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZWY2ZGU5MC1hMzljLTRhMjgtOTBmYy0xY2MyZmViZjFhMzciLCJzdWIiOiJzdW5pdEBnbWFpbC5jb20iLCJpc3MiOiJEZW1vIEFwcCIsImlhdCI6MTU5NzYwNDYwNywiZXhwIjoxMDIzNzUxODIwNywiZmlyc3ROYW1lIjoiU3VuaXQiLCJsYXN0TmFtZSI6IkNoYXR0ZXJqZWUiLCJpc0FkbWluIjp0cnVlfQ.VvvfRsZ75erZ6-k1-BeAI3IS6yXCM_3p5jSsxPCFkfI";
    final String EXPIRED_JWT = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJlOGNkODFkNy0zMmM5LTQ0Y2EtYWNhZS1kOWEwNDdjODhmYzYiLCJzdWIiOiJzdW5pdGNoYXR0ZXJqZWVAdGhvdWdodHdvcmtzLmNvbSIsImlzcyI6IlNwcmluZyBCb290IEFwcCIsImlhdCI6MTU5ODExNjAyOSwiZXhwIjoxNTk4MTE2MDMwLCJGaXJzdE5hbWUiOiJTdW5pdCIsIkxhc3ROYW1lIjoiQ2hhdHRlcmplZSJ9.zUzc0SPatm_b4HSYDBhIQzcsMGI2D7MzDhHyXl-Kqws2_7_HlCqBh26srXAWrOAGPnOAcsB_qBvLcu_gbGt8Wg";



    @Autowired
    private JWTUtil jwtUtil;

    @Mock
    private User user;

    @BeforeEach
    void setup() {
        when(user.getUsername()).thenReturn("sunitchatterjee@thoughtworks.com");
        when(user.getFirstName()).thenReturn("Sunit");
        when(user.getLastName()).thenReturn("Chatterjee");
    }
    
    @Test
    void shouldBeAbleToCreateJWT() {

        String jwt = jwtUtil.createJWT(user);

        assertNotNull(jwt);
        System.out.println(jwt);

        String[] jwtParts = jwt.split("\\.");

        assertEquals(3, jwtParts.length, "JWT Should contain 3 parts separated by dot");

        String userName = jwtUtil.parseJWT(jwt).getSubject();

        assertEquals("sunitchatterjee@thoughtworks.com", userName);
    }


    @Test
    void shouldMatchClaimsSetInJWT() {
        Claims claims = jwtUtil.parseJWT(VALID_JWT_WITH_LONG_EXPIRY);

        assertEquals("Spring Boot App", claims.getIssuer());
        assertEquals("sunitchatterjee@thoughtworks.com", claims.getSubject());
        assertEquals("Sunit", claims.get("FirstName", String.class));
        assertEquals("Chatterjee", claims.get("LastName", String.class));
    }


    @Test
    void shouldThrowExceptionIfJWTIsSignedUsingDifferentKey() {
        assertThrows(SignatureException.class,
                () -> jwtUtil.parseJWT(JWT_SIGNED_WITH_DIFFERENT_KEY));
    }

    @Test
    void shouldThrowExceptionWhenExpiredJWTTokenIsParsed() {
        assertThrows(ExpiredJwtException.class,
                () -> jwtUtil.parseJWT(EXPIRED_JWT));
    }

    @Test
    void shouldThrowExceptionWhenInvalidJWTTokenIsParsed() {
        assertThrows(MalformedJwtException.class,
                () -> jwtUtil.parseJWT("dummy.dummy.dummy"));
    }

    @Test
    void shouldThrowExceptionWhenNullJWTTokenIsParsed() {
        assertThrows(IllegalArgumentException.class,
                () -> jwtUtil.parseJWT(null));
    }

    @Test
    void shouldThrowExceptionWhenAnEmptyJWTTokenIsParsed() {
        assertThrows(IllegalArgumentException.class,
                () -> jwtUtil.parseJWT( ""));
    }

}