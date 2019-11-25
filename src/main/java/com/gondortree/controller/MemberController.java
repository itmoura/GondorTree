package com.gondortree.controller;

import com.gondortree.model.Blazon;
import com.gondortree.model.Family;
import com.gondortree.model.Member;
import com.gondortree.service.FamilyService;
import com.gondortree.service.MemberService;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author itmoura
 */
@Controller
@RequestMapping("member.htm")
public class MemberController {

    @Autowired
    private MemberService service;
    
    @Autowired
    private FamilyService fmService;

    private String prefix = "/member";
    private String prefix2 = "member";

    @RequestMapping(params = "action=register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request) throws Exception {
        ModelAndView ret = new ModelAndView();
        ret.setViewName(this.prefix + "/register");
        return ret;
    }

    @RequestMapping(params = "action=register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest req, Member bean) {
        ModelAndView ret = new ModelAndView();
        if (service.register(bean)) {
            ret.setView(new RedirectView(this.prefix2 + ".htm?action=login"));
        } else {
            ret.setView(new RedirectView(this.prefix2 + ".htm?action=register&fail=true"));
        }
        //return
        return ret;
    }

    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 90 * 1024;
    private final int maxMemSize = 4 * 1024;
    private File file;

    @RequestMapping(params = "action=edit", method = RequestMethod.POST)
    public ModelAndView edit(HttpServletRequest req, Member bean) {
        isMultipart = ServletFileUpload.isMultipartContent(req);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        String photo = "";
        String photoCover = "";
        int ji = 0;
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
                            String folder = "profile";
                            String content = service.putBucket(bucketName, newName, new File(fimlocation[1]), folder);
                        } catch (IOException e) {
                            System.out.println("error: " + e);
                        } catch (Throwable t) {
                            System.out.println(t);
                        }
                        if(ji == 0){
                            photo = newName;
                            ji++;
                        } else
                            photoCover = newName;
                    }
                } else {
                    String fieldName = fi.getFieldName();
                    String fieldValue = fi.getString();
                    if (fieldName.equals("name")) {
                        bean.setName(fieldValue);
                    } else if(fieldName.equals("family.id")) {
                        bean.setFamilyId(new Blazon(new Long(fieldValue)));
                    } else if(fieldName.equals("description")) {
                        bean.setDescription(fieldValue);
                    } else if(fieldName.equals("id")) {
                        bean.setId(new Long(fieldValue));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        bean.setPhoto(photo);
        bean.setCoverPhoto(photoCover);
        
        ModelAndView ret = new ModelAndView();
        if(service.edit(bean)){
            ret.setView(new RedirectView(this.prefix2 + ".htm?action=profile&id="+bean.getId()*19819));
        } else {
            ret.setView(new RedirectView(this.prefix2 + ".htm?action=profile&id="+bean.getId()*19819+"&fail=true"));
        }
        //return
        return ret;
    }
    
    @RequestMapping(params = "action=login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) throws Exception {
        ModelAndView ret = new ModelAndView();
        ret.setViewName(this.prefix + "/login");
        return ret;
    }

    @RequestMapping(params = "action=login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, Member bean) {
        ModelAndView ret = new ModelAndView();

        Member m = service.login(bean);
        if (m == null) {
            ret.setView(new RedirectView(this.prefix + ".htm?action=login&fail=true"));
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", m);
            ret.setView(new RedirectView("blazon.htm?action=list"));
        }
        //return
        return ret;
    }

    @RequestMapping(params = "action=jsonFamily")
    public ModelAndView json(HttpServletRequest request) {
        ModelAndView ret = new ModelAndView();
        ret.setViewName("json");
        String id = request.getParameter("member");
        Long idF = Long.parseLong(id);
        List<Family> l = fmService.list();
        JSONArray arr = new JSONArray();
        for (Family ref : l) {
            if(ref.getMemberId().getId() == idF){
                JSONObject obj = ref.toJSON();
                arr.put(obj);
            }
        }
        ret.addObject("json", arr.toString());
        return ret;
    }

    @RequestMapping(params = "action=profile", method = RequestMethod.GET)
    public ModelAndView profile(HttpServletRequest request, Long id) throws Exception {
        ModelAndView ret = new ModelAndView();
        Long id2 = id / 19819;
        ret.addObject("bean", service.findByID(id2));
        ret.setViewName(this.prefix + "/profile");
        return ret;
    }

    @RequestMapping(params = "action=logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView ret = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        session.invalidate();
        ret.setView(new RedirectView(this.prefix2 + ".htm?action=login"));
        return ret;
    }

}
