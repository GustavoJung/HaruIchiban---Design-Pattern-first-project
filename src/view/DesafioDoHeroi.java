package view;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import controle.ControleJogo;
import controle.ControleJogoImpl;
import controle.Observador;
import java.awt.Button;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class DesafioDoHeroi extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;
	
	class HeroiTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public int getRowCount() {
			return 5;
		}

		@Override
		public Object getValueAt(int row, int col) {
			try {
				return controle.getPeca(col, row);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return null;
			}
		}
		
	}
	
	class HeroiRenderer extends DefaultTableCellRenderer {
	
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			setIcon((ImageIcon) value);
			
			return this;
		}

	}

	private ControleJogo controle;
	private JTable tabuleiro;
	
	public DesafioDoHeroi() throws Exception {
		this.controle = new ControleJogoImpl();
		this.controle.inicializar();
		this.controle.addObservador(this);
		
		setTitle("Haru Ichiban");
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		initComponents();
		pack();
	
	}

        JRadioButton jrVermelho;
        JRadioButton jrAmarelo;
	private void initComponents() {

		// criar o tabuleiro e seus componentes
		tabuleiro = new JTable();
		tabuleiro.setModel(new HeroiTableModel());
		for (int x=0;x<tabuleiro.getColumnModel().getColumnCount();x++) {
			tabuleiro.getColumnModel().getColumn(x).setWidth(100);
			tabuleiro.getColumnModel().getColumn(x).setMinWidth(100);
			tabuleiro.getColumnModel().getColumn(x).setMaxWidth(100);
		}
		tabuleiro.setRowHeight(100);
		tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabuleiro.setShowGrid(false);
		tabuleiro.setIntercellSpacing(new Dimension(0, 0));
		tabuleiro.setDefaultRenderer(Object.class, new HeroiRenderer());

		tabuleiro.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					controle.pressTecla( e.getKeyCode() );
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
			
		});
		
		
		add(tabuleiro, CENTER);

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.setSize(600, 200);
                
                JLabel player = new JLabel("Player 1 será: ");
                jp.add(player);
                
                Button b1 = new Button("Jogar");
                b1.setSize(30,30);
                
		b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                         
                        JDialog jdialog = new JDialog(getJFrame(), true);
                        jdialog.setSize(270, 200);
                        jdialog.setLayout(new FlowLayout());
                        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                        jdialog.add(new JLabel("Player 1 terá as flores iniciais :"));
                          
                        jdialog.add(new JLabel(" " + (new Random().nextInt(8)) +1));
                        jdialog.add(new JLabel(" " + new Random().nextInt(8) + 1));
                        jdialog.add(new JLabel(" " + new Random().nextInt(8) + 1));
               
                        jdialog.add(new JLabel("Player 2 terá as flores iniciais :"));
                          
                        jdialog.add(new JLabel(" " + (new Random().nextInt(8)) +1));
                        jdialog.add(new JLabel(" " + new Random().nextInt(8) + 1));
                        jdialog.add(new JLabel(" " + new Random().nextInt(8) + 1));
               
                        
                        jdialog.setLocationRelativeTo(getJFrame());
                        jdialog.setVisible(true);
                    }
                });
                
                         JPanel jrGrupo = new JPanel();

		ButtonGroup bgTipoHeroi = new ButtonGroup();
		
		jrAmarelo = new JRadioButton("Amarelo");
		jrAmarelo.setSelected(true);
		jrAmarelo.setActionCommand("Amarelo");
		jrGrupo.add(jrAmarelo);
		bgTipoHeroi.add(jrAmarelo);
		
                
		jrVermelho = new JRadioButton("Vermelho");
		jrGrupo.add(jrVermelho);
		jrVermelho.setActionCommand("Vermelho");
		bgTipoHeroi.add(jrVermelho);
		
		ActionListener radioAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					controle.setTipoHeroi(event.getActionCommand());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString());
				}
			}
		};
		jrAmarelo.addActionListener(radioAction);
		jrVermelho.addActionListener(radioAction);
		radioAction.actionPerformed(new ActionEvent(jrAmarelo, ActionEvent.ACTION_PERFORMED, jrAmarelo.getActionCommand()));
		
                jp.add(jrGrupo);
                jp.add(b1);
                
		add(jp,SOUTH);
		
		
	}

	
	
	public static void main(String[] args) {
		try {
			DesafioDoHeroi d = new DesafioDoHeroi();
			d.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}

	}

	@Override
	public void iniciouJogo() {
		
	}

	@Override
	public void mudouTabuleiro() {
		tabuleiro.repaint();
	}

	@Override
	public void fimDeJogo(String msgErro) {
		JOptionPane.showMessageDialog(null, msgErro);
		System.exit(0);
	}
        
        private JFrame getJFrame(){
            return this;
        }

}
