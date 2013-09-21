package net.bitacademy.java41.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.bitacademy.java41.controls.PageControl;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		try {
			Map<String,String> cookieMap = createCookieMap(request);
			model.put("cookies", cookieMap);
			if (ServletFileUpload.isMultipartContent(request)) {
				model.put("params", getMultipartParameterMap(request));
			} else {
				model.put("params", request.getParameterMap());
			}
			model.put("session", request.getSession() );
			model.put("request", request);
			model.put("response", response);
		
			String viewUrl = null;
			PageControl control = (PageControl)request.getServletContext()
									.getAttribute(request.getServletPath());
			if (control != null) {
				viewUrl = control.execute(model);
				transferFromControlDataToRequest(request, model);
				processResult(request, response, viewUrl);
			} else {
				throw new Exception("해당 요청을 처리할 수 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			processResult(request, response, "/error.jsp");
		}
	}

	private Map<String,Object> getMultipartParameterMap(HttpServletRequest request) 
			throws Exception {
		HashMap<String,Object> params = new HashMap<String,Object>();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(factory);
		List<FileItem> partList = uploadHandler.parseRequest(request);
		for(FileItem item : partList) {
			if (item.isFormField()) {
				params.put(item.getFieldName(), item.getString("UTF-8"));
			} else {
				params.put(item.getFieldName(), item);
			}
		}
		return params;
	}
	private void processResult(HttpServletRequest request,
			HttpServletResponse response, String viewUrl)
			throws ServletException, IOException {
		if (viewUrl.startsWith("redirect:")) {
			response.sendRedirect(viewUrl.substring(9));
		} else if (viewUrl.startsWith("include:")) {
			RequestDispatcher rd = 
					request.getRequestDispatcher(viewUrl.substring(8));
			rd.include(request, response);
		} else {
			RequestDispatcher rd = 
					request.getRequestDispatcher(viewUrl);
			rd.forward(request, response);
		}
	}

	private void transferFromControlDataToRequest(HttpServletRequest request,
			HashMap<String, Object> model) {
		Set<String> keyList = model.keySet();
		for(String key : keyList) {
			if (!key.equals("cookies") && !key.equals("params")) {
				request.setAttribute(key, model.get(key));	
			}
		}
	}

	private Map<String,String> createCookieMap(HttpServletRequest request) {
		Hashtable<String,String> cookieMap = new Hashtable<String,String>();
		Cookie[] cookieList = request.getCookies();
		if (cookieList != null) {
			for(Cookie cookie : cookieList) {
				cookieMap.put(cookie.getName(), cookie.getValue());
			}
		}
		return cookieMap;
	}
	
	
}






















