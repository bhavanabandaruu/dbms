package dbms;
package DBMS;
//import java.io.*;
import java.sql.*;
public class CreateTables 
{
	public static void main(String[] args)throws Exception
	{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",""
				+ "bhavana","bhavana");
		Statement st=con.createStatement();
		
		//Queries for creating tables
		
		CREATE TABLE users( user_id number(20), username 
				varchar(40), password varchar(40));
		CREATE TABLE item( item_id number(20), item_name 
				varchar(40), item_qty number(20));
				CREATE TABLE cart( cart_id number(20), cart_total 
				number(20));
				CREATE TABLE stock( item_name varchar(40), item_code 
				number(20), quantity number(20));
				CREATE TABLE billing( vat varchar(40), discount 
				varchar(40), total_amount varchar(40), paid varchar(40), 
				due varchar(40), payment_type varchar(40), 
				payment_status varchar(40));
		
		
		
		
		st.executeUpdate(sql);
		System.out.println("Table created successfully");
		con.close();
	}
}
package DBMS;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//1602-19-737-070 Bhavana b
@SuppressWarnings("serial")
public class InsertTables extends Frame implements ActionListener 
{
	itemlist il;
	Item i1,i2,i3,i4,i5,i6;
	Menu user,item,cart,billing,stock;
	Button insertButton;
	TextField useridText, usernameText, passwordText;
	TextField itemidText, itemnameText, itemquantityText;
	TextField cartidText, carttotalText;
	TextField vatText, discountText,totalamountText,paidText,dueText,paymenttypeText,paymentstatusText;
    TextField itemnameText,itemcodeText,quantityText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	
	
	//For updates
	Button updateButton;
	List itemList,ordersList;
	ResultSet rs;
	//TextField itemidText, itemnameText, itempriceText, itemquantityText;
	
	//For delete
	Button deleteRowButton;
	
	public InsertTables()
	{
		try 
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) 
		{
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB ();
	}

	public void connectToDB() 
    {
		try 
		{
		  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","arvind");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
    }
	public void buildFrame()
	{
		//Basic Frame Properties
		setTitle("Store Management System");
		setSize(500, 600);
		setVisible(true);
		
		//itemlist
		il = new itemlist();
		setitemlist(il);  
        setSize(550,500);  
        setLayout(null);  
        setVisible(true);
        
        //items
         items=new items("items");  
        
         i1=new Items("Insert items");  
         i2=new Items("Update items");  
         i3=new Items("Delete items");  
         i4=new Items("View items");
           
        items.add(i1);  
        items.add(i2);  
        items.add(i3); 
        items.add(i4);
        
        il.add(items);
        
        
        
        //Orders
        orders=new items("Orders");  
        i5=new Items("Insert Orders");
        i6=new Items("Update Orders");
        i7=new Items("Delete Orders");
        i8=new Items("View Orders");
       
        orders.add(m5);
        orders.add(m6);
        orders.add(m7);
        orders.add(m8);
        
        il.add(orders);
        
       //Registering action Listeners
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i6.addActionListener(this);
        i7.addActionListener(this);
        i8.addActionListener(this);
       
        
        
      
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String arg = ae.getActionCommand();
		if(arg.equals("Insert items"))
			this.buildGUIMenu();
		if(arg.equals("Update items"))
			this.updateMenuGUI();
		if(arg.equals("Delete items"))
			this.deleteGUIMenu();
		if(arg.equals("View items"))
			this.viewMenuGUI();	
		if(arg.equals("Insert Orders"))
			this.buildGUIOrders();
		if(arg.equals("Update Orders"))
			this.updateOrdersGUI();
		if(arg.equals("Delete Orders"))
			this.deleteGUIOrders();
		if(arg.equals("View Orders"))
			this.viewOrdersGUI();	
	}
	
	public void buildGUIitems() 
	{	
		removeAll();
		//Handle Insert Account Button
		insertButton = new Button("Insert items");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO items VALUES('" + itemidText.getText() + "', " + "'" + itemnameText.getText() + "'," + itempriceText.getText() + ",'" + itemquantityText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		itemidText = new TextField(15);
		itemnameText = new TextField(15);
		itempriceText = new TextField(15);
		itemquantityText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("item ID:"));
		first.add(itemidText);
		first.add(new Label("item Name:"));
		first.add(itemnameText);
		first.add(new Label("item Price:"));
		first.add(itempriceText);
		first.add(new Label("item quantity:"));
		first.add(itemquantityText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    
	}
	
	public void buildGUIOrders() 
	{	removeAll();	
		//Handle Insert Account Button
		insertButton = new Button("Insert Orders");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO Orders VALUES('" + orderidText.getText() + "', " + "'" + ordernameText.getText() + "'," + orderpriceText.getText() + ",'" + ordertypeText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		orderidText = new TextField(15);
		ordernameText = new TextField(15);
		orderpriceText = new TextField(15);
		ordertypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Payment Type:"));
		first.add(ordertypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);
		

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    

	}
	
	private void loaditems() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM items");
		  while (rs.next()) 
		  {
			  itemList.add(rs.getString("itemid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateitemsGUI() 
	{	
		removeAll();
		itemList = new List(6);
		loaditems();
		add(itemList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM items");
					while (il.next()) 
					{
						if (il.getString("itemid").equals(itemList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						itemidText.setText(il.getString("itemid"));
						itemnameText.setText(il.getString("itemname"));
						itempriceText.setText(il.getString("itemprice"));
						itemquantityText.setText(ril.getString("itemquantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Update items");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE items "
					+ "SET itemprice=" + itempriceText.getText()  
					+ " WHERE itemid = '" + itemList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					itemList.removeAll();
					loaditems();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		itemidText = new TextField(15);
		itemidText.setEditable(false);
		itemnameText = new TextField(15);
		itemnameText.setEditable(false);
		itempriceText = new TextField(15);
		itemquantityText = new TextField(15);
		itemquantityText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("item ID:"));
		first.add(itemidText);
		first.add(new Label("item Name:"));
		first.add(itemnameText);
		first.add(new Label("item Price:"));
		first.add(itempriceText);
		first.add(new Label("item quantity:"));
		first.add(itemquantityText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUIMenu() 
	{	
		removeAll();
	    itemList = new List(10);
		loaditems();
		add(itemList);
		
		//When a list item is selected populate the text fields
		itemList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM items");
					while (il.next()) 
					{
						if (il.getString("itemid").equals(itemList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						itemidText.setText(il.getString("itemid"));
						itemnameText.setText(il.getString("itemname"));
						itempriceText.setText(il.getString("itemprice"));
						itemquantityText.setText(il.getString("itemquantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete menu Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM items WHERE itemid = '" + itemList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					itemidText.setText(null);
					itemnameText.setText(null);
					itempriceText.setText(null);
					itemquantityText.setText(null);
					itemList.removeAll();
					loaditems();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		itemidText = new TextField(15);
		itemnameText = new TextField(15);
		itempriceText = new TextField(15);
		itemquantityText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		itemidText.setEditable(false);
		itemnameText.setEditable(false);
		itempriceText.setEditable(false);
		itemquantityText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("item ID:"));
		first.add(itemidText);
		first.add(new Label("item Name:"));
		first.add(itemnameText);
		first.add(new Label("item Price:"));
		first.add(itempriceText);
		first.add(new Label("item quantity:"));
		first.add(itemquantityText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadOrders() 
	{	   
		try 
		{
		  il = statement.executeQuery("SELECT * FROM orders");
		  while (il.next()) 
		  {
			  ordersList.add(il.getString("orderid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM orders");
					while (il.next()) 
					{
						if (il.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						orderidText.setText(il.getString("orderid"));
						ordernameText.setText(il.getString("ordername"));
						orderpriceText.setText(il.getString("orderprice"));
						ordertypeText.setText(il.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Update Order");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE items "
					+ "SET orderprice=" + orderpriceText.getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loaditems();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		orderidText = new TextField(15);
		orderidText.setEditable(false);
		ordernameText = new TextField(15);
		ordernameText.setEditable(false);
		orderpriceText = new TextField(15);
		ordertypeText = new TextField(15);
		ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	public void deleteGUIOrders()
	{
		removeAll();
	    ordersList = new List(10);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM orders");
					while (il.next()) 
					{
						if (il.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						orderidText.setText(il.getString("orderid"));
						ordernameText.setText(il.getString("ordername"));
						orderpriceText.setText(il.getString("orderprice"));
						ordertypeText.setText(il.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete orders Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM orders WHERE orderid = '" + ordersList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					orderidText.setText(null);
					ordernameText.setText(null);
					orderpriceText.setText(null);
					ordertypeText.setText(null);
					ordersList.removeAll();
					loadOrders();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		orderidText = new TextField(15);
		ordernameText = new TextField(15);
		orderpriceText = new TextField(15);
		ordertypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		orderidText.setEditable(false);
		ordernameText.setEditable(false);
		orderpriceText.setEditable(false);
		ordertypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadcart() 
	{	   
		try 
		{
		  il = statement.executeQuery("SELECT * FROM cart");
		  while (il.next()) 
		  {
			  cartList.add(il.getString("cartid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updatecartGUI() 
	{	
		removeAll();
		cartList = new List(6);
		loadcart();
		add(cartList);
		
		//When a list item is selected populate the text fields
		cartList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM cart");
					while (il.next()) 
					{
						if (il.getString("cartid").equals(cartList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						cartidText.setText(il.getString("cartid"));
						carttotalText.setText(il.getString("carttotal"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update items Button
		updateButton = new Button("Update cart");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE cart "
					+ "SET cartid=" + cartidText.getText()  
					+ " WHERE carttotal = '" + cartList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					cartList.removeAll();
					loadcart();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
	    cartidText = new TextField(15);
		cartidText.setEditable(false);
		carttotalText = new TextField(15);
		carttotalText.setEditable(false);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("cart ID:"));
		first.add(cartidText);
		first.add(new Label("cart total":"));
		first.add(carttotalText);
		
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUIcart()
	{
		removeAll();
	    cartList = new List(10);
		loadcart();
		add(cartList);
		
		//When a list item is selected populate the text fields
		cartList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM cart");
					while (il.next()) 
					{
						if (il.getString("carttid").equals(cartList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						cartidText.setText(ci.getString("cartid"));
						carttotalText.setText(rs.getString("carttotal""));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete restaurant Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM cart WHERE cartid = '" + cartList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					cartidText.setText(null);
					carttotalText.setText(null);
					cartList.removeAll();
					loadcart();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		cartidText = new TextField(15);
		carttotalText = new TextField(15);
		
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
	    cartidText.setEditable(false);
		carttotalText.setEditable(false);
		
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("cart ID:"));
		first.add(cartidText);
		first.add(new Label("cart total:"));
		first.add(carttotalText);
		
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	public void viewitemsGUI() 
	{	
		removeAll();
		itemsList = new List(6);
		loaditems();
		add(itemsList);
		
		//When a list item is selected populate the text fields
		itemsList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM items");
					while (il.next()) 
					{
						if (il.getString("itemid").equals(itemsList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						itemidText.setText(il.getString("itemid"));
						itemnameText.setText(il.getString("itemname"));
						itempriceText.setText(il.getString("itemprice"));
						itemquantityText.setText(il.getString("itemquantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Update items");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE items "
					+ "SET itemprice=" + itempriceText.getText()  
					+ " WHERE itemid = '" + itemList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					itemList.removeAll();
					loaditems();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		itemidText = new TextField(15);
		itemidText.setEditable(false);
		itemnameText = new TextField(15);
		itemnameText.setEditable(false);
		itempriceText = new TextField(15);
		itempriceText.setEditable(false);
		itemquantityText = new TextField(15);
		itemquantityText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("item ID:"));
		first.add(itemidText);
		first.add(new Label("item Name:"));
		first.add(itemnameText);
		first.add(new Label("item Price:"));
		first.add(itempriceText);
		first.add(new Label("item quantity:"));
		first.add(itemquanityText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewcartGUI() 
	{	
		removeAll();
		cartList = new List(6);
		loadcart();
		add(cartList);
		
		//When a list item is selected populate the text fields
		cartList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM cart");
					while (il.next()) 
					{
						if (il.getString("cartid").equals(cartList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						cartidText.setText(il.getString("cartid"));
						carttotalText.setText(il.getString("carttotal"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update items Button
		updateButton = new Button("Update cart");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE cart "
					+ "SET carttotal=" + carttotalText.getText()  
					+ " WHERE cartid = '" + cartList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					cartList.removeAll();
					loadcart();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		cartidText = new TextField(15);
		cartidText.setEditable(false);
		carttotalText = new TextField(15);
		carttotalText.setEditable(false);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("cart ID:"));
		first.add(cartidText);
		first.add(new Label("cart total:"));
		first.add(carttotalText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					il = statement.executeQuery("SELECT * FROM orders");
					while (il.next()) 
					{
						if (il.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!il.isAfterLast()) 
					{
						orderidText.setText(il.getString("orderid"));
						ordernameText.setText(il.getString("ordername"));
						orderpriceText.setText(il.getString("orderprice"));
						ordertypeText.setText(il.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Update Order");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE items "
					+ "SET orderprice=" + orderpriceText.getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loaditems();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		orderidText = new TextField(15);
		orderidText.setEditable(false);
		ordernameText = new TextField(15);
		ordernameText.setEditable(false);
		orderpriceText = new TextField(15);
		orderpriceText.setEditable(false);
		ordertypeText = new TextField(15);
		ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void displaySQLErrors(SQLException e) 
	{
		errorText.append("\nSQLException: " + e.getMessage() + "\n");
		errorText.append("SQLState:     " + e.getSQLState() + "\n");
		errorText.append("VendorError:  " + e.getErrorCode() + "\n");
	}

public static void main(String[] args) 
	{
		InsertTables it = new InsertTables();
		it.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			System.exit(0);
		  }
		});
		it.buildFrame();
	}
}

