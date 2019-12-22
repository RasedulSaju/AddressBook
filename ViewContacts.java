import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class ArList{
    // Components
    String name;
    String phone;
    String email;
    String address;
    //. Components


    // Constructor
    public ArList(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    //. Constructor
}

public class ViewContacts {
    // Components
    private JFrame f;
    private JButton back;
    private JPanel p1;
    private JPanel p2;
    private JScrollPane sp;
    private JTable t;
    //. Components

    public ViewContacts(){
        // Frame
        f = new JFrame("View Contacts | Address Book");
        f.setLayout(new FlowLayout());
        f.setSize(550,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //. Frame


        // Back Button Functionality
        back = new JButton("Back");

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddressBook();
                f.setVisible(false);
            }
        });
        //. Back Button Functionality


        // Array List Generate
        ArrayList<ArList> data = new ArrayList<>();

        // Table Column Names
        String[] col = {"Name", "Phone", "Email", "Street Address"};

        // Table Functionality
        try{
            FileReader fr = new FileReader("Contacts.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;

            while( (line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                String name = parts[0];
                String phone = parts[1];
                String email = parts[2];
                String address = parts[3];

                ArList details = new ArList(name, phone, email, address);
                data.add(details);
            }

            String[][] info = new String[data.size()][col.length];

            for(int i=0; i < data.size();i++){
                ArList item = data.get(i);
                info[i][0] = item.name;
                info[i][1] = item.phone;
                info[i][2] = item.email;
                info[i][3] = item.address;
            }

            t = new JTable(info, col);
            t.setAutoCreateColumnsFromModel(true);
            t.setAutoCreateRowSorter(true);
            t.setFillsViewportHeight(true);

            br.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //. Table Functionality

        // Adding Components to Frame
        p1 = new JPanel();
        p2 = new JPanel();
        p1.add(back);
        f.add(p1);

        sp = new JScrollPane(t);
        p2.add(sp);
        f.add(p2);

        f.setVisible(true);
        //. Adding Components to Frame
    }

    public static void main(String[] args) {
        new ViewContacts();
    }

}
