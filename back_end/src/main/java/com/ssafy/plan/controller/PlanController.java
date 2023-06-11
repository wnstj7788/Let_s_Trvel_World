package com.ssafy.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.plan.model.dto.PlaceDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.dto.TourInfoDto;
import com.ssafy.plan.model.service.PlanService;

@CrossOrigin
@RestController
@RequestMapping("/plan")
public class PlanController {
	public PlanController() {
	}

	@Autowired
	private PlanService pService;

	// 여행계획 등록 (post) (x)
	@PostMapping("/insert")
	public ResponseEntity<?> addPlan(@RequestBody PlanDto plan,HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		System.out.println(token);
		int resultNum = pService.addPlan(plan,token);
		
		String result = resultNum > 0 ? "success" : "fail";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 여행지 등록창 지역 가져오기

	// 여행지 지역 정한 후 관련 리스트 가져오기 (o)
	@PostMapping("/regionlist")
	public ResponseEntity<?> getRegionPlace(@RequestBody Map<String, String> region) {
		int sidoCode = Integer.parseInt(region.get("sidoCode"));
		int gugunCode = Integer.parseInt(region.get("gugunCode"));
		List<PlaceDto> result = pService.getRegionPlace(sidoCode, gugunCode);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 여행지(시, 군) 정보 가져오기 (o)
	@GetMapping("/location")
	public ResponseEntity<?> getRegionList() {
		List<Map<String, String>> result = pService.getRegion();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 여행지 등록 창 이동 (get)

	// 여행계획 수정 (put)

	// 여행 계획 삭제 (delete)

	// 지도에 띄울 정보(X)

	// 여행 내역 list (get) (o)
	@GetMapping("/list/{pageNo}")
	public ResponseEntity<?> getPlanList(@PathVariable int pageNo) {
		List<PlanDto> list = pService.getPlanList(pageNo);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// keyword 여행 내역 list (get) (o)
	@GetMapping("/list/{keyword}/{pageNo}")
	public ResponseEntity<?> getPlanListByKeyword(@PathVariable String keyword, @PathVariable int pageNo) {
		List<PlanDto> list = pService.getPlanListByKeyword(keyword, pageNo);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 여행계획 세부 내용 출력 (o)
	@GetMapping("/{planId}")
	public ResponseEntity<?> getPlanInfo(@PathVariable int planId) {
		PlanDto result = pService.getPlanInfo(planId);
		List<HashMap<String,Object>> list = pService.getPlanInfoList(planId);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("planInfo",result);
		m.put("list",list);
		return new ResponseEntity<>(m, HttpStatus.OK);
	}

	// 여행 장소 list (get) (o)
	@GetMapping("/location/list/{page}")
	public ResponseEntity<?> getLocationList(@PathVariable int page) {
		List<PlaceDto> list = pService.getLocationList(page);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// 여행 장소 list keyword 검색 (get) (o)
	@GetMapping("/location/list/{keyword}/{page}")
	public ResponseEntity<?> getLocationListByKeyword(@PathVariable int page, @PathVariable String keyword) {
		List<PlaceDto> list = pService.getLocationListByKeyword(page, keyword);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 여행 장소 list keyword 검색 (get) (o)
		@GetMapping("/location/list/{locationName}/{keyword}/{page}")
		public ResponseEntity<?> getLocationListByLocationNameAndKeyword(@PathVariable String locationName,@PathVariable int page, @PathVariable String keyword) {
			page = 1;
			List<PlaceDto> list = pService.getLocationListByLocationNameAndKeyword(locationName, keyword);
			return new ResponseEntity<>(list, HttpStatus.OK);
		}

	// 여행지 세부 내용 출력 (o)
	@GetMapping("/location/{contentId}")
	public ResponseEntity<?> getLocationInfo(@PathVariable int contentId) {
		Map<String, Object> result = pService.getLocationInfo(contentId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 가장 많이 간 여행 장소 (get) (o)
		@GetMapping("/location/hotLocation")
		public ResponseEntity<?> getHotLocation() {
			List<Map<String, Object>> list = pService.getHotLocation();
			return new ResponseEntity<>(list, HttpStatus.OK);
		}


}
