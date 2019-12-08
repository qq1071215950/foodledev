package com.haojing.center;

import com.haojing.bo.center.CenterUserBO;
import com.haojing.controller.BaseController;
import com.haojing.pojo.Users;
import com.haojing.resources.FileUpload;
import com.haojing.service.center.CenterUserService;
import com.haojing.utils.CookieUtils;
import com.haojing.utils.DateUtil;
import com.haojing.utils.JSONresult;
import com.haojing.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户信息接口",tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;
    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "POST")
    @PostMapping("update")
    public JSONresult update(@RequestParam String userId,
                             HttpServletRequest request, HttpServletResponse response,
                             @RequestBody @Valid CenterUserBO centerUserBO,
                             BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errorMap = getErrors(result);
            return JSONresult.errorMap(errorMap);
        }
        Users userResult = centerUserService.updateUserInfo(userId,centerUserBO);
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);
        // todo 后续修改 增加token,会整合redis
        return JSONresult.ok();
    }
    @ApiOperation(value = "用户头像修改",notes = "用户头像修改",httpMethod = "POST")
    @PostMapping("uploadFace")
    public JSONresult uploadFace(@RequestParam String userId,
                             MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 文件保存地址
         String fileSpace = IMAGE_USER_FACE_LOCATION;
        //String fileSpace = fileUpload.getImageUserFaceLocation();
        // 不同用户文件的保存
        String uploadPathPrefix = File.separator + userId;
        if (file != null) {
            // 获取上传文件的名称
            String fileName = file.getOriginalFilename();
            if (StringUtils.isNotBlank(fileName)) {
                String fileNameArr[] = fileName.split("\\.");
                String suffix = fileNameArr[fileNameArr.length - 1];
                if (!suffix.equalsIgnoreCase("png") &&
                        !suffix.equalsIgnoreCase("jpg" )&&
                                !suffix.equalsIgnoreCase("jpeg")){
                    return JSONresult.errorMsg("上传的图片文件格式不正确");
                }
                // 文件名称重组
                String newFileName = "face-" + userId + "." + suffix;
                // 文件最终上传的地址
                String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                // 用于提供web访问的地址
                uploadPathPrefix += ("/"+newFileName);
                File outFile = new File(finalFacePath);
                if (outFile.getParentFile() != null) {
                    // 创建文件夹
                    outFile.getParentFile().mkdirs();
                }
                // 文件输出
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                }

            }
        } else {
            return JSONresult.errorMsg("文件不能为空");
        }
        String imageServerUrl = IMAGE_SERVER_URL;
        // 加上时间
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix +"?t="+ DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        Users userResult = centerUserService.updateUserFace(userId,finalUserFaceUrl);
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        return JSONresult.ok("上传成功");
    }


    private Map<String, String> getErrors( BindingResult result){
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrorList  = result.getFieldErrors();
        for (FieldError error : fieldErrorList){
           String errorField = error.getField();
           String errorMessage =error.getDefaultMessage();
            map.put(errorField,errorMessage);
        }
        return map;
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
