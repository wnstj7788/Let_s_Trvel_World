package com.ssafy.member.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.util.security.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {
	 @Value("${url}")
	    private String url;
	 @Value("${file.path}")
	    private String path;

	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder bPasswordEncoder;

	@GetMapping("/login")
	public ResponseEntity<Void> login() {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/login")); // 리다이렉트할 페이지 설정
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@GetMapping("/register")
	public ResponseEntity<Void> register() {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/register")); // 리다이렉트할 페이지 설정
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}
	
	@GetMapping("/plan/list")
	public ResponseEntity<?> getPlanListForJournal(HttpServletRequest request) {
		 String token = request.getHeader("Authorization");
		List<Map<String, Object>> list = memberService.getPlanListForJournal(token);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/journal/list")
	public ResponseEntity<?> getJournalList(HttpServletRequest request) {
		 String token = request.getHeader("Authorization");
		List<Map<String, Object>> list = memberService.getJournalList(token);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/userimage")
	public ResponseEntity<?> getUserImage(HttpServletRequest request) {
		 String token = request.getHeader("Authorization");
		String url = memberService.getUserImage(token);
		return new ResponseEntity<>(url, HttpStatus.OK);
	}

// 	클라이언트로 부터 값을 추출하고 토클 비교 예정
	
	@GetMapping("/mypage")
	public ResponseEntity<?> mypage(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		MemberDto userInfo = memberService.getUserInfo(token);
		if(userInfo != null) {
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		}else
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // JWT 토큰이 유효하지 않은 경우 401 Unauthorized 에러 반환
		}
	
	
	
	@GetMapping("/myplan")
	public ResponseEntity<?> myplan(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		List<PlanDto> list = memberService.getMyPlan(token);
		if(list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // JWT 토큰이 유효하지 않은 경우 401 Unauthorized 에러 반환
		}
	
	

	@GetMapping("/mypageModify")
	public ResponseEntity<Void> mypageModify() {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/mypageModify")); // 리다이렉트할 페이지 설정
		return new ResponseEntity<>(headers, HttpStatus.FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody MemberDto memberInfo, HttpServletResponse response) {

		// 아이디 기준으로 값 불러오고 여기에 들어있는 PW와 넘겨 받은 PW가 같은지 확인할거임

		MemberDto originMember = new MemberDto();
		originMember.setPw(memberService.findId(memberInfo.getId()));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(memberInfo.getPw(), originMember.getPw())) {
		    String id = memberInfo.getId();
		    String token = jwtService.getToken("id", id);
		    System.out.println("jwt token : " + token);
		    HttpHeaders headers = new HttpHeaders();
		    response.setHeader("Access-Control-Expose-Headers", "Authorization");
		    headers.set("Authorization", "Bearer " + token);
		    return new ResponseEntity<>(id, headers, HttpStatus.OK);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletResponse res) {
		Cookie cookie = new Cookie("token", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);

		res.addCookie(cookie);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody MemberDto memberInfo, HttpServletResponse res) {
		System.out.println(memberInfo.toString());
		MemberDto member = new MemberDto();
		member.setId(memberInfo.getId());
		member.setPhone(memberInfo.getPhone());
		member.setEmail(memberInfo.getEmail());
		member.setNickName(memberInfo.getNickName());
		member.setUserName(memberInfo.getUserName());
		String hashingPW = this.bPasswordEncoder.encode(memberInfo.getPw());
		member.setPw(hashingPW);
	
		return new ResponseEntity<>(memberService.save(member), HttpStatus.OK);
	}

	@GetMapping("checkId/{userId}")
	public ResponseEntity<?> getRegionPlace(@PathVariable String userId){
		int result = memberService.checkId(userId);
		boolean used = false;
		if(result != 0) {
			used = true; // 사용중 
		}else {
			used = false;
		}
		
		return new ResponseEntity<>(used, HttpStatus.OK);
	}
	@PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
      if (image.isEmpty()) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      try {
        // 이미지를 저장할 폴더 경로일명 생성 (원하는 파일명으로 변경 가능)
    	  String savePath = "c:/"+path;

			// 이미지 저장
			String fileName = image.getOriginalFilename();
			String filePath = savePath + "/" + fileName;
			image.transferTo(new File(filePath));

			// 저장된 이미지의 주소 생성 (예: 이미지가 웹 애플리케이션의 정적 리소스로 제공되는 경우)
			String imageUrl = "http://"+url+":80/img/" + fileName;
			return ResponseEntity.ok(imageUrl);
    // 파일 저장 성공 메시지 반환
  } catch (IOException e) {
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
	
	@PutMapping("/mypageModify")
	 public ResponseEntity<?> mypageModify(@RequestBody MemberDto memberInfo, HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		 if (token == null || token.isEmpty()) {
		        return new ResponseEntity<>("Token is missing", HttpStatus.UNAUTHORIZED);
		 }
		 memberInfo.setPw(this.bPasswordEncoder.encode(memberInfo.getPw()));
		 memberService.userModify(memberInfo,token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/myjournal")
	 public ResponseEntity<?> mypageJournal(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		 if (token == null || token.isEmpty()) {
		        return new ResponseEntity<>("Token is missing", HttpStatus.UNAUTHORIZED);
		 }
		 List<Map<String,Object>> list = memberService.userJournal(token);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	

	// 로그인창 이동 (get)
	// 로그인 확인 (post)

	// 회원가입 창 이동(get)
	// 회원가입 (post)

	// 마이페이지 이동 (get)
	// 마이페이지 수정창 이동 (get)
	// 마이페이지 수정 (put)
	// 비번 수정, 재설정(put)

	// id 중복 검사 (get)

}
