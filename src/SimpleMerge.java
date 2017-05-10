// 이 연습의 의의
// 1) GIT 숙달
// 2) 파일을 불러오기 위한 자바에서 제공하는 JFileChooser의 올바른 사용법 습득.
// 3) 브런치!!
// 4) 브런치 & 머지 한 다음의 master로 푸쉬해보는 주석
// 5) LCS 알고리즘 실습

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private JPanel rightFilePanel = new JPanel();
    private JPanel leftFilePanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JButton openFirstBtn = new JButton();
    private JButton openSecondBtn = new JButton();
    private JButton compareBtn = new JButton();
    private JLabel fileFirstLabel = new JLabel();
    private JLabel fileSecondLabel = new JLabel();
    private JTextField firstAddress = new JTextField();
    private JTextField secondAddress = new JTextField();

    public UIFrame()
    {
        //기본 컴포넌트 설정
        fileFirstLabel.setText("Left URL");
        fileSecondLabel.setText("Right URL");
        firstAddress.setColumns(25);
        secondAddress.setColumns(25);
        openFirstBtn.setText("OPEN");
        openSecondBtn.setText("OPEN");
        compareBtn.setText("COMPARE");

        //액션 리스너 장착
        openFirstBtn.addActionListener(this);
        openSecondBtn.addActionListener(this);
        compareBtn.addActionListener(this);

        //각 패널 설정 및 컴포넌트 장착
        //leftFilePanel = 왼쪽 파일에 대한 정보들을 위한 패널
        //rightFilePanel = 오른쪽 파일에 대한 정보들을 위한 패널
        leftFilePanel.add(fileFirstLabel);
        leftFilePanel.add(firstAddress);
        leftFilePanel.add(openFirstBtn);

        rightFilePanel.add(fileSecondLabel);
        rightFilePanel.add(secondAddress);
        rightFilePanel.add(openSecondBtn);

        //southPanel = 비교 버튼
        southPanel.add(compareBtn);

        //프레임에 패널 장착
        frm.add(leftFilePanel, "North");
        frm.add(rightFilePanel, "Center");
        frm.add(southPanel, "South");

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

    // LCS 알고리즘
    // 갓-위키피디아의 알고리즘과 완죤 똑같음
    public String lcs(String l, String r) {
        int m = l.length();
        int n = r.length();
        int[][] L = new int[m+1][n+1];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if ( l.charAt(i) == r.charAt(j) ) { // 글자가 같으면 1점 더함
                    L[i+1][j+1] = L[i][j] + 1;
                } else { // 같지 않으면 1을 더하지 않음. Max값 이용하여 이전 값으로 할당.
                    L[i+1][j+1] = Math.max(L[i+1][j], L[i][j+1]);
                }
            }
        }

        System.out.println("LCS 값 : " + L[m][n]);

        // 배열을 이용하여 일치하는 값들을 스트링으로 뽑아냄
        // x = 0 y = 0으로 초기화 하고 while 조건을 x < m && y < n 으로 해두고
        // x-- 대신 x++, y-- 대신 y++으로 하면 return 할때 sb.reverse ~~ 대신 sb.toString로 해도 코드는 동일하게 작동됨.
        StringBuffer sb = new StringBuffer();
        int x = m, y = n;
        while ( x != 0 && y != 0 ) {
            if ( L[x][y] == L[x-1][y] ) { // Max 당시 기준 x-1, y 값이었을 경우 x 1 감소
                x--;
            }
            else if ( L[x][y] == L[x][y-1] )  { // Max 당시 기준 x, y-1 값이었을 경우 y 1 감소
                y--;
            }
            else {
                if (l.charAt(x-1) == r.charAt(y-1)) sb.append(l.charAt(x-1)); // 같으면 스트링버퍼에 푸쉬
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == openFirstBtn) // 왼쪽 파일 불러오는 이벤트
        {
            int returnVal = fileChooser.showOpenDialog(frm);
            if( returnVal == JFileChooser.APPROVE_OPTION)
            {
                //OPEN 버튼 누를 시
                File file = fileChooser.getSelectedFile();
                firstAddress.setText(file.toString());
            }
        } else if ( e.getSource() == openSecondBtn) { // 오른쪽 파일 불러오는 이벤트
            int returnVal = fileChooser.showOpenDialog(frm);
            if( returnVal == JFileChooser.APPROVE_OPTION)
            {
                //OPEN 버튼 누를 시
                File file = fileChooser.getSelectedFile();
                secondAddress.setText(file.toString());
            }
        } else if ( e.getSource() == compareBtn) {
            if ( firstAddress.getText().compareTo("") != 0 && secondAddress.getText().compareTo("") != 0 ) { // 한번이라도 둘 다 불러왔는가?
                // 현재 제약조건
                // !!!!!파일의 첫 문장만 읽어오기!!!!!
                try {
                    String l,r;
                    BufferedReader in = new BufferedReader(new FileReader(firstAddress.getText()));
                    l = in.readLine();
                    in.close();

                    in = new BufferedReader(new FileReader(secondAddress.getText()));
                    r = in.readLine();
                    in.close();

                    //파일의 첫문장들을 출력
                    System.out.println("The first line of the left file : " + l);
                    System.out.println("The first line of the right file : " + r);

                    //알고리즘 수행
                    String result = lcs(l, r);
                    //결과 출력
                    System.out.println("결과 : " + result);

                } catch ( IOException iox ) {
                    // 파일이 없거나 누락될 경우
                    iox.fillInStackTrace();
                }
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