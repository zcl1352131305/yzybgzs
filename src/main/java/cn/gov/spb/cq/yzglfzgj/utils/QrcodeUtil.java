package cn.gov.spb.cq.yzglfzgj.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class QrcodeUtil {
	//二维码图片格式
	private static final String fileExt="png";
	//二维码存放根目录
	private static final String fileRoot="grcode";

	public static String rootPath;

	@Value("${audit.upload.root-path}")
	public void setStaticRootPath(String rootPath){
		this.rootPath = rootPath;
	}
	
	/**
	 * 
	 * @param text  需要写入二维码里面的内容
	 * @throws Exception
	 */
	public static  String enCodeQr(String text) throws Exception{
		String imagePath=null;
		if(!StringUtils.isEmpty(text)){
			text=new String(text.getBytes("UTF-8"), "ISO-8859-1");
			int width = 200;
			int height = 200;
			String format = "png";
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
			 // 创建新的文件名
			String newFileName = WebUtils.getTime("yyyyMMddHHmmss")
					+ WebUtils.getRandomString(5) + "." + fileExt;
			//文件的相对路径
			String path= fileRoot+"/"+WebUtils.getTime("yyyyMMdd")+"/"+newFileName;
			//文件保存路径
			String savePath =rootPath+"/" +path;
			
            File outputFile = new File(savePath);
            if (!outputFile.exists()) {
            	outputFile.mkdirs();
			}

			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			
			imagePath= "/file/"+path;
		}
		return imagePath; 
		
	}
	

	/**
	 * 
	 * @param filePath  二维码的路径
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static  String deCodeQr(String filePath) throws Exception{
		Result result = null;
		try {
			
            MultiFormatReader formatReader = new MultiFormatReader();
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);;
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer  binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			@SuppressWarnings("rawtypes")
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			result = formatReader.decode(binaryBitmap,hints);
			
			// log.info("result = "+ result.toString());
			// log.info("resultFormat = "+ result.getBarcodeFormat());
			// log.info("resultText = "+ result.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return result.getText();
	}

}
