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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;



public class DesafioDoHeroi extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;

    @Override
    public void jardineiroJunior(int player) {            
        jdialog.add(new JLabel("O Player " + player + " será o primeiro "
                                + "a jogar! Boa Sorte!"));
        jdialog.validate();
        jdialog.repaint();
    }
	
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
	JLabel jardineiroJunior;
        JDialog jdialog; 
        JLabel florAmarela1;
        JLabel florAmarela2;
        JLabel florAmarela3;
        JLabel florVermelha1;
        JLabel florVermelha2;
        JLabel florVermelha3;
        
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
                        jdialog = new JDialog(getJFrame(), true);
                        String player1 = jrAmarelo.isSelected() ? "Amarelo": "Vermelho";
                        controle.setPlayer1(player1);
                         
                        jdialog.setSize(350, 480);
                        jdialog.setLayout(new FlowLayout());
                        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                        int [] p1 = controle.sortearNPlayer1();
                        int[] p2 = controle.sortearNPlayer2();
                        
                        florAmarela1 = new JLabel(); 
                        florAmarela1.setIcon(new ImageIcon("imagens/Amarela.png"));
                            florAmarela1.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p1[0],"Amarelo");                                   
                                        florAmarela1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[0]) + ".png"));       
                                        florAmarela2.setEnabled(false);
                                        florAmarela3.setEnabled(false);
                                }
                            });
                        
                             florAmarela2 = new JLabel();
                            florAmarela2.setIcon(new ImageIcon("imagens/Amarela.png"));
                            florAmarela2.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p1[1],"Amarelo");
                                       florAmarela2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[1])+ ".png"));
                                       florAmarela1.setEnabled(false);
                                       florAmarela3.setEnabled(false);
                                }
                            });
                        
                             florAmarela3 = new JLabel();
                            florAmarela3.setIcon(new ImageIcon("imagens/Amarela.png"));
                            florAmarela3.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p1[2],"Amarelo");
                                       florAmarela3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[2])+ ".png"));
                                       florAmarela1.setEnabled(false);
                                       florAmarela2.setEnabled(false);
                                }
                            });
                            
                           florVermelha1 = new JLabel();  
                            florVermelha1.setIcon(new ImageIcon("imagens/Vermelha.png"));
                            florVermelha1.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p2[0],"Vermelho");
                                       florVermelha1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[0])+ ".png"));
                                       florVermelha2.setEnabled(false);
                                       florVermelha3.setEnabled(false);
                                }
                            });

                            florVermelha2 = new JLabel(); 
                            florVermelha2.setIcon(new ImageIcon("imagens/Vermelha.png"));
                            florVermelha2.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p2[1],"Vermelho");
                                       florVermelha2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[1])+ ".png"));
                                       florVermelha1.setEnabled(false);
                                       florVermelha3.setEnabled(false);
                                }
                            });
                             florVermelha3 = new JLabel();
                            florVermelha3.setIcon(new ImageIcon("imagens/Vermelha.png"));
                            florVermelha3.addMouseListener(new MouseAdapter() {
                                public void mouseClicked(MouseEvent e){
                                       controle.setPlayerFirstNumber(p2[2],"Vermelho");
                                       florVermelha3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[2])+ ".png"));
                                       florVermelha1.setEnabled(false);
                                       florVermelha2.setEnabled(false);
                                    }
                            });

                            
                        jdialog.add(new JLabel("Player1 Escolha uma das flores para iniciar:"));
                        
                        if(player1.equalsIgnoreCase("Amarelo")){
                            jdialog.add(florAmarela1);
                            jdialog.add(florAmarela2);
                            jdialog.add(florAmarela3);
                            }else{                        
                            jdialog.add(florVermelha1);
                            jdialog.add(florVermelha2);
                            jdialog.add(florVermelha3);
                        }

           
               
                        jdialog.add(new JLabel("Player2 Escolha uma das flores para iniciar :"));
                          if(!player1.equalsIgnoreCase("Vermelho")){
                            jdialog.add(florVermelha1);
                            jdialog.add(florVermelha2);
                            jdialog.add(florVermelha3);
                            }else{
                                jdialog.add(florAmarela1);
                                jdialog.add(florAmarela2);
                                jdialog.add(florAmarela3);
                        }
                        
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
