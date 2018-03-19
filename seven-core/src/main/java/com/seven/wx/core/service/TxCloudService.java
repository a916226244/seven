package com.seven.wx.core.service;

import com.google.common.collect.Lists;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.seven.wx.dao.SevenDao;
import com.seven.wx.dao.domain.Seven;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaozhiping on 18/3/17.
 */
@Component
public class TxCloudService {

    private static Logger logger = LoggerFactory.getLogger(TxCloudService.class);

    private static int PAGE_SIZE=10;

    @Autowired
    private SevenDao sevenDao;


    public int delete(long id){
        return sevenDao.deleteById(id);
    }


    public List<Seven> select(int page){
        int count = sevenDao.selectCount();
        if((page-1)*PAGE_SIZE >count){
            return Lists.newArrayList();
        }
        return sevenDao.selectByPage((page-1)*PAGE_SIZE,PAGE_SIZE);
    }

    /**
     * 照片上传
     * @param byteArrayInputStream
     * @param size
     */
    public void  upload(ByteArrayInputStream byteArrayInputStream,String name,String desc,long size){
        COSCredentials cred = new BasicCOSCredentials("AKIDfSJ0azFMG7sSr5kyJdlhR4joqhNtNMKA", "sa6TfjbLwT7av54zlMB5c2H6oLhHQTPA");
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
        COSClient cosclient = new COSClient(cred, clientConfig);
        String bucketName = "77-1256128949";
        // 指定要上传到 COS 上的路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String key = sdf.format(new Date()) + "." + name;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType("image/jpeg");
        PutObjectResult putObjectResult = cosclient.putObject(bucketName, key,byteArrayInputStream,objectMetadata);
        logger.info("上传结果=" + putObjectResult);
        Seven seven = new Seven();
        seven.setBucketName(bucketName);
        seven.setPath("/");
        seven.setCreateTime(new Date());
        seven.setSay(desc);
        seven.setName(key);
        sevenDao.insert(seven);
    }

}
