package mazesolver;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Andrew Hyun
 */
public class MazeSolver {
    /* Declare Variables                                                      */
    public static JButton btnGenerate;
    public static JButton btnExit;
    public static JButton btnBrowsePathPNG; // Has not been incorporated
    public static JButton btnBrowsePathSolution; //Has not been incorporated
    public static JTabbedPane tabMaze;
    public static JPanel tabCirc;
    public static JPanel tabHex;
    public static JPanel tabRect;
    public static JPanel tabTri;
    public static JTextField txtPathPNG; // Has not been incorporated
    public static JTextField txtPathSolution; // Has not been incorporated
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
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
        btnBrowsePathPNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnBrowsePathPNGActionPerformed(evt);
            }     
        });
        
        btnBrowsePathSolution.setText("Browse");
        btnBrowsePathSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnBrowsePathSolutionActionPerformed(evt);
            }
        });

        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        /* End of Button Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Text Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        txtPathPNG.setFont(new Font("Times New Roman", Font.BOLD, 12));
        txtPathPNG.setSize(100, 50);
        
        txtPathSolution.setFont(new Font("Times New Roman", Font.BOLD, 12));
        txtPathSolution.setSize(100, 50);

        /* End of Text Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Tab Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /* ********************************************************************/
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        GroupLayout tabRectLayout = new GroupLayout(tabRect);
        tabRect.setLayout(tabRectLayout);
        tabRectLayout.setHorizontalGroup(
            tabRectLayout.createParallelGroup(GroupLayout.Alignment.LEADING) //Leading alignment used because we are doing it relative to left edge
            .addGroup(tabRectLayout.createSequentialGroup()
                .addGap(90)
                .addComponent(btnGenerate)
                .addGap(60)
                .addComponent(btnExit)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        tabRectLayout.setVerticalGroup(
            tabRectLayout.createParallelGroup(GroupLayout.Alignment.LEADING) //Leading alignment used because we are doing it relative to top edge
            .addGroup(GroupLayout.Alignment.TRAILING, tabRectLayout.createSequentialGroup() 
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(tabRectLayout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
                    .addComponent(btnGenerate)
                    .addComponent(btnExit))
                .addGap(50))
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
    
    };

    
    //When btnBrowsePathPNG is clicked perform an avent
    public static void btnBrowsePathPNGActionPerformed(ActionEvent evt){
    
    }
    
    //When btnGenerate is clicked perform an event
    public static void btnGenerateActionPerformed(ActionEvent evt){
        
    }
    
    //When btnExit is clicked perform an event
    public static void btnExitActionPerformed(ActionEvent evt){
        
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
        
        System.out.println("Hello");
        
    }
    
}
