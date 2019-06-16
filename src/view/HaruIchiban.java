package view;

import command.ColocaFlor;
import command.ColocaSapo;
import command.ColocaSapoRegia;
import command.CommandInvoker;
import command.MoveNenufar;
import command.NovaRegiaEscura;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import static java.awt.BorderLayout.NORTH;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class HaruIchiban extends JFrame implements Observador {

    private ControleJogo controle;
    private CommandInvoker commandInvoker;
    private JTable tabuleiro;
    private JDialog jdialog;
    private JPanel jp;
    private JLabel Jplayer;
    private JLabel jardineiroJunior;
    private JLabel florAmarela1;
    private JLabel florAmarela2;
    private JLabel florAmarela3;
    private JLabel florVermelha1;
    private JLabel florVermelha2;
    private JLabel florVermelha3;
    private JLabel turno;
    private JLabel pontuacaoP1;
    private JLabel pontuacaoP2;
    private JLabel ipontuacaoP1;
    private JLabel ipontuacaoP2;
    private JPanel panelPontuacao;
    private JRadioButton jrVermelho;
    private JRadioButton jrAmarelo;
    private Button b1;

    private static final long serialVersionUID = 1L;

    @Override
    public void notificarAlterouPontuacao(int pontuacaoPlayer1, int pontuacaoPlayer2) {
        ipontuacaoP1.setText(pontuacaoPlayer1+"");
        ipontuacaoP2.setText(pontuacaoPlayer2+"");
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

    class PecaRenderer extends DefaultTableCellRenderer {

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
        commandInvoker = new CommandInvoker();

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
        tabuleiro.setDefaultRenderer(Object.class, new PecaRenderer());

        add(tabuleiro, CENTER);
        tabuleiro.setCellSelectionEnabled(false);
        controle.sortearNPlayer1();
        controle.sortearNPlayer2();

        jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.setSize(600, 200);

        JLabel player = new JLabel("Player 1 será: ");
        jp.add(player);

        b1 = new Button("Jogar");
        b1.setSize(30, 30);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addJDialog();
            }
        });

        addJrGroup();
        addPlacarPontuacao();

    }

    private void addPlacarPontuacao() {
        panelPontuacao = new JPanel();
        panelPontuacao.setLayout(new FlowLayout());
        panelPontuacao.setSize(50, 50);

        pontuacaoP1 = new JLabel("Pontuacao player 1 = ");
        pontuacaoP2 = new JLabel("Pontuacao player 2 = ");

        ipontuacaoP1 = new JLabel(0+"");
        ipontuacaoP2 = new JLabel(0+"");
        
        JPanel placar = new JPanel();
        placar.add(pontuacaoP1);
        placar.add(ipontuacaoP1);
        placar.add(pontuacaoP2);
        placar.add(ipontuacaoP2);
        placar.setSize(50, 50);
        panelPontuacao.add(placar);
        add(panelPontuacao, NORTH);
    }

    public void addJrGroup() {
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

    private void addJDialog() {
        jdialog = new JDialog(getJFrame(), true);
        String player1 = jrAmarelo.isSelected() ? "Amarelo" : "Vermelho";
        String player2 = jrAmarelo.isSelected() ? "Vermelho" : "Amarelo";
        controle.setPlayer1(player1);
        controle.setPlayer2(player2);

        jdialog.setSize(350, 300);
        jdialog.setLayout(new FlowLayout());
        jdialog.setUndecorated(true);
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        florAmarela1 = new JLabel();
        florAmarela1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp1()[0]) + ".png"));
        florAmarela1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Amarelo");
                controle.changeFlowers(1, "Amarelo");
                controle.setPlayerFirstNumber(controle.getp1()[0], "Amarelo");
            }
        });

        florAmarela2 = new JLabel();
        florAmarela2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp1()[1]) + ".png"));
        florAmarela2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Amarelo");
                controle.changeFlowers(2, "Amarelo");
                controle.setPlayerFirstNumber(controle.getp1()[1], "Amarelo");

            }
        });

        florAmarela3 = new JLabel();
        florAmarela3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp1()[2]) + ".png"));
        florAmarela3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Amarelo");
                controle.changeFlowers(3, "Amarelo");
                controle.setPlayerFirstNumber(controle.getp1()[2], "Amarelo");

            }
        });

        florVermelha1 = new JLabel();
        florVermelha1.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp2()[0]) + ".png"));
        florVermelha1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Vermelho");
                controle.changeFlowers(1, "Vermelho");
                controle.setPlayerFirstNumber(controle.getp2()[0], "Vermelho");

            }
        });

        florVermelha2 = new JLabel();
        florVermelha2.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp2()[1]) + ".png"));
        florVermelha2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Vermelho");
                controle.changeFlowers(2, "Vermelho");

                controle.setPlayerFirstNumber(controle.getp2()[1], "Vermelho");
            }
        });

        florVermelha3 = new JLabel();
        florVermelha3.setIcon(new ImageIcon("imagens/" + controle.converteNumero(controle.getp2()[2]) + ".png"));
        florVermelha3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controle.florClicada("Vermelho");
                controle.changeFlowers(3, "Vermelho");

                controle.setPlayerFirstNumber(controle.getp2()[2], "Vermelho");
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

    public static void main(String[] args) {
        try {
            HaruIchiban d = new HaruIchiban();
            d.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    private void startGame(JLabel label) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Timer t = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        tabuleiro.setCellSelectionEnabled(true);
                        tabuleiro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        jdialog.dispose();
                        remove(jdialog);
                        controle.jogoIniciou();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });
    }

    private JFrame getJFrame() {
        return this;
    }

    private void removeListenersVermelhos(JLabel flor1, JLabel flor2, JLabel flor3) {
        for (MouseListener a : flor1.getMouseListeners()) {
            flor1.removeMouseListener(a);
        }
        for (MouseListener b : flor2.getMouseListeners()) {
            flor2.removeMouseListener(b);
        }
        for (MouseListener c : flor3.getMouseListeners()) {
            flor3.removeMouseListener(c);
        }
    }

    private void removeListenersAmarelos(JLabel flor1, JLabel flor2, JLabel flor3) {
        for (MouseListener a : flor1.getMouseListeners()) {
            flor1.removeMouseListener(a);
        }
        for (MouseListener b : flor2.getMouseListeners()) {
            flor2.removeMouseListener(b);
        }
        for (MouseListener c : flor3.getMouseListeners()) {
            flor3.removeMouseListener(c);
        }
    }

    private void addListeners() {
        tabuleiro.addMouseListener(listenerColocaFlor);
    }

    private void alteraDialog(String acao) {
        remove(jp);
        jp = new JPanel();
        jp.setLayout(new FlowLayout());

        turno = new JLabel(acao);
        jp.add(turno);
        add(jp, SOUTH);
        validate();
        repaint();
    }

    //Notificadores
    @Override
    public void mudouTabuleiro() {
        tabuleiro.repaint();
    }

    @Override
    public void notificarRegiaEscuraInvalida(String acaoAtual) {
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarRemoveListenerRegiaEscura(String acaoAtual) {
        removeListener();
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarRemoveListeners() {
        removeListener();
    }

    @Override
    public void notificarMovimentoCelulaInvalido(String acaoAtual) {
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarNovaRodada() {
        addJDialog();
    }

    @Override
    public void fimDeJogo(String msgErro) {
        JOptionPane.showMessageDialog(null, msgErro);
        System.exit(0);
    }

    @Override
    public void jardineiroJunior(int player) {
        if (player != 0) {
            Jplayer = new JLabel("O Player " + player + " será o primeiro a jogar! Boa Sorte!");
            removeListener();
            if (SwingUtilities.isEventDispatchThread()) {
                jdialog.add(Jplayer);
                jdialog.validate();
                jdialog.repaint();
                startGame(Jplayer);
            }
        } else {
            Jplayer = new JLabel("FLORAÇÃO AUTOMÁTICA!!");
            jdialog.add(Jplayer);
            jdialog.validate();
            jdialog.repaint();
            controle.floracaoAutomatica();
        }

    }

    @Override
    public void notificarSapoColocado(String acaoAtual) {
        tabuleiro.removeMouseListener(listenerColocaSapo);

        tabuleiro.addKeyListener(listenerMoveCells);
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarSapoLocalErrado(String acaoAtual) {
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarJogadaAconteceu(String acaoAtual) {
        alteraDialog(acaoAtual);
        mudouTabuleiro();
    }

    @Override
    public void notificarMudancaFlor(int numero, String player) {
        switch (player) {
            case "Amarelo":
                if (numero == 1) {
                    florAmarela2.setIcon(new ImageIcon("imagens/Amarela.png"));
                    florAmarela3.setIcon(new ImageIcon("imagens/Amarela.png"));
                } else if (numero == 2) {
                    florAmarela1.setIcon(new ImageIcon("imagens/Amarela.png"));
                    florAmarela3.setIcon(new ImageIcon("imagens/Amarela.png"));
                } else {
                    florAmarela2.setIcon(new ImageIcon("imagens/Amarela.png"));
                    florAmarela1.setIcon(new ImageIcon("imagens/Amarela.png"));
                }
                break;
            case "Vermelho":
                if (numero == 1) {
                    florVermelha2.setIcon(new ImageIcon("imagens/Vermelha.png"));
                    florVermelha3.setIcon(new ImageIcon("imagens/Vermelha.png"));
                } else if (numero == 2) {
                    florVermelha1.setIcon(new ImageIcon("imagens/Vermelha.png"));
                    florVermelha3.setIcon(new ImageIcon("imagens/Vermelha.png"));
                } else {
                    florVermelha2.setIcon(new ImageIcon("imagens/Vermelha.png"));
                    florVermelha1.setIcon(new ImageIcon("imagens/Vermelha.png"));
                }
                break;
        }
    }

    @Override
    public void florAmarelaClicked() {
        removeListenersAmarelos(florAmarela1, florAmarela2, florAmarela2);
    }

    @Override
    public void florVermelhaClicked() {
        removeListenersVermelhos(florVermelha1, florVermelha2, florVermelha2);
    }

    @Override
    public void notifcarJogoIniciou() {
        controle.primeiraRodada();
        addListeners();
    }

    @Override
    public void notificarFloracaoAutomatica() {
        mudouTabuleiro();
        controle.mudaValor();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Timer t = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {

                        removeListener();
                        jdialog.dispose();
                        remove(jdialog);
                        addJDialog();
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        });
    }

    @Override
    public void notificarColocarSapo(String acao) {
        if (acao.equalsIgnoreCase("regiaEscura")) {
            tabuleiro.removeMouseListener(listenerColocaRegiaEscura);
            tabuleiro.addMouseListener(listenerColocaSapoRegia);

        } else {
            tabuleiro.removeMouseListener(listenerColocaFlor);
            tabuleiro.removeMouseListener(listenerColocaRegiaEscura);
            tabuleiro.addMouseListener(listenerColocaSapo);

        }
    }

    @Override
    public void removeListener() {
        tabuleiro.removeMouseListener(listenerColocaFlor);
        tabuleiro.removeMouseListener(listenerColocaSapo);
        tabuleiro.removeMouseListener(listenerColocaSapoRegia);
        tabuleiro.removeMouseListener(listenerColocaRegiaEscura);
        tabuleiro.removeKeyListener(listenerMoveCells);
    }

    @Override
    public void notificarColocouFlor(String acao) {
        tabuleiro.removeMouseListener(listenerColocaFlor);

        tabuleiro.addKeyListener(listenerMoveCells);
        alteraDialog(acao);
    }

    @Override
    public void notificarMoveuCelula(String acaoAtual) {
        tabuleiro.removeKeyListener(listenerMoveCells);
        alteraDialog(acaoAtual);
        tabuleiro.addMouseListener(listenerColocaRegiaEscura);

    }

    @Override
    public void notificarFlorLocalErrado(String acaoAtual) {
        alteraDialog(acaoAtual);
    }

    @Override
    public void notificarNovaRegiaEscura(String acaoAtual) {
        //alteraDialog(acaoAtual);
    }

    @Override
    public void iniciouJogo() {
        tabuleiro.setCellSelectionEnabled(true);
    }

    //Listeners
    MouseListener listenerColocaFlor = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            commandInvoker.execute(new ColocaFlor(controle, tabuleiro.getSelectedColumn(),
                    tabuleiro.getSelectedRow(), controle.getJardineiroS()));
        }
    };

    MouseListener listenerColocaSapo = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            commandInvoker.execute(new ColocaSapo(controle, tabuleiro.getSelectedColumn(), 
                    tabuleiro.getSelectedRow(), controle.getSapoClicked()));
        }
    };

    MouseListener listenerColocaSapoRegia = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            commandInvoker.execute(new ColocaSapoRegia(controle, tabuleiro.getSelectedColumn(),
                    tabuleiro.getSelectedRow(), controle.getSapoClicked()));
        }
    };

    MouseListener listenerColocaRegiaEscura = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            commandInvoker.execute(new NovaRegiaEscura(controle, tabuleiro.getSelectedColumn(), 
                    tabuleiro.getSelectedRow()));
        }
    };

    KeyListener listenerMoveCells = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent k) {
            commandInvoker.execute(new MoveNenufar(controle, tabuleiro.getSelectedRow(), 
                    tabuleiro.getSelectedColumn(), k.getKeyCode()));
        }
    };
}
