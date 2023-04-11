package com.reach.common.utils;

import cn.hutool.json.JSONUtil;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
public class S3Util {

    /**
     * upload user logo
     */
    public static String uploadToS3(CommonsMultipartFile multipartFile, String prefixPath, String classification) {
        String AWS_ACCESS_KEY = RedisUtil.getString("AWS_ACCESS_KEY");
        String AWS_SECRET_KEY = RedisUtil.getString("AWS_SECRET_KEY");
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        AWSStaticCredentialsProvider aws = new AWSStaticCredentialsProvider(basicAWSCredentials);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(aws)
                .build();
        return uploadFile(s3Client,multipartFile, createFilePath(prefixPath, multipartFile.getName(), classification));
    }

    /**
     * create file path on s3
     */
    private static String createFilePath(String prefixPath, String fileName, String classification) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefixPath);
        buffer.append("/");
        buffer.append(Calendar.getInstance().get(Calendar.YEAR));
        buffer.append("/");
        buffer.append(classification);
        buffer.append("/");
        buffer.append(createImageName());
        buffer.append(".");
        buffer.append(fileNameSuffix(fileName));
        return buffer.toString();
    }

    /**
     * fetch fileName suffix
     */
    private static String fileNameSuffix(String fileName) {
        String[] split = StringUtils.split(fileName, ".");
        return split[split.length - 1];
    }

    /**
     * create images name
     */
    private static String createImageName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * upload file to amazon s3
     */
    private static String uploadFile(AmazonS3 s3Client, CommonsMultipartFile multipartFile, String filePath) {
        String AWS_BUCKET_NAME = RedisUtil.getString("AWS_BUCKET_NAME");
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        String urlString = null;
        try {
            // do upload
            s3Client.putObject(new PutObjectRequest(AWS_BUCKET_NAME, filePath, multipartFile.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(
                    AWS_BUCKET_NAME, filePath);
            URL url = s3Client.generatePresignedUrl(urlRequest);
            String[] urlArr = url.toString().split("\\?");
            urlString = urlArr[0];
            log.info(urlString);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw ReachException.build(ResponseEnum.UPLOAD_TO_S3_ERROR);
        }
        return filePath;
    }


}
