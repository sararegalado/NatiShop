 package ventanas;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VentanaPortada extends JFrame {
    private static VentanaPortada miVentana;
    private EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private JProgressBar pbReproduccion;
    private JTextField tfBuscar;
	private JFrame vActual, vAnterior;

    public VentanaPortada(JFrame va) {
		vActual = this;
		vAnterior = va;
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setLocation(-223, -103);
        getContentPane().setLayout(null);
        mediaPlayerComponent.setSize(1778, 988);
        
        //setUndecorated(true);
        
        pbReproduccion = new JProgressBar(0, 1000);
        pbReproduccion.setPreferredSize(new java.awt.Dimension(200, 20));

        JPanel panelNorte = new JPanel(new GridLayout(1, 3));
        panelNorte.setSize(1283, 27);
        panelNorte.setLocation(0, 0);

        JPanel panelBotonera = new JPanel(new GridLayout(1, 3));
        tfBuscar = new JTextField();
        tfBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
        tfBuscar.setEditable(false);
        tfBuscar.setBorder(new LineBorder(Color.BLACK));
        tfBuscar.setText("BUSCAR");
        tfBuscar.setColumns(10);
        
        JPanel j = new JPanel(); //vacio
        j.setBackground(Color.WHITE);
        panelNorte.add(j);
        panelNorte.add(tfBuscar);

        JButton btnIS = new JButton("Inicio Sesion");
        btnIS.setBackground(new Color(105, 105, 105));
        btnIS.setBorder(new LineBorder(Color.BLACK));
        btnIS.setForeground(Color.WHITE);
        panelBotonera.add(btnIS);

        JButton btnCesta = new JButton("Cesta");
        btnCesta.setBackground(new Color(105, 105, 105));
        btnCesta.setBorder(new LineBorder(Color.BLACK));
        btnCesta.setForeground(Color.WHITE);
        panelBotonera.add(btnCesta);

        panelNorte.add(panelBotonera);
        getContentPane().add(panelNorte);

        JLabel lbLogo = new JLabel("");
        lbLogo.setForeground(new Color(0, 0, 0));
        lbLogo.setAutoscrolls(true);
        lbLogo.setBounds(28, 57, 105, 92);
        lbLogo.setSize(100,100);

        lbLogo.setIcon(new ImageIcon(VentanaPortada.class.getResource("/imagenes/logo1.png")));
        lbLogo.setOpaque(true);
        getContentPane().setBackground(new Color(0, 0, 0, 0));

        getContentPane().add(lbLogo);
        getContentPane().add(mediaPlayerComponent);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        //Listeners para los JButtons y JTextfield
        tfBuscar.addMouseListener(new MouseAdapter() {
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				new VentanaPrincipal(vActual);
				vActual.setVisible(false);
				
			}
		});
        
        btnIS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicioSesion(vActual);
				
				
			}
		});
        

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().stop();
                mediaPlayerComponent.mediaPlayer().release();
            }
        });

        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                pbReproduccion.setValue((int) (1000L * newTime / mediaPlayer.status().length()));
            }
        });
        pbReproduccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().setTime(
                        mediaPlayerComponent.mediaPlayer().status().length() * e.getX() / pbReproduccion.getWidth());
            }
        });

        // Establecer bucle de reproducción
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);

        int anchoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getWidth();
        int altoP = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode()
                .getHeight();
        setSize(anchoP, altoP);
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * Reproduce un medio especificado por su ruta.
     * Configura el volumen al máximo antes de iniciar la reproducción.
     * @param mrl La ruta del medio a reproducir (Media Resource Locator).
     */
    public void lanza(String mrl) {
        mediaPlayerComponent.mediaPlayer().audio().setVolume(100);
        mediaPlayerComponent.mediaPlayer().media().play(mrl);
    }

    /**
     * Método principal que inicia la aplicación.
     * Realiza la configuración inicial del reproductor de medios y reproduce un video de bienvenida.
	*/
    public static void main(String[] args) {
        boolean found = (new NativeDiscovery()).discover();
        if (!found)
            System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");

        // Obtén la URL del video desde el classpath
        URL videoUrl = VentanaPortada.class.getResource("/imagenes/tienda1.mp4");

        // Verifica si la URL es nula
        if (videoUrl == null) {
            System.err.println("Error: No se puede encontrar el archivo de video.");
            return;
        }

        // Convierte la URL a una ruta de archivo
        String videoPath = "";
        try {
            videoPath = Paths.get(videoUrl.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace(); 
            return;
        }

        miVentana = new VentanaPortada(null);
        miVentana.lanza(videoPath);
    }
}
