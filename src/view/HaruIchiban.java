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
import java.awt.event.MouseListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class HaruIchiban extends JFrame implements Observador {

    private ControleJogo controle;
    private JTable tabuleiro;
    private JLabel Jplayer;
    private JRadioButton jrVermelho;
    private JRadioButton jrAmarelo;
    private JLabel jardineiroJunior;
    private JDialog jdialog;
    private JLabel florAmarela1;
    private JLabel florAmarela2;
    private JLabel florAmarela3;
    private JLabel florVermelha1;
    private JLabel florVermelha2;
    private JLabel florVermelha3;
    private static final long serialVersionUID = 1L;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    int[] p1;
    int[] p2;
    
    
    @Override
    public void jardineiroJunior(int player) {
        Jplayer = new JLabel("O Player " + player + " será o primeiro a jogar! Boa Sorte!");
        removeMouseListeners(florAmarela1, florAmarela2, florAmarela3, florVermelha1, florVermelha2, florVermelha3);
        jdialog.add(Jplayer);
        jdialog.validate();
        jdialog.repaint();
       
        executorService.scheduleAtFixedRate(startGame(), 2, 3, TimeUnit.SECONDS);

    }

    private Runnable startGame() {
        return () -> {
            remove(jdialog);
            jdialog.setVisible(false);
            validate();
            repaint();
            executorService.shutdown();
            tabuleiro.setCellSelectionEnabled(true);
        };
    }

    @Override
    public void notificarMudancaFlor(int numero, String player) {
        
    }
    
    class HaruTableModel extends AbstractTableModel {

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

    public HaruIchiban() throws Exception {
        this.controle = new ControleJogoImpl();
        this.controle.inicializar();
        this.controle.addObservador(this);

        setTitle("Haru Ichiban");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        pack();

    }

    private void initComponents() {

        // criar o tabuleiro e seus componentes
        tabuleiro = new JTable();
        tabuleiro.setModel(new HaruTableModel());
        for (int x = 0; x < tabuleiro.getColumnModel().getColumnCount(); x++) {
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
        tabuleiro.setCellSelectionEnabled(false);
        
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.setSize(600, 200);

        JLabel player = new JLabel("Player 1 será: ");
        jp.add(player);

        Button b1 = new Button("Jogar");
        b1.setSize(30, 30);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jdialog = new JDialog(getJFrame(), true);
                String player1 = jrAmarelo.isSelected() ? "Amarelo" : "Vermelho";
                controle.setPlayer1(player1);

                jdialog.setSize(350, 350);
                jdialog.setLayout(new FlowLayout());
                jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                p1 = controle.sortearNPlayer1();
                p2 = controle.sortearNPlayer2();

                florAmarela1 = new JLabel();
                florAmarela1.setIcon(new ImageIcon("imagens/"+controle.converteNumero(p1[0]) + ".png"));
//                florAmarela1.setIcon(new ImageIcon("imagens/Amarela.png"));
//                florAmarela1.addMouseListener(new MouseAdapter() {
//                    public void mouseClicked(MouseEvent e) {
//                        controle.setPlayerFirstNumber(p1[0], "Amarelo");
//                        florAmarela1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[0]) + ".png"));
//
//                    }
//                });

                florAmarela2 = new JLabel();
                florAmarela2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[1]) + ".png"));             
                florAmarela2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        controle.setPlayerFirstNumber(p1[1], "Amarelo");
                        florAmarela2.setIcon(new ImageIcon("imagens/Amarela.png"));

                    }
                });

                florAmarela3 = new JLabel();
                florAmarela3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[2]) + ".png"));
//                florAmarela3.setIcon(new ImageIcon("imagens/Amarela.png"));
//                florAmarela3.addMouseListener(new MouseAdapter() {
//                    public void mouseClicked(MouseEvent e) {
//                        controle.setPlayerFirstNumber(p1[2], "Amarelo");
//                        florAmarela3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p1[2]) + ".png"));
//
//                    }
//                });

                florVermelha1 = new JLabel();
                florVermelha1.setIcon(new ImageIcon("imagens/Vermelha.png"));
                florVermelha1.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        controle.setPlayerFirstNumber(p2[0], "Vermelho");
                        florVermelha1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[0]) + ".png"));

                    }
                });

                florVermelha2 = new JLabel();
                florVermelha2.setIcon(new ImageIcon("imagens/Vermelha.png"));
                florVermelha2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        controle.setPlayerFirstNumber(p2[1], "Vermelho");
                        florVermelha2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[1]) + ".png"));

                    }
                });
                florVermelha3 = new JLabel();
                florVermelha3.setIcon(new ImageIcon("imagens/Vermelha.png"));
                florVermelha3.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        controle.setPlayerFirstNumber(p2[2], "Vermelho");
                        florVermelha3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(p2[2]) + ".png"));

                    }
                });

                jdialog.add(new JLabel("Player1 Escolha uma das flores para iniciar:"));

                if (player1.equalsIgnoreCase("Amarelo")) {
                    jdialog.add(florAmarela1);
                    jdialog.add(florAmarela2);
                    jdialog.add(florAmarela3);
                } else {
                    jdialog.add(florVermelha1);
                    jdialog.add(florVermelha2);
                    jdialog.add(florVermelha3);
                }

                jdialog.add(new JLabel("Player2 Escolha uma das flores para iniciar :"));
                if (!player1.equalsIgnoreCase("Vermelho")) {
                    jdialog.add(florVermelha1);
                    jdialog.add(florVermelha2);
                    jdialog.add(florVermelha3);
                } else {
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

        add(jp, SOUTH);

    }

    public static void main(String[] args) {
        try {
            HaruIchiban d = new HaruIchiban();
            d.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    @Override
    public void iniciouJogo() {
        tabuleiro.setCellSelectionEnabled(true);
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

    private JFrame getJFrame() {
        return this;
    }

    private void removeMouseListeners(JLabel flor1, JLabel flor2, JLabel flor3, JLabel flor4, JLabel flor5, JLabel flor6) {
        for (MouseListener a : flor1.getMouseListeners()) {
            flor1.removeMouseListener(a);
        }
        for (MouseListener b : flor2.getMouseListeners()) {
            flor2.removeMouseListener(b);
        }
        for (MouseListener c : flor3.getMouseListeners()) {
            flor3.removeMouseListener(c);
        }
        for (MouseListener d : flor4.getMouseListeners()) {
            flor4.removeMouseListener(d);
        }
        for (MouseListener e : flor5.getMouseListeners()) {
            flor5.removeMouseListener(e);
        }
        for (MouseListener f : flor6.getMouseListeners()) {
            flor6.removeMouseListener(f);
        }

    }

}
