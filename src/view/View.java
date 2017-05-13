/**
 *
 * Created by iskyc on 2017-05-13.
 */

import javax.swing.*;
import java.awt.*;

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

    View() {
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

        JButton btnCompare = new JButton("Compare");
        JButton btnMergeLeftToRightAll = new JButton(">>"); 
        JButton btnMergeLeftToRight = new JButton(">"); 
        JButton btnMergeRightToLeft = new JButton("<"); 
        JButton btnMergeRightToLeftAll = new JButton("<<");

        pnlControls.setLayout(new GridLayout(5, 1));

        pnlControls.add(btnCompare, 0);
        pnlControls.add(btnMergeLeftToRightAll, 1);
        pnlControls.add(btnMergeLeftToRight, 2);
        pnlControls.add(btnMergeRightToLeft, 3);
        pnlControls.add(btnMergeRightToLeftAll, 4);
    }
}
