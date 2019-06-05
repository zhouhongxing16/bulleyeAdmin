package com.chris.bulleyeadmin.common.utils;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 验证码（系统登陆用）工具类
 *
 *
 */
public class ValidateCodeUtils {
	// 验证码图片的宽度。
	private static int width = 60;

	// 验证码图片的高度。
	private static int height = 20;

	// 验证码字符个数
	private static int codeCount = 4;

	/*
	 * char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
	 * 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	 * 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
	 * 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	 */
	private static char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	/**
	 * 获取系统验证码图片
	 * @return
	 */
	public static BufferedImage getValidateCodeImage(HttpSession session) {
		int x = width / (codeCount + 1);
		int fontHeight = height; // 字体高度
		int codeY = height;

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("黑体", Font.BOLD, fontHeight - 5);
		// 设置字体。
		g.setFont(font);
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(10)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			g.setColor(getRandColor(20, 130));
			// 用随机产生的颜色将验证码绘制到图像中。
			g.drawString(strRand, (i + 1) * x - 7, codeY - 5);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}

		// 将验证码写入Session
		session.setAttribute("VALIDATE_CODE", randomCode.toString());

		return buffImg;
	}


	/**
	 * 获取验证码字符串
	 * @return
	 */
	public static String  getRandomValidateCode(HttpSession session) {
		// 创建一个随机数生成器类
		Random random = new Random();
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(10)]);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		System.out.println(randomCode.toString());
		// 将验证码写入Session
		session.setAttribute("VALIDATE_CODE", randomCode.toString());
		return  randomCode.toString();
	}

	/**
	 * 获取验证码字符串（用于验证用户验证码输入）
	 * @return
	 */
	public static String getValidateCode(HttpSession session) {
		return (String)session.getAttribute("VALIDATE_CODE");
	}

	/**
	 * 产生随机颜色
	 *
	 * @param num1
	 * @param num2
	 * @return Color
	 */
	private static Color getRandColor(int num1, int num2) {
		Random random = new Random();
		if (num1 > 255)
			num1 = 255;
		if (num2 > 255)
			num2 = 255;
		int r = num1 + random.nextInt(num2 - num1);
		int g = num1 + random.nextInt(num2 - num1);
		int b = num1 + random.nextInt(num2 - num1);
		return new Color(r, g, b);
	}
}
