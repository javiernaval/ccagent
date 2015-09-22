package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.CombinationsKeys;
import es.securitasdirect.moduloweb.model.FieldConfig;
import es.securitasdirect.moduloweb.service.AdminService;
import es.securitasdirect.moduloweb.web.dto.response.ListCombinationsKeysResponse;import es.securitasdirect.moduloweb.service.AuditService;
import es.securitasdirect.moduloweb.web.dto.request.InsertCombinationsKeysRequest;
import es.securitasdirect.moduloweb.web.dto.request.SearchInstallationRequest;
import es.securitasdirect.moduloweb.web.dto.response.AuditResponse;
import es.securitasdirect.moduloweb.web.dto.response.ListFieldConfigResponse;
import es.securitasdirect.moduloweb.web.dto.response.SimpleResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Tab of the Installation
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Inject
    protected AdminService adminService;


    @RequestMapping(value = "getFieldConfig", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getFieldConfig() {
        ListFieldConfigResponse response = new ListFieldConfigResponse();
        List<FieldConfig> list = adminService.getFieldConfig();
        response.setFieldConfig(list);
        return response;
    }
    /*
    @RequestMapping(value = "/getTabKeys", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getTabKeys() {
        GetTabKeysResponse response = new GetTabKeysResponse();
        try{
	        response.setTabKeys(adminService.getTabKeys());
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    @RequestMapping(value = "/getUsers", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getUsers() {
        GetUsersResponse response = new GetUsersResponse();
        try{
	        //TODO: response.setUsers(adminService.getUsers());
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
*/
    @RequestMapping(value = "listcombinationskeys", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getCombinationsKeys() {
        try{
            ListCombinationsKeysResponse response = new ListCombinationsKeysResponse();
            List<CombinationsKeys> list = adminService.getCombinationsKeys();
            response.setCombinationsKeys(list);
            return response;
        }catch(Exception exception){
            return processException(exception);
        }
    }
    @RequestMapping(value = "insertCombinationsKeys", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse insertCombinationsKeys(@RequestBody InsertCombinationsKeysRequest request) {
        BaseResponse response = new BaseResponse();
        try {
        	adminService.insertCombinationsKeys(request.getCombinationKeys());
        	response.info(messageUtil.getProperty("createtask.create.success"));
        } catch (Exception e) {
            LOGGER.error("Error creating task.", e);
            response = processException(e);
        }
        return response;
    }

}
