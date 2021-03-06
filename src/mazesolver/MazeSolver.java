package mazesolver;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import mazesolver.Algorithms.Algorithm;
import mazesolver.Algorithms.BFS;
import mazesolver.Algorithms.DFS;
import mazesolver.Containers.Graph;
import mazesolver.Containers.Node;
import mazesolver.Mazes.RectMaze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Andrew Hyun
 */
public class MazeSolver {
    /* Define and Initialize Logger Variable -- For Debugging ~~~~~~~~~~~~~~~~*/
    final static Logger LOG = LogManager.getLogger(MazeSolver.class.getName());
    /* End of Definition/Initialization of Logger Variable ~~~~~~~~~~~~~~~~~~~*/
    
    /* Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static Algorithm alg;
    private static File filePNG;
    private static String solutionName;
    private static JFrame frame;
    private static JFileChooser fcPNG;
    private static JFileChooser fcFolder;
    private static JButton btnGenerate;
    private static JButton btnExit;
    private static JButton btnBrowsePathPNG;
    private static JTabbedPane tabMaze;
    private static JPanel tabCirc;
    private static JPanel tabHex;
    private static JPanel tabRect;
    private static JPanel tabTri;
    private static JTextField txtPathPNG; 
    private static JTextField txtPathSolution; 
    private static JLabel lblPathPNG;
    private static JLabel lblPathSolution;
    private static JLabel lblAlgorithm;
    private static ButtonGroup buttonGroup;
    private static JRadioButton radioDFS;
    private static JRadioButton radioBFS;
    /* End of Declare Variables ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* Method to initialize all components onto the JFrame                    **
    ** This includes setting up Listeners, setting titles, location, modality,**
    ** enabling, etc.                                                         */
    public static void initComponents(JFrame frame){
        /* Initialize Swing Components/Containers ~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        fcPNG = new JFileChooser();
        fcFolder = new JFileChooser();
        tabMaze = new JTabbedPane();
        btnBrowsePathPNG = new JButton();
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
        lblAlgorithm = new JLabel();
        radioBFS = new JRadioButton();
        radioDFS = new JRadioButton();
        buttonGroup = new ButtonGroup();
        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());
        /* End of Initialize Swing Components/Containers ~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Frame Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Menu");
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        frame.setLocation(new Point(0, 0));
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setUndecorated(true);
        /* End of Frame Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* JFileChooser Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        fcPNG.setCurrentDirectory(new File("./src/res/Mazes/Rectangular"));
        fcPNG.setDialogTitle("Choose Rectangular Maze.PNG");
        fcPNG.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fcPNG.setFileFilter(imageFilter);
        
        fcFolder.setCurrentDirectory(new File("./src/res/Mazes/Rectangular"));
        fcFolder.setDialogTitle("Choose Folder to store solution output");
        fcFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        /* End of JFileChooser Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Tabbed Container Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        tabMaze.setPreferredSize(new Dimension(400, 300));
        /* End of Tabbed Container Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Radio/Button Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        btnBrowsePathPNG.setText("Browse");
        btnBrowsePathPNG.setMinimumSize(new Dimension(90,30));
        btnBrowsePathPNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnBrowsePathPNGActionPerformed(evt);
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
        
        radioBFS.setText("BFS");
        
        radioDFS.setText("DFS");
        
        /* This will maintain selection state of the JRadioButtons -- only    **
        ** one button will be selected at a time.                             */
        buttonGroup.add(radioBFS);
        buttonGroup.add(radioDFS);
        
        /* End of Radio/Button Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
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
        lblPathSolution.setText("Output Name: ");
        lblPathSolution.setMinimumSize(new Dimension(80, 30));
        
        lblAlgorithm.setFont(new Font("Courier", Font.BOLD, 10));
        lblAlgorithm.setText("Algorithm: ");
        lblAlgorithm.setMinimumSize(new Dimension(80, 30));
        /* End of Label Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* Tab Settings ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /* ********************************************************************/       
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* NOTE: The first tabbed index, which is the RECTANGLE tab will be   **
        ** initialized first. As a result, the following code will initialize **
        ** the tab and any other tab selection will be handled by events and  **
        ** listeners.                                                         */
        GroupLayout tabRectLayout = new GroupLayout(tabRect);
        tabRect.setLayout(tabRectLayout);
        tabRectLayout.setHorizontalGroup(                                       //Horizontal Group
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
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(tabRectLayout.createSequentialGroup()
                .addGap(25)
                .addComponent(lblAlgorithm)
                .addGap(20)
                .addComponent(radioBFS)
                .addGap(20)
                .addComponent(radioDFS)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        tabRectLayout.setVerticalGroup(                                         //Vertical Group
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
                    .addComponent(lblAlgorithm)
                    .addComponent(radioBFS)
                    .addComponent(radioDFS))
                    .addGap(100))
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
                    .addComponent(txtPathSolution))
                .addGap(150))
        );

        tabMaze.addTab("Rectangular", tabRect);
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~End of Rect~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~Start of Circ~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
        
        tabMaze.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                tabStateChanged(changeEvent);
            }
        });
       
        /* "Triangular", and "Hexagonal" tabs' enabled boolean
        ** has been set to FALSE at the moment. As of right now, no
        ** code or logic has been created for these types of mazes.
        */
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
    
    /* Event Handling ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    //When btnBrowsePathPNG is clicked perform an avent
    public static void btnBrowsePathPNGActionPerformed(ActionEvent evt){
        LOG.debug("Entering MazeSolver.btnBrowsePathPNGActionPerformed()...");
        
        try{
            fcPNG.showOpenDialog(btnBrowsePathPNG);
            if(fcPNG.getSelectedFile() != null){
                LOG.info("File selected. PNG Textfield has been updated");
                filePNG = fcPNG.getSelectedFile();
                txtPathPNG.setText(filePNG.getAbsolutePath());
            }
            else{
                LOG.info("No File was selected -- no update has occured to PNG Textfield.");
            }
        }catch(Exception ex){
            LOG.error("Error has occured: " + ex.toString());
        }
        
        LOG.debug("Exiting MazeSolver.btnBrowsePathPNGActionPerformed()...");
    }
    
    //When btnGenerate is clicked perform an event
    public static void btnGenerateActionPerformed(ActionEvent evt){
        LOG.debug("Entering MazeSolver.btnGenerateActionPerformed()...");
        if(!txtPathPNG.getText().equals("")){
            if(!txtPathSolution.getText().equals("")){
                if(filePNG != null){
                    solutionName=txtPathSolution.getText();
                    Graph graph = new Graph(filePNG, solutionName, RectMaze.class.getName());
                    Node[] nodeStartExit = graph.createGraph();
                    if(radioBFS.isSelected()){
                        alg = new BFS(nodeStartExit[0], nodeStartExit[1]);
                    }
                    else if(radioDFS.isSelected()){
                        alg = new DFS(nodeStartExit[0], nodeStartExit[1]);
                    }else{
                        alg = null;
                    }
                    if(alg != null){
                        alg.findSolution();
                        LOG.info("Algorithm Took: " + alg.getTime() + " seconds." );
                        graph.drawMaze();
                    }
                    else{
                        LOG.warn("Failed to identify algorithm -- No algorithm was selected.");
                    }
                }
                else{
                    LOG.warn("Failed to obtain a file -- File was NULL");
                }
            }
            else{
                LOG.warn("The textfield for the solution folder dir is empty");
            }
        }
        else{
            LOG.warn("The textfield for the PNG path is empty");
        }
        
        LOG.debug("Exiting MazeSolver.btnGenerateActionPerformed()...");
    }
    
    //When btnExit is clicked perform an event
    public static void btnExitActionPerformed(ActionEvent evt){
        LOG.debug("Entering MazeSolver.btnExitActionPerformed()...");
        
        frame.dispose();
        
        LOG.debug("Exiting MazeSolver.btnExitActionPerformed())...");
    }
    
    //When tab changes
    public static void tabStateChanged(ChangeEvent changeEvent){
        LOG.debug("Entering MazeSolver.tabStateChanged()...");
        
        //Get which tab was selected to "move" JComponents over
        int currentTabIndex = tabMaze.getSelectedIndex();
        /* Start of MOVING JCOMPONENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        if(currentTabIndex == 0){                                               //Rectangle Tab Settings
            /* Start of RECTANGLE TAB SETTINGS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
            GroupLayout tabRectLayout = new GroupLayout(tabRect);
            tabRect.setLayout(tabRectLayout);
            tabRectLayout.setHorizontalGroup(                                   //Horizontal Group
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
                    .addContainerGap(25, Short.MAX_VALUE))
                .addGroup(tabRectLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(lblAlgorithm)
                    .addGap(20)
                    .addComponent(radioBFS)
                    .addGap(20)
                    .addComponent(radioDFS)
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
                        .addComponent(lblAlgorithm)
                        .addComponent(radioBFS)
                        .addComponent(radioDFS))
                        .addGap(100))
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
                        .addComponent(txtPathSolution))
                    .addGap(150))
            );
            /* End of RECTANGLE TAB SETTINGS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        }
        else if(currentTabIndex == 1){                                          //Circle Tab Settings
            /* Start of CIRCLE TAB SETTINGS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
            GroupLayout tabCircLayout = new GroupLayout(tabCirc);
            tabCirc.setLayout(tabCircLayout);
            tabCircLayout.setHorizontalGroup(                                   //Horizontal Group
                tabCircLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(tabCircLayout.createSequentialGroup()
                    .addGap(100)
                    .addComponent(btnGenerate)
                    .addGap(20)
                    .addComponent(btnExit)
                    .addContainerGap(25, Short.MAX_VALUE))
                .addGroup(tabCircLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(lblPathPNG)
                    .addGap(20)
                    .addComponent(txtPathPNG)
                    .addGap(20)
                    .addComponent(btnBrowsePathPNG)
                    .addContainerGap(25, Short.MAX_VALUE)
                    )
                .addGroup(tabCircLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(lblPathSolution)
                    .addGap(20)
                    .addComponent(txtPathSolution)
                    .addContainerGap(25, Short.MAX_VALUE))
                .addGroup(tabCircLayout.createSequentialGroup()
                    .addGap(25)
                    .addComponent(lblAlgorithm)
                    .addGap(20)
                    .addComponent(radioBFS)
                    .addGap(20)
                    .addComponent(radioDFS)
                    .addContainerGap(25, Short.MAX_VALUE))
            );
            tabCircLayout.setVerticalGroup(
                tabCircLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, tabCircLayout.createSequentialGroup() 
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addGroup(tabCircLayout.createParallelGroup(GroupLayout.Alignment.BASELINE) 
                        .addComponent(btnGenerate)
                        .addComponent(btnExit))
                    .addGap(50))
                .addGroup(GroupLayout.Alignment.TRAILING, tabCircLayout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addGroup(tabCircLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlgorithm)
                        .addComponent(radioBFS)
                        .addComponent(radioDFS))
                        .addGap(100))
                .addGroup(GroupLayout.Alignment.TRAILING, tabCircLayout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addGroup(tabCircLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPathPNG)
                        .addComponent(txtPathPNG)
                        .addComponent(btnBrowsePathPNG))
                    .addGap(200))
                .addGroup(GroupLayout.Alignment.TRAILING, tabCircLayout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addGroup(tabCircLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPathSolution)
                        .addComponent(txtPathSolution))
                    .addGap(150))
            );
            /* End of CIRCLE TAB SETTINGS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        }
        /* End of MOVING JCOMPONENTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        
        //Reset the JComponent information
        txtPathPNG.setText("");
        txtPathSolution.setText("");
        buttonGroup.clearSelection();
        
        LOG.debug("Exiting MazeSolver.tabStateChanged()...");
    }
    /* End of Event Handling ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    
    /* I am unfamiliar with good coding practice; however, I believe proper   **
    ** coding practice states to remove logic from the main class and method. **
    ** As a result, I have created different class(es) to organize and        **
    ** properly encapsulate the 'Logic' portion of the task.                  **
    ** Everything found in the main class will either call another object to  **
    ** handle the logic and algorithm, or handle the GUI.                     */
    public static void main(String[] args) throws InterruptedException { 
        //Intialize frame and its components and make visible when complete      
        frame = new JFrame();
        initComponents(frame);    
        frame.setVisible(true);    
    }
    
}
