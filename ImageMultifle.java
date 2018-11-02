
package imagemultifle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
class ImageCreator
{
    public ImageIcon getImage(int i)
    {
        if(i==0)
            return new ImageIcon("n.JPG");
        if(i==1)
            return new ImageIcon("1.JPG");
        if(i==2)
            return new ImageIcon("2.JPG");
        if(i==3)
            return new ImageIcon("3.JPG");
        if(i==4)
            return new ImageIcon("4.JPG");
        if(i==5)
            return new ImageIcon("5.JPG");
        if(i==6)
            return new ImageIcon("6.JPG");
        if(i==7)
            return new ImageIcon("7.JPG");
        return new ImageIcon("8.JPG");
    }
}
class Btn
{
    JButton field;
    boolean mine,open;
    int subMine=0;
    ImageIcon a=new ImageIcon("z.JPG");
    ImageIcon b=new ImageIcon("mine.JPG");
   
    Btn()
    {
        field=new JButton(a);
        Cursor cr =new Cursor(Cursor.HAND_CURSOR);
        field.setCursor(cr);
        mine=false;
        open=false;
    }
    public void addMine(){
        mine=true;
    }
    public void open()
    {
        if(mine)
            field=new JButton(b);
        else{
            ImageCreator ic=new ImageCreator();
            field = new JButton(ic.getImage(subMine));
        }
        open=true;
    }
}
class Jbtn
{
    
    int i,j,x,y,closed=81;
    Btn[][] icon =new Btn[9][9];
    Random r=new Random();
    boolean gameEnd=false;
    Jbtn(){
        JFrame frame=new JFrame("Avoid The Mines");
        frame.setBounds(350,150,700,490);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon ig=new ImageIcon("mine.JPG");
        frame.setResizable(false);
        frame.setIconImage(ig.getImage());
        
        JPanel pan=new JPanel();
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                icon[i][j]= new Btn();
                pan.add(icon[i][j].field);
                icon[i][j].field.addActionListener(new ActionListener(){
                    int p=i;
                    int q=j;
                    public void actionPerformed(ActionEvent ae){
                        if(gameEnd)
                            return;
                        try{
                            openButon(p,q);
                        }catch(Exception e ){
                            System.out.println(e);
                        }
                        pan.removeAll();
                        for(x=0;x<9;x++){
                            for(y=0;y<9;y++){
                                pan.add(icon[x][y].field);
                            }
                        }
                        if(closed==10){
                                showAll(pan);
                                JLabel jl=new JLabel("Congratulation! You Win!");
                                jl.setForeground(Color.green);
                                pan.add(jl);
                                gameEnd=true;
                        }
                        if(icon[p][q].mine){
                            showAll(pan);
                            frame.setSize(4,4);
                            frame.setSize(700,490);
                            try{
                                Font f=new Font("saf",0,Font.BOLD);
                                JLabel jl=new JLabel("You Lose!");
                                jl.setForeground(Color.red);
                                jl.setBackground(Color.BLUE);
                                pan.add(jl);
                                gameEnd=true;
                            }catch(Exception e){
                                System.out.println("error "+e);
                            }
                        }
                        frame.setSize(4,4);
                        frame.setSize(700,490);
                    }
                });
            }
        }
        for(i=0;i<10;i++){
            x=r.nextInt(9);
            y=r.nextInt(9);
            if(icon[x][y].mine==false){
                icon[x][y].mine=true;
                addSubMine(x,y+1);
                addSubMine(x+1,y+1);
                addSubMine(x,y-1);
                addSubMine(x-1,y);
                addSubMine(x-1,y+1);
                addSubMine(x-1,y-1);
                addSubMine(x+1,y);
                addSubMine(x+1,y-1);
            }
            else 
                i--;
        }
        frame.add(pan);
        frame.setVisible(true);
    }
    public void openButon(int p, int q){
        if(p>-1 && p<9 && q>-1 && q<9 && icon[p][q].open==false){
            icon[p][q].open();
            closed--;
            if(icon[p][q].subMine==0 && icon[p][q].mine==false){
                try{openButon(p,q-1);}catch(Exception e){};
                try{openButon(p,q+1);}catch(Exception e){};
                try{openButon(p+1,q);}catch(Exception e){};
                try{openButon(p+1,q-1);}catch(Exception e){};
                try{openButon(p+1,q+1);}catch(Exception e){};
                try{openButon(p-1,q);}catch(Exception e){};
                try{openButon(p-1,q-1);}catch(Exception e){};
                try{openButon(p-1,q+1);}catch(Exception e){};
            }
        }
    }
    public void addSubMine(int p, int q)
    {
        if(p>-1 && p<9 && q>-1 && q<9)
            icon[p][q].subMine++;
    }
    public void showAll(JPanel pan)
    {
        int p,q;
        pan.removeAll();
        for(p=0;p<9;p++){
            for(q=0;q<9;q++){
                if(icon[p][q].mine)
                    icon[p][q].open();
                pan.add(icon[p][q].field);
            }
        }
    }
    
}
        
public class ImageMultifle {

    public static void main(String[] args) {
        Jbtn bn=new Jbtn();
    }
}
