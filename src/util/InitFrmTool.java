package util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 给窗体设置背景图片类
 * @author HP
 *
 */
public class InitFrmTool {
	private String imgPath;
	public InitFrmTool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InitFrmTool(String imgPath) {
		super();
		this.imgPath = imgPath;
	}
	
	
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void InitFrm(String path,JFrame jf) {
		 	jf.setLocationRelativeTo(null);//窗口居中
	      	jf.getContentPane().setLayout(null);//无布局，记得下面添加控件时要设置它们位置和大小
	        //设置背景图片
	        JPanel imPanel=(JPanel) jf.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
	        imPanel.setOpaque(false);//将内容面板设为透明
	        ImageIcon icon=new ImageIcon(path);//背景图
	        JLabel label = new JLabel(icon);//往一个标签中加入图片
	        label.setBounds(0, 0,jf.getWidth(),jf.getHeight());//设置标签位置大小，记得大小要和窗口一样大
	        icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
	        jf.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
	        jf.setVisible(true);
	}
}
