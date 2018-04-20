package edu.uga.cs1302.mytunes;
import java.awt.*; 
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 * 
 * @author abdul_000
 *
 */
public class MyTunes extends JFrame{
    private JTable                     table;
    private JTextField                 txtFilter;
    private TableModel                 model;
    private TableRowSorter<TableModel> sorter;
    private MP3Collection a;

   
    // construct the panel and components for this frame
   MyTunes( String title, String path )
    {
	
   super (title);
	a = new MP3Collection(path);

	setDefaultCloseOperation( EXIT_ON_CLOSE );
	setSize( 700, 400 );

        setMinimumSize( new Dimension( 600, 400 ) );

	// create column names
	String columns[] = { "Author", "Title", "Album", "Year"};

	// get the rows array with JTable cell values;
	// the values in each row must correspond to the column labels
	Object[][] rows = getTableData();

	//model = new DefaultTableModel( rows, columns );


	// create a TableModel with the rectangular data and column labels
	model = new DefaultTableModel( rows, columns ) {
		@Override
		public boolean isCellEditable(int row, int column) {
		    return false;
		}
	    };


	// create a table attached to the created TableModel
	table = new JTable( model );
	//	table.setEnabled(false);

	// prevent multiple rows from being selected in the table
	table.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        // create a right renderer for the Price column
        TableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        ((JLabel) rightRenderer).setHorizontalAlignment( SwingConstants.RIGHT );

        // set the price column width
        table.getColumnModel().getColumn(3).setMaxWidth( 60 );
        table.getColumnModel().getColumn(3).setCellRenderer( rightRenderer );

	// Attach a list selection listener to the table's selection model, to
	// be notified whenever the user selects a row. Use this opportunity to
	// output the view and model indices on the status label.

	

	// set up a TableRow Sorter for our table
	sorter = new TableRowSorter<TableModel>( model );
	table.setRowSorter( sorter );

	// create a scroll pane including the table
	JScrollPane scrollPane = new JScrollPane( table );

	// it's possible to set its preferred (and minimum/maximum) size
	scrollPane.setPreferredSize( new Dimension (300, 100) );

	// create a panel for buttons and filter text box
	JPanel filterPanel = new JPanel();

	// create a label for the text box
	filterPanel.add( new JLabel( "Filter:" ) );
        filterPanel.setMaximumSize( new Dimension( 1000, 350 ) );
        filterPanel.setMinimumSize( new Dimension( 800,  200 ) );

	// create a text box for the filter text
	txtFilter = new JTextField( 20 );
	txtFilter.addActionListener( new TextFieldListener() );
	filterPanel.add( txtFilter );

	// create a button to set the filter
	JButton setFilter = new JButton( "Set Filter" );
	setFilter.addActionListener( new FilterButtonListener() );
	filterPanel.add( setFilter );
	
	JButton play = new JButton( "Play" );
	play.addActionListener( new PlayButtonListener() );
	filterPanel.add( play );
	JButton stop = new JButton( "stop" );
	stop.addActionListener( new StopButtonListener() );
	filterPanel.add( stop );
	
	
	JButton cancle = new JButton( "Cancle" );
	cancle.addActionListener( new CancleButtonListener() );
	filterPanel.add( cancle );
	
	// create an empty border around the frame
	Border border = BorderFactory.createEmptyBorder( 20, 20, 20, 20 );
	//Border border = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );
	getRootPane().setBorder( border );

	// get the content pane of the frame
	Container c = getContentPane();

	// set its layout to BoxLayout along the Y-axis
        c.setLayout( new BoxLayout( c, BoxLayout.Y_AXIS ) );
        c.add( filterPanel );
        c.add( scrollPane );

	// display the frame and start processing events
	setVisible(true);
    }
     

    // this listener illustrates how to set a sorter filter
   /**
    * 
    * @author abdul_000
    *
    */
    private class FilterButtonListener 
	implements ActionListener
    {
	public void actionPerformed( ActionEvent e )
	{
	    // Install a new row filter.

	    String expr = txtFilter.getText();

	    // create a row filter for our JTable's sorter
	    sorter.setRowFilter( RowFilter.regexFilter( expr ) );

	    // if you'd like a case insensitive filtering, add (?i) in front of the filter text
	    // sorter.setRowFilter( RowFilter.regexFilter( "(?i)" + expr ) );

	    // Unsort the view.
	    sorter.setSortKeys( null );
	}
    };

    // this listener illustrates how to set a sorter filter directly with the text field box
    /**
     * 
     * @author abdul_000
     *
     */
    private class TextFieldListener 
	implements ActionListener
    {
   /**
    * 
    */
	public void actionPerformed( ActionEvent e )
	{
	    // Install a new row filter.

	    String expr = txtFilter.getText();

	    // create a row filter for our JTable's sorter
	    sorter.setRowFilter( RowFilter.regexFilter( expr ) );

	    // if you'd like a case insensitive filtering, add (?i) in front of the filter text
      // sorter.setRowFilter( RowFilter.regexFilter( "(?i)" + expr ) );

	    // Unsort the view.
	    sorter.setSortKeys( null );
	}
    };

    // this listener illustrates how to get an index of the selected row
    /**
     * 
     * @author abdul_000
     *
     */
    private class CancleButtonListener 
	implements ActionListener
    {
    	/**
    	 * 
    	 */
	public void actionPerformed( ActionEvent e )
	{
		  // Install a new row filter.

	    String expr = "";

	    // create a row filter for our JTable's sorter
	    sorter.setRowFilter( RowFilter.regexFilter( expr ) );

	    // if you'd like a case insensitive filtering, add (?i) in front of the filter text
      // sorter.setRowFilter( RowFilter.regexFilter( "(?i)" + expr ) );

	    // Unsort the view.
	    sorter.setSortKeys( null );

	}
    };
    /**
     * 
     * @author abdul_000
     *
     */
    private class StopButtonListener 
   	implements ActionListener
       {
    	/**
    	 * 
    	 */
   	public void actionPerformed( ActionEvent e )
   	{
   	
   	  
   	  a.stopPlay();
   	 
   	 
   	}
   	};
    
    /**
     * 
     * @author abdul_000
     *
     */
    private class PlayButtonListener 
 	implements ActionListener
     {
    	/**
    	 * @param e
    	 */
 	public void actionPerformed( ActionEvent e )
 	{
 	
 	
 	  MP3Player c = new MP3Player();
 	  a.stopPlay();
 	  a.startPlay(table.getSelectedRow());
 	 
 	}
 	};

    // format a 2-D Object array suitable for a JTable
 	/**
 	 * @author abdul_000
 	 * @return
 	 */
    private Object[][] getTableData()
    {
	
	return a.getTableData();

	
    }
/**
 * 
 * @param args
 */
    public static void main( String [] args )
    {
    	
    SwingUtilities.invokeLater( new Runnable() {
		@Override
		    public void run() {
		    MyTunes dowGUI = new MyTunes( "My Tunes", args[0] );
		    dowGUI.setVisible(true);
		}
	    } );
    }
}