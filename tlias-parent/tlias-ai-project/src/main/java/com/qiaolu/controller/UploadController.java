package com.qiaolu.controller;


import com.qiaolu.pojo.Result;
import com.qiaolu.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
//    /**
//     * 文件存储(存储在本地)
//     */
//    @PostMapping("/upload")
//    public Result uploadFile(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("上传文件:{}, {}. {}",name, age, file);
//        if (!file.isEmpty()) {
//            String originName = file.getOriginalFilename();
//            String stuf = originName.substring(originName.lastIndexOf("."));
//            String newFileName = UUID.randomUUID().toString() + stuf;
//            file.transferTo(new File("C:/Users/lijun/Desktop/" + newFileName)); // 这里是文件路径,如果不存需要创建,mkdir
//        }
//        return Result.success();
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws Exception {
        String upload = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(upload);
    }
}
