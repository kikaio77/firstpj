package kr.co.goodee39.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import kr.co.goodee39.service.BBSService;
import kr.co.goodee39.vo.BBSVO;
import kr.co.goodee39.vo.FileVO;

@Controller
@RequestMapping("/bbs")
public class BBSController {
	
	@Autowired
	private BBSService service;
	
	@GetMapping("/main")                                                   // 아무것도 없으면 1이 보여지게 디폴트값 설정 1페이지가된다. 
	public String getBBSList(Model model,@RequestParam(defaultValue = "1")int num,
			                                                  @RequestParam(defaultValue = "") String title,
			                                                  @RequestParam(defaultValue = "") String content) {
		
	
		service.selectBBSList(model,num,title,content);
		return "bbs";
	}
	
	@GetMapping("/detail")
	public String getBBSDetail(@ModelAttribute("bbsVO") BBSVO vo,Model model) {
		service.selectBBS(model,vo);
		return "bbs_detail";
	}
	
	@GetMapping("/create")
	public String getBBSCreate(BBSVO vo) {
		return "bbs_create";
	}
	
	@PostMapping("/create_result")
	public String setBBSCreate(BBSVO vo) {
//		System.out.println(vo.getTitle());
//		System.out.println(vo.getContent());
//		System.out.println(vo.getOwnerid());
//		System.out.println(vo.getOwnername());
		System.out.println(vo.getFilelist());
		
	
		vo.setCreatedate(new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(new Date()));
		service.insertBBS(vo);
		return "redirect:/bbs/main";
	}

	
	@GetMapping("/delete_bbs")
	public String delBBS(BBSVO vo) {
		service.deleteBBS(vo);
		return "redirect:/bbs/main";
	}
	
	@GetMapping("/modify_bbs")
	public String modifyBBS(@ModelAttribute("bbsVO") BBSVO vo , Model model) {
		service.selectBBS(model,vo);
		return "bbs_modify";
	}
	
	@PostMapping("/modify_result")
	public String modifyBBSResult(@ModelAttribute("bbsVO") BBSVO vo) {
		service.updateBBS(vo);
		return "redirect:/bbs/main";
	}
	
	@PostMapping("/deleteFileAll")
	public @ResponseBody ResponseEntity<String> deleteFileAll(@RequestBody FileVO fvo){
		System.out.println( fvo.getBnum());
		service.deleteBBSFileAll(fvo);
		return new ResponseEntity<String>("deleteFileAll Success ",HttpStatus.OK);
		
	}
	
	@PostMapping("/deleteFile")
	public @ResponseBody ResponseEntity<String> deleteFile(@RequestBody FileVO[] fvos){
//		System.out.println(num);
		
		for(FileVO fileVO :fvos) {
			System.out.println(fileVO.getNum());
		}
		service.deleteBBSFile(fvos);
		return new ResponseEntity<String>("deleteFile ",HttpStatus.OK);
		
	}
	
	@PostMapping("/uploadfile")
	public @ResponseBody ResponseEntity<List<FileVO>> uploadFile(MultipartFile[] uploadFile) throws Exception{
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		
		for (MultipartFile file : uploadFile) {
			if(!file.getOriginalFilename().isEmpty()) {
				String name = UUID.randomUUID().toString();
				file.transferTo(new File("D:/temp", name));
				System.out.println(file.getOriginalFilename()+"저장 완료.");
				list.add(new FileVO(name, file.getOriginalFilename() ));
			}else {
				System.out.println("데이터가 존재하지 않습니다.");
			}
		}
		System.out.println("모든 데이터가 저장되었습니다.");
		
		ResponseEntity<List<FileVO>> response = new ResponseEntity<List<FileVO>>(list,HttpStatus.OK);
		
		return response;
	}

}
