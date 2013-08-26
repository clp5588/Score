package com.sanzhong.score.action;

import com.sanzhong.score.common.Page;
import com.sanzhong.score.pojo.Operation;
import com.sanzhong.score.pojo.Resource;
import com.sanzhong.score.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LCD
 * Date: 13-8-19
 * Time: 下午11:59
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/resource/manage")
    public String findAllResources(HttpServletRequest request) {
        try {
            String currentPage = request.getParameter("current");
            String resName = request.getParameter("resName");
            Page page = new Page();
            if (!"".equals(currentPage) && currentPage != null) {
                page.setCurrent(Integer.valueOf(currentPage.trim()));
            }
            List<Resource> list = systemService.findResourceByPage(page, resName);
            int total = systemService.findResourceSize(resName);
            List<Resource> all = systemService.findAllResource();
            page.setTotal(total);
            List<Operation> operations = systemService.findResourceType();
            request.setAttribute("page", page);
            request.setAttribute("list", list);
            request.setAttribute("allRes", all);
            request.setAttribute("operations", operations);
        } catch (Exception e) {

        }
        return "/resource/resource";
    }

    @RequestMapping(value = "/resource/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestParam("resName") String resName, @RequestParam("resUrl") String resUrl,
                      @RequestParam("resType") String resType, @RequestParam("resAuth") String resAuth,
                      @RequestParam("resParentId") String resParentId, @RequestParam("resIcon") String resIcon) {
        try {
            Resource resource = new Resource();
            resource.setAuth(Integer.valueOf(resAuth));
            resource.setIcon(resIcon.trim());
            resource.setIs_module(Integer.valueOf(resType));
            resource.setUrl(resUrl.trim());
            resource.setParent_id(Integer.valueOf(resParentId));
            resource.setName(resName.trim());
            systemService.addResource(resource);
        } catch (Exception e) {
            return "failure";
        }
        return "success";
    }
    @RequestMapping(value = "/resource/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestParam("resId")String id,@RequestParam("resName") String resName,
                       @RequestParam("resUrl") String resUrl,@RequestParam("resType") String resType,
                       @RequestParam("resAuth") String resAuth,@RequestParam("resParentId") String resParentId,
                       @RequestParam("resIcon") String resIcon) {
        try {
            Resource resource = new Resource();
            resource.setId(Integer.valueOf(id));
            resource.setAuth(Integer.valueOf(resAuth));
            resource.setIcon(resIcon.trim());
            resource.setIs_module(Integer.valueOf(resType));
            resource.setUrl(resUrl.trim());
            resource.setParent_id(Integer.valueOf(resParentId));
            resource.setName(resName.trim());
            systemService.modifyResource(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }
    @RequestMapping(value = "/resource/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(HttpServletRequest request,
                                   @RequestParam("id") String id) {
        try {
            systemService.deleteResource(id);
        } catch (Exception e) {
            return "failure";
        }
        return "success";
    }
}
