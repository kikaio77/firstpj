package kr.co.goodee39.service;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.google.gson.Gson;

import kr.co.goodee39.vo.BBSVO;
import kr.co.goodee39.vo.FileVO;

@Service
public class BBSService {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public void selectBBSList(Model model,int num,String title, String content) {
		
		BBSVO vo = new BBSVO();
		vo.setStart((num-1)*vo.getCount()); //0인덱스부터 시작
		//0 0-9 시작지점-1 *10가 시작지점
		if(!title.equals("")) {
			 model.addAttribute("title",title);
			 
		vo.setTitle("%"+title+"%");         //title을 포함하는 쿼리에 like로 줬기떄문
		}
		if(!content.equals("")) {
			 model.addAttribute("content",content);
			vo.setContent("%"+content+"%");
		
		
		}
		
		
		model.addAttribute("list", sqlSessionTemplate.selectList("bbs.selectBBSList", vo)); 
		//하나더 추가하자
		model.addAttribute("count",sqlSessionTemplate.selectOne("bbs.selectBBSCount",vo));
		//전체적인 색인이 더빠르다.
		
		model.addAttribute("num",num);
		
	}
	
	public void selectBBS(Model model,BBSVO vo) {
		//vo = sqlSessionTemplate.selectOne("bbs.selectBBS", vo);
		BBSVO vo2 = sqlSessionTemplate.selectOne("bbs.selectBBS", vo);
		vo.setNum(vo2.getNum());
		vo.setTitle(vo2.getTitle());
		vo.setContent(vo2.getContent());
		vo.setOwnerid(vo2.getOwnerid());
		vo.setOwnername(vo2.getOwnername());
		vo.setCreatedate(vo2.getCreatedate());
		
		FileVO fvo = new FileVO();
		fvo.setBnum(vo2.getNum());
		
		
		List<FileVO> filelist = sqlSessionTemplate.selectList("file.selectFile",fvo);
		model.addAttribute("filelist",filelist);
		
	}
	
	//트랜잭션을 꼭 줘야함
	@Transactional
	public void insertBBS(BBSVO vo) {
	Gson gson = new Gson();
		
		FileVO[] fileArray = gson.fromJson(vo.getFilelist(),FileVO[].class);
		//객체를 다른 곳에 받아놓고 gson으로 과정
		//gson이 있으면 제이슨 오브젝트 핸들링이 쉬워진다
	   List<FileVO> fileList = Arrays.asList(fileArray);
	   
		/*
		 * for(FileVO fileVO : fileList) { System.out.println(fileVO.getLocalName());
		 * System.out.println(fileVO.getServerName()); }
		 */
		sqlSessionTemplate.insert("bbs.insertBBS", vo);
		 //인서트가 끝나야함
		System.out.println(vo.getNum());
		
		for(FileVO fileVO : fileList) {
			fileVO.setBnum(vo.getNum());
			sqlSessionTemplate.insert("file.insertFile",fileVO);
			
		}
	}
	
	public void deleteBBS(BBSVO vo) {
		sqlSessionTemplate.delete("bbs.deleteBBS", vo);
	}

	public void updateBBS(BBSVO vo) {
	
Gson gson = new Gson();
		
		FileVO[] fileArray = gson.fromJson(vo.getFilelist(),FileVO[].class);
		
		if(fileArray != null) {
		//객체를 다른 곳에 받아놓고 gson으로 과정
		//gson이 있으면 제이슨 오브젝트 핸들링이 쉬워진다
	   List<FileVO> fileList = Arrays.asList(fileArray);
	   sqlSessionTemplate.update("bbs.updateBBS", vo);
	   
	   //filelist가 0인경우는 필요가없다.
		/*
		 * for(FileVO fileVO : fileList) { System.out.println(fileVO.getLocalName());
		 * System.out.println(fileVO.getServerName()); }
		 */
		sqlSessionTemplate.insert("bbs.insertBBS", vo);
		 //인서트가 끝나야함
		System.out.println(vo.getNum());
		
		for(FileVO fileVO : fileList) {
			fileVO.setBnum(vo.getNum());
			sqlSessionTemplate.insert("file.insertFile",fileVO);
	 	}
		}
	}

	public void deleteBBSFileAll(FileVO fvo) {
//		FileVO fvo = new FileVO();
//		fvo.setBnum(bnum);
		
		sqlSessionTemplate.delete("file.deleteFile",fvo);
		
		
	}
   @Transactional
	public void deleteBBSFile(FileVO[] fvos) {
	   
//		   FileVO fvo = new FileVO();
//		   fvo.setNum(num);
	   
	   for(FileVO fvo:fvos)
		   sqlSessionTemplate.delete("file.deleteFile",fvo);
		   
	   }
		
	}





