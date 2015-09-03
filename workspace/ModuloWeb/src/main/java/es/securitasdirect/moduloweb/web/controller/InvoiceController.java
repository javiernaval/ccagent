package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.model.InvoiceInfo;
import es.securitasdirect.moduloweb.service.InvoiceService;
import es.securitasdirect.moduloweb.web.dto.response.InvoiceResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller for the Tab of the Installation
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Inject
    protected InvoiceService invoiceService;


    @RequestMapping(value = "getInvoice", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInvoice(@RequestParam(value = "installationNumber", required = true) Integer installationNumber) {
    	try{
    		InvoiceResponse response = new InvoiceResponse();
            InvoiceInfo invoice = invoiceService.getInvoice(installationNumber);
            List<CycleFeeds> cyclefeeds= invoiceService.getCycleFeeds(11111);
            List<InvoiceData> invoiceList=invoiceService.getListInvoices(installationNumber);
            Cuote cuote=invoiceService.getCuotes(installationNumber);
            response.setInvoiceInfo(invoice);
            response.setCycleFeeds(cyclefeeds);
            response.setInvoiceList(invoiceList);
            response.setCuote(cuote);
            return response;
    	}catch(Exception exception){
    		return processException(exception);
    	}
    	
//        
//        if (invoice != null) {
//            response.success(messageUtil.getProperty("installationData.success"));
//        } else {
//            response.danger(messageUtil.getProperty("installationData.notFound"));
//        }
        
    }

}
