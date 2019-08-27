package com.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: TODO(������һ�仰��������������)
* @author neuedu_zhj
* @date 2019��8��22�� ����4:17:06
*
*/
public class GetImageUtil {
    public static Image getImg(String imgName) {
    	URL resource = GetImageUtil.class.getClassLoader().getResource(imgName);
    	BufferedImage bufferedImage = null;
    	try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return bufferedImage;
    }
}
