package client.component;

import client.pages.DeleteSuccess;
import client.pages.Mistake;
import client.socket.BuySocket;
import client.socket.deleteCar;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

class tablecar extends MouseAdapter {
    JTable jTable;
    String id;
    JFrame jFrame;

    public tablecar(JTable jTable, String id, JFrame jFrame) {
        this.jTable = jTable;
        this.id = id;
        this.jFrame = jFrame;
    }

    public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
        //得到选中的行列的索引值
        int r = jTable.rowAtPoint(e.getPoint());
        int c = jTable.columnAtPoint(e.getPoint());
        //得到选中的单元格的值，表格中都是字符串
        Object value = jTable.getValueAt(r, c);
        if (value.equals("delete")) {
            try {
                String re = deleteCar.deleteServe(Integer.parseInt((String) jTable.getValueAt(r, 3)), id);
                if (re.equals("0")) {
                    jFrame.dispose();
                    new DeleteSuccess(id, "删除成功！");
                } else {
                    jFrame.setVisible(false);
                    new Mistake(jFrame);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (value.equals("buy")) {
            try {
                String re = BuySocket.buyServe(Integer.parseInt((String) jTable.getValueAt(r, 3)), id, Integer.parseInt((String) jTable.getValueAt(r, 1)));
                if (re.equals("0")) {
                    jFrame.dispose();
                    new DeleteSuccess(id, "购买成功！");
                } else {
                    jFrame.setVisible(false);
                    new Mistake(jFrame);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}

public class CarTable extends JTable {
    JFrame jFrame;
    String id;

    public CarTable(Object[][] products, Object[] name, JFrame jFrame, String id) {
        super(products, name);
        this.jFrame = jFrame;
        this.id = id;
        setRowHeight(60);
        for (int i = 0; i < name.length; i++) {
            //设置表数据居中显示
            //DefaultTableCellRenderer类可以绘制单元格的背景、字体颜色等功能
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            render.setHorizontalAlignment(SwingConstants.CENTER);
            render.setBackground(new Color(234, 252, 251));
            getColumn(name[i]).setCellRenderer(render);
        }
        setFont(new Font("楷体", Font.PLAIN, 26));
        JTableHeader head = getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 60));// 设置表头大小
        head.setFont(new Font("楷体", Font.PLAIN, 32));// 设置表格字体
        //设置表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        getTableHeader().setDefaultRenderer(hr);
        //设置表头背景色
        getTableHeader().setBackground(Color.white);
        setEnabled(false);
        TableColumn tableColumn = getColumn(name[name.length - 1]);

        TableColumn tableColumn2 = getColumn(name[name.length - 2]);


        DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
        DefaultTableCellRenderer backGroundColor2 = new DefaultTableCellRenderer();
        //绘制开关列
        tableColumn2.setPreferredWidth(10);
        backGroundColor.setBackground(new Color(236, 90, 68));
        backGroundColor2.setBackground(new Color(202, 210, 216));
        backGroundColor.setForeground(Color.white);
        backGroundColor2.setForeground(Color.white);
        backGroundColor.setHorizontalAlignment(SwingConstants.CENTER);
        backGroundColor2.setHorizontalAlignment(SwingConstants.CENTER);
        tableColumn.setCellRenderer(backGroundColor);
        tableColumn.setPreferredWidth(5);

        tableColumn2.setCellRenderer(backGroundColor2);
        tablecar tablecar = new tablecar(this, id, jFrame);
        this.addMouseListener(tablecar);
    }
}
