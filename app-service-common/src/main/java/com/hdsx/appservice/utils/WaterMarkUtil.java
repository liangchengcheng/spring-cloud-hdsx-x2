package com.hdsx.appservice.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class WaterMarkUtil {

    /**
     * @param srcImgPath       源图片路径
     * @param tarImgPath       保存的图片路径
     * @param markContentColor 水印颜色
     */
    public static void addWaterMark(String srcImgPath, String tarImgPath, Color markContentColor) {
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);
            // 读取GPS信息
            Metadata metadata;
            metadata = ImageMetadataReader.readMetadata(srcImgFile);

            String lat = null, lon = null, timestamp = null, date = null;
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    if (directory.getName().equals("GPS")) {
                        switch (tag.getTagType()) {
                            case 2://GPS Latitude
                                lat = tag.getDescription();
                                break;
                            case 4:
                                lon = tag.getDescription();
                                break;
                            case 7:
                                timestamp = tag.getDescription();
                                break;
                            case 29:
                                date = tag.getDescription();
                                break;
                        }
                    }
                }
            }

            // String waterMarkContent = new String("纬度：" + lat + "\n经度:" + lon + "\n时间:" + timestamp + "\n日期:" + date);
            // 文件转化为图片
            BufferedImage srcImg = ImageIO.read(srcImgFile);
            // 获取图片的宽
            int srcImgWidth = srcImg.getWidth(null);
            // 获取图片的高
            int srcImgHeight = srcImg.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            // 根据图片的背景设置水印颜色
            g.setColor(markContentColor);
            Font font = new Font("宋体", Font.PLAIN, getFontSize(srcImgWidth, srcImgHeight));
            // 设置字体
            g.setFont(font);
            // 设置水印的坐标
            // int x = srcImgWidth - 2*getWatermarkLength(waterMarkContent, g);
            // int y = srcImgHeight - 2*getWatermarkLength(waterMarkContent, g);
            int x = srcImgWidth / 20;
            int y = srcImgHeight / 20;
            // g.drawString(waterMarkContent, x, y);

            // 画出水印
            int step = font.getSize();
            g.drawString("日期:" + checkStr(date), x, y);
            y += step;
            g.drawString("时间:" + checkStr(timestamp), x, y);

            y += step;
            g.drawString("经度:" + checkStr(lon), x, y);

            y += step;
            g.drawString("纬度：" + checkStr(lat), x, y);

            y += step;
            g.drawString(getAddress(lat, lon), x, y);

            y += step;
            g.drawString("环渤海执法APP", x, y);

            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            System.err.format("ERROR: %s", e.getMessage());
        }
    }


    private static String checkStr(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    /***
     * 根据图片尺寸计算水印字体大小
     */
    private static int getFontSize(int imgWidth, int imgHeight) {
        int v = imgWidth > imgHeight ? imgHeight : imgWidth;
        return v / 50;
    }

    /***
     * 根据经纬度计算位置
     */
    private static String getAddress(String lat, String lon) {
        return "   ";
    }

}
