package com.xina.soul2.controller;

import com.xina.soul2.service.DeadSeivice;
import com.xina.soul2.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class SoulFileController {
    @Resource
    private UserService userService;
    @Resource
    private DeadSeivice deadSeivice;

    @PostMapping("/")
    public boolean upload(@RequestParam MultipartFile file, HttpServletRequest httpServletRequest){

        String parentPath = "";
        String databasePath = "";

        String id = httpServletRequest.getParameter("id");

        String table = httpServletRequest.getParameter("table");

        String pathNow = httpServletRequest.getParameter("pathNow");

        String originalFilename = file.getOriginalFilename();

        String property = System.getProperty("user.dir");

        // 先删除原来的文件
        if("user".equals(table)){
            if(!"/img/defaultimg.png".equals(pathNow)){
                File beforeFile = new File(property + "\\src\\main\\resources\\static\\" + pathNow);
                if(beforeFile.exists()){
                    beforeFile.delete();
                }
            }
        }

        if("dead".equals(table)){
            if(!"/deadphoto/none.png".equals(pathNow)){
                File beforeFile = new File(property + "\\src\\main\\resources\\static\\" + pathNow);
                if(beforeFile.exists()){
                    beforeFile.delete();
                }
            }
        }

        if(table != null){
            parentPath = property + "\\src\\main\\resources\\static\\img\\upload\\"+table;
        }else{
            parentPath = property + "\\src\\main\\resources\\static\\img\\upload\\files";
        }

        File parent = new File(parentPath);

        if (!parent.exists()){
            parent.mkdirs();
        }

        String finalName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        String uploadPath = parent.getPath() + "\\" + finalName;

        File upload = new File(uploadPath);

        try {
            file.transferTo(upload);
            //修改数据库的路径
            if(table != null){
                databasePath = "/img/upload/"+table+"/"+ finalName;
            }else{
                databasePath = "/img/upload/files/" + finalName;
            }
            boolean flag = false;
            if("user".equals(table)){
                flag = userService.updateImagePath(databasePath, Integer.valueOf(id));
            }
            if("dead".equals(table)){
                flag = deadSeivice.updateImage(databasePath, Integer.valueOf(id));
            }

            return flag;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
