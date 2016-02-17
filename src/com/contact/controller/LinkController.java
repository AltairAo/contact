package com.contact.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact.beans.User;
import com.contact.core.Json;
import com.contact.service.*;

@Controller
public class LinkController {

	@Resource
	private LinkService linkService;

	@RequestMapping(value = "/test", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void Init(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<User> ls = new ArrayList<User>();
		String name = request.getParameter("name");
		Json json = new Json();
		if (StringUtils.isNotBlank(name) && name != null) {
			ls = linkService.testlink(name);
		} else {
			ls = linkService.testlink();
		}
		JSONArray jsonArray = new JSONArray();
		for (int ii = 0; ii < ls.size(); ii++) {
			User user = ls.get(ii);
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("Userid", user.getId());
			jsonObject.element("name", user.getName());
			jsonObject.element("phoneno", user.getPhoneno());
			jsonObject.element("location", user.getLocation());
			jsonArray.add(jsonObject);
		}
		JSONObject resultJSONObject = new JSONObject();
		resultJSONObject.element("list", jsonArray);
		json.writeJSON(response, resultJSONObject);
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void Add(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		Long phoneno = Long.parseLong(request.getParameter("phoneno"));
		String location = request.getParameter("location");
		User user = new User();
		user.setName(name);
		user.setPhoneno(phoneno);
		user.setLocation(location);
		linkService.Add(user);
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void Delete(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = Long.parseLong(request.getParameter("id"));
		linkService.Delete(id);

	}

	@RequestMapping(value = "/modify", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void Modify(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		Long phoneno = Long.parseLong(request.getParameter("phoneno"));
		String location = request.getParameter("location");
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPhoneno(phoneno);
		user.setLocation(location);
		linkService.Modify(user);
	}
}