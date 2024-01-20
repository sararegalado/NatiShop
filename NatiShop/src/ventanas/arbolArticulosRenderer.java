package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class arbolArticulosRenderer extends DefaultTreeCellRenderer {
	private Font fuente = new Font( "Arial", Font.BOLD, 14);
	

	@Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel l = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        l.setFont(fuente);
        l.setForeground(Color.BLACK);
		l.setBackground(Color.WHITE);
		l.setOpaque(true);
		
		if( value  instanceof DefaultMutableTreeNode) {
			DefaultMutableTreeNode raiz= (DefaultMutableTreeNode) value;
			if(raiz.isRoot()) {
				ImageIcon imagenRaiz = new ImageIcon(VentanaAdministrador.class.getResource("/imagenes/nombreTienda.png"));
				int ancho =200;
				int alto = 130;
				Image img = imagenRaiz.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
				
				l.setIcon(new ImageIcon(img));
			}
		}
        return l;
	
	}
}
