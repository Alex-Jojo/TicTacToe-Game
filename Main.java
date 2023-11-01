package TicTacToe;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JPanel principal;
    private JPanel info;
    private JPanel menu;
    private JPanel game;
    private JPanel resume;
    private CardLayout cardLayout;

    private JButton btnStart;
    private JLabel resultadoLabel;

    private JButton[][] tablero;
    private boolean turnoDeX;
    private boolean res = false;

    private BufferedImage brImage;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Main();
            } 
        });
    }
    
    public Main (){
        try{
            brImage = ImageIO.read(new File("tictac.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        setTitle("Game");
        setDefaultCloseOperation(3);
        setSize(400,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        principal = new JPanel();
        principal.setBounds(0,100,400,500);
        cardLayout = new CardLayout();
        principal.setLayout(cardLayout);
        principal.setOpaque(false);

        info = new JPanel();
        info.add(new JLabel("TIC TAC TOE"));
        info.setBounds(0,0,400,100);
        info.setOpaque(false);

        menu = new JPanel();
        //menu.setBackground(Color.DARK_GRAY);
        menu.setVisible(true);
        menu.setOpaque(false);
        game = new JPanel();
        //game.setBackground(Color.BLUE);
        game.setLayout(new GridLayout(3,3));
        game.setVisible(false);
        game.setOpaque(false);
        resume = new JPanel();
        //resume.setBackground(Color.LIGHT_GRAY);
        resume.setVisible(false);
        resume.setOpaque(false);

        principal.add(menu, "menu");
        principal.add(game,"game");
        principal.add(resume,"resume");

        resultadoLabel = new JLabel();
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultadoLabel.setBounds(60,300, 240,40);
        resultadoLabel.setVisible(false);
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (menu.isVisible()) {
                    menu.setVisible(false);
                    game.setVisible(true);
                    iniciarJuego();
                    btnStart.setVisible(false);
                    btnStart.setText("Reset");
                    System.out.println("test3");
                } else if (resume.isVisible()) {
                    game.removeAll();
                    game.revalidate();
                    game.repaint();
                    resume.setVisible(false);
                    menu.setVisible(true);
                    reiniciarJuego();
                    resultadoLabel.setVisible(false);
                    btnStart.setText("Start");
                }
            }
        });
        System.out.println("test");
        
        menu.add(btnStart);
        add(resultadoLabel);
        add(info, BorderLayout.NORTH);
        add(principal, BorderLayout.CENTER);
        add(btnStart, BorderLayout.SOUTH);
        cardLayout.show(principal, "menu");
        setVisible(true);
    }

    private void iniciarJuego() {
        // Inicializa el tablero de Tic-Tac-Toe
        tablero = new JButton[3][3];
        turnoDeX = true;

        // Crea botones para representar el tablero y agrega ActionListener
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = new JButton("");
                tablero[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                game.add(tablero[i][j]);

                final int fila = i;
                final int columna = j;

                tablero[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tablero[fila][columna].getText().equals("")) {
                            if (turnoDeX) {
                                tablero[fila][columna].setText("X");
                            } else {
                                tablero[fila][columna].setText("O");
                            }
                            turnoDeX = !turnoDeX;
            
                            String resultado = verificarGanador();
                            if (resultado.equals("X")) {
                                // X ganó
                                System.out.println("testX");
                                JOptionPane.showMessageDialog(null, "Se ha acabado el juego");
                                resultadoLabel.setText("¡Jugador X ha ganado!");
                                game.setVisible(false);
                                resume.setVisible(true);
                                btnStart.setVisible(true);
                                resultadoLabel.setVisible(true);
                                //reiniciarJuego();
                                //res = true;
                            } else if (resultado.equals("O")) {
                                // O ganó
                                System.out.println("testO");
                                JOptionPane.showMessageDialog(null, "Se ha acabado el juego");
                                resultadoLabel.setText("¡Jugador O ha ganado!");
                                game.setVisible(false);
                                resume.setVisible(true);
                                btnStart.setVisible(true);
                                resultadoLabel.setVisible(true);
                                //reiniciarJuego();
                                //res = true;
                            } else if (resultado.equals("empate")) {
                                // Empate
                                System.out.println("testEmpate");
                                JOptionPane.showMessageDialog(null, "Se ha acabado el juego");
                                resultadoLabel.setText("¡El juego terminó en empate!");
                                game.setVisible(false);
                                resume.setVisible(true);
                                btnStart.setVisible(true);
                                resultadoLabel.setVisible(true);
                                //reiniciarJuego();
                                //res = false;
                            }
                        }
                    }
                });
                
            }
        }
        System.out.println("test4");
    }

    private void reiniciarJuego() {
        // Limpia el tablero y reinicia el juego
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j].setText("");
            }
        }
        turnoDeX = true;
    }

    private String verificarGanador() {
        // Verificar filas y columnas
    for (int i = 0; i < 3; i++) {
        if (tablero[i][0].getText().equals("X") && tablero[i][1].getText().equals("X") && tablero[i][2].getText().equals("X") ||
            tablero[0][i].getText().equals("X") && tablero[1][i].getText().equals("X") && tablero[2][i].getText().equals("X")) {
            return "X";
        } else if (tablero[i][0].getText().equals("O") && tablero[i][1].getText().equals("O") && tablero[i][2].getText().equals("O") ||
                   tablero[0][i].getText().equals("O") && tablero[1][i].getText().equals("O") && tablero[2][i].getText().equals("O")) {
            return "O";
        }
    }

    // Verificar diagonales
    if (tablero[0][0].getText().equals("X") && tablero[1][1].getText().equals("X") && tablero[2][2].getText().equals("X") ||
        tablero[0][2].getText().equals("X") && tablero[1][1].getText().equals("X") && tablero[2][0].getText().equals("X")) {
        return "X";
    } else if (tablero[0][0].getText().equals("O") && tablero[1][1].getText().equals("O") && tablero[2][2].getText().equals("O") ||
               tablero[0][2].getText().equals("O") && tablero[1][1].getText().equals("O") && tablero[2][0].getText().equals("O")) {
        return "O";
    }

    // Si no hay ganador y el tablero está lleno, es un empate
    if (tableroLleno()) {
        return "empate";
    }

    // Si no se cumple ninguna de las condiciones anteriores, el juego sigue en curso
    return "en curso";
    }
    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].getText().isEmpty()) {
                    return false; // Si una casilla está vacía, el tablero no está lleno
                }
            }
        }
        return true; // el tablero está lleno
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int x = (getWidth() - brImage.getWidth()) / 2;
        int y = (getHeight() - brImage.getHeight()) / 2;
        g.drawImage(brImage,x,y,this);
    }
}
