package Model;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import view.View;

/**
 * Created by xstel on 2017-05-14.
 */
public class Model {

    private View view;

    public Model() {

    }

    public Model(View view) {
        this.view = view;
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


    public void setLeftFileContent() {
        JFileChooser fileChooser = new JFileChooser();
        String path = "";
        int returnVal = fileChooser.showOpenDialog(view.getFrame());
        if( returnVal == JFileChooser.APPROVE_OPTION)
        {
            //OPEN 버튼 누를 시
            File file = fileChooser.getSelectedFile();
            path = file.getPath();
            view.getJTextField("txtLeftPath").setText(path);
        }

        try {
            String l;
            BufferedReader in = new BufferedReader(new FileReader(path));
            l = in.readLine();

            while ( l != null ) {
                view.getJTextArea("txtLeftArea").append(l + '\n');
                l = in.readLine();
            }
            in.close();
        } catch ( IOException iox ) {
            iox.printStackTrace();
        }
    }


    public void setRightFileContent() {
        JFileChooser fileChooser = new JFileChooser();
        String path = "";
        int returnVal = fileChooser.showOpenDialog(view.getFrame());
        if( returnVal == JFileChooser.APPROVE_OPTION)
        {
            //OPEN 버튼 누를 시
            File file = fileChooser.getSelectedFile();
            path = file.getPath();
            view.getJTextField("txtRightPath").setText(path);
        }

        try {
            String l;
            BufferedReader in = new BufferedReader(new FileReader(path));
            l = in.readLine();

            while ( l != null ) {
                view.getJTextArea("txtRightArea").append(l + '\n');
                l = in.readLine();
            }
            in.close();
        } catch ( IOException iox ) {
            iox.printStackTrace();
        }
    }

}
