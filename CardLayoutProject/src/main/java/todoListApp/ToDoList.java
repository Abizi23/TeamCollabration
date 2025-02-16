package todoListApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList {

    private JMenuBar menuBar;
    private JMenuItem home,exit,addTask;
    private JMenu menu;
    private JFrame frame;
    private JPanel homePanel,addTaskPanel;
    private JPanel currentPanel;
    private DefaultListModel<String> taskListModel;

    private ArrayList<Task> listOfTasks;
    private JList<String> taskList;
    private JTextField toDoField;
    private JTextField idField;
    private JTextField dueDateField;

    ToDoList(){
        frame = new JFrame("TO DO");
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listOfTasks = new ArrayList<>();


        homePanel = new JPanel(new BorderLayout());
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        homePanel.add(new JScrollPane(taskList),BorderLayout.CENTER);


        addTaskPanel = new JPanel(new BorderLayout());

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");

        home = new JMenuItem("Home");
        exit = new JMenuItem("Exit");
        addTask = new JMenuItem("addTask");

        menu.add(home);
        menu.add(addTask);
        menu.add(exit);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        currentPanel = homePanel;

//        homePanel.add(menuBar,BorderLayout.NORTH);



        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayToDos();
                switchPanel(homePanel);
            }
        });

        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTodos();
                switchPanel(addTaskPanel);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExit();
            }
        });




        frame.add(homePanel,BorderLayout.CENTER);

        frame.setVisible(true);

    }

    private void handleExit() {
        int response = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?","Exit Confirmation",
                JOptionPane.OK_CANCEL_OPTION);

        if (response == JOptionPane.OK_OPTION){
            System.exit(0);
        }

    }

    private void addTodos() {


        String taskName = JOptionPane.showInputDialog(frame, "Enter Task Name:");
        String dueDate = JOptionPane.showInputDialog(frame, "Enter Due Date:");

        int taskID = listOfTasks.size() + 1; // inorder to automatically generate id
        Task newTask = new Task(taskID, taskName, dueDate);

        listOfTasks.add(newTask);

        displayToDos();
    }



    private void displayToDos() {

        taskListModel.clear();

        for (Task task : listOfTasks) {
            taskListModel.addElement(task.getTaskID() + ". " + task.getTaskName() + " - " + task.getDueDate());
        }

    }

    private void switchPanel(JPanel newPanel) {
        frame.remove(currentPanel);
        currentPanel = newPanel;
        frame.add(currentPanel,BorderLayout.CENTER);
        frame.revalidate(); // Refresh UI
        frame.repaint();
    }


    public static void main(String[] args) {

        new ToDoList();
    }



}
