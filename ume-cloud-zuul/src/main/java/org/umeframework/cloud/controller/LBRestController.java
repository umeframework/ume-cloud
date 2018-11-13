//package org.umeframework.cloud.controller;
//
//import java.util.Enumeration;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//import org.umeframework.dora.log.Logger;
//import org.umeframework.dora.message.MessageProperties;
//import org.umeframework.dora.web.controller.CommonApiController;
//
///**
// * Load balance controller
// */
//@RestController
//public class LBRestController {
//	/**
//	 * Logger of log4j2 instance
//	 */
//    @Resource
//	private Logger logger;
//	/**
//	 * statistics
//	 */
//	private static Map<String, ServiceInvokeStatistics> statistics = new ConcurrentHashMap<String, ServiceInvokeStatistics>();
//
//	/**
//	 * Load balanced restful instance
//	 */
//	@Autowired
//	private RestTemplate restTemplate;
//
//	/**
//	 * App name mapping config
//	 */
//	@Autowired
//	@Qualifier("appMapping")
//	private MessageProperties appMapping;
//
//	/**
//	 * Protocol name
//	 */
//	private String protocol = "http";
//	/**
//	 * Cloud gateway API mapping name start flag
//	 */
//	private static final String CG_API_REQUEST_MAPPING_START_FLAG = "cloudAppName";
//	/**
//	 * Cloud gateway API mapping rules
//	 */
//	private static final String CG_API_SERVICE_MAPPING = "cg/{" + CG_API_REQUEST_MAPPING_START_FLAG +  "}/" + CommonApiController.COMMON_API_SERVICE_MAPPING;
//
//	/**
//	 * doGet
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	@RequestMapping(value = CG_API_SERVICE_MAPPING + "/**", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//	public String doGet(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        @PathVariable("cloudAppName") String cloudAppName,
//	        @RequestBody(required = false) String jsonInput) {
//		return execute(request, response, cloudAppName, jsonInput);
//	}
//
//	/**
//	 * doPost
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	@RequestMapping(value = CG_API_SERVICE_MAPPING, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	public String doPost(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        @PathVariable("cloudAppName") String cloudAppName,
//	        @RequestBody(required = false) String jsonInput) {
//		return execute(request, response, cloudAppName, jsonInput);
//	}
//
//	/**
//	 * doPost
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	@RequestMapping(value = CG_API_SERVICE_MAPPING, method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
//	public String doPut(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        @PathVariable("cloudAppName") String cloudAppName,
//	        @RequestBody(required = false) String jsonInput) {
//		return execute(request, response, cloudAppName, jsonInput);
//	}
//
//	/**
//	 * doPost
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	@RequestMapping(value = CG_API_SERVICE_MAPPING, method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
//	public String doDelete(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        @PathVariable("cloudAppName") String cloudAppName,
//	        @RequestBody(required = false) String jsonInput) {
//		return execute(request, response, cloudAppName, jsonInput);
//	}
//
//	/**
//	 * doPost
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	@RequestMapping(value = CG_API_SERVICE_MAPPING, method = RequestMethod.PATCH, produces = "application/json;charset=UTF-8")
//	public String doPatch(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        @PathVariable("cloudAppName") String cloudAppName,
//	        @RequestBody(required = false) String jsonInput) {
//		return execute(request, response, cloudAppName, jsonInput);
//	}
//
//	/**
//	 * getStatistics
//	 * 
//	 * @param request
//	 * @param response
//	 * @param wid
//	 * @return
//	 */
//	@RequestMapping(value = "statistics/{service}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//	public String getStatistics(
//			HttpServletRequest request, 
//			HttpServletResponse response, 
//			@PathVariable("service") String wid) {
//		if (wid.startsWith("[") && wid.endsWith("]")) {
//			wid = wid.substring(1, wid.length() - 1);
//			if (wid.startsWith("\"") && wid.endsWith("\"")) {
//				wid = wid.substring(1, wid.length() - 1);
//			}
//		}
//		StringBuilder json = new StringBuilder();
//		for (Map.Entry<String, ServiceInvokeStatistics> e : statistics.entrySet()) {
//			String k = e.getKey();
//			if (wid.equals("*") || (wid.contains("@") && wid.equals(k)) || (!wid.contains("@") && k.startsWith(wid))) {
//				if (json.length() > 0) {
//					json.append(",");
//				}
//				json.append("{");
//				json.append("\"wid\":");
//				json.append("\"");
//				json.append(e.getKey());
//				json.append("\"");
//				json.append(",");
//				json.append("\"statistics\":");
//				json.append(e.getValue().toString());
//				json.append("}");
//			}
//		}
//		if (json.length() == 0) {
//			return wid + " has not statistics data.";
//		} else {
//			return "[" + json.toString() + "]";
//		}
//	}
//
//	/**
//	 * execute
//	 * 
//	 * @param category
//	 * @param request
//	 * @param response
//	 * @param cloudAppName
//	 * @param system
//	 * @param service
//	 * @param jsonInput
//	 * @return
//	 */
//	protected String execute(
//	        HttpServletRequest request,
//	        HttpServletResponse response,
//	        String cloudAppName,
//	        String jsonInput) {
//		String name = appMapping.get(cloudAppName);
//		if (name != null && !name.trim().equals("")) {
//			cloudAppName = name;
//		}
//
//		String thread = "[" + Thread.currentThread().getId() + "]";
//		String remoteAddr = request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr() : request.getHeader("x-forwarded-for");
//		
//		String servletPath = request.getServletPath();
//		servletPath = !servletPath.startsWith("/") ? "/" + servletPath : servletPath;
//		String url = protocol + "://" + cloudAppName + servletPath;
//		ResponseEntity<String> res = null;
//		long startTime = System.currentTimeMillis();
//		if (RequestMethod.GET.toString().equals(request.getMethod())) {
//			url = url + (jsonInput != null && !jsonInput.trim().equals("") ? jsonInput : "") + getRequestParameters(request);
//			logger.info(thread + "Request@" + remoteAddr + "," + request.getMethod() + ":" + url);
//
//			try {
//				res = getRestTemplate().getForEntity(url, String.class);
//			} catch (RestClientException e) {
//				logger.error(e);
//			}
//		} else if (RequestMethod.POST.toString().equals(request.getMethod())) {
//			HttpHeaders headers = getRequestHeaders(request);
//			HttpEntity<String> entity = new HttpEntity<String>(jsonInput, headers);
//			url = url + getRequestParameters(request);
//			logger.info(thread + "Request@" + remoteAddr + "," + request.getMethod() + ":" + url);
//			try {
//				res = getRestTemplate().postForEntity(url, entity, String.class);
//			} catch (RestClientException e) {
//				logger.error(e);
//			}
//		} else {
//			logger.error("Unsupport method in current controller:" + request.getMethod());
//			return null;
//		}
//
//		long useTime = System.currentTimeMillis() - startTime;
//		updateStatistics(url, useTime);
//
//		Integer status = res != null ? res.getStatusCodeValue() : null;
//		logger.info(thread + "Request@" + remoteAddr + ",Status:" + status + ",Used:" + useTime);
//
//		String body = res != null ? res.getBody() : null;
//		return body;
//	}
//
//	/**
//	 * getRequestHeaders
//	 * 
//	 * @param request
//	 * @return
//	 */
//	protected HttpHeaders getRequestHeaders(HttpServletRequest request) {
//		HttpHeaders headers = new HttpHeaders();
//		request.getHeaderNames();
//		Enumeration<String> e = request.getHeaderNames();
//		while (e.hasMoreElements()) {
//			String headerName = e.nextElement();
//			String headerValue = request.getHeader(headerName);
//			headers.add(headerName, headerValue);
//		}
//		return headers;
//	}
//
//	/**
//	 * getRequestParameters
//	 * 
//	 * @param request
//	 * @return
//	 */
//	protected String getRequestParameters(HttpServletRequest request) {
//		StringBuilder builder = new StringBuilder();
//		Enumeration<String> names = request.getParameterNames();
//		while (names.hasMoreElements()) {
//			String name = names.nextElement();
//			String value = request.getParameter(name);
//			builder.append("&");
//			builder.append(name);
//			builder.append("=");
//			builder.append(value);
//		}
//		String params = builder.length() > 0 ? "?" + builder.substring(1) : "";
//		return params;
//	}
//
//	/**
//	 * updateStatistics
//	 * 
//	 * @param wid
//	 * @param useTime
//	 */
//	synchronized protected void updateStatistics(String wid, long useTime) {
//		if (!statistics.containsKey(wid)) {
//			statistics.put(wid, new ServiceInvokeStatistics());
//		}
//		ServiceInvokeStatistics e = statistics.get(wid);
//		e.update(useTime);
//	}
//
//	/**
//	 * @return the restTemplate
//	 */
//	public RestTemplate getRestTemplate() {
//		return restTemplate;
//	}
//
//}
