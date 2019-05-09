import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MovingButton extends Frame {
    JButton myButton;
    int screenX, screenY;

    public MovingButton() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = (int) screenSize.getWidth();
        screenY = (int) screenSize.getHeight();

        setLayout(null);
        setSize(screenX, screenY);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        myButton = new JButton("Click me!");
        myButton.setSize(150, 40);
        int x = screenX / 2 - 150;
        int y = screenY / 2 - 40;
        myButton.setLocation(x, y);
        add(myButton);
        setVisible(true);

        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = myButton.getWidth();// + 18;
                int height = myButton.getHeight();// + 42;
                int x = myButton.getX();    //pobieram wsp x
                int y = myButton.getY();    //pobieram wsp y

                Random rand = new Random(); //losowanie miejsca w ktorym ma sie znalezc
                do {
                    x = rand.nextInt(screenX - width);
                    y = rand.nextInt(screenY - height);
                } while (x == myButton.getX() && y == myButton.getY()); //zeby nie losowalo tych samych polozen

                int X = myButton.getX();
                int Y = myButton.getY();

                myButton.setLocation(X, Y);
                setVisible(true);

                int i=0;
                do {

                    if (X > x && Y > y) {
                        X--;
                        Y--;
                    }
                    if (X > x && Y < y) {
                        X--;
                        Y++;
                    }
                    if (X < x && Y > y) {
                        X++;
                        Y--;
                    }
                    if (X < x && Y < y) {
                        X++;
                        Y++;
                    }

                    i++;
                    if(i%5==0) {
                        setVisible(false);
                    }

                    myButton.setLocation(X, Y);
                    setVisible(true);

                    try {
                        Thread.sleep(100);                 //5000 milliseconds is five seconds.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                }while(X != x && Y != y);

                setVisible(false);
                setVisible(true);
            }
        });
    }

    public static void main(String args[]){
        new MovingButton();
    }
}
