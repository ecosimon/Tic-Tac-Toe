import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class BoardFrame extends JPanel {
	private JButton[] button = new JButton[9];
	int counter = 0;
	
	
	public BoardFrame(int option){
		setLayout(new GridLayout(3,3));
		
		int op = option;
		
		if(op == 0){
			init2players();
		} else {
			initAI();
		} 
	}
	
	public void init2players(){
		for(int i =0; i < 9; i++){
			button[i] = new JButton();
			button[i].setText(" ");
			button[i].addActionListener(new ActionListener(){
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
								System.exit(JFrame.DISPOSE_ON_CLOSE);
							}
						} else {
							int selectedOp = JOptionPane.showConfirmDialog(null, "O Won the round!", "Options", JOptionPane.YES_NO_OPTION);
							if(selectedOp == 0){
								restartGame();
							} else {
								System.exit(JFrame.DISPOSE_ON_CLOSE);
							}
						}
					} 
					else if(isDraw()){
						int selectedOp = JOptionPane.showConfirmDialog(null, "Draw! Play again?", "Options", JOptionPane.YES_NO_OPTION);
						if(selectedOp == 0){
							restartGame();
						} else {
							System.exit(JFrame.DISPOSE_ON_CLOSE);
						}
					}
					counter++;
			
				}
			});
			add(button[i]);
		}
	}
	
	public void initAI(){
		for(int i =0; i < 9; i++){
			button[i] = new JButton();
			button[i].setText(" ");
			button[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton clicked = (JButton)e.getSource();
					
					clicked.setText("X");
					clicked.setFont(new Font("Arial", Font.BOLD, 200));
					clicked.setForeground(Color.RED);
							
					counter++;
						
					int random = (int)(Math.random()*10);
						
						while(counter%2==1){
						
							if(checkEmpty(random))
							{
								button[random].setText("O");
								button[random].setFont(new Font("Arial", Font.BOLD, 200));
								button[random].setForeground(Color.BLUE);
								counter++;
							} else {
								random = (int)(Math.random()*10);
							}
							
						}
						
						if(checkWin() == true){ // a logical error in this code, need to rethink of a way for the ai to place its text.
							if(counter %2 ==0){
								int selectedOp = JOptionPane.showConfirmDialog(null, "X Won the round!", "Options", JOptionPane.YES_NO_OPTION);
								if(selectedOp == 0){
									restartGame();
								} else {
									System.exit(JFrame.DISPOSE_ON_CLOSE);
								}
							} else {
								int selectedOp = JOptionPane.showConfirmDialog(null, "O Won the round!", "Options", JOptionPane.YES_NO_OPTION);
								if(selectedOp == 0){
									restartGame();
								} else {
									System.exit(JFrame.DISPOSE_ON_CLOSE);
								}
							}
						} 
						else if(isDraw()){
							int selectedOp = JOptionPane.showConfirmDialog(null, "Draw! Play again?", "Options", JOptionPane.YES_NO_OPTION);
							if(selectedOp == 0){
								restartGame();
							} else {
								System.exit(JFrame.DISPOSE_ON_CLOSE);
							}
						}
					}
				
			});
			add(button[i]);
		}
	}
	
	public void restartGame(){
		for(int j = 0; j<9; j++){
			button[j].setText(" ");
		}
		counter = 0;
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
	
	public boolean checkEmpty(int a){
		if(!button[a].getText().equals(" ")){
			return false;
		} return true;
	}
	
	public boolean checkAdj(int a, int b){	// returns true if x or o have adjacent letters
		if(button[a].getText().equals(button[b].getText()) && !button[a].getText().equals(" "))
			return true;
		else 
			return false;
			}
	
	public static void main(String[] args){
		Object[] options = {"2 Players", "AI"};
		int choice = JOptionPane.showOptionDialog(null, "Welcome to Simon's Tic Tac Toe!", "User Choice", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		BoardFrame window = new BoardFrame(choice);
		JFrame start = new JFrame("Tic Tac Toe");
		
		start.add(window);
		start.setSize(700,700);
		start.setVisible(true);
		start.setResizable(false);
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.setLocationRelativeTo(null);
		
	}
}