package com.seven.wx.provider.api;

import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.seven.wx.core.service.TxCloudService;
import com.seven.wx.dao.SevenDao;
import com.seven.wx.dao.domain.Seven;
import com.seven.wx.provider.vo.SevenVo;
import com.seven.wx.provider.vo.UploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by xiaozhiping on 18/3/4.
 */
@RestController
public class SevenCoreController {

    private static final Logger logger = LoggerFactory.getLogger(SevenCoreController.class);

    @Resource
    private TxCloudService txCloudService;

    @Autowired
    private SevenDao sevenDao;

    @RequestMapping("/seven/{page}")
    public List<SevenVo> getPhoto(@PathVariable int page) {
        List<SevenVo> list = Lists.newArrayList();
        try {
            List<Seven> sevenList = txCloudService.select(page);
            if (sevenList.size() != 0) {
                for (Seven seven : sevenList) {
                    SevenVo sevenVo = new SevenVo();
                    sevenVo.setDesc(seven.getSay());
                    sevenVo.setId(seven.getId());
                    sevenVo.setSrc("https://77-1256128949.cos.ap-beijing.myqcloud.com/" + seven.getName());
                    list.add(sevenVo);
                }
            }
        }catch(Exception e){
            logger.error("获取列表错误" + page,e);
        }
        return list;
    }

    @RequestMapping("/remove")
    public String getPhoto(@RequestParam("id") int id,@RequestParam("token") String token) {
        try {
            if(token.equals("*******77*******")){
                txCloudService.delete(id);
            }else{
                return "添加失败";
            }
        }catch(Exception e){
            logger.error("获取列表错误",e);
            return "添加失败";
        }
        return "添加成功";
    }

    @RequestMapping("/upload")
    public UploadVo uploadPhoto(@RequestParam("file") MultipartFile file,@RequestParam("desc") String desc, @RequestParam("token") String token) throws IOException {
            UploadVo uploadVo = new UploadVo();
        try {
            if (StringUtils.isEmpty(token) || !token.equals("20170313.77")) {
                logger.info("errorCode:" + token);
                uploadVo.setCode(UploadVo.TOKEN_ERROR);
                uploadVo.setMessage("密钥错误");
                return uploadVo;
            }
            logger.info("fileName：" + file.getOriginalFilename() + "." + desc);
            ByteArrayInputStream fin = (ByteArrayInputStream) file.getInputStream();
            txCloudService.upload(fin, file.getOriginalFilename(), desc, file.getSize());
        }catch (Exception e){
            logger.error("上传错误:" + desc,e);
            uploadVo.setCode(UploadVo.OTHER_ERROR);
            uploadVo.setMessage("其他未知错误");
        }
            return uploadVo;

    }
}
