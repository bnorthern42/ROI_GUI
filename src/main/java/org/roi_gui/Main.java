package org.roi_gui;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Scanner;  // Import the Scanner class
import org.roi_gui.Background.*;

public class Main extends JFrame{

    private Double roi(Double rate, Double start){
      //  System.out.println((rate/100)+1);1
        return (((rate/100)+1)*start);
    }


    public Main(Double starting) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
     //   UIManager.setLookAndFeel("Nimbus");
        //headers for the table
        String[] columns = new String[] {
                "% gain", "sell price","$ gain"
        };
        Double[] arrs = new Double[2006];
        int num_p = 2006;
        Double curr = 0.0;
        for (int i = 0; i < num_p; i++) {

            arrs[i] = curr;
            if(curr <= 0.9){
                curr+=0.1;
            }else {
                curr += 0.2;
            }
        }

        //actual data for the table in a 2d array
        Object[][] data = new Object[2006][3];// {
               // {0.0, String.format("%.2f",roi(0.0,starting)) },
            int size = arrs.length;
        for (int r = 0; r < size; r++) {
            String temp = String.format("%.2f", arrs[r]);
                data[r][0] = temp;//arrs[r];
                double roiv = roi(arrs[r],starting);
                data[r][1] = String.format("%.8f",roiv);
                double gain = roiv-starting;
            data[r][2] = String.format("%.8f",gain);

            System.out.println(data[r][0] + " :: " + data[r][1] );

              // 0.0, String.format("%.2f",roi(0.0,starting)) }
        }
   //      //create table with data





        JTable table = new JTable(data, columns);
       //  table.getDefaultRenderer(Object.class);
       //  table.setBackground(new Color(0,0,0,0));
      //  table.setGridColor(Color.CYAN);
      //  table.setForeground(Color.RED);
      //  this.setUndecorated(true);
                table.setFont(new Font("Serif", Font.BOLD, 18));
        this.setAlwaysOnTop(true);
        //add the table to the frame
        this.add(new JScrollPane(table));
        table.setDefaultEditor(Object.class, null);
        this.setTitle("Simple ROI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setVisible(true);
    }


    public static void main(String[] args) throws IOException {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            System.out.println("AVAILABLE");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("not avail"); // If Nimbus is not available, you can set the GUI to another look and feel.
                }
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                System.out.println("starting value: ");

                Double start = myObj.nextDouble();  // Read user input

                try {
                    new Main(start);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        }
    }



