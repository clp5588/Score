package com.sanzhong.score.action;

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
    public String findAllResources(HttpServletRequest request) throws Exception {
        List<Resource> list = systemService.findAllResource();
        List<Operation> operations = systemService.findResourceType();
        request.setAttribute("list", list);
        request.setAttribute("operations", operations);
        return "/resource/resource";
    }

    @RequestMapping(value = "/resource/add",method = RequestMethod.POST)
    @ResponseBody
    public String findAllResources(HttpServletRequest request,
                                   @RequestParam("resName") String resName, @RequestParam("resUrl") String resUrl,
                                   @RequestParam("resType") String resType, @RequestParam("resAuth") String resAuth,
                                   @RequestParam("resParentId") String resParentId, @RequestParam("resIcon") String resIcon) throws Exception {
        Resource resource = new Resource();
        resource.setAuth(Integer.valueOf(resAuth));
        resource.setIcon(resIcon.trim());
        resource.setIs_module(Integer.valueOf(resType));
        resource.setUrl(resUrl.trim());
        resource.setParent_id(Integer.valueOf(resParentId));
        resource.setName(resName.trim());
        systemService.addResource(resource);
        return "success";
    }
}
