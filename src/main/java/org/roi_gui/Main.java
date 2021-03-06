package org.roi_gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.Scanner;  // Import the Scanner class


public class Main extends JFrame implements ActionListener {
    JToolBar tb;
   private JButton submit;
   private JButton clear;
   private JTextField textField;
   private double start = 0.0;
   private JTable table;
   private String[] columns = new String[] {
            "% gain", "sell price","$ gain"
    };
   private boolean has_table;
   private TableColumnModel columnModel;


    private Double roi(Double rate, Double start){
      //  System.out.println((rate/100)+1);1
        return (((rate/100)+1)*start);
    }
    private Object[][] set_data(double starting) {
        Double[] arrs = new Double[2006];
        int num_p = 2006;
        Double curr = 0.0;
        for (int i = 0; i < num_p; i++) {

            arrs[i] = curr;
            if (curr <= 2.2) {
                curr += 0.1;
            } else {
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
            double roiv = roi(arrs[r], starting);
            data[r][1] = String.format("%.8f", roiv);
            double gain = roiv - starting;
            data[r][2] = String.format("%.8f", gain);


        }
        return data;
    }


    public Main(Double starting) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        tb = new JToolBar();
        submit = new JButton("Submit");
        clear  = new JButton("Clear");
        textField= new JTextField(8);
        submit.addActionListener(this);
        clear.addActionListener(this);
        tb.add(textField);
        tb.addSeparator();
        tb.add(submit);
        tb.addSeparator();
        tb.add(clear);


        this.add(tb,BorderLayout.NORTH);
        tb.setFloatable(false);



        this.setPreferredSize(new Dimension(285, 400));

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
                 double start = 0.0;
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

    /**
     * Set If statements to check each button and perform outputs
     * @param e Action event variable
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == submit){
            this.setAlwaysOnTop(true);
            if (has_table == true) {
                this.remove(this.table);
                table = null;
                columnModel = null;
                this.revalidate();
                this.repaint();
                this.setVisible(false);


            }


           start = Double.parseDouble(textField.getText());
            System.out.println(start);

            Object[][] data = set_data(start);//
            table = new JTable(data, columns);
           columnModel = table.getColumnModel();

            columnModel.getColumn(0).setPreferredWidth(60);
            columnModel.getColumn(1).setPreferredWidth(95);
            columnModel.getColumn(2).setPreferredWidth(90);


            table.setFont(new Font("Serif", Font.BOLD, 18));

            //add the table to the frame
            this.add(new JScrollPane(table));

            table.setDefaultEditor(Object.class, null);
            this.pack();
            this.setVisible(true);
            has_table = true;


        }
        if (e.getSource() == clear && has_table == true){
            System.out.println("hello");
            this.setVisible(false);
             this.remove(table);

            this.revalidate();
            this.repaint();

            this.setVisible(true);
        }

    }
}



