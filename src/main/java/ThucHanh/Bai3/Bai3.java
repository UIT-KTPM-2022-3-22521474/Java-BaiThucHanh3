package ThucHanh.Bai3;

import ThucHanh.SQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Bai3 extends JFrame implements SQLConnection{
    private Connection connection;
    private JTable table;
    private DefaultTableModel model;

    ArrayList<String> columnNameList;

    public Bai3() throws SQLException {
        this.setTitle("BT3->8 - To Vinh Tien - 22521474");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(1000, 600);

        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sinh Viên", new SinhVienTab());
        tabbedPane.addTab("Lớp", new LopTab());

        this.add(tabbedPane, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
