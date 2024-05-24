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
import java.util.Stack;

public class SinhVienTab extends JPanel implements SQLConnection{
    private Statement statement;
    private Connection connection;
    private ArrayList<Object> columnNameList;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;



    public SinhVienTab() throws SQLException {
        super();

        connection = SQLConnection.getConnection(url, username, password);

        this.setLayout(new GridBagLayout());

        loadConstraint();

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
        JLabel lblHeader = new JLabel("Tùy chỉnh danh sách sinh viên");
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
        btnAdd = new JButton("Thêm thông tin sinh viên");
        btnAdd.setBackground(Color.GREEN);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(5,2));
                JLabel lblMaSV = new JLabel("Mã sinh viên");
                panelAdd.add(lblMaSV);
                JTextField txtMaSV = new JTextField();
                panelAdd.add(txtMaSV);
                JLabel lblTenSV = new JLabel("Họ tên sinh viên");
                panelAdd.add(lblTenSV);
                JTextField txtTenSV = new JTextField();
                panelAdd.add(txtTenSV);
                JLabel lblLop = new JLabel("Lớp");
                panelAdd.add(lblLop);
                JTextField txtLop = new JTextField();
                panelAdd.add(txtLop);
                JLabel lblDiem = new JLabel("Điểm TB");
                panelAdd.add(lblDiem);
                JTextField txtDiem = new JTextField();
                panelAdd.add(txtDiem);
                JButton btnOK = new JButton("OK");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.GREEN);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("INSERT INTO SinhVien VALUES ('" + txtMaSV.getText() + "', '" + txtTenSV.getText() + "', '" + txtLop.getText() + "', " + txtDiem.getText() + ")");
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

                JOptionPane.showOptionDialog(null, panelAdd, "Thêm thông tin sinh viên", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        });
        btnEdit = new JButton("Sửa thông tin sinh viên");
        btnEdit.setBackground(Color.YELLOW);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(5,2));
                JLabel lblMaSV = new JLabel("Mã sinh viên");
                panelAdd.add(lblMaSV);
                JTextField txtMaSV = new JTextField();
                panelAdd.add(txtMaSV);
                JLabel lblTenSV = new JLabel("Họ tên sinh viên");
                panelAdd.add(lblTenSV);
                JTextField txtTenSV = new JTextField();
                panelAdd.add(txtTenSV);
                JLabel lblLop = new JLabel("Lớp");
                panelAdd.add(lblLop);
                JTextField txtLop = new JTextField();
                panelAdd.add(txtLop);
                JLabel lblDiem = new JLabel("Điểm TB");
                panelAdd.add(lblDiem);
                JTextField txtDiem = new JTextField();
                panelAdd.add(txtDiem);
                JButton btnOK = new JButton("UPDATE");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.GREEN);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("UPDATE SinhVien SET HoTen = '" + txtTenSV.getText() + "', Lop = '" + txtLop.getText() + "', DiemTB = " + txtDiem.getText() + " WHERE MaSV = '" + txtMaSV.getText() + "'");
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

                JOptionPane.showOptionDialog(null, panelAdd, "Sửa thông tin sinh viên", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);

            }
        });
        btnDelete = new JButton("Xóa sinh viên");
        btnDelete.setBackground(Color.RED);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(2,2));
                JLabel lblMaSV = new JLabel("Mã sinh viên");
                panelAdd.add(lblMaSV);
                JTextField txtMaSV = new JTextField();
                panelAdd.add(txtMaSV);
                JButton btnOK = new JButton("DELETE");
                panelAdd.add(btnOK);
                btnOK.setBackground(Color.RED);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Statement statement = connection.createStatement();
                            statement.executeUpdate("DELETE FROM SinhVien WHERE MaSV = '" + txtMaSV.getText() + "'");
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

                JOptionPane.showOptionDialog(null, panelAdd, "Xóa thông tin sinh viên", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        });
        JComboBox<String> filterComboBox= new JComboBox<>(new String[]{"Lọc theo...", "Mã sinh viên", "Họ tên sinh viên", "Lớp", "Điểm TB"});
        final boolean[] isAppStarting = {true};
        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAppStarting[0]) {
                    filterComboBox.removeItemAt(0);
                    isAppStarting[0] = false;
                }
                String selectedFilter = (String) filterComboBox.getSelectedItem();
                JPanel panelAdd = new JPanel();
                panelAdd.setSize(900,600);
                panelAdd.setLayout(new GridLayout(2,2));
                if (selectedFilter.equals("Mã sinh viên")) {
                    JLabel lblMaSV = new JLabel("Mã sinh viên");
                    panelAdd.add(lblMaSV);
                    JTextField txtMaSV = new JTextField();
                    panelAdd.add(txtMaSV);
                    JButton btnOK = new JButton("Filter");
                    panelAdd.add(btnOK);
                    btnOK.setBackground(Color.GREEN);
                    btnOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                loadData("SELECT * FROM SinhVien WHERE MaSV = '" + txtMaSV.getText() + "'");
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
                } else if (selectedFilter.equals("Họ tên sinh viên")) {
                    JLabel lblTenSV = new JLabel("Họ tên sinh viên");
                    panelAdd.add(lblTenSV);
                    JTextField txtTenSV = new JTextField();
                    panelAdd.add(txtTenSV);
                    JButton btnOK = new JButton("Filter");
                    panelAdd.add(btnOK);
                    btnOK.setBackground(Color.GREEN);
                    btnOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                loadData("SELECT * FROM SinhVien WHERE HoTen = '" + txtTenSV.getText() + "'");                                ((Window) SwingUtilities.getRoot(panelAdd)).dispose();
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
                } else if (selectedFilter.equals("Lớp")) {
                    JLabel lblLopSV = new JLabel("Lớp");
                    panelAdd.add(lblLopSV);
                    JTextField txtLopSV = new JTextField();
                    panelAdd.add(txtLopSV);
                    JButton btnOK = new JButton("Filter");
                    panelAdd.add(btnOK);
                    btnOK.setBackground(Color.GREEN);
                    btnOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                loadData("SELECT * FROM SinhVien WHERE Lop = '" + txtLopSV.getText() + "'");
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
                } else if (selectedFilter.equals("Điểm TB")) {
                    panelAdd.setLayout(new GridLayout(2,3));
                    JLabel lblDiemSV = new JLabel("Điểm TB sinh viên");
                    panelAdd.add(lblDiemSV);
                    JTextField txtDiemSV1 = new JTextField();
                    panelAdd.add(txtDiemSV1);
                    JTextField txtDiemSV2 = new JTextField();
                    panelAdd.add(txtDiemSV2);
                    JButton btnOK = new JButton("Filter");
                    panelAdd.add(btnOK);
                    btnOK.setBackground(Color.GREEN);
                    btnOK.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                loadData("SELECT * FROM SinhVien WHERE DiemTB BETWEEN '" + txtDiemSV1.getText() + "' AND '" + txtDiemSV2.getText() + "'");                            } catch (SQLException throwables) {
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
                }

                JOptionPane.showOptionDialog(null, panelAdd, "Lọc thông tin sinh viên", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
            }
        });
        JComboBox<String> sortComboBox = new JComboBox<>(new String[] {"Sắp xếp theo điểm...", "Tăng dần", "Giảm dần"});
        final boolean[] isAppStarted = {true};
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isAppStarted[0]) {
                    sortComboBox.removeItemAt(0);
                    isAppStarted[0] = false;
                    return;
                }
                String selectedItem = sortComboBox.getSelectedItem().toString();
                if (selectedItem == "Tăng dần") {
                    try {
                        loadData("SELECT * FROM SinhVien ORDER BY DiemTB ASC");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    try{
                        connection = SQLConnection.getConnection(url, username, password);
                        loadData("SELECT * FROM SinhVien ORDER BY DiemTB DESC");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        JComboBox<String> query = new JComboBox<>(new String[] {"Số sinh viên từng lớp", "Lớp có số sinh viên nhiều nhất", "Sinh viên có ĐTB cao nhất", "Sinh viên có ĐTB không đạt"});
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (query.getSelectedIndex()) {
                    case 0:
                        try {
                            model.setColumnCount(0);
                            model.addColumn("Lop");
                            model.addColumn("SO_SINH_VIEN");
                            loadData("SELECT Lop, COUNT(MaSV) SO_SINH_VIEN FROM SinhVien GROUP BY Lop");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 1:
                        try {
                            model.setColumnCount(0);
                            model.addColumn("Lop");
                            model.addColumn("SO_SINH_VIEN");
                            loadData("SELECT TOP 1 WITH TIES Lop, COUNT(MaSV) SO_SINH_VIEN FROM SinhVien GROUP BY Lop ORDER BY COUNT(MaSV) DESC");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 2:
                        try {
                            model.setColumnCount(0);
                            model.addColumn("Lop");
                            model.addColumn("DIEM_TB_LOP");
                            loadData("SELECT TOP 1 WITH TIES Lop, AVG(DiemTB) DIEM_TB_LOP FROM SinhVien GROUP BY Lop ORDER BY AVG(DiemTB) DESC");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 3:
                        try {
                            model.setColumnCount(0);
                            model.addColumn("Lop");
                            model.addColumn("SV_DUOI_TB");
                            loadData("SELECT TOP 1 WITH TIES Lop, COUNT(MaSV) SV_DUOI_TB FROM SinhVien WHERE DiemTB < 5 GROUP BY Lop ORDER BY COUNT(MaSV) DESC");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
            }
        });
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reloadModel();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        panelButton.add(btnAdd);
        panelButton.add(btnEdit);
        panelButton.add(btnDelete);
        panelButton.add(filterComboBox);
        panelButton.add(sortComboBox);
        panelButton.add(query);
        panelButton.add(btnReset);

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

            ResultSet resultSet = statement.executeQuery("SELECT * FROM SinhVien");
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

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new SinhVienTab());
        frame.setVisible(true);
    }
    public void reloadModel() throws SQLException {
        model.setRowCount(0);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM SinhVien");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        if (model.getColumnCount() != 5) {
            model.setColumnCount(0);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                model.addColumn(columnName);
            }
        }

        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            model.addRow(row);
        }
    }

    public void loadData(String str) throws SQLException {
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(str);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        model.setRowCount(0);

        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            model.addRow(row);
        }
    }

    public void loadConstraint() throws SQLException {
        if (connection!=null)
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME='SinhVien'");
            System.out.println("=========DANH SÁCH CÁC CONSTRAINTS=========");
            int i=1;
            while(resultSet.next()) {
                System.out.println(i + ". "+resultSet.getString(1)  + "__"+
                        resultSet.getString(2));
                i++;
            }
        }
    }
}