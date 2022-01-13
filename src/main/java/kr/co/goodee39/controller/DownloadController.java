package kr.co.goodee39.controller;

import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
	
	@GetMapping("/downloadFile/{clientname}/{servername}")
	public void downloadFile(
						HttpServletResponse response,
						@PathVariable String servername,
						@PathVariable String clientname) {
		
		try {
			String path = "D:/temp/"+servername;
			
			Path file = Paths.get(path);
			response.setHeader("Content-Disposition", "attachment;filename="+clientname);
			
			FileChannel fc = FileChannel.open(file, StandardOpenOption.READ);
			
			WritableByteChannel outputChannel = Channels.newChannel(response.getOutputStream());
			
			fc.transferTo(0, fc.size(), outputChannel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
