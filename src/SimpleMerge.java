// 이 연습의 의의
// 1) GIT 숙달
// 2) 파일을 불러오기 위한 자바에서 제공하는 JFileChooser의 올바른 사용법 습득.
// 3) 브런치!!
// 4) 브런치 & 머지 한 다음의 master로 푸쉬해보는 주석

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

class UIFrame implements ActionListener
{
    private JFrame frm = new JFrame();
    private JFileChooser fileChooser = new JFileChooser();
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JButton openBtn = new JButton();
    private JLabel fileLabel = new JLabel();
    private JTextField address = new JTextField();

    public UIFrame()
    {
        //기본 컴포넌트 설정
        fileLabel.setText("File URL");
        address.setColumns(25);
        openBtn.setText("OPEN");

        //액션 리스너 장착
        openBtn.addActionListener(this);

        //각 패널 설정 및 컴포넌트 장착
        centerPanel.add(fileLabel);
        centerPanel.add(address);
        northPanel.setLayout(new GridLayout(0,2));
        northPanel.add(openBtn);

        //프레임에 패널 장착
        frm.add(northPanel, "North");
        frm.add(centerPanel, "Center");

        //파일탐색기 포맷 설정
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text files", "txt", "xml");
        fileChooser.setFileFilter(filter);

        //기본 프래임 셋팅
        frm.setTitle("File Open Manager");
        frm.setLocation(300, 300);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.pack();
        frm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == openBtn)
        {
            int returnVal = fileChooser.showOpenDialog(frm);
            if( returnVal == JFileChooser.APPROVE_OPTION)
            {
                //OPEN 버튼 누를 시
                File file = fileChooser.getSelectedFile();
                address.setText(file.toString()+" has been loaded.");
            }
            else
            {
                //CANCEL 버튼 누를 시
                address.setText("Failed to load the file.");
            }
        }
    }
}

public class SimpleMerge
{
    public static void main(String[] args)
    {
        UIFrame my = new UIFrame();
    }
}