package com.sirdc.modules.service.sys;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jodd.io.FileUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.ckfinder.connector.ServletContextFactory;
import com.sirdc.modules.core.config.Global;
import com.sirdc.modules.core.filter.Paging;
import com.sirdc.modules.sys.entity.SysFile;
import com.sirdc.modules.sys.filter.SysFileFilter;
import com.sirdc.modules.sys.service.SysFileService;
import com.sirdc.modules.ueditor.UeditorService;
import com.sirdc.modules.utils.CollectionsUtils;
import com.sirdc.modules.utils.UUIDGenerator;
import com.sirdc.modules.utils.date.DateUtils;
import com.sirdc.modules.utils.file.FileUtils;

/**
 * UeditorService实现 - Fastdfs
 */
@Component("ueditorServiceImpl")
public class UeditorServiceImpl implements UeditorService{

	@Autowired
	private SysFileService fileService;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public com.sirdc.modules.ueditor.MultipartFile getMultipartFile(String filedName, HttpServletRequest request) {
		com.sirdc.modules.ueditor.MultipartFile resultFile = null;
		try {
			MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartHttpservletRequest.getFile(filedName);
			if (!multipartFile.isEmpty()) {
				resultFile = new com.sirdc.modules.ueditor.StandardMultipartFile(filedName, multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getSize());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	@Override
	public State saveFileByInputStream(com.sirdc.modules.ueditor.MultipartFile multipartFile, long maxSize) {
		try {
			if (multipartFile.getSize() > maxSize) {
				return new BaseState(false, AppInfo.MAX_SIZE);
			}

			return saveFile(multipartFile.getBytes(), multipartFile.getOriginalFilename());
		} catch (Exception e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}
	}
	
	@Override
	public State saveBinaryFile(byte[] data, String fileName) {
		try {
			return saveFile(data, fileName);
		} catch (Exception e) {
			return new BaseState(false, AppInfo.IO_ERROR);
		}
	}
	
	@Override
	public State listFile(String[] allowFiles, int start, int pageSize) {
		// 下面的代码，仅作示例
		MultiState state = new MultiState(true);
		
		// 把计入数据库中的文件信息读取出来，返回即可
		Paging paging = new Paging(pageSize);
		int pageNumber = start / pageSize + 1;
		paging.setCurrentPage(pageNumber);
		
		SysFileFilter fileFilter = new SysFileFilter();
		fileFilter.setPaging(paging);
		String baseURI;
		try {
			//系统根路径
			String rootPath = ServletContextFactory.getServletContext().getContextPath();
			if(rootPath.length() == 1) {
				rootPath = "";
			}
			baseURI = rootPath;
			List<SysFile> list = fileService.query(fileFilter);
			if (CollectionsUtils.isNotEmpty(list)) {
				for (SysFile file : list) {
					BaseState fileState = new BaseState(true);
					fileState.putInfo("url", baseURI + file.getUrl());
					state.addState(fileState);
				}
				
				state.putInfo("start", fileFilter.getPaging().getStartRow());
				state.putInfo("total", fileFilter.getPaging().getRecords());
			} else {
				state.putInfo("start", start);
				state.putInfo("total", 0);
			}
		} catch (Exception e) {
			state.putInfo("start", start);
			state.putInfo("total", 0);
		}
		return state;
	}
	
	/**
	 * 保存文件调用
	 * @author: huangcheng.dong
	 * @param data
	 * @param fileName
	 * @return 
	 * @throws Exception 
	 */
	public BaseState saveFile(byte[] data, String fileName) throws Exception {
		//原有文件名
		String originalFilename = fileName;
		String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String baseDirectory = new StringBuffer(Global.getUploadBaseDir()).toString();
		
		//新文件名
		String filename = System.currentTimeMillis() + UUIDGenerator.randomBase62(5);
		
		//文件保存相对路径
		String relativeDirectory = new StringBuffer(DateUtils.getDate("yyyy/MM/dd/")).toString();
		String relativeFilePath = new StringBuffer(relativeDirectory).append(filename).append(".").append(fileType).toString();
		
		//文件保存绝对地址
		String absoluteDirectory = new StringBuffer(baseDirectory).append(relativeDirectory).toString();
		String absoluteFilePath = new StringBuffer(baseDirectory).append(relativeFilePath).toString();
		
		FileUtils.createDirectory(FileUtils.path(absoluteDirectory));
		FileUtil.writeBytes(absoluteFilePath, data);
		
		//文件读取相对路径
		String baseUserUri = new StringBuffer("/").append(Global.USER_DIR).append("/").toString();
		//系统根路径
		String rootPath = ServletContextFactory.getServletContext().getContextPath();
		if(rootPath.length() == 1) {
			rootPath = "";
		}
		String relativeReadDir = new StringBuffer(rootPath).append(baseUserUri).toString();
		
		BaseState state = new BaseState(true);
		state.putInfo("size", data.length);
		state.putInfo("title", originalFilename.substring(0, originalFilename.lastIndexOf(".")));
		state.putInfo("url", relativeReadDir + relativeFilePath);

		//文件保存
		SysFile file = new SysFile();
		file.setFilename(filename);
		file.setFileType(fileType);
		file.setUrl(baseUserUri + relativeFilePath);
		fileService.save(file);
		
		return state;
	}
}
