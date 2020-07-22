package com.example.shirodemo.utils;

import com.qcloud.image.ImageClient;
import com.qcloud.image.exception.AbstractImageException;
import com.qcloud.image.request.*;

import java.io.File;

/**
 * 腾讯云Ocr文字识别
 *
 * @author lixingwu
 */
public class OcrUtil {
    private static final String APP_ID = "1252304661";
    private static final String SECRET_ID = "**";
    private static final String SECRET_KEY = "**";
    private static final String BUCKET_NAME = "lg-28a1vt9i-1252304661";
    private static final ImageClient IMAGECLIENT = new ImageClient(APP_ID, SECRET_ID, SECRET_KEY, ImageClient.NEW_DOMAIN_recognition_image_myqcloud_com);

    /**
     * 方法描述：识别身份证URL方式.
     * 创建时间：2018-12-19 11:54:36
     *
     * @param url      图片地址
     * @param cardType 0正面，1反面
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrIdCardUrl(String url, Integer cardType) {
        String ret = null;
        String[] idcardUrlList = new String[]{url};
        IdcardDetectRequest idReq = new IdcardDetectRequest(BUCKET_NAME, idcardUrlList, cardType);
        try {
            ret = IMAGECLIENT.idcardDetect(idReq);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：识别身份证path方式.
     * 创建时间：2018-12-19 11:54:36
     *
     * @param path     图片路径
     * @param cardType 0正面，1反面
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrIdCardPath(String path, Integer cardType) {
        String ret = null;
        File[] idcardUrlList = new File[]{new File(path)};
        IdcardDetectRequest idReq = new IdcardDetectRequest(BUCKET_NAME, idcardUrlList, cardType);
        try {
            ret = IMAGECLIENT.idcardDetect(idReq);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：驾驶证识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrDrivingLicenceUrl(String url) {
        String ret = null;
        OcrDrivingLicenceRequest request = new OcrDrivingLicenceRequest(BUCKET_NAME, OcrDrivingLicenceRequest.TYPE_DRIVER_LICENSE, url);
        try {
            ret = IMAGECLIENT.ocrDrivingLicence(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：驾驶证识别path方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrDrivingLicencePath(String path) {
        String ret = null;
        File file = new File(path);
        OcrDrivingLicenceRequest request = new OcrDrivingLicenceRequest(BUCKET_NAME, OcrDrivingLicenceRequest.TYPE_DRIVER_LICENSE, file);
        try {
            ret = IMAGECLIENT.ocrDrivingLicence(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：行驶证识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrVehicleLicenceUrl(String url) {
        String ret = null;
        OcrDrivingLicenceRequest request = new OcrDrivingLicenceRequest(BUCKET_NAME, OcrDrivingLicenceRequest.TYPE_VEHICLE_LICENSE, url);
        try {
            ret = IMAGECLIENT.ocrDrivingLicence(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：行驶证识别path方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrVehicleLicencePath(String path) {
        String ret = null;
        File file = new File(path);
        OcrDrivingLicenceRequest request = new OcrDrivingLicenceRequest(BUCKET_NAME, OcrDrivingLicenceRequest.TYPE_VEHICLE_LICENSE, file);
        try {
            ret = IMAGECLIENT.ocrDrivingLicence(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：车牌识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrPlateUrl(String url) {
        String ret = null;
        OcrPlateRequest request = new OcrPlateRequest(BUCKET_NAME, url);
        try {
            ret = IMAGECLIENT.ocrPlate(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：车牌识别path方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrPlatePath(String path) {
        String ret = null;
        File file = new File(path);
        OcrPlateRequest request = new OcrPlateRequest(BUCKET_NAME, file);
        try {
            ret = IMAGECLIENT.ocrPlate(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：营业执照识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrBizLicenseUrl(String url) {
        String ret = null;
        OcrBizLicenseRequest request = new OcrBizLicenseRequest(BUCKET_NAME, url);
        try {
            ret = IMAGECLIENT.ocrBizLicense(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：营业执照识别path方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrBizLicensePath(String path) {
        String ret = null;
        File file = new File(path);
        OcrBizLicenseRequest request = new OcrBizLicenseRequest(BUCKET_NAME, file);
        try {
            ret = IMAGECLIENT.ocrBizLicense(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：银行卡识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrBankCardUrl(String url) {
        String ret = null;
        OcrBankCardRequest request = new OcrBankCardRequest(BUCKET_NAME, url);
        try {
            ret = IMAGECLIENT.ocrBankCard(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：银行卡识别path方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrBankCardPath(String path) {
        String ret = null;
        File file = new File(path);
        OcrBankCardRequest request = new OcrBankCardRequest(BUCKET_NAME, file);
        try {
            ret = IMAGECLIENT.ocrBankCard(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：通用印刷体识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param url 图片地址
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrGeneralUrl(String url) {
        String ret = null;
        GeneralOcrRequest request = new GeneralOcrRequest(BUCKET_NAME, url);
        try {
            ret = IMAGECLIENT.generalOcr(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 方法描述：通用印刷体识别URL方式.
     * 创建时间：2018-12-19 12:52:27
     *
     * @param path 图片路径
     * @return the string
     * @author "lixingwu"
     */
    public static String ocrGeneralPath(String path) {
        String ret = null;
        File file = new File(path);
        GeneralOcrRequest request = new GeneralOcrRequest(BUCKET_NAME, file);
        try {
            ret = IMAGECLIENT.generalOcr(request);
        } catch (AbstractImageException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
