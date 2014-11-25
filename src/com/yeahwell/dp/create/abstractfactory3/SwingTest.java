package com.yeahwell.dp.create.abstractfactory3;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Swing图形化演示
 * @author yeahwell
 *
 */
public class SwingTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		/**
		 * 设置视感，默认提供以下几个
		 * javax.swing.plaf.metal.MetalLookAndFeel
		 * com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel   最漂亮
		 * com.sun.java.swing.plaf.motif.MotifLookAndFeel
		 * com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		 */
		String lf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		
		/**
		 * 设置视感
		 */
		UIManager.setLookAndFeel(lf);
		
		/**
		 * 创建窗口
		 */
		JFrame window = new JFrame("swing测试");
		
		/**
		 * 设置面板布局为流式布局
		 */
		window.getContentPane().setLayout(new FlowLayout());
		
		/**
		 * 创建控件
		 */
		JButton button = new JButton("按钮");
		JTextField textFiled = new JTextField("测试文本");
		
		/**
		 * 设置窗口关闭方法
		 */
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * 组装控件
		 */
		window.getContentPane().add(button);
		window.getContentPane().add(textFiled);
		
		/**
		 * 窗口定位及显示
		 */
		window.setLocationRelativeTo(null);
		window.pack();
		window.setVisible(true);
		
		System.out.println("视感类： " + UIManager.getLookAndFeel().getClass());
		System.out.println("按钮： " + button.getUI().getClass());
		System.out.println("文本" + textFiled.getUI().getClass());
	}
}
