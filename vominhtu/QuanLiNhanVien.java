package FRM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.regexp.internal.REUtil;

import Element.NhanVien;

public class QuanLiNhanVien extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con=null;
	private JFrame frame;
	private JTextField textma;
	private JTextField textten;
	private JTextField textsdt;
	private JTextField textdc;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiNhanVien window = new QuanLiNhanVien();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuanLiNhanVien() {
		initialize();
		showtable();
	}
	public Connection getConnection() {
		Connection con ;
		ResultSet rs = null;
		try {
			con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +  
			         "databaseName=QLCH;user=sa;password=1234");
			String SQL = "SELECT TOP 10 * FROM khachHang";  
			while (rs.next()) {  
	            System.out.println(rs.getString(1) + " |" + rs.getString(2)+ " |" + rs.getString(3)+ " |" + rs.getString(4));  
	         }  
			return con;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			}
		
	}
	public ArrayList<NhanVien> getlist() {
		ArrayList<NhanVien> list= new ArrayList<NhanVien>();
		Connection con = getConnection();
		String query= "SELECT * FROM  nhanVien";
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs =st.executeQuery(query);
			NhanVien nv;
			while(rs.next()){
				nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getString("sdtNV"), rs.getString("diachiNV"));
				list.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void  showtable() {
		ArrayList<NhanVien> ds = getlist();
		DefaultTableModel model = new DefaultTableModel();
		Object [] row = new Object[4];
		for(int i = 0 ; i< ds.size() ;i++){
			row[0] = ds.get(i).getMaNV();
			row[0] = ds.get(i).getTenNV();
			row[0] = ds.get(i).getSdt();
			row[0] = ds.get(i).getDiachiNV();
			
			model.addRow(row);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblQuanlinhanvien = new JLabel("QuanLiNhanVien");
		lblQuanlinhanvien.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuanlinhanvien.setForeground(Color.RED);
		lblQuanlinhanvien.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblma = new JLabel("maNV:");
		
		JLabel lblten = new JLabel("tenNV:");
		
		JLabel lblsdt = new JLabel("sdt:");
		
		JLabel lbldc = new JLabel("diachi:");
		
		textma = new JTextField();
		textma.setColumns(10);
		
		textten = new JTextField();
		textten.setColumns(10);
		
		textsdt = new JTextField();
		textsdt.setColumns(10);
		
		textdc = new JTextField();
		textdc.setColumns(10);
		
		JButton btnadd = new JButton("add");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query ="insert into Khachhang(maNV,tenNV,sdtNV,diachiNV) values (?,?,?,?)";
					PreparedStatement pst= con.prepareStatement(query);
					pst.setString(1, textma.getText());
					pst.setString(2, textten.getText());
					pst.setString(3, textsdt.getText());
					pst.setString(4, textdc.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"add thanh cong");
					pst.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton btndelete = new JButton("delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from nhanVien where maNV='"+textma.getText()+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Xóa thành công");
					pst.close();
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnup = new JButton("update");
		btnup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Update nhanVien set maKH='"+textma.getText()+"',tenkh='"+textten.getText()+"',sdt='"+textsdt.getText()+"',dachi='"+textdc.getText()+"' where maKH='"+textma.getText()+"' ";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Chỉnh sửa thành công");
					pst.close();
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnsearch = new JButton("search");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblQuanlinhanvien, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lbldc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblsdt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblten, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblma, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textma, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
						.addComponent(textten)
						.addComponent(textsdt)
						.addComponent(textdc))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnsearch, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(btnup, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(btndelete, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
						.addComponent(btnadd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 517, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblQuanlinhanvien)
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblma, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(textma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnadd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblten, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndelete))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblsdt, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textsdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnup))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbldc, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textdc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnsearch))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"maNV", "tenNV", "sdtNV", "diachiNV"
			}
		));
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
