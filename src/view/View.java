/**
 *
 * Created by iskyc on 2017-05-13.
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frmMain;
    private JPanel pnlFrmBody;
    private JPanel pnlWestBody, pnlLeftFileBody, pnlLeftFileNorth, pnlLeftFileMenu;
    private JPanel pnlEastBody, pnlRightFileBody, pnlRightFileNorth, pnlRightFileMenu;
    private JPanel pnlControls;

    //Modification Required
    //QuickView
    //LeftFile TextArea
    //RightFile TextArea;

    private JButton btnLeftLoad, btnLeftSave, btnLeftSaveAs, btnLeftEdit;
    private JButton btnRightLoad, btnRightSave, btnRightSaveAs, btnRightEdit;
    private JButton btnCompare, btnMergeLeftToRightAll, btnMergeLeftToRight, btnMergeRightToLeft, btnMergeRightToLeftAll;
    
    private JTextField txtLeftPath, txtLeftStatus;
    private JTextField txtRightPath, txtRightStatus;

    public View() {
        initFrmMain();
        initPnlWest();
        initPnlEast();
    }

    private void initFrmMain() {
        frmMain = new JFrame();

        pnlFrmBody = new JPanel();

        frmMain.setTitle("SimpleMerge - Team 1"); //set the title of JFrame
        frmMain.setSize(600, 400); //set the size of JFrame
        frmMain.setLocationRelativeTo(null); //set the location of JFrame
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the close option of JFrame
        frmMain.setVisible(true); //set visible for this window
        
        pnlFrmBody.setLayout(new GridLayout(1, 2));

        frmMain.add(pnlFrmBody);
        
    }

    private void initPnlWest() {
        pnlWestBody = new JPanel();
        pnlLeftFileBody = new JPanel();
        pnlLeftFileNorth = new JPanel();
        pnlLeftFileMenu = new JPanel();

        btnLeftLoad = new JButton("L");
        btnLeftSave = new JButton("S");
        btnLeftSaveAs = new JButton("SA");
        btnLeftEdit = new JButton("E");

        txtLeftPath = new JTextField();
        txtLeftStatus = new JTextField();

        pnlWestBody.setLayout(new BorderLayout());
        pnlLeftFileBody.setLayout(new BorderLayout());
        pnlLeftFileNorth.setLayout(new BorderLayout());
        pnlLeftFileMenu.setLayout(new GridLayout(1,4));

        pnlFrmBody.add(pnlWestBody, 0);
        /* Modification Required : QuickView */
        JPanel pnlQuickView = new JPanel();
        pnlWestBody.add(pnlQuickView, BorderLayout.WEST);
        pnlWestBody.add(pnlLeftFileBody, BorderLayout.CENTER);
        pnlLeftFileBody.add(pnlLeftFileNorth, BorderLayout.NORTH);
        pnlLeftFileBody.add(txtLeftStatus, BorderLayout.SOUTH);
        pnlLeftFileNorth.add(pnlLeftFileMenu, BorderLayout.NORTH);
        pnlLeftFileNorth.add(txtLeftPath, BorderLayout.SOUTH);

        pnlLeftFileMenu.add(btnLeftLoad, 0);
        pnlLeftFileMenu.add(btnLeftSave, 1);
        pnlLeftFileMenu.add(btnLeftSaveAs, 2);
        pnlLeftFileMenu.add(btnLeftEdit, 3);
    }

    private void initPnlEast() {
        pnlEastBody = new JPanel();
        pnlRightFileBody = new JPanel();
        pnlRightFileNorth = new JPanel();
        pnlRightFileMenu = new JPanel();

        btnRightLoad = new JButton("L");
        btnRightSave = new JButton("S");
        btnRightSaveAs = new JButton("SA");
        btnRightEdit = new JButton("E");

        txtRightPath = new JTextField();
        txtRightStatus = new JTextField();

        pnlEastBody.setLayout(new BorderLayout());
        pnlRightFileBody.setLayout(new BorderLayout());
        pnlRightFileNorth.setLayout(new BorderLayout());
        pnlRightFileMenu.setLayout(new GridLayout(1,4));

        initPnlControls();

        pnlFrmBody.add(pnlEastBody, 1);
        pnlEastBody.add(pnlControls, BorderLayout.WEST);
        pnlEastBody.add(pnlRightFileBody, BorderLayout.CENTER);
        pnlRightFileBody.add(pnlRightFileNorth, BorderLayout.NORTH);
        pnlRightFileBody.add(txtRightStatus, BorderLayout.SOUTH);
        pnlRightFileNorth.add(pnlRightFileMenu, BorderLayout.NORTH);
        pnlRightFileNorth.add(txtRightPath, BorderLayout.SOUTH);

        pnlRightFileMenu.add(btnRightLoad, 0);
        pnlRightFileMenu.add(btnRightSave, 1);
        pnlRightFileMenu.add(btnRightSaveAs, 2);
        pnlRightFileMenu.add(btnRightEdit, 3);
    }

    private void initPnlControls() {
        pnlControls = new JPanel();

        btnCompare = new JButton("Compare");
        btnMergeLeftToRightAll = new JButton(">>");
        btnMergeLeftToRight = new JButton(">");
        btnMergeRightToLeft = new JButton("<");
        btnMergeRightToLeftAll = new JButton("<<");

        pnlControls.setLayout(new GridLayout(5, 1));

        pnlControls.add(btnCompare, 0);
        pnlControls.add(btnMergeLeftToRightAll, 1);
        pnlControls.add(btnMergeLeftToRight, 2);
        pnlControls.add(btnMergeRightToLeft, 3);
        pnlControls.add(btnMergeRightToLeftAll, 4);
    }

    // 현재 J프레임을 리턴해주는 메소드.
    // 주로 컨트롤러에서 창을 띄울 때 사용함.
    public JFrame getFrame() {
        return this.frmMain;
    }

    // 원하는 텍스트필드를 리턴해주는 메소드.
    // 주로 컨트롤러에서 이 것을 사용할 것이다.
    public JTextField getJTextField(String name) {
        switch ( name ) {
            case "txtLeftPath":
                return this.txtLeftPath;
            case "txtRightPath":
                return this.txtRightPath;
        }

        return null;
    }

    // 버튼들에 대한 액션리스너를 컨트롤러에 추가시킨다.
    // 그리고, 그 버튼들에 대해 이름을 설정해준다.
    // 나중에 발생하는 이벤트들을 구별하기 위함임.
    public void setActionListener(ActionListener al) {
        btnLeftLoad.addActionListener(al);
        btnLeftLoad.setName("btnLeftLoad");
        btnLeftSave.addActionListener(al);
        btnLeftSave.setName("btnLeftSave");
        btnLeftSaveAs.addActionListener(al);
        btnLeftSaveAs.setName("btnLeftSaveAs");
        btnLeftEdit.addActionListener(al);
        btnLeftEdit.setName("btnLeftEdit");

        btnRightLoad.addActionListener(al);
        btnRightLoad.setName("btnRightLoad");
        btnRightSave.addActionListener(al);
        btnRightSave.setName("btnRightSave");
        btnRightSaveAs.addActionListener(al);
        btnRightSaveAs.setName("btnRightSaveAs");
        btnRightEdit.addActionListener(al);
        btnRightEdit.setName("btnRightEdit");

        btnCompare.addActionListener(al);
        btnCompare.setName("btnCompare");
        btnMergeLeftToRight.addActionListener(al);
        btnMergeLeftToRight.setName("btnMergeLeftToRight");
        btnMergeLeftToRightAll.addActionListener(al);
        btnMergeLeftToRightAll.setName("btnMergeLeftToRightAll");
        btnMergeRightToLeft.addActionListener(al);
        btnMergeRightToLeft.setName("btnMergeRightToLeft");
        btnMergeRightToLeftAll.addActionListener(al);
        btnMergeRightToLeftAll.setName("btnMergeRightToLeftAll");
    }
}
