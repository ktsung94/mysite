package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	private static final String SAVE_PATH = "D:\\mysite-upload";
	private static final String URL = "/images";

	@Autowired
	private SiteRepository siteRespository;

	public boolean updateSite(SiteVo siteVo) {
		int count = siteRespository.update(siteVo);
		return count == 1;
	}

	public SiteVo find() {
		SiteVo siteVo = siteRespository.find();
		return siteVo;
	}

	public String findProfile() {
		String profile = "";
		SiteVo siteVo = siteRespository.find();
		profile = siteVo.getProfile();

		return profile;
	}

	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {
			if(multipartFile.isEmpty())
				return null;

			String originFilename = multipartFile.getOriginalFilename();

			// 확장자 이름만 추출
			int lastIndex = originFilename.lastIndexOf('.');
			String extName = originFilename.substring(lastIndex+1);

			String saveFilename = generateSaveFilename(extName);

			long fileSize = multipartFile.getSize();

			System.out.println("###### " + originFilename);
			System.out.println("###### " + saveFilename);
			System.out.println("###### " + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(fileData);
			os.close();

			url = URL + "/" + saveFilename;

		} catch(IOException ex) {
			throw new RuntimeException("fileupload error:" + ex);
		}

		return url;
	}

	private String generateSaveFilename(String extName) {
		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

}
