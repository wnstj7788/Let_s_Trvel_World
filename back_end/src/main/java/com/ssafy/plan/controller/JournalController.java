package com.ssafy.plan.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.plan.model.dto.PlaceDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.dto.TourInfoDto;
import com.ssafy.plan.model.service.JournalService;
import com.ssafy.plan.model.service.PlanService;

@CrossOrigin
@RestController
@RequestMapping("/journal")
public class JournalController {
	public JournalController() {
	}
	 @Value("${url}")
	    private String url;

	@Autowired
	private JournalService jService;

	@PostMapping("/write")
	public ResponseEntity<?> setPlanWrite(@RequestBody TourInfoDto write,HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		int result = jService.setPlanWrite(write,token);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@PostMapping("/fileUpload")
	@ResponseBody
	public ResponseEntity<String> fileUpload(@RequestBody MultipartFile images) {
		try {
			String savePath = "c:/springboot/img";

			// 이미지 저장
			String fileName = images.getOriginalFilename();
			String filePath = savePath + "/" + fileName;
			images.transferTo(new File(filePath));

			// 저장된 이미지의 주소 생성 (예: 이미지가 웹 애플리케이션의 정적 리소스로 제공되는 경우)
			String imageUrl = "http://"+url+":80/img/" + fileName;
			return ResponseEntity.ok(imageUrl);
		} catch (IOException e) {
			// 이미지 저장 중에 오류가 발생한 경우
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	// 여행기 리스트 가져오기
	@GetMapping("/list/{page}")
	public ResponseEntity<?> getJournalList(@PathVariable int page) {
		List<Map<String, Object>> list = jService.getJournalList(page);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	

	// 여행기 리스트 키워드 검색
	@GetMapping("/list/{keyword}/{page}")
	public ResponseEntity<?> getJournalListByKeyword(@PathVariable int page, @PathVariable String keyword) {
		List<Map<String, Object>> list = jService.getJournalListByKeyword(page, keyword);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 여행기 상세 보기
	@GetMapping("/info/{journalId}")
	public ResponseEntity<?> getJournalInfo(@PathVariable int journalId) {
		Map<String, Object> journalInfo = jService.getJournalInfo(journalId);
		return new ResponseEntity<>(journalInfo, HttpStatus.OK);
	}


}
