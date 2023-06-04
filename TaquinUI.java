import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaquinUI implements ActionListener {
    
    private TaquinGame game;
    private JFrame frame;
    private JPanel boardPanel;
    private JPanel DisplayPanel;
    private JButton[][] tileButtons;
    private JButton newGameButton;
    private JButton searchButton;
    static JTextArea DisplayMoves ;
    private JPanel container ;
    private JRadioButton bfsRadioBtn, dfsRadioBtn;
    private JLabel Moves ;
    private JTextArea textArea;
    public static List<String> LBFS = new ArrayList<>(); 
    public static List<String> LDFS = new ArrayList<>();
    public static int val =100; 
   

    public void shuffle(JButton b1, JButton b2) {
		String step = b2.getText();
		if (step.equals("0")) {
			b2.setText(b1.getText());
			b1.setText("0");
		}
	}

    
    public TaquinUI(int size) {
        this.game = new TaquinGame(size);
        this.frame = new JFrame("Taquin Game");
        this.boardPanel = new JPanel(new GridLayout(size, size));
        this.DisplayPanel = new JPanel(new FlowLayout());
        this.tileButtons = new JButton[size][size];
        this.newGameButton = new JButton("New Game");
        this.searchButton = new JButton("Search");
        TaquinUI.DisplayMoves= new JTextArea("HELLLO");
        this.Moves= new JLabel("Moves");
        this.container = new JPanel(new FlowLayout());
        this.textArea = new JTextArea(2, 10);
        TaquinUI.DisplayMoves = new JTextArea(20,20);

        JScrollPane scrollPane = new JScrollPane(DisplayMoves);

        textArea.setPreferredSize(new Dimension(10, 10)); 
        textArea.setText("counts");
        container.add(Moves);
        container.add(textArea);


        DisplayMoves.setBackground(Color.WHITE);
        ButtonGroup group = new ButtonGroup();
        this.bfsRadioBtn = new JRadioButton("BFS", true);
        bfsRadioBtn.addActionListener(this);;
        this.dfsRadioBtn = new JRadioButton("DFS", false);
        dfsRadioBtn.addActionListener(this);
        group.add(bfsRadioBtn);
        group.add(dfsRadioBtn);
        // Create the tile buttons and add them to the board panel
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                button.setFocusable(false);
                button.addActionListener(this);
                tileButtons[row][col] = button;
                boardPanel.add(button);
            }
        }
        
        DisplayPanel.add(scrollPane); 
        // Add the board panel to the frame
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(DisplayPanel , BorderLayout.EAST);


        // Create the control panel and add it to the frame
        JPanel controlPanel = new JPanel(new GridLayout(2, 3));
        controlPanel.setPreferredSize(new Dimension(80, 100));
        controlPanel.add(bfsRadioBtn);
        controlPanel.add(dfsRadioBtn);
        
        controlPanel.add(container);

        newGameButton.setPreferredSize(new Dimension(40, 40));
        searchButton.setPreferredSize(new Dimension(40, 40));

        controlPanel.add(newGameButton);
        controlPanel.add(searchButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Add action listeners to the control buttons
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.shuffle();
                updateUI();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (bfsRadioBtn.isSelected()) {
                    LBFS = BFS.breadthFirstSearch();
                    String moves = "";
                    for (int i = 0; i < LBFS.size(); i++) {
                        String element = LBFS.get(i);
                        game.move(element); 
                        
                        String boardState = getBoardStateAsString();
                        moves += element + "\n" + boardState + "\n";
                        updateUI();
                    }
                    DisplayMoves.setText(moves);   
                      
                } else {
                    LDFS = DFS.depthFirstSearch();
                    String moves = "";
                    for (int i = 0; i < LDFS.size(); i++) {
                        String element = LDFS.get(i);
                        game.move(element);
                        
                        String boardState = getBoardStateAsString();
                        moves += element + "\n" + boardState + "\n";
                        updateUI();
                    }
                    DisplayMoves.setText(moves); 
                }
            }
        });

        // Configure the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Shuffle the board
        game.shuffle();
        updateUI();
    }

    public void actionPerformed(ActionEvent e) {
        // Get the button that was clicked
        JButton button = (JButton) e.getSource();

        // Find the row and column of the button
        int row = -1, col = -1;
        for (int i = 0; i < tileButtons.length; i++) {
            for (int j = 0; j < tileButtons[i].length; j++) {
                if (tileButtons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1) break;
        }

        // Move the tile and update the UI
        if (game.isValidMove(row, col)) {
            game.moveTile(row, col);
            updateUI();
        }
    }

    private void updateUI() {
        // Update the text on each tile button
        for (int row = 0; row < tileButtons.length; row++) {
            for (int col = 0; col < tileButtons[row].length; col++) {
                int tileValue = game.getTileValue(row, col);
                String text = (tileValue == 0) ? "" : Integer.toString(tileValue);
                tileButtons[row][col].setText(text);
                
            }
        }
        textArea.setText("Moves: " + game.getNumMoves());
        
    }
    private String getBoardStateAsString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < tileButtons.length; row++) {
            for (int col = 0; col <tileButtons[row].length; col++) {
                sb.append(game.getTileValue(row, col));
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
