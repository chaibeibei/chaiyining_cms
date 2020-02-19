package com.chaiyining.cms.controller.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/** 
* @author 作者:
* @version 创建时间：2019年12月13日 下午1:28:08 
* 类功能说明 
*/
@Controller
@RequestMapping("/file/")
public class FileManager {
	/** 日志记录器 **/
	private static Logger log = Logger.getLogger(FileManager.class);
	/** cms.properties配置文件中读取 **/
	@Value("${upload.path}")
	String picRoot;

	@Value("${pic.path}")
	String picUrl;
	
	/**
	 * @Title: upload   
	 * @Description: 文件上传   
	 * @param: @param request
	 * @param: @param imgFile
	 * @param: @return
	 * @param: @throws FileUploadException      
	 * @return: FileResult      
	 * @throws
	 */
	@RequestMapping("upfile")
	@ResponseBody
	public FileResult upFile(HttpServletRequest request, @RequestParam("fileName") MultipartFile imgFile) throws FileUploadException {
		return upload(request,imgFile);
	}
	@SuppressWarnings("unused")
	@RequestMapping("upload")
	@ResponseBody
	public FileResult upload(HttpServletRequest request, @RequestParam MultipartFile imgFile) throws FileUploadException {

		log.info("开始上传文件啊");
		// 文件保存目录路径 todo
		// String savePath = pageContext.getServletContext().getRealPath("/") + "pic/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/pic/";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 100000000;

		// response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			log.info(getError("请选择文件。"));
			return null;
		}
		// 检查目录
		File uploadDir = new File(picRoot);
		log.info("picRoot:" + picRoot);
		if (!uploadDir.isDirectory()) {
			log.info(getError("上传目录不存在。"));
			return null;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			log.info(getError("上传目录没有写权限。"));
			return null;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			log.info(getError("目录名不正确。"));
			return null;
		}
		// 创建文件夹
		String savePath = picRoot + "/" + dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		log.info("1");
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		// ServletFileUpload upload = new ServletFileUpload(factory);
		// upload.setHeaderEncoding("UTF-8");

		// List items = upload.parseRequest(request);

		List<FileResult> fileList = new ArrayList<>();

		// Iterator itr = items.iterator();
		log.info("2");

		log.info("循环");
		// FileItem item = (FileItem) itr.next();
		String fileName = imgFile.getOriginalFilename();
		long fileSize = imgFile.getSize();

		// 检查文件大小
		if (imgFile.getSize() > maxSize) {
			log.info(getError("上传文件大小超过限制。"));
			return null;
		}
		// 检查扩展名
		log.info("fileName is " + fileName);
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)) {
			log.info(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
			return null;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try {
			log.info("savePath, newFileName :" + savePath + " -- " + newFileName);
			File uploadedFile = new File(savePath, newFileName);
			// item.write(uploadedFile);
			imgFile.transferTo(uploadedFile);
		} catch (Exception e) {
			log.info(getError("上传文件失败。"));
			return null;
		}

		return new FileResult(0, saveUrl + newFileName);

	}
	/**
	 * @Title: manager   
	 * @Description: 文件管理   
	 * @param: @param request
	 * @param: @return      
	 * @return: JSONObject      
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("manager")
	public JSONObject manager(HttpServletRequest request) {

		/** 根目录路径，可以指定绝对路径，比如 /var/www/attached **/
		String rootPath = picRoot;//request.getSession().getServletContext().getRealPath("/") + "attached/";
		/** 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached **/
		String rootUrl = request.getContextPath() + picUrl;
		/** 图片扩展名 **/
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String>asList(new String[] { "image", "flash", "media", "file" }).contains(dirName)) {
				log.info("Invalid Directory name.");
				return null;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";

		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			log.info("Access is not allowed.");
			return null;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			log.info("Parameter is not valid.");
			return null;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			log.info("Directory does not exist.");
			return null;
		}

		// 遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		JSONObject result = new JSONObject();
		result.put("moveup_dir_path", moveupDirPath);
		result.put("current_dir_path", currentDirPath);
		result.put("current_url", currentUrl);
		result.put("total_count", fileList.size());
		result.put("file_list", fileList);

		// response.setContentType("application/json; charset=UTF-8");
		// out.println(result.toJSONString());
		return result;
	}

	@SuppressWarnings("unchecked")
	private String getError(String message) {
		log.info("error " + message);
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

}

@SuppressWarnings("rawtypes")
class NameComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
		}
	}
}

@SuppressWarnings("rawtypes")
class SizeComparator implements Comparator {
	public SizeComparator() {
	}

	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
				return 1;
			} else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

@SuppressWarnings("rawtypes")
class TypeComparator implements Comparator {
	public int compare(Object a, Object b) {
		Hashtable hashA = (Hashtable) a;
		Hashtable hashB = (Hashtable) b;
		if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
			return -1;
		} else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
			return 1;
		} else {
			return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
		}
	}
}
