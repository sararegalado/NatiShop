package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Administrador;

public class VentanaARSolicitud extends JFrame {
	private JPanel pnlCentro, pnlBotonera;
	private JButton btnAceptar, btnRechazar;
	private DefaultTableModel modeloTablaSolicitudes;
	private JTable tablaSolicitudes;
	private JScrollPane scrollTablaSolicitudes;
	
	
	
	public VentanaARSolicitud () {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1300, 500);
		
		pnlCentro = new JPanel(new BorderLayout());
		
		String [] titulos = {"DNI","NOMBRE", "APELLIDO","CORREO", "TELEFONO", "JORNADA ACTUAL", "JORNADA SOLICITADA", "RAZON"};
		modeloTablaSolicitudes = new DefaultTableModel();
		modeloTablaSolicitudes.setColumnIdentifiers(titulos);
		tablaSolicitudes = new JTable(modeloTablaSolicitudes);
		scrollTablaSolicitudes = new JScrollPane(tablaSolicitudes);
		
		//pnlCentro.add(scrollTablaCompras, BorderLayout.CENTER);
		getContentPane().add(scrollTablaSolicitudes, BorderLayout.CENTER);
		
		pnlBotonera = new JPanel(new GridLayout(1, 5));
		pnlBotonera.add(new JPanel());
		btnRechazar = new JButton("RECHAZAR");
		btnRechazar.setBackground(Color.RED);
		pnlBotonera.add(btnRechazar);
		pnlBotonera.add(new JPanel());
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBackground(Color.GREEN);
		pnlBotonera.add(btnAceptar);
		pnlBotonera.add(new JPanel());
		
		getContentPane().add(pnlBotonera, BorderLayout.SOUTH);
		
		Connection con = BD.initBD("NatiShop.db");
		BD.cargarTablaAdminsSolic(con, modeloTablaSolicitudes);
		BD.closeBD(con);
		
		tablaSolicitudes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p= e.getPoint();
				int fila= tablaSolicitudes.rowAtPoint(p);
				String dni = tablaSolicitudes.getModel().getValueAt(fila, 0).toString();
				Connection con = BD.initBD("NatiShop.db");
				Administrador a = BD.buscarAdministrador(con, dni);
				BD.closeBD(con);
				
				btnAceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String jornadaSolicitada = tablaSolicitudes.getModel().getValueAt(fila, 6).toString();
						Connection con = BD.initBD("NatiShop.db");
						BD.modificarJornadaAdmin(con, VentanaInicioSesion.getAdministrador().getDni(), jornadaSolicitada);
						JOptionPane.showMessageDialog(null, "El cambio de jornada del administrador" + a.getNombre() + " " + a.getApellido() +
								" se ha realizado correctamente", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
						BD.borrarAdminSolic(con, dni);
						if (a.getDni().equals(VentanaInicioSesion.getAdministrador().getDni())) {
							VentanaAdministrador.getTfJornada().setText(jornadaSolicitada);
						}
						modeloTablaSolicitudes.setRowCount(0);
						BD.cargarTablaAdminsSolic(con, modeloTablaSolicitudes);
						BD.closeBD(con);
					}
				});
				
				btnRechazar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Connection con = BD.initBD("NatiShop.db");
						JOptionPane.showMessageDialog(null, "El cambio de jornada del administrador" + a.getNombre() + " " + a.getApellido() +
								" se ha reachazado", "Rechazar", JOptionPane.INFORMATION_MESSAGE);
						BD.borrarAdminSolic(con, dni);
						modeloTablaSolicitudes.setRowCount(0);
						BD.cargarTablaAdminsSolic(con, modeloTablaSolicitudes);
						BD.closeBD(con);
					}
				});
				BD.closeBD(con);
			}
		});

		
		
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
