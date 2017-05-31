import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class BoardFrame extends JFrame {
	private JButton[] button = new JButton[9];
	int counter = 0;
	
	
	public BoardFrame(){
		setTitle("TTT");
		setLayout(new GridLayout(3,3));
		setSize(1000,1000);
		setLocationRelativeTo(null); // placed after set size causes center screen, null also does this, another thing is that it positions the window to the component if not null
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		initGameboard();
	
	}
	
	public void initGameboard(){
		for(int i =0; i < 9; i++){
			button[i] = new JButton();
			button[i].setText(" ");
			button[i].addActionListener(new ButtonListener());
			
			add(button[i]);
		}
	}
	
	public void restartGame(){
		for(int j = 0; j<9; j++){
			button[j].setText(" ");
		}
		counter = 0;
	}
	
	class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			JButton clicked = (JButton)e.getSource();
			if(counter %2 == 0){
				clicked.setText("X");
				clicked.setFont(new Font("Arial", Font.BOLD, 200));
				clicked.setForeground(Color.RED);
			}else{
				clicked.setText("O");
				clicked.setFont(new Font("Arial", Font.BOLD, 200));
				clicked.setForeground(Color.BLUE);
				}
			
			if(checkWin() == true){
				if(counter %2 ==0){
					int selectedOp = JOptionPane.showConfirmDialog(null, "X Won the round!", "Options", JOptionPane.YES_NO_OPTION);
					if(selectedOp == 0){
						restartGame();
					} else {
						System.exit(DISPOSE_ON_CLOSE);
					}
				} else {
					int selectedOp = JOptionPane.showConfirmDialog(null, "O Won the round!", "Options", JOptionPane.YES_NO_OPTION);
					if(selectedOp == 0){
						restartGame();
					} else {
						System.exit(DISPOSE_ON_CLOSE);
					}
				}
			} else if(isDraw()){
				int selectedOp = JOptionPane.showConfirmDialog(null, "Draw! Play again?", "Options", JOptionPane.YES_NO_OPTION);
				if(selectedOp == 0){
					restartGame();
				} else {
					System.exit(DISPOSE_ON_CLOSE);
				}
			}
			counter++;
		}
	}
	
	public boolean checkWin(){	// checks for vertical, horizontal, diagonal win
		if(checkAdj(0,1) && checkAdj(1,2)){	// checks for horizontal
			return true;
		} else if( checkAdj(3,4) && checkAdj(4,5)){
			return true;
		} else if (checkAdj(6,7) && checkAdj(7,8)){
			return true;
		}
		
		else if(checkAdj(0,3) && checkAdj(3,6)){ // checks for vertical
			return true;
		} else if(checkAdj(1,4) && checkAdj(4,7)){
			return true;
		} else if(checkAdj(2,5) && checkAdj(5,8)){
			return true;
		}
		
		else if(checkAdj(0,4) && checkAdj(4,8)){ // checks for diagonal
			return true;
		} else if(checkAdj(2,4) && checkAdj(4,6)){ // checks for anti diag
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isDraw(){
		for(int f=0; f<9;f++)
		if(button[f].getText().equals(" ")){
			return false;
	}
		return true;
	}
	
	public boolean checkAdj(int a, int b){	// returns true if x or o have adjacent letters
		if(button[a].getText().equals(button[b].getText()) && !button[a].getText().equals(" "))
			return true;
		else 
			return false;
	}
	
	public static void main(String[] args){
		new BoardFrame();
	}
}