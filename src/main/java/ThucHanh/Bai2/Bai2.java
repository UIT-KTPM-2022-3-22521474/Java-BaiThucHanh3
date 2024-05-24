package ThucHanh.Bai2;

import ThucHanh.SQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Bai2 extends JFrame implements SQLConnection {

    private Connection connection;
    private JTable table;
    private DefaultTableModel model;

    ArrayList<String> columnNameList;

    public Bai2() {
        this.setTitle("BT2 - To Vinh Tien - 22521474");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(900, 600);

        this.setLayout(new BorderLayout());

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

            Statement statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("SELECT * FROM SinhVien SV JOIN Lop L ON SV.Lop = L.MaLop");
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
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
