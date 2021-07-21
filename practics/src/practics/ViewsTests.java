package practics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewsTests {

	final JDialog dialog = new JDialog();
	int cnt,c,r;
	String [] columns;
	String [][] data;
	public ViewsTests() {
		String s = "select * from tests";
		columns = new String[]{"Test Name","Amount","Normal Range","Upper Limit","Lower Limit","Units"};
		Connection cc = MyConnection.connect();
		try{
			PreparedStatement ps = cc.prepareStatement(s);
			ResultSet rs = ps.executeQuery();
			cnt = 50;
			data = new String[cnt][6];
			while(rs.next()){
				data[r][c] = rs.getString("tname");
				++c;
				data[r][c] = String.valueOf(rs.getInt("amount"));
				++c;
				data[r][c] = rs.getString("nrange");
				++c;
				data[r][c] = String.valueOf(rs.getDouble("upper"));
				++c;
				data[r][c] = String.valueOf(rs.getDouble("lower"));
				++c;
				data[r][c] = rs.getString("units");
				c = 0;
				++r;
			}
			JTable table = new JTable(data,columns);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane jsp = new JScrollPane(table);
			table.setEnabled(false);
			
			JButton back = new JButton("Close");
			dialog.setLayout(null);
			dialog.setModal(true);
			dialog.setSize(500,400);
			back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.dispose();
					
				}
			});
			back.setBounds(10,10,80,30);
			jsp.setBounds(10,60,400,300);
			dialog.add(back);
			dialog.add(jsp);
			dialog.setVisible(true);
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
	}

}
