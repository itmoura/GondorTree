package com.gondortree.controller;

import com.gondortree.model.Blazon;
import com.gondortree.model.Family;
import com.gondortree.model.Member;
import com.gondortree.service.BlazonService;
import com.gondortree.service.FamilyService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author itmoura
 */
@Controller
@RequestMapping("blazon.htm")
public class BlazonController {

    @Autowired
    private BlazonService service;
    
    @Autowired
    private FamilyService fmService;
    
    private final String prefix2 = "/blazon";
    private final String prefix = "blazon";

    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 90 * 1024;
    private final int maxMemSize = 4 * 1024;
    private File file;
    
    @RequestMapping(params = "action=list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) throws Exception {
        ModelAndView ret = new ModelAndView();
        ret.addObject("list", service.list());
        ret.setViewName(this.prefix2 + "/blazon");
        return ret;
    }
    
    @RequestMapping(params = "action=infamily", method = RequestMethod.POST)
    public ModelAndView infamily(HttpServletRequest req) {
        
        String memberId = req.getParameter("member.id");
        String familyId = req.getParameter("family.id");
        String memberFunction = req.getParameter("membersFunction");
        
        Family bean = new Family();
        bean.setMemberId(new Member(new Long(memberId)));
        bean.setFamilyId(new Blazon(new Long(familyId)));
        bean.setMembersFunction(new Integer(memberFunction));
        
        // bean
        fmService.register(bean);
        
        ModelAndView ret = new ModelAndView();
        ret.setView(new RedirectView(this.prefix + ".htm?action=list"));
        return ret;        
    }

    @RequestMapping(params = "action=register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest req, Blazon bean) {
        isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        String img = "";
        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(req);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    if (sizeInBytes > 0) {
                        String storeLocation = fi.toString().concat("StoreLocation");
                        String store[] = storeLocation.split(",");
                        String location = store[1];
                        String[] fimlocation = location.split("=");
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        String newName = timeStamp.concat(fileName);

                        try {
                            String bucketName = "gondor-tree";
                            String folder = "blazon";
                            String content = service.putBucket(bucketName, newName, new File(fimlocation[1]), folder);
                        } catch (IOException e) {
                            System.out.println("error: " + e);
                        } catch (Throwable t) {
                            System.out.println(t);
                        }
                        img = newName;
                    }
                } else {
                    String fieldName = fi.getFieldName();
                    String fieldValue = fi.getString();
                    if (fieldName.equals("name")) {
                        bean.setName(fieldValue);
                    } else if(fieldName.equals("create.id")) {
                        bean.setCreateId(new Member(new Long(fieldValue)));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        bean.setImage(img);
        //bean
        service.register(bean);
        //return
        ModelAndView ret = new ModelAndView();
        ret.setView(new RedirectView(this.prefix + ".htm?action=list"));
        return ret;
    }
}
