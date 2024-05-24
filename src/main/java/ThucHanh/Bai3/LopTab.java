package ThucHanh.Bai3;

import ThucHanh.SQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class LopTab extends JPanel implements SQLConnection {
    private final Statement statement;
    private Connection connection;
    private ArrayList<Object> columnNameList;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    public LopTab() {
        super();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.1;
        gbc.weightx = 1;

        JPanel Header = new JPanel();
        Header.setLayout(new GridBagLayout());
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbcHeader.fill = GridBagConstraints.BOTH;
        gbcHeader.anchor = GridBagConstraints.WEST;
        JLabel lblHeader = new JLabel("Tùy chỉnh danh sách lớp");
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        Header.add(lblHeader, gbcHeader);

        this.add(Header, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.9;
        gbc.weightx = 1;

        JPanel Content = new JPanel();
        Content.setLayout(new GridBagLayout());
        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 0;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.weighty = 0.2;
        gbcContent.weightx = 1;

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        btnAdd = new JButton("Thêm thông tin lớp");
        btnAdd.setBackground(Color.GREEN);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(4,2));
                JLabel lblLop = new JLabel("Mã lớp");
                panelAdd.add(lblLop);
                JTextField txtMalop = new JTextField();
                panelAdd.add(txtMalop);
                JLabel lblTenLop = new JLabel("Tên lớp");
                panelAdd.add(lblTenLop);
                JTextField txtTenLop = new JTextField();
                panelAdd.add(txtTenLop);
                JLabel lblCVHT = new JLabel("Cố vấn học tập");
                panelAdd.add(lblCVHT);
                JTextField txtCVHT = new JTextField();
                panelAdd.add(txtCVHT);
                JButton btnOK = new JButton("OK");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.GREEN);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            connection = SQLConnection.getConnection(url, username, password);
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("INSERT INTO Lop VALUES ('" + txtMalop.getText() + "', '" + txtTenLop.getText() + "', '" + txtCVHT.getText() + "')");
                            reloadModel();
                            ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                JButton btnCancel = new JButton("Cancel");
                panelAdd.add(btnCancel);
                btnCancel.setBackground(Color.RED);
                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                    }
                });

                JOptionPane.showOptionDialog(null, panelAdd, "Thêm lớp", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        });
        btnEdit = new JButton("Sửa thông tin lớp");
        btnEdit.setBackground(Color.YELLOW);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(4,2));
                JLabel lblLop = new JLabel("Mã lớp");
                panelAdd.add(lblLop);
                JTextField txtMalop = new JTextField();
                panelAdd.add(txtMalop);
                JLabel lblTenLop = new JLabel("Tên lớp");
                panelAdd.add(lblTenLop);
                JTextField txtTenLop = new JTextField();
                panelAdd.add(txtTenLop);
                JLabel lblCVHT = new JLabel("Cố vấn học tập");
                panelAdd.add(lblCVHT);
                JTextField txtCVHT = new JTextField();
                panelAdd.add(txtCVHT);
                JButton btnOK = new JButton("UPDATE");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.GREEN);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            connection = SQLConnection.getConnection(url, username, password);
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("UPDATE Lop SET TenLop = '" + txtTenLop.getText() + "', CVHT = '" + txtCVHT.getText() + "' WHERE MaLop = '" + txtMalop.getText() + "'");
                            reloadModel();
                            ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                JButton btnCancel = new JButton("Cancel");
                panelAdd.add(btnCancel);
                btnCancel.setBackground(Color.RED);
                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                    }
                });

                JOptionPane.showOptionDialog(null, panelAdd, "Sửa lớp", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

            }
        });
        btnDelete = new JButton("Xóa lớp");
        btnDelete.setBackground(Color.RED);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(2,2));
                JLabel lblMaLop = new JLabel("Mã lớp");
                panelAdd.add(lblMaLop);
                JTextField txtMaLop = new JTextField();
                panelAdd.add(txtMaLop);
                JButton btnOK = new JButton("DELETE");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.RED);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            connection = SQLConnection.getConnection(url, username, password);
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("DELETE FROM Lop WHERE MaLop = '" + txtMaLop.getText() + "'");
                            reloadModel();
                            ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                JButton btnCancel = new JButton("Cancel");
                panelAdd.add(btnCancel);
                btnCancel.setBackground(Color.YELLOW);
                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
                    }
                });

                JOptionPane.showOptionDialog(null, panelAdd, "Xóa lớp", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        });
        panelButton.add(btnAdd);
        panelButton.add(btnEdit);
        panelButton.add(btnDelete);

        Content.add(panelButton, gbcContent);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setOpaque(false);
        table.setShowVerticalLines(false);
        JScrollPane scrollPane = new JScrollPane(table);

        connection = null;
        columnNameList = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection(url, username, password);
            System.out.println("Connected to SQL Server successfully.");

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Lop");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                model.addColumn(columnName);
            }

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        gbcContent= new GridBagConstraints();
        gbcContent.gridx = 0;
        gbcContent.gridy = 1;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.weighty = 0.8;
        gbcContent.weightx = 1;
        Content.add(scrollPane, gbcContent);


        this.add(Content, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new LopTab());
        frame.setVisible(true);
    }
    public void reloadModel() throws SQLException {
        model.setRowCount(0);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Lop");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            model.addRow(row);
        }
    }
}
