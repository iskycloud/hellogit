package Controller;

import view.View;
import Model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller implements ActionListener
{
    private JFileChooser fileChooser = new JFileChooser();
    private View view;
    private Model model;

    // 디폴트 컨스트럭터
    public Controller() {}

    // 컨스트럭터
    // 모델과 뷰를 설정한다.
    // 모델 설정 이유 = 모델을 조작
    // 뷰 설정 이유 = 버튼 이벤트 설정을 이 쪽으로 가져오기 위함
    public Controller(Model m, View v) {
        this.model = m;
        this.view = v;
        this.view.setActionListener(this);
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
    public void actionPerformed(ActionEvent e) {
        String actionName = ((JButton)e.getSource()).getName();

        int returnVal;
        switch ( actionName ) { // 버튼을 눌렀다!
            case "btnLeftLoad": // 왼쪽 패널 로드 버튼일 시
                returnVal = fileChooser.showOpenDialog(view.getFrame());
                if( returnVal == JFileChooser.APPROVE_OPTION)
                {
                    //OPEN 버튼 누를 시
                    File file = fileChooser.getSelectedFile();
                    view.getJTextField("txtLeftPath").setText(file.getPath());
                }
                break;
            case "btnRightLoad": // 오른쪽 패널 로드 버튼일 시
                returnVal = fileChooser.showOpenDialog(view.getFrame());
                if( returnVal == JFileChooser.APPROVE_OPTION)
                {
                    //OPEN 버튼 누를 시
                    File file = fileChooser.getSelectedFile();
                    view.getJTextField("txtRightPath").setText(file.getPath());
                }
                break;
        }
    }
        /* 아직 안넣은 코드
        else if ( e.getSource() == compareBtn) {
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
        */
}