package com.sist.ex0911_jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.ex0911_jwt.jwt.JwtProvider;

import io.jsonwebtoken.security.Keys;

// 단언테스트를 static improt를 하자
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@SpringBootTest
class Ex0911JwtApplicationTests {

	@Value("${custom.jwt.secretKey}")
	private String secretKeyCode;

	@Test
	@DisplayName("시크릿키 코드 체크")
	void loadSecretKeyCode() {
		// 단언: assertJ
		assertThat(secretKeyCode).isNotNull();
	}

	@Test
	@DisplayName("암호화알고리즘으로 시크릿key 암호화")
	void getBase64(){
		// 시크릿 키를 암호화 한다
		String encoding = Base64.getEncoder().encodeToString(secretKeyCode.getBytes());
		
		SecretKey sKey = Keys.hmacShaKeyFor(encoding.getBytes());

		assertThat(sKey).isNotNull();
	}

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	@DisplayName("accessToken발급")
	void tokenTest(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", "admin");
		claims.put("uname", "어두일미");
		claims.put("upwd", "1234");
		claims.put("uemail", "admin@korea.com");

		String accessToken = jwtProvider.genToken(claims, 60*60*3);
		System.out.println("ACCESS-TOKEN: " + accessToken);

		assertThat(accessToken).isNotNull();
	}

	@Test
	@DisplayName("동일한 SecretKey인지 확인")
	void sameSecretKey(){
		SecretKey sKey1 = jwtProvider.getSecretKey();
		SecretKey sKey2 = jwtProvider.getSecretKey();

		// 객체를 새로 만들어도 주소까지 완전히 동일한 객체인지 확인을 위해 진행

		assertThat(sKey1 == sKey2).isTrue();
	}

	@Test
	@DisplayName("유효한 토큰인지? 확인")
	void tokenValidTest(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("mid", "admin");
		claims.put("mname", "이도");
		claims.put("phone", "01012345678");

		// 토큰생성 -1넣어 바로 만료되는 토큰을 받는다
		String token = jwtProvider.genToken(claims, -1);
		System.out.println("TOKEN: " + token);

		// true라고 물어봄
		// true라면 토큰이 유요한 상태인거임
		assertThat(jwtProvider.verify(token)).isTrue();
	}

	@Test
	@DisplayName("토큰으로 claims 정보 확인")
	void tokenClaimsTest(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("mid", "admin");
		claims.put("mname", "이도");
		claims.put("phone", "01012345678");

		// 토큰생성
		String token = jwtProvider.genToken(claims, 60*60);
		System.out.println("TOKEN: " + token);

		// 유효한 토큰인지 아닌지 검증을 받는다
		assertThat(jwtProvider.verify(token)).isTrue();

		// 토큰에 저장되어 있는 정보를 받는다.
		// (위 Map과 동일한 것인지 확인)
		Map<String, Object> cMap = jwtProvider.getClaims(token);
		System.out.println("cMap.NAME: " + cMap.get("mname"));

	}

}
