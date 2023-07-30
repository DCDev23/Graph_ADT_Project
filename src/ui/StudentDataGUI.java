package ui;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jwetherell.algorithms.data_structures.Graph;

import file.StudentData;
import utility.MatrixOnesConverter;
import utility.MatrixZeroDeletion;
import file.GraphReader;
import file.ReadData;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

/**
 * Class which implements the GUI for facilitating the core
 * functionality of this project
 * 
 * @author DA Chan
 * @version miniproject
 *
 */
public class StudentDataGUI extends JFrame {
    private JLabel academicResultLabel, homeLanguageLabel, feesLabel, firstGenerationLabel, spaceLabel, familyStructureLabel;
    private JTextField academicResultField, homeLanguageField, feesField, firstGenerationField, spaceField, familyStructureField;
    private JButton addStudentButton, removeStudentButton;
    private JButton updateGraphButton;
    private JButton closenessVisualizerButton;
    private JButton kmeansVisualizerButton;
    private JButton chooseDataSetButton;
    private JFileChooser fileChooser;
    private File selectedFile;
    
    private static String FILE_PATH = " ";
    private static String DATA_PATH = " ";
    
    /**
     * Default constructor
     */
    public StudentDataGUI() {
    	
    	// Initialize UI elements
    	
        setTitle("Student Data GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Academic Result ui elements
        academicResultLabel = new JLabel("Academic Result (G,A,F):");
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 0, 5);
        add(academicResultLabel, gbc);

        academicResultField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1; 
        gbc.insets = new Insets(10, 0, 0, 10);
        add(academicResultField, gbc);

        // Home Language ui elements
        homeLanguageLabel = new JLabel("Home Language (1,2):");
        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.insets = new Insets(5, 10, 0, 5);
        add(homeLanguageLabel, gbc);

        homeLanguageField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2; 
        gbc.insets = new Insets(5, 0, 0, 10);
        add(homeLanguageField, gbc);

        // High School Fees ui elements
        feesLabel = new JLabel("High School Fees (H,L):");
        gbc.gridx = 0;
        gbc.gridy = 3; 
        gbc.insets = new Insets(5, 10, 0, 5);
        add(feesLabel, gbc);

        feesField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3; 
        gbc.insets = new Insets(5, 0, 0, 10);
        add(feesField, gbc);

        // First Generation ui elements
        firstGenerationLabel = new JLabel("First Generation (Y,N):");
        gbc.gridx = 0;
        gbc.gridy = 4; 
        gbc.insets = new Insets(5, 10, 0, 5);
        add(firstGenerationLabel, gbc);

        firstGenerationField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4; 
        gbc.insets = new Insets(5, 0, 0, 10);
        add(firstGenerationField, gbc);

        // Living Space ui elements
        spaceLabel = new JLabel("Living Space (1-10):");
        gbc.gridx = 0;
        gbc.gridy = 5; 
        gbc.insets = new Insets(5, 10, 0, 5);
        add(spaceLabel, gbc);

        spaceField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 5; 
        gbc.insets = new Insets(5, 0, 0, 10);
        add(spaceField, gbc);

        // Family Structure ui elements
        familyStructureLabel = new JLabel("Family Structure (1-3):");
        gbc.gridx = 0;
        gbc.gridy = 6; 
        gbc.insets = new Insets(5, 10, 10, 5);
        add(familyStructureLabel, gbc);

        familyStructureField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 6; 
        gbc.insets = new Insets(5, 0, 10, 10); 
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(familyStructureField, gbc);

        // Button UI elements
        
        addStudentButton = new JButton("Add Student");
        gbc.gridx = 0;
        gbc.gridy = 8; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(addStudentButton, gbc);

        removeStudentButton = new JButton("Remove Student");
        gbc.gridx = 0;
        gbc.gridy = 9; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(removeStudentButton, gbc);

        updateGraphButton = new JButton("Update Graph");
        gbc.gridx = 0;
        gbc.gridy = 10; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(updateGraphButton, gbc);

        closenessVisualizerButton = new JButton("Visualize Closeness Centrality");
        gbc.gridx = 0;
        gbc.gridy = 11; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(closenessVisualizerButton, gbc);

        kmeansVisualizerButton = new JButton("Visualize K-Means algorithm");
        gbc.gridx = 0;
        gbc.gridy = 12; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(kmeansVisualizerButton, gbc);

        
        JButton chooseDataSetButton = new JButton("Choose Data Set");
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        add(chooseDataSetButton, gbc);
        
        // Use Action Listeners to define the operations of the GUI buttons
        
        chooseDataSetButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            
            // Set the default directory of the file chooser to the current directory
            fileChooser.setCurrentDirectory(new File("data"));
            
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                // update the FILE_PATH with the selected file path
                FILE_PATH = filePath;
            }
        });
        
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        
        removeStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        
        updateGraphButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateGraph();
            }
        });
        
        
        closenessVisualizerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            	// Create a file filter to only show specific files
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text files", "txt");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(filter);

                // Set the default directory of the file chooser to the current directory
                fileChooser.setCurrentDirectory(new File("data"));

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    String filePath = selectedFile.getAbsolutePath();

                    // Check if the selected file is one of the specified files
                    if (!fileName.equals("AverageStudents.txt") && !fileName.equals("GoodStudents.txt") && !fileName.equals("FailedStudents.txt")) {
                        JOptionPane.showMessageDialog(null, "Please select one of the following files: AverageStudents.txt, GoodStudents.txt, FailedStudents.txt", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Update the DATA_PATH with the selected file path
                    DATA_PATH = filePath;
                }
     	
                GraphReader reader = new GraphReader(DATA_PATH);
                
            	int[][] adjacencyMatrix = reader.getAdjacencyMatrix();  
            	 
             	int[][] newMatrix = MatrixOnesConverter.convertToOnes(adjacencyMatrix);
            	 
                // Visualize the ui
                ClosenessGraphVisualization.visualizeGraph(newMatrix);
            }
        });

        kmeansVisualizerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
                
                // Set the default directory of the file chooser to the current directory
                fileChooser.setCurrentDirectory(new File("data"));
                
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    // update the FILE_PATH with the selected file path
                    DATA_PATH = filePath;
                }
            	
            	GraphReader reader = new GraphReader(DATA_PATH);
            	int[][] adjacencyMatrix = 
            			reader.getAdjacencyMatrix();        	    
            	
            	KMeansVisualization.visualizeGraph(adjacencyMatrix);            	
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Method to add students and their features to the Student Dataset
     */
    private void addStudent() {
    	
    	// Get the user input for each text field
        String academicResult = academicResultField.getText().trim();
        String homeLanguage = homeLanguageField.getText().trim();
        String fees = feesField.getText().trim();
        String firstGeneration = firstGenerationField.getText().trim();
        String space = spaceField.getText().trim();
        String familyStructure = familyStructureField.getText().trim();
                
        // Check if any field is empty
        if(academicResult.isEmpty() || homeLanguage.isEmpty() || fees.isEmpty() || firstGeneration.isEmpty() || space.isEmpty() || familyStructure.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check correct academicResult input
        if(!academicResult.matches("[FAG]")) {
            JOptionPane.showMessageDialog(this, "Invalid input for academic result. Please enter F, A or G.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check correct homeLanguage input
        if(!homeLanguage.matches("[12]")) {
            JOptionPane.showMessageDialog(this, "Invalid input for home language. Please enter 1 or 2.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check correct fees input
        if(!fees.matches("[HL]")) {
            JOptionPane.showMessageDialog(this, "Invalid input for high school fees. Please enter H or L.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check correct firstGeneration input
        if(!firstGeneration.matches("[YN]")) {
            JOptionPane.showMessageDialog(this, "Invalid input for first-generation student. Please enter Y or N.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check correct living/study space input
        if(!space.matches("[1-9]|10")) {
            JOptionPane.showMessageDialog(this, "Invalid input for living/study space. Please enter a number between 1 and 10.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check correct familyStructure input
        if(!familyStructure.matches("[1-3]")) {
            JOptionPane.showMessageDialog(this, "Invalid input for family structure. Please enter a number between 1 and 3.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
            
            // Find the index of the last student in the file
            int lastIndex = getLastStudentIndex();
            
            // Append the new student data to the file
            String newStudentData = String.format("%03d,%s,%s,%s,%s,%s,%s",
                    lastIndex + 1, academicResult, homeLanguage, fees, firstGeneration, space, familyStructure);
            writer.write(newStudentData);
            writer.newLine();
            
            writer.close();
            
            // Clear the input fields
            academicResultField.setText("");
            homeLanguageField.setText("");
            feesField.setText("");
            firstGenerationField.setText("");
            spaceField.setText("");
            familyStructureField.setText("");
            
            // Dialog box to indicate adding student successful
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } 
        catch (FileNotFoundException ex) {
			ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please choose data file before adding student.", "Error", JOptionPane.ERROR_MESSAGE);

		}
        catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while adding student.", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    /**
     * Accessor method for the index of the last student in a Student Dataset file
     * @return lastIndex - the index number of the last student
     */
    private int getLastStudentIndex() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        int lastIndex = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            int index = Integer.parseInt(fields[0]);
            lastIndex = Math.max(lastIndex, index);
        }
        
        reader.close();
        
        return lastIndex;
    }

    /**
     * Method to remove students from the selected Student Dataset
     * and its corresponding node and edge representation in a graph
     */
    private void removeStudent() {
        String indexString = JOptionPane.showInputDialog(this, "Enter the index of the student to remove:");
        if (indexString == null) {
            // User canceled the operation
            return;
        }
        
        try {
            int index = Integer.parseInt(indexString.trim());
            if (index <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid index value. Please enter a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            File inputFile = new File(FILE_PATH);
            File tempFile = new File("data/temp.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String line;
            boolean studentFound = false;
            
            while ((line = reader.readLine()) != null)
            {
            	String[] fields = line.split(",");
            	int studentIndex = Integer.parseInt(fields[0]);
            	
                // Check if the current line corresponds to the student to be removed
                if (studentIndex == index) {
                    studentFound = true;
                    continue; 
                }
                
                // Write the line to the temp file
                writer.write(line);
                writer.newLine();
            }
            
            reader.close();
            writer.close();
            
            // Dialog box to indicate successful removal
            JOptionPane.showMessageDialog(this, "Student removed");
            
            // Replace the original file with the temp file
            if (studentFound) {
                if (inputFile.delete()) {
                    if (!tempFile.renameTo(inputFile)) {
                        JOptionPane.showMessageDialog(this, "Error occurred while removing student.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error occurred while removing student.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not found with index: " + index, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid index value. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
			ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Please choose data file before removing student.", "Error", JOptionPane.ERROR_MESSAGE);

		}
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while removing student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Method which will create or update graphs using the selected Student Dataset,
     * convert these graphs to adjacency matrices
     * and write these matrices to specific text files
     */
    private void updateGraph() {
        StudentData[] studentData = ReadData.readStudentData(FILE_PATH);
        
        // Create linked lists to split the students into three separate lists
        LinkedList<StudentData> failed = new LinkedList<>();
        LinkedList<StudentData> average = new LinkedList<>();
        LinkedList<StudentData> good = new LinkedList<>();
        ReadData.splitStudents(studentData, failed, average, good);

        // Create a graph to represent the student similarity scores for the failed result students
        Graph<Integer> graphFailed = new Graph<Integer>();

        // Create a graph to represent the student similarity scores for the average result students
        Graph<Integer> graphAverage = new Graph<Integer>();

        // Create a graph to represent the student similarity scores for the good result students
        Graph<Integer> graphGood = new Graph<Integer>();

        // Compute Similarity Scores and create graphs for each linked list
        ReadData.computeSimilarityScore(failed, graphFailed);
        ReadData.computeSimilarityScore(average, graphAverage);
        ReadData.computeSimilarityScore(good, graphGood);

        // Store the created graphs as adjacency matrices and print out these matrices
        String adjacencyMatrixFailed =  ReadData.convertToAdjacencyMatrix(graphFailed);
        System.out.println(adjacencyMatrixFailed);

        String adjacencyMatrixAverage =  ReadData.convertToAdjacencyMatrix(graphAverage);
        System.out.println(adjacencyMatrixAverage);

        String adjacencyMatrixGood =  ReadData.convertToAdjacencyMatrix(graphGood);
        System.out.println(adjacencyMatrixGood);

        // Correct the format of the adjacency matrices
        String newMatrixFailed = MatrixZeroDeletion.deleteZeroRowsAndColumns(adjacencyMatrixFailed);
        System.out.println("New Matrix: " + "\n" + newMatrixFailed);

        String newMatrixAverage = MatrixZeroDeletion.deleteZeroRowsAndColumns(adjacencyMatrixAverage);
        System.out.println("New Matrix: " + "\n" + newMatrixAverage);

        String newMatrixGood = MatrixZeroDeletion.deleteZeroRowsAndColumns(adjacencyMatrixGood);
        System.out.println("New Matrix: " + "\n" + newMatrixGood);
        
        // Set the file names for the text files to store the graph data
        String failedFileName = "FailedStudents.txt";
        String averageFileName = "AverageStudents.txt";
        String goodFileName = "GoodStudents.txt";

        String filePath = "data";

        // Write the adjacency matrices to their respective text files
        try {
        	ReadData.createAndWriteToFile(failedFileName, filePath, newMatrixFailed);
            ReadData.createAndWriteToFile(averageFileName, filePath, newMatrixAverage);
			ReadData.createAndWriteToFile(goodFileName, filePath, newMatrixGood);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * @param args - no args since main class
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentDataGUI();
            }
        });
    }
}


