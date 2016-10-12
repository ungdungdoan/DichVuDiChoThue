package FRM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connect.connectdb;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JScrollPane;

public class QuanLiKhachHang extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	private PreparedStatement stmt;
	private ResultSet rs;
	private JFrame frame;
	private JTextField txtma;
	private JTextField txtten;
	private JTextField txtgt;
	private JTextField txtsdt;
	private JTextField txtdc;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiKhachHang window = new QuanLiKhachHang();
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
	public QuanLiKhachHang() {
		
		initialize();
		showtable();
	}
	public void showtable(){
		Vector col = new Vector();
		col.addElement("maKH");
		col.addElement("tenKH");
		col.addElement("gioitinh");
		col.addElement("sdt");
		col.addElement("diachi");
		Vector data = new Vector();
		String  sql= "select * from KhachHang";
		try {
			stmt= connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()){
				Vector kh = new Vector();
				kh.addElement(rs.getString("maKH"));
				kh.addElement(rs.getString("tenKH"));
				kh.addElement(rs.getString("gioitinh"));
				kh.addElement(rs.getInt("sdtKH"));
				kh.addElement(rs.getString("diachiKH"));
				data.add(kh);
			}
		} catch (Exception e) {
			table.setModel(new DefaultTableModel(null, col));
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblma = new JLabel("maKH :");
		
		JLabel lblten = new JLabel("tenKH :");
		
		JLabel lblgt = new JLabel("gioitinh :");
		
		JLabel lblsdt = new JLabel("sdt :");
		
		JLabel lbldc = new JLabel("diachi :");
		
		JLabel lblNewLabel_5 = new JLabel("KhachHang");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_5.setForeground(Color.RED);
		
		JButton btnadd = new JButton("add");
		btnadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query ="insert into Khachhang(maKH,tenKH,gioitinh,sdtKH,diachiKH) values (?,?,?,?,?)";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, txtma.getText());
					pst.setString(2, txtten.getText());
					pst.setString(3, txtgt.getText());
					pst.setString(4, txtsdt.getText());
					pst.setString(5, txtdc.getText());
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from KhachHang where makH='"+txtma.getText()+"'";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Xóa thành công");
					pst.close();
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		JButton btnupdate = new JButton("update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="Update KhachHang set maKH='"+txtma.getText()+"',tenkh='"+txtten.getText()+"',gioitinh='"+txtgt.getText()+"',sdt='"+txtsdt.getText()+"',dachi='"+txtdc.getText()+"' where maKH='"+txtma.getText()+"' ";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null,"Chỉnh sửa thành công");
					pst.close();
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnsearch = new JButton("search");
		
		txtma = new JTextField();
		txtma.setColumns(10);
		
		txtten = new JTextField();
		txtten.setColumns(10);
		
		txtgt = new JTextField();
		txtgt.setColumns(10);
		
		txtsdt = new JTextField();
		txtsdt.setColumns(10);
		
		txtdc = new JTextField();
		txtdc.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblten)
											.addGap(30))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblgt, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
											.addGap(26)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblsdt)
										.addGap(76)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbldc)
									.addGap(64))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblma, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtdc)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtma, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
						.addComponent(txtten)
						.addComponent(txtgt)
						.addComponent(txtsdt))
					.addGap(156)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnsearch, 0, 0, Short.MAX_VALUE)
						.addComponent(btnupdate, 0, 0, Short.MAX_VALUE)
						.addComponent(btnadd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btndelete, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addGap(67))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addComponent(lblNewLabel_5)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnadd)
						.addComponent(txtma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblma, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblten, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btndelete)
						.addComponent(txtten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblgt)
								.addComponent(txtgt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblsdt)
								.addComponent(txtsdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbldc)
								.addComponent(txtdc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnupdate)
							.addGap(18)
							.addComponent(btnsearch)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(57)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"maKH", "teKH", "gioitinh", "sdt", "diachi"
			}
		));
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
