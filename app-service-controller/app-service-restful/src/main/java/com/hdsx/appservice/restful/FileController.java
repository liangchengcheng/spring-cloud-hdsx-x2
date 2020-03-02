package com.hdsx.appservice.restful;

import com.hdsx.appservice.bean.XbinResult;
import com.hdsx.appservice.utils.CompressUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "file")
@Api(value = "API - 文件上传的服务", description = "文件上传的服务（doc、ppt、excel文件上传）", tags = "API-FILE")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${drinkwater.imagePath}")
    private String imagePath;

    @Value("${drinkwater.filePath}")
    private String filePath;

    @Value("${drinkwater.vuePath}")
    private String vuePath;

    @ApiOperation(value = "文件上传接口（单个）")
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult uploadOneFile(@RequestParam MultipartFile file) {
        if (file != null ) {
            String fileName = file.getOriginalFilename();
            String File_url = filePath + fileName;
            File dest = new File(File_url);
            //  不覆盖原来的文件
            if (dest.exists()) {
                return XbinResult.build(0, "文件已存在");
            }

            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            try {
                // 保存文件
                file.transferTo(dest);
                String url = "appadmin/file/"  + fileName;
                return XbinResult.ok(url);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return XbinResult.build(0, "上传文件失败");
            } catch (IOException e) {
                e.printStackTrace();
                return XbinResult.build(0, "上传文件失败");
            }
        }
        return XbinResult.build(0, "上传文件失败");
    }

    @ApiOperation(value = "前端zip文件上传接口（单个）")
    @RequestMapping(value = "/uploadOneFileForZip", method = RequestMethod.POST)
    @ResponseBody
    public XbinResult uploadOneFileForZip(@RequestParam MultipartFile file) {
        if (file != null ) {
            String fileName = file.getOriginalFilename();
            String dirName = file.getOriginalFilename().replace(".zip", "");

            String File_url = vuePath + fileName;
            File dest = new File(File_url);

            // 判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            String dir_url = vuePath + dirName;
            File destDir = new File(dir_url);
            //  原来的文件夹改成 + "-"
            if (destDir.exists()) {
                String fileNewUrl = vuePath + dirName + "-";
                File fileNew = new File(fileNewUrl);
                destDir.renameTo(fileNew);
            }

            try {
                // 保存文件
                file.transferTo(dest);
                File[] unzip = CompressUtil.unzip(dest, vuePath, "");
                if (unzip != null) {
                    File file1 = unzip[0];
                    String name = dirName;
                    String url = name;
                    return XbinResult.ok(url);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return XbinResult.build(0, "上传文件失败");
            } catch (IOException e) {
                e.printStackTrace();
                return XbinResult.build(0, "上传文件失败");
            } catch (ZipException e) {
                e.printStackTrace();
                return XbinResult.build(0, "上传文件失败");
            }
        }
        return XbinResult.build(0, "上传文件失败");
    }

}
