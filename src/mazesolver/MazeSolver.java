package mazesolver;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class MazeSolver {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger log = Logger.getLogger(MazeSolver.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static JButton btnGenerate;
    public static JButton btnExit;
    public static JButton btnBrowsePathPNG; // Has not been incorporated
    public static JButton btnBrowsePathSolution; //Has not been incorporated
    public static JTabbedPane tabMaze;
    public static JPanel tabCirc;
    public static JPanel tabHex;
    public static JPanel tabRect;
    public static JPanel tabTri;
    public static JTextField txtPathPNG; 
    public static JTextField txtPathSolution; 
    public static JLabel lblPathPNG;
    public static JLabel lblPathSolution;
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    /* End of Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Method to initialize all components onto the JFrame                    **
    ** This includes setting up Listeners, setting titles, location, modality,**
    ** enabling, etc.                                                         */
    public static void initComponents(JFrame frame){
        /* Initialize Swing Components/Containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        tabMaze = new JTabbedPane();
        btnBrowsePathPNG = new JButton();
        btnBrowsePathSolution = new JButton();
        btnGenerate = new JButton();
        btnExit = new JButton();
        txtPathPNG = new JTextField();
        txtPathSolution = new JTextField();
        tabRect = new JPanel();
        tabCirc = new JPanel();
        tabTri = new JPanel();
        tabHex = new JPanel();
        lblPathPNG = new JLabel();
        lblPathSolution = new JLabel();
        /* End of Initialize Swing Components/Containers ~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Frame Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Menu");
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        frame.setLocation(new Point(0, 0));
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        /* End of Frame Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Tabbed Container Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        tabMaze.setPreferredSize(new Dimension(400, 300));
        /* End of Tabbed Container Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Button Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        btnBrowsePathPNG.setText("Browse");
        btnBrowsePathPNG.setMinimumSize(new Dimension(90,30));
        btnBrowsePathPNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnBrowsePathPNGActionPerformed(evt);
            }     
        });
        
        btnBrowsePathSolution.setText("Browse");
        btnBrowsePathSolution.setMinimumSize(new Dimension(90,30));
        btnBrowsePathSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnBrowsePathSolutionActionPerformed(evt);
            }
        });

        btnGenerate.setText("Generate");
        btnGenerate.setMinimumSize(new Dimension(90, 30));
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.setMinimumSize(new Dimension(90, 30));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        /* End of Button Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Text Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        txtPathPNG.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtPathPNG.setMinimumSize(new Dimension(130, 15));
        txtPathPNG.setMaximumSize(new Dimension(130, 30));

        
        txtPathSolution.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        txtPathSolution.setMinimumSize(new Dimension(130, 15));
        txtPathSolution.setMaximumSize(new Dimension(130, 30));
        /* End of Text Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Label Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        lblPathPNG.setFont(new Font("Courier", Font.BOLD, 10));
        lblPathPNG.setText("PATH-Maze.PNG");
        lblPathPNG.setMinimumSize(new Dimension(80, 30));
        
        lblPathSolution.setFont(new Font("Courier", Font.BOLD, 10));
        lblPathSolution.setText("PATH-Solution");
        lblPathSolution.setMinimumSize(new Dimension(80, 30));
        /* End of Label Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Tab Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /* ********************************************************************/
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout tabRectLayout = new GroupLayout(tabRect);
        tabRect.setLayout(tabRectLayout);
        tabRectLayout.setHorizontalGroup( //Horizontal Group
            tabRectLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tabRectLayout.createSequentialGroup()
                .addGap(100)
                .addComponent(btnGenerate)
                .addGap(20)
                .addComponent(btnExit)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(tabRectLayout.createSequentialGroup()
                .addGap(25)
                .addComponent(lblPathPNG)
                .addGap(20)
                .addComponent(txtPathPNG)
                .addGap(20)
                .addComponent(btnBrowsePathPNG)
                .addContainerGap(25, Short.MAX_VALUE)
                )
            .addGroup(tabRectLayout.createSequentialGroup()
                .addGap(25)
                .addComponent(lblPathSolution)
                .addGap(20)
                .addComponent(txtPathSolution)
                .addGap(20)
                .addComponent(btnBrowsePathSolution)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        tabRectLayout.setVerticalGroup( //Vertical Group
            tabRectLayout.createParallelGroup(GroupLayout.Alignment.LEADING) 
            .addGroup(GroupLayout.Alignment.TRAILING, tabRectLayout.createSequentialGroup() 
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(tabRectLayout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
                    .addComponent(btnGenerate)
                    .addComponent(btnExit))
                .addGap(50))
            .addGroup(GroupLayout.Alignment.TRAILING, tabRectLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(tabRectLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPathPNG)
                    .addComponent(txtPathPNG)
                    .addComponent(btnBrowsePathPNG))
                .addGap(200))
            .addGroup(GroupLayout.Alignment.TRAILING, tabRectLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(tabRectLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPathSolution)
                    .addComponent(txtPathSolution)
                    .addComponent(btnBrowsePathSolution))
                .addGap(150))
        );

        tabMaze.addTab("Rectangular", tabRect);
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~End of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Circ~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout tabCircLayout = new GroupLayout(tabCirc);
        tabCirc.setLayout(tabCircLayout);
        tabCircLayout.setHorizontalGroup(
            tabCircLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        tabCircLayout.setVerticalGroup(
            tabCircLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        tabMaze.addTab("Circular", tabCirc);
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~End of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Tri~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout tabTriLayout = new GroupLayout(tabTri);
        tabTri.setLayout(tabTriLayout);
        tabTriLayout.setHorizontalGroup(
            tabTriLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        tabTriLayout.setVerticalGroup(
            tabTriLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        tabMaze.addTab("Triangular", tabTri);
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~End of Tri~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Hex~~~~~~`~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout tabHexLayout = new GroupLayout(tabHex);
        tabHex.setLayout(tabHexLayout);
        tabHexLayout.setHorizontalGroup(
            tabHexLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        tabHexLayout.setVerticalGroup(
            tabHexLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        tabMaze.addTab("Hexagonal", tabHex);
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~End of Hex~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
       
        /* "Circular", "Triangular", and "Hexagonal" tabs' enabled boolean
        ** has been set to FALSE at the moment. As of right now, no
        ** code or logic has been created for these types of mazes.
        */
        tabMaze.setEnabledAt(1, false);
        tabMaze.setEnabledAt(2, false);
        tabMaze.setEnabledAt(3, false);
        /**********************************************************************/
        /* End of Tab Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Frame Layout Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabMaze, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabMaze, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        frame.pack();
        frame.setLocationRelativeTo(null);
        /* End of Frame Layout Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }
    
    //When btnBrowsePathSolution is clicked perform an event
    public static void btnBrowsePathSolutionActionPerformed(ActionEvent evt){
        System.out.println("btnBrowsesol");
    };

    
    //When btnBrowsePathPNG is clicked perform an avent
    public static void btnBrowsePathPNGActionPerformed(ActionEvent evt){
        System.out.println("btnBrowsesol");

    }
    
    //When btnGenerate is clicked perform an event
    public static void btnGenerateActionPerformed(ActionEvent evt){
        System.out.println("btnBrowsesol");

    }
    
    //When btnExit is clicked perform an event
    public static void btnExitActionPerformed(ActionEvent evt){
        System.out.println("btnBrowsesol");

    }
    
    /* I am unfamiliar with good coding practice; however, I believe proper   **
    ** coding practice states to remove logic from the main class and method. **
    ** As a result, I have created different class(es) to organize and        **
    ** properly encapsulate the 'Logic' portion of the task.                  **
    ** Everything found in the main class will either call another object to  **
    ** handle the logic and algorithm, or handle the GUI.                     */
    public static void main(String[] args) throws InterruptedException { 
        
        //Create a JFrame and intialize components in JFrame
        JFrame frame = new JFrame();
        
        
        initComponents(frame);
        frame.setVisible(true);       
    }
    
}
