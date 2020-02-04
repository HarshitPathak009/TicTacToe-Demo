//edit use action listener instead of mouse listener
//see the conditions again
//see font change
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class TicTacToeDemo extends JFrame implements ActionListener
{
   char player[]={'X','O'};
   int moves=0,s[]={0,0};
  JPanel p = new JPanel();
  JPanel p1 = new JPanel();
  JPanel p3 = new JPanel();
  JButton b[][] = new JButton[3][3];
  JButton reset; 
  JLabel chance[] = new JLabel[2];
  JLabel score[] = new JLabel[2];
  public void frame()
  {
    setVisible(true);
   setSize(900,1050);
    setResizable(false);//doesnt allow user to change the size of frame
    setLayout(new BorderLayout());
    setDefaultCloseOperation(EXIT_ON_CLOSE);//closes the program when close is pressed on the frame
    setButton();
    add(p, BorderLayout.NORTH);//add panel
    add(p3, BorderLayout.SOUTH);//add panel
    add(p1, BorderLayout.CENTER);//add panel
    p1.setLayout(new GridLayout(3,3));
    p.setLayout(new GridLayout(1,3));	
    p3.setLayout(new GridLayout(1,2));
  }
  public void setButton() 
  {
    reset = new JButton("Reset");
    reset.setBackground(Color.RED);
    chance[0] = new JLabel("Player 1: X");
    chance[1] = new JLabel("Player 2: O");
    score[0] = new JLabel("Player 1 SCORE: 0");
    score[1] = new JLabel("Player 2 SCORE: 0");
    p3.add(score[0]);
    p3.add(score[1]);
    p.add(reset);
    p.add(chance[0]);
    p.add(chance[1]);
    reset.setActionCommand("Reset");
    reset.addActionListener(this);
    reset.setFont(new Font("Arial",Font.PLAIN,36));
    chance[0].setBackground(Color.YELLOW);
    chance[0].setFont(new Font("Arial",Font.BOLD,28));
    chance[0].setOpaque(true);
    chance[1].setBackground(Color.GREEN);
    chance[1].setFont(new Font("Arial",Font.BOLD,28));
    chance[1].setOpaque(true);//to display the colour required
    score[0].setBackground(Color.YELLOW);
    score[0].setFont(new Font("Arial",Font.BOLD,28));
    score[0].setOpaque(true);
    score[1].setBackground(Color.GREEN);
    score[1].setFont(new Font("Arial",Font.BOLD,28));
    score[1].setOpaque(true);//to display the colour required
        //add(reset);  
        //add(chance[0]);  
        //add(chance[1]); 
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
      {
        b[i][j] = new JButton(""); 
        b[i][j].setActionCommand(i+" "+j);
        b[i][j].addActionListener(this);  
        p1.add(b[i][j]);
        //add(b[i][j]);
        b[i][j].setFont(new Font("Arial",Font.BOLD,142));// font name, type-plain/bold/italics,  size
      }
  }
  public static void main(String ar[])
  {
    TicTacToeDemo obj = new TicTacToeDemo();
    obj.frame();  
  }
public boolean check(int i, int j)
{
  if(b[i][j].getText().equals(""))
   return true;
  else
   return false;
}
public void remove()
{
    for(int a=0;a<3;a++)
    {
     for(int c=0;c<3;c++)
     b[a][c].removeActionListener(this);
     }
}
public void startagain()
{
     moves=0;
    chance[0].setText("Player 1: X");
    chance[1].setText("Player 2: O");
    for(int i=0;i<3;i++)
      for(int j=0;j<3;j++)
      {
        b[i][j].addActionListener(this);
        b[i][j].setText("");
        b[i][j].setBackground(new JButton().getBackground());
      }
}
public boolean win(int i, int j)
{
    if(b[i][j].getText().equals(b[i][(j+1)%3].getText())&&b[i][j].getText().equals(b[i][(j+2)%3].getText()))
    {
      if(moves%2==0)
      {
        for(int m=0;m<3;m++)
        b[i][(j+m)%3].setBackground(Color.GREEN);
      }
      else
      {
        for(int m=0;m<3;m++)
        b[i][(j+m)%3].setBackground(Color.YELLOW);
      }      
      return true;
     }
    else if(b[i][j].getText().equals(b[(i+2)%3][j].getText())&&b[i][j].getText().equals(b[(i+1)%3][j].getText()))
     {
      if(moves%2==0)
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][j].setBackground(Color.GREEN);
      }
      else
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][j].setBackground(Color.YELLOW);
      }     
       return true;
     }
    else if((i==j)&&(b[i][j].getText().equals(b[(i+1)%3][(j+1)%3].getText())&&b[i][j].getText().equals(b[(i+2)%3][(j+2)%3].getText())))
    {
      if(moves%2==0)
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][(j+m)%3].setBackground(Color.GREEN);
      }
      else
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][(j+m)%3].setBackground(Color.YELLOW);
      }     
       return true;
    }
    else if((i+j==2)&&(b[i][j].getText().equals(b[(i+1)%3][(j+2)%3].getText())&&b[i][j].getText().equals(b[(i+2)%3][(j+1)%3].getText())))
    {
      if(moves%2==0)
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][(3+j-m)%3].setBackground(Color.GREEN);
      }
      else
      {
        for(int m=0;m<3;m++)
        b[(i+m)%3][(3+j-m)%3].setBackground(Color.YELLOW);
      }     
       return true;
     }
    else
      return false;
}
boolean full()
{
  for(int i=0;i<3;i++)
  {
    for(int j=0;j<3;j++)
    if(b[i][j].getText().equals(""))
     return false;
  }
  return true;
}
public void actionPerformed(ActionEvent e)
{
    if(e.getActionCommand().equalsIgnoreCase("Reset"))
      startagain();
   else{
    int i = Character.getNumericValue(e.getActionCommand().charAt(0));
    int j = Character.getNumericValue(e.getActionCommand().charAt(2));
    if(check(i,j))
     b[i][j].setText(Character.toString(player[(moves++)%2]));
    if(win(i,j)&&moves!=0)
    {
        chance[(((moves-1)%2))].setText("Player "+(((moves-1)%2)+1)+": Wins");
        chance[(((moves)%2))].setText("Player "+(((moves)%2)+1)+": Loses");
        score[(((moves-1)%2))].setText("Player"+(((moves-1)%2)+1)+": "+(++s[(((moves-1)%2))]));
        remove();
     }
    else if(full())
    {
        chance[(((moves-1)%2))].setText("Player "+(((moves-1)%2)+1)+": Draw");
        chance[(((moves)%2))].setText("Player "+(((moves)%2)+1)+": Draw");
        score[(((moves-1)%2))].setText("Player"+(((moves-1)%2)+1)+": "+(++s[(((moves-1)%2))]));
        score[(((moves)%2))].setText("Player"+(((moves)%2)+1)+": "+(++s[(((moves)%2))]));
      remove();
    }
  }
}
}