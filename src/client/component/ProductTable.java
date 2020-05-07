package client.component;

import client.json.Product;
import client.pages.DeleteSuccess;
import client.pages.DownSuccess;
import client.pages.Mistake;
import client.socket.BuySocket;
import client.socket.DownSocket;
import client.socket.deleteCar;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

class tablemy extends MouseAdapter{
    JTable jTable;
    String id;
    JFrame jFrame;
    public tablemy(JTable jTable,String id,JFrame jFrame){
        this.jTable=jTable;
        this.id=id;
        this.jFrame=jFrame;
    }
    public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
        //得到选中的行列的索引值
        int r = jTable.rowAtPoint(e.getPoint());
        int c = jTable.columnAtPoint(e.getPoint());
        //得到选中的单元格的值，表格中都是字符串
        Object value = jTable.getValueAt(r, c);
        if(value.equals("下架")){
            try {
                String re = DownSocket.downServe( Integer.parseInt((String) jTable.getValueAt(r, 3)),id);
                if(re.equals("0")){
                    jFrame.dispose();
                    new DownSuccess(id,"下架成功！");
                }else {
                    jFrame.setVisible(false);
                    new Mistake(jFrame);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


}
public class ProductTable extends JTable  {
    // String name[]={"商品名称","价格","商品状态","上架时间","停售开关"};
    public ProductTable(Object[][] products, Object[] name,JFrame jFrame,String id) {
        super(products, name);
        setRowHeight(60);
        for(int i=0;i<name.length;i++){
            //设置表数据居中显示
            //DefaultTableCellRenderer类可以绘制单元格的背景、字体颜色等功能
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            render.setHorizontalAlignment(SwingConstants.CENTER);
            render.setBackground(new Color(234,252,251));
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
        TableColumn tableColumn = getColumn(name[name.length-1]);





        DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();

        //绘制开关列

        backGroundColor.setBackground(new Color(216, 22, 33));
        backGroundColor.setForeground(Color.white);
        backGroundColor.setHorizontalAlignment(SwingConstants.CENTER);
        tableColumn.setCellRenderer(backGroundColor);
        tableColumn.setPreferredWidth(12);
        tablemy tablecar =new tablemy(this,id,jFrame);
        this.addMouseListener(tablecar);


    }
}
