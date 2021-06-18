package util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ���������ñ���ͼƬ��
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
		 	jf.setLocationRelativeTo(null);//���ھ���
	      	jf.getContentPane().setLayout(null);//�޲��֣��ǵ�������ӿؼ�ʱҪ��������λ�úʹ�С
	        //���ñ���ͼƬ
	        JPanel imPanel=(JPanel) jf.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
	        imPanel.setOpaque(false);//�����������Ϊ͸��
	        ImageIcon icon=new ImageIcon(path);//����ͼ
	        JLabel label = new JLabel(icon);//��һ����ǩ�м���ͼƬ
	        label.setBounds(0, 0,jf.getWidth(),jf.getHeight());//���ñ�ǩλ�ô�С���ǵô�СҪ�ʹ���һ����
	        icon.setImage(icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
	        jf.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
	        jf.setVisible(true);
	}
}
