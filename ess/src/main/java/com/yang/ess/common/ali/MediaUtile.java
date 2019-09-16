package com.yang.ess.common.ali;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import com.aliyun.vod.upload.impl.PutObjectProgressListener;
import com.yang.ess.common.utils.Utils;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadFileStreamRequest;
import com.aliyun.vod.upload.req.UploadImageRequest;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadFileStreamResponse;
import com.aliyun.vod.upload.resp.UploadImageResponse;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteImageRequest;
import com.aliyuncs.vod.model.v20170321.DeleteImageResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

public class MediaUtile {

    // 账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAINiDMsu3UA2Pa";
    // 账号AK信息请填写(必选)
    private static final String accessKeySecret = "SSoNb6Aj7y3ZOswT1o6Ghp3QKjygaB";

    private static final String imageType = "default";

    public static Long KNOWLEGE = 1000001210L;
    public static Long TRAILER = 1000001211L;
    public static Long FIVETEENSEC = 1000003520L;
    public static Long AUDIO = 1000001213L;

    public static void main(String[] args) {
        // 一、视频文件上传
        // 视频标题(必选)
        String title = "测试标题";
        // 1.本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
        // 2.网络流上传时，文件名称为源文件名，如文件名称.mp4(必选)。
        // 3.流式上传时，文件名称为源文件名，如文件名称.mp4(必选)。
        // 任何上传方式文件名必须包含扩展名
        String fileName = "D:\\\\1231231231.jpg";
        try {
            // 本地文件上传
            testUploadVideo(accessKeyId, accessKeySecret, title, "D:/testFile/123.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 待上传视频的网络流地址
        //String url = "http://video.sample.com/sample.mp4";
        // 2.网络流上传
        // testUploadURLStream(accessKeyId, accessKeySecret, title, fileName,
        // url);
        // 3.文件流上传
        // testUploadFileStream(accessKeyId, accessKeySecret, title, fileName);
        // 4.流式上传，如文件流和网络流
        //InputStream inputStream = null;
        // 4.1 文件流
        // try {
        // inputStream = new FileInputStream(fileName);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        // 4.2 网络流
        // try {
        // inputStream = new URL(url).openStream();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // testUploadStream(accessKeyId, accessKeySecret, title, fileName,
        // inputStream);
        // 二、图片上传
        // 1.图片上传-本地文件上传
        testUploadImageLocalFile(fileName);
        // 2.图片上传-流式上传(文件流和网络流)
        // testUploadImageStream(accessKeyId, accessKeySecret,);
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(1 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定） */
        request.setTaskNum(1);
        /*
         * 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，
         * 适用于超时3000秒仍不能上传完成的大文件。 注意:
         * 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启
         */
        request.setEnableCheckpoint(true);
        /*
         * OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位:
         * 毫秒，默认为300000毫秒
         */
        // request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s */
        // request.setSlowRequestsThreshold(300000L);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印 */
        // request.setIsShowWaterMark(true);
        /*
         * 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档
         * https://help.aliyun.com/document_detail/57029.html
         */
        // request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        // request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        // request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        // request.setDescription("视频描述");
        /* 封面图片(可选) */
        // request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        // request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        // request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /*
             * 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，
             * 此时需要根据返回错误码分析具体错误原因
             */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 网络流上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param url
     */
    @SuppressWarnings("unused")
    private static void testUploadURLStream(String accessKeyId, String accessKeySecret, String title, String fileName,
                                            String url) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, fileName, url);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印 */
        // request.setIsShowWaterMark(true);
        /*
         * 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档
         * https://help.aliyun.com/document_detail/57029.html
         */
        // request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        // request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        // request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        // request.setDescription("视频描述");
        /* 封面图片(可选) */
        // request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        // request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        // request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /*
             * 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，
             * 此时需要根据返回错误码分析具体错误原因
             */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 文件流上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    @SuppressWarnings("unused")
    private static void testUploadFileStream(String accessKeyId, String accessKeySecret, String title,
                                             String fileName) {
        UploadFileStreamRequest request = new UploadFileStreamRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印 */
        // request.setShowWaterMark(true);
        /*
         * 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档
         * https://help.aliyun.com/document_detail/57029.html
         */
        // request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        // request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        // request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        // request.setDescription("视频描述");
        /* 封面图片(可选) */
        // request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        // request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        // request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadFileStreamResponse response = uploader.uploadFileStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /*
             * 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，
             * 此时需要根据返回错误码分析具体错误原因
             */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 流式上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param inputStream
     */
    public static GetPlayInfoResponse testUploadStream(String accessKeyId, String accessKeySecret, String title,
                                                       String fileName, Long cateId, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName,
                inputStream);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印 */
        // request.setShowWaterMark(true);
        /*
         * 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档
         * https://help.aliyun.com/document_detail/57029.html
         */
        // request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */

        request.setCateId(cateId);
        /* 视频标签,多个用逗号分隔(可选) */
        // request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        // request.setDescription("视频描述");
        /* 封面图片(可选) */
        // request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        // request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        // request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        request.setProgressListener(new PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            String regionId = "cn-shanghai";
            // 上传凭证
            DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            GetPlayInfoRequest req = new GetPlayInfoRequest();
            req.setVideoId(response.getVideoId());
            GetPlayInfoResponse videoURL = null;
            try {
                videoURL = client.getAcsResponse(req);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            String playUrl = videoURL.getPlayInfoList().get(0).getPlayURL();
            String coverUrl = videoURL.getVideoBase().getCoverURL();
            System.out.println("音视频播放地址为:" + playUrl + "****************************************************");
            System.out.println("图片地址为:" + coverUrl + "****************************************************");

            System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
            return videoURL;
        } else { // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return null;
    }

    /**
     * 图片上传接口，本地文件上传示例 参数参考文档 https://help.aliyun.com/document_detail/55619.html
     *
     * @param accessKeyId
     * @param accessKeySecret
     */
    // private static void testUploadImageLocalFile(String accessKeyId, String
    // accessKeySecret) {
    // UploadImageRequest request = new UploadImageRequest(accessKeyId,
    // accessKeySecret, imageType);
    // /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
    // // request.setImageType("cover");
    // /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
    // //request.setImageExt("png");
    // /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
    // String title = Utils.getGUID();
    // request.setTitle(title);
    // /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
    // //request.setTags("标签1,标签2");
    // /* 存储区域（可选）*/
    // //request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
    // /* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选)*/
    // request.setFileName("F://images//FIL326.JPG");
    // /* 开启默认上传进度回调 */
    // request.setPrintProgress(true);
    // /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
    // request.setProgressListener(new PutObjectProgressListener());
    // UploadImageImpl uploadImage = new UploadImageImpl();
    // UploadImageResponse response = uploadImage.upload(request);
    // System.out.print("RequestId=" + response.getRequestId() + "\n");
    // System.out.print("ErrorCode=" + response.getCode() + "\n");
    // System.out.print("ErrorMessage" + response.getMessage() + "\n");
    // System.out.print("ImageId=" + response.getImageId() + "\n");
    // System.out.print("ImageURL=" + response.getImageURL() + "\n");
    // }
    /**
     * 图片上传接口，流式上传示例（支持文件流和网络流） 参数参考文档
     * https://help.aliyun.com/document_detail/55619.html
     *
     * @param accessKeyId
     * @param accessKeySecret
     */
    public static UploadImageResponse testUploadImageStream(String accessKeyId, String accessKeySecret,
                                                            MultipartFile file) {
        UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
        saveFile(file);
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印） */
        // request.setImageType("cover");
        /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
        // request.setImageExt("png");
        /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
        // request.setTitle("图片标题");
        /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
        // request.setTags("标签1,标签2");
        /* 存储区域（可选） */
        // request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
        /* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选) */
        request.setFileName(file.getOriginalFilename());
        /* 开启默认上传进度回调 */
        request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        request.setProgressListener(new PutObjectProgressListener());
        // 1.文件流上传
        // InputStream fileStream = getFileStream(request.getFileName());
        // if (fileStream != null) {
        try {
            request.setInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // }
        // 2.网络流上传
        // String url = "http://image.sample.com/sample.png";
        // InputStream urlStream = getUrlStream(url);
        // if (urlStream != null) {
        // request.setInputStream(urlStream);
        // }
        // 开始上传图片
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        System.out.print("ErrorCode=" + response.getCode() + "\n");
        System.out.print("ErrorMessage" + response.getMessage() + "\n");
        System.out.print("ImageId=" + response.getImageId() + "\n");
        System.out.print("ImageURL=" + response.getImageURL() + "\n");
        return response;
    }

    @SuppressWarnings("unused")
    private static InputStream getFileStream(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unused")
    private static InputStream getUrlStream(String url) {
        try {
            return new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        // 点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    public static GetPlayInfoResponse getPlayInfo(String mediaId) throws Exception {
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(mediaId);
        return client.getAcsResponse(request);
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    public static GetPlayInfoResponse testUploadVideo(String accessKeyId, String accessKeySecret, String title,
                                                      String fileName, Long CateId) throws Exception {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
        request.setPartSize(1 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定） */
        request.setTaskNum(1);
        /*
         * 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，
         * 适用于超时3000秒仍不能上传完成的大文件。 注意:
         * 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启
         */
        request.setEnableCheckpoint(true);
        /*
         * OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位:
         * 毫秒，默认为300000毫秒
         */
        // request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s */
        // request.setSlowRequestsThreshold(300000L);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印 */
        // request.setIsShowWaterMark(true);
        /*
         * 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档
         * https://help.aliyun.com/document_detail/57029.html
         */
        // request.setCallback("http://callback.sample.com");
        /* 视频分类ID(可选) */
        request.setCateId(CateId);
        /* 视频标签,多个用逗号分隔(可选) */
        // request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        // request.setDescription("视频描述");
        /* 封面图片(可选) */
        // request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        // request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 存储区域(可选) */
        // request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        request.setProgressListener(new com.aliyun.vod.upload.impl.PutObjectProgressListener());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            String regionId = "cn-shanghai";

            // 上传凭证
            DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            GetPlayInfoRequest req = new GetPlayInfoRequest();
            req.setVideoId(response.getVideoId());

            GetPlayInfoResponse videoURL = client.getAcsResponse(req);
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            String playUrl = videoURL.getPlayInfoList().get(0).getPlayURL();
            String coverUrl = videoURL.getVideoBase().getCoverURL();
            System.out.println("音视频播放地址为:" + playUrl + "****************************************************");
            System.out.println("图片地址为:" + coverUrl + "****************************************************");

            System.out.print("RequestId=" + response.getRequestId() + "\n"); // 请求视频点播服务的请求ID
            return videoURL;
        } else {
            /*
             * 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，
             * 此时需要根据返回错误码分析具体错误原因
             */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return null;
    }

    /**
     * 图片上传接口，本地文件上传示例 参数参考文档 https://help.aliyun.com/document_detail/55619.html
     *
     * @param
     * @param
     */
    public static UploadImageResponse testUploadImageLocalFile(String fileName) {
        UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印） */
        // request.setImageType("cover");
        /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
        // request.setImageExt("png");
        /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
        String title = Utils.getGUID();
        request.setTitle(title);
        /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
        // request.setTags("标签1,标签2");
        /* 存储区域（可选） */
        // request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
        /* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选) */
        request.setFileName(fileName);
        /* 开启默认上传进度回调 */
        request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 ProgressListener) */
        request.setProgressListener(new PutObjectProgressListener());
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        System.out.print("ErrorCode=" + response.getCode() + "\n");
        System.out.print("ErrorMessage" + response.getMessage() + "\n");
        System.out.print("ImageId=" + response.getImageId() + "\n");
        System.out.print("ImageURL=" + response.getImageURL() + "\n");
        return response;
    }

    public static String saveFile(MultipartFile file) {
        String path = "D:\\testFile\\";
        String fileName = file.getOriginalFilename();
        // CommonsMultipartFile cFile = (CommonsMultipartFile) file;
        // DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path + fileName;
    }

    public static DeleteVideoResponse deleteVideo(Map<String, Object> param) throws Exception {
        String ids = param.get("mediaIds").toString();
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        DeleteVideoRequest request = new DeleteVideoRequest();
        // 多个用逗号分隔，最多支持20个
        request.setVideoIds(ids);
        return client.getAcsResponse(request);
    }

    public static DeleteImageResponse deleteImage(Map<String, Object> param) throws ClientException {
        String regionId = "cn-shanghai";
        String action = "DeleteImage";
        String deleteImageType = "ImageURL";
        String imageIds = param.get("imageId").toString();
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        DeleteImageRequest request = new DeleteImageRequest();
        request.setActionName(action);
        request.setDeleteImageType(deleteImageType);
        request.setImageURLs(imageIds);
        return client.getAcsResponse(request);
    }

    public static String Trailer(Map<String, Object> param) {
        String shortVideoId = "";
        String type = param.get("type").toString();
        String charge = param.get("charge").toString();
        // 如果是视频上传15秒试看视频
        if ("1".contains(type) && "1".equals(charge)) {
            String title = Utils.getGUID();
            File filePath = null;
            String shortFile = null;
            String playUrl = param.get("playUrl").toString();
           // shortFile = ConvertVideo.processFLV(playUrl);
            filePath = new File(shortFile);
            InputStream is = null;
            Long cateId = FIVETEENSEC;
            try {
                is = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GetPlayInfoResponse shortPlayInfo = MediaUtile.testUploadStream(accessKeyId, accessKeySecret, "15秒" + title,
                    shortFile, cateId, is);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            shortVideoId = shortPlayInfo.getVideoBase().getVideoId();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //ConvertVideo.delFile(shortFile);
        }

        return shortVideoId;
    }
}

