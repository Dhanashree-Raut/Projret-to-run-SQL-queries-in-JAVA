import java.awt.*;                  
import java.awt.event.*;
import java.sql.*;

class MyFrame1 extends Frame implements ActionListener
{
    Button b1,b2;
    TextArea ta;
    TextField tf;
    Label l1;
    Label l2;
    Font f;
    Color col;
    
    MyFrame1()
    {
        setVisible(true);
        setLayout(null);
        setTitle("SQl Querys (DML And DQL)");
        setSize(560,730); 
        setLayout(null);
        col =new Color(196,196,196);
        setBackground(col);
        
        f = new Font("Courier New",Font.BOLD,16);
        
        b1 = new Button("RUN");
        b1.setFont(f);
        b2 = new Button("Exit");
        b2.setFont(f);
        ta = new TextArea(10,40);
        ta.setFont(f);
        ta.setEditable(false);
        tf = new TextField(40);
        tf.setFont(f);
        l1 = new Label("Enter Query:");
        l1.setFont(f);
        l2 = new Label("Output:"); 
        l2.setFont(f);
        
        l1.setBounds(30,30,100,50);
        tf.setBounds(30,80,500,300);
        l2.setBounds(30,380,100,50);
        ta.setBounds(30,430,500,200);
        b1.setBounds(30,640,100,50);
        b2.setBounds(430,640,100,50);
        
        
        add(l1);      add(tf);
        add(l2);      add(ta);
        add(b1);      add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
       addWindowListener(new WindowAdapter()
       {  
            public void windowClosing(WindowEvent e) 
            {  
                dispose();  
            }  
        }               
                        );      
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==b1)
        {
        try 
        {        
            Connection con= DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Raut\\Desktop\\JDBC\\Student.accdb");
            try
            {
                ta.setText("");
                Statement st= con.createStatement();
                
                String s = tf.getText();
                s=s.toUpperCase();
                
                if(!s.matches("(.*)SELECT(.*)") )
                {
                    int rs1= st.executeUpdate(tf.getText());
                    ta.setText(rs1+" Rows Affected");        
                }
                else
                {
                    ResultSet rs= st.executeQuery(tf.getText());
                    ta.append("\n Rollno\tName");
                    ta.append("\n----------------------------------");
              
                    while(rs.next())
                    {     
                        ta.append("\n  "+ rs.getInt("rollno")+"\t | \t"+rs.getString("name")); 
                    } 
                }             
            }       
            catch(Exception e1)
            {   
                ta.setText("Error!!\nInvalid Query![Please enter DML or DQL query Only.]");
            }
        }
        catch(Exception e1)
        {
            System.out.println(e1.getMessage());
        }
        }
        else
        {
            System.exit(0);
        }
    }
}

class SqlProject
{
    public static void main(String[] args) 
    {
        MyFrame1 f = new MyFrame1();   
    }   
}
