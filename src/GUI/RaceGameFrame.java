package GUI;

import factory.RaceBuilder;
import game.arenas.Arena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import utilities.EnumContainer;
import utilities.designPatterns.Director;
import utilities.designPatterns.carRaceBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RaceGameFrame extends JFrame implements ActionListener {
    private static RaceBuilder builder = RaceBuilder.getInstance();
    private static ArrayList<Racer> racers;
    private static Arena arena = null;
    private JTextField ArenaLengthField;
    private JTextField MaxRacersField;
    private JTextField RacerNameField;
    private JTextField MaxSpeedField;
    private JTextField AccelerationField;
    private JTextField NumberOfRacersBuilderField;
    private JComboBox<String> ArenaComboBox;
    private JComboBox<String> RacerComboBox;
    private JComboBox<String> ColorComboBox;
    private JComboBox<String> copyRacerComboBox;
    private JComboBox<String> copyColorComboBox;
    private int arenaLength = 1000;
    private int arenaHeight = 700;
    private int maxRacers = 8;
    private int numOfRacers = 0;
    private String chosenArena = null;
    private String chosenRacer = null;
    private String chosenColor = null;
    private String chosenCopyColor = null;
    private int arenaChosenOption;
    private int chosenLength;
    private int chosenMaxRacers;
    private EnumContainer.Color color = null;
    private ImageIcon racersImages[] = null;
    private ImageIcon arenasImages[] = {new ImageIcon("icons/AerialArena.jpg"),new ImageIcon("icons/LandArena.jpg"),new ImageIcon("icons/NavalArena.jpg")} ;
    private boolean raceStarted = false;
    private boolean raceFinished = false;
    private JFrame RacersTable;

    private  JLabel arenaImageLabel;

    public RaceGameFrame() {
        super("Race");
        this.setContentPane(getMainPanel());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JPanel getMainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //------------------control panel-------------------//


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setPreferredSize(new Dimension(320,arenaHeight));
        mainPanel.add(controlPanel,BorderLayout.EAST);

        //----------------choose arena section------------//

        JLabel chooseArenaLabel = new JLabel("Choose Arena:");
        controlPanel.add(chooseArenaLabel);
        chooseArenaLabel.setLocation(50,10);
        chooseArenaLabel.setSize(150,25);

        String[] arenaOptions = {"AerialArena","LandArena","NavalArena"};
        ArenaComboBox = new JComboBox<>(arenaOptions);
        ArenaComboBox.setLocation(25,35);
        ArenaComboBox.setSize(150,25);
        controlPanel.add(ArenaComboBox);
        if(chosenArena!=null) {
            ArenaComboBox.setSelectedIndex(arenaChosenOption);
        }

        JLabel ArenaLengthLabel = new JLabel("Arena Length:");
        ArenaLengthLabel.setLocation(50,65);
        ArenaLengthLabel.setSize(150,25);
        controlPanel.add(ArenaLengthLabel);

        ArenaLengthField = new JTextField("" + arenaLength);
        ArenaLengthField.setLocation(25, 90);
        ArenaLengthField.setSize(150, 25);
        controlPanel.add(ArenaLengthField);

        JLabel MaxRacersLabel = new JLabel("Max Racers Number:");
        MaxRacersLabel.setLocation(40, 120);
        MaxRacersLabel.setSize(150, 25);
        controlPanel.add(MaxRacersLabel);

        MaxRacersField = new JTextField(""+maxRacers);
        MaxRacersField.setLocation(25, 145);
        MaxRacersField.setSize(150, 25);
        controlPanel.add(MaxRacersField);

        JButton buildArenaBtn = new JButton("Build arena");
        buildArenaBtn.setLocation(25, 185);
        buildArenaBtn.setSize(150, 25);
        buildArenaBtn.addActionListener(this::BuildArena);
        controlPanel.add(buildArenaBtn);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setLocation(0, 220);
        separator.setSize(185, 10);
        controlPanel.add(separator);

        //---------------------choose racer section----------------------------//

        JLabel chooseRacerLabel = new JLabel("Choose Racer:");
        controlPanel.add(chooseRacerLabel);
        chooseRacerLabel.setLocation(50,240);
        chooseRacerLabel.setSize(150,25);

        String[] racerOptions = {"Airplane","Helicopter","Bicycle","Car","Horse","RowBoat","SpeedBoat"};
        RacerComboBox = new JComboBox<>(racerOptions);
        RacerComboBox.setLocation(25,270);
        RacerComboBox.setSize(150,25);
        controlPanel.add(RacerComboBox);

        JLabel chooseColorLabel = new JLabel("Choose Color:");
        controlPanel.add(chooseColorLabel);
        chooseColorLabel.setLocation(50,300);
        chooseColorLabel.setSize(150,25);

        String[] colorOptions = {"RED", "GREEN", "BLUE", "BLACK", "YELLOW"};
        ColorComboBox = new JComboBox<>(colorOptions);
        ColorComboBox.setLocation(25,330);
        ColorComboBox.setSize(150,25);
        controlPanel.add(ColorComboBox);

        JLabel RacerNameLabel = new JLabel("Racer Name:");
        RacerNameLabel.setLocation(50, 360);
        RacerNameLabel.setSize(150, 25);
        controlPanel.add(RacerNameLabel);

        RacerNameField = new JTextField("");
        RacerNameField.setLocation(25, 390);
        RacerNameField.setSize(150, 25);
        controlPanel.add(RacerNameField);

        JLabel MaxSpeedLabel = new JLabel("Max speed:");
        MaxSpeedLabel.setLocation(50, 420);
        MaxSpeedLabel.setSize(150, 25);
        controlPanel.add(MaxSpeedLabel);

        MaxSpeedField = new JTextField("");
        MaxSpeedField.setLocation(25, 450);
        MaxSpeedField.setSize(150, 25);
        controlPanel.add(MaxSpeedField);

        JLabel AccelerationLabel = new JLabel("Acceleration:");
        AccelerationLabel.setLocation(50, 480);
        AccelerationLabel.setSize(150, 25);
        controlPanel.add(AccelerationLabel);

        AccelerationField = new JTextField("");
        AccelerationField.setLocation(25, 510);
        AccelerationField.setSize(150, 25);
        controlPanel.add(AccelerationField);

        JButton addRacerBtn = new JButton("Add Racer");
        addRacerBtn.setLocation(25, 560);
        addRacerBtn.setSize(150, 25);
        addRacerBtn.addActionListener(this::AddRacer);
        controlPanel.add(addRacerBtn);

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setLocation(0, 600);
        separator1.setSize(185, 10);
        controlPanel.add(separator1);

        //---------------------buttons------------------//

        JButton startRaceBtn = new JButton("Start Race");
        startRaceBtn.setLocation(25,620 );
        startRaceBtn.setSize(150, 25);
        startRaceBtn.addActionListener(this::StartRace);
        controlPanel.add(startRaceBtn);

        JButton showInfoBtn = new JButton("Show Info");
        showInfoBtn.setLocation(25, 650);
        showInfoBtn.setSize(150, 25);
        showInfoBtn.addActionListener(this::ShowInfo);
        controlPanel.add(showInfoBtn);

        //------------------prototype panel-----------//

        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        separator2.setLocation(185, 0);
        separator2.setSize(10, arenaHeight);
        controlPanel.add(separator2);

        JLabel copyLabel = new JLabel("Racer to Copy:");
        controlPanel.add(copyLabel);
        copyLabel.setLocation(200,10);
        copyLabel.setSize(100,25);


        copyRacerComboBox = new JComboBox<>();
        controlPanel.add(copyRacerComboBox);
        copyRacerComboBox.setLocation(200,35);
        copyRacerComboBox.setSize(100,25);
        copyRacerComboBox.removeAllItems();

        for (int i = 0;i<numOfRacers;i++){
            //add the racer to the copy comboBox.
            copyRacerComboBox.addItem(racers.get(i).getName());
            JLabel racerImageLabel = new JLabel(racersImages[i]);
            racerImageLabel.setLocation((int)racers.get(i).getCurrentLocation().getX()+5,(int)racers.get(i).getCurrentLocation().getY()+5);
            racerImageLabel.setSize(70,70);
            arenaImageLabel.add(racerImageLabel);
        }

        JLabel copyColorLabel = new JLabel("choose Color:");
        controlPanel.add(copyColorLabel);
        copyColorLabel.setLocation(200,65);
        copyColorLabel.setSize(100,25);

        copyColorComboBox = new JComboBox<>(colorOptions);
        controlPanel.add(copyColorComboBox);
        copyColorComboBox.setLocation(200,90);
        copyColorComboBox.setSize(100,25);

        JButton copyBtn = new JButton("Copy Racer");
        controlPanel.add(copyBtn);
        copyBtn.setLocation(190,130);
        copyBtn.setSize(120,25);
        copyBtn.addActionListener(this::CopyBtn);

        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setLocation(200, 160);
        separator3.setSize(100, 10);
        controlPanel.add(separator3);

        //------------------builder panel-------------//

        JLabel builderNumberLabel = new JLabel("Enter Number:");
        controlPanel.add(builderNumberLabel);
        builderNumberLabel.setLocation(200,180);
        builderNumberLabel.setSize(100,25);

        NumberOfRacersBuilderField = new JTextField();
        controlPanel.add(NumberOfRacersBuilderField);
        NumberOfRacersBuilderField.setLocation(200,205);
        NumberOfRacersBuilderField.setSize(100,25);

        JButton builderBtn = new JButton("Race Builder");
        controlPanel.add(builderBtn);
        builderBtn.setLocation(190,235);
        builderBtn.setSize(120,25);
        builderBtn.addActionListener(this::BuilderBtn);

        JSeparator separator4 = new JSeparator(SwingConstants.HORIZONTAL);
        separator4.setLocation(200, 265);
        separator4.setSize(100, 10);
        controlPanel.add(separator4);


        //------------------game panel----------------//

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(arenaLength+80,arenaHeight));

        ImageIcon arenaImage = new ImageIcon(new ImageIcon("icons/"+chosenArena+".jpg").getImage().getScaledInstance(arenaLength+80,arenaHeight,Image.SCALE_DEFAULT));
        arenaImageLabel = new JLabel(arenaImage);
        arenaImageLabel.setLocation(0,0);
        arenaImageLabel.setSize(arenaLength+80,arenaHeight);
        gamePanel.add(arenaImageLabel);

        for (int i = 0;i < numOfRacers;i++){
                JLabel racerImageLabel = new JLabel(racersImages[i]);
                racerImageLabel.setLocation((int)racers.get(i).getCurrentLocation().getX()+5,(int)racers.get(i).getCurrentLocation().getY()+5);
                racerImageLabel.setSize(70,70);
                arenaImageLabel.add(racerImageLabel);
        }

        mainPanel.add(gamePanel,BorderLayout.WEST);
        mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.CENTER);

        return mainPanel;
    }

    private void updateFrame() {
        this.setContentPane(getMainPanel());
        this.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        this.setLocation(x, y);
        this.setVisible(true);

    }


    public void BuildArena(ActionEvent event){

        arenaChosenOption = ArenaComboBox.getSelectedIndex();
        chosenLength = Integer.parseInt(ArenaLengthField.getText());
        chosenMaxRacers = Integer.parseInt(MaxRacersField.getText());

        if(raceStarted && !raceFinished){
            JOptionPane.showMessageDialog(this,"Please wait until Race finished");
            return;
        }
        try {
            if (chosenLength < 100 || chosenLength > 3000 || chosenMaxRacers <= 0 || chosenMaxRacers > 20)
                throw new Exception();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Invalid Input Values ,Please Try Again.");
        }
        this.arenaLength = chosenLength;

        int newHeight = (chosenMaxRacers + 1)*70;

        if (newHeight > 700)
            this.arenaHeight = newHeight;
        else this.arenaHeight = 700;

        raceStarted = raceFinished = false;
        numOfRacers = 0;
        chosenArena = (String) ArenaComboBox.getSelectedItem();
        racers = new ArrayList<>();
        racersImages = new ImageIcon[chosenMaxRacers];

        String arenaTypes[] = {"air.AerialArena","land.LandArena","naval.NavalArena"};

        try {
            arena = builder.buildArena("game.arenas."+arenaTypes[arenaChosenOption], chosenLength, chosenMaxRacers);
            System.out.println(arenaTypes[arenaChosenOption]+" - built successfully, length: "+chosenLength+", max racers: "+chosenMaxRacers);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
            System.out.println("Unable to build arena!");
        }
        updateFrame();
    }

    public void AddRacer(ActionEvent event) {

        if (raceStarted){
            JOptionPane.showMessageDialog(this, "please wait until the race finished.");
            return;
        }
        if (raceFinished){
            JOptionPane.showMessageDialog(this, "please build arena first.");
            return;
        }
        if (numOfRacers == chosenMaxRacers){
            JOptionPane.showMessageDialog(this, "arena is full!");
            return;
        }

        Racer racer = null;
        String name;
        double maxSpeed , acceleration;

        if(arena == null){
            JOptionPane.showMessageDialog(this,"please build arena first.");
        }
        else{
            try{
                name = RacerNameField.getText();
                maxSpeed = Double.parseDouble(MaxSpeedField.getText());
                acceleration = Double.parseDouble(AccelerationField.getText());
                if(name.isEmpty() || maxSpeed <= 0 || acceleration <= 0)
                    throw new Exception();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this,"Invalid Input Values ,Please Try Again.");
                return;
            }

            chosenRacer = (String) RacerComboBox.getSelectedItem();//selected racer.
            chosenColor = (String) ColorComboBox.getSelectedItem();//selected color.

            switch (chosenColor){
                case "RED":
                    color = EnumContainer.Color.RED;
                    break;
                case "GREEN":
                    color = EnumContainer.Color.GREEN;
                    break;
                case "BLUE":
                    color = EnumContainer.Color.BLUE;
                    break;
                case "BLACK":
                    color = EnumContainer.Color.BLACK;
                    break;
                case "YELLOW":
                    color = EnumContainer.Color.YELLOW;
                    break;
            }
            switch (chosenRacer){
                case "Airplane":
                    try {
                        racer=builder.buildWheeledRacer("game.racers.air.Airplane", name, maxSpeed, acceleration,color, 3);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Helicopter":
                    try {
                        racer=builder.buildRacer("game.racers.air.Helicopter", name, maxSpeed, acceleration,color);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Bicycle":
                    try {
                        racer=builder.buildWheeledRacer("game.racers.land.Bicycle", name, maxSpeed, acceleration,color, 2);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Car":
                    try {
                        racer=builder.buildWheeledRacer("game.racers.land.Car", name, maxSpeed, acceleration,color, 4);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Horse":
                    try {
                        racer=builder.buildRacer("game.racers.land.Horse", name, maxSpeed, acceleration,color);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "RowBoat":
                    try {
                        racer=builder.buildRacer("game.racers.naval.RowBoat",name, maxSpeed, acceleration,color);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "SpeedBoat":
                    try {
                        racer=builder.buildRacer("game.racers.naval.SpeedBoat",name, maxSpeed, acceleration,color);
                        racer.introduce();
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
                             InstantiationException
                             | IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
            try {
                arena.addRacer(racer);
            }catch (RacerLimitException e){
                System.out.println("[Error] " + e.getMessage());
            }catch (RacerTypeException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }

            racers.add(racer);
            arena.initRace();
            racersImages[numOfRacers] = new ImageIcon(new ImageIcon("icons/"+chosenRacer+chosenColor+".png").getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
            numOfRacers++;

            updateFrame();
        }
    }

    public void StartRace(ActionEvent event){
        if(arena==null || numOfRacers == 0) {
            JOptionPane.showMessageDialog(this,"Please build arena first and add racers");
            return;
        }
        if (raceFinished) {
            JOptionPane.showMessageDialog(this, "Race finished! Please build a new arena.");
            return;
        }
        if (raceStarted) {
            JOptionPane.showMessageDialog(this, "Race started! No racers can be added.");
            return;
        }

        raceStarted = true;

        new Thread(() -> {
            while (arena.hasActiveRacers() || arena.hasBrokenRacers())
            {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                updateFrame();
            }
            updateFrame();
            raceFinished = true;
        }).start();

        //creating thread for every racer and activate the run method in racer class.
        for(int i=0;i<arena.getActiveRacers().size();i++) {
            //because we implement runnable insted of extanding the Thread class
            // we need to create an instance of thread and give him the runnable
            // object that overrides run method.

            Thread thread=new Thread(arena.getActiveRacers().get(i));
            System.out.println("new Thread for: "+arena.getActiveRacers().get(i).getName());
            thread.start();
        }
    }

    public void ShowInfo(ActionEvent event){
        if(arena == null){
            JOptionPane.showMessageDialog(this,"Please build arena first");
        } else if (arena!=null && numOfRacers ==0) {
            JOptionPane.showMessageDialog(this,"no data to show. Please add racers");
        } else{
            String[] columns = {"Racer Name","Current Speed","Max Speed","Current X Location","Finished"};
            String[][] racersData = new String[numOfRacers][columns.length];
            int i = 0;
            for(int j = 0; j < arena.getCompletedRacers().size();j++){
                racersData[i][0] = arena.getCompletedRacers().get(j).getName();
                racersData[i][1] = ""+arena.getCompletedRacers().get(j).getCurrentSpeed();
                racersData[i][2] = ""+arena.getCompletedRacers().get(j).getMaxSpeed();
                racersData[i][3] = ""+arena.getCompletedRacers().get(j).getCurrentLocation().getX();
                racersData[i][4] = "Yes";
                i++;
            }
            for(int j = 0; j < arena.getActiveRacers().size();j++){
                racersData[i][0] = arena.getActiveRacers().get(j).getName();
                racersData[i][1] = ""+arena.getActiveRacers().get(j).getCurrentSpeed();
                racersData[i][2] = ""+arena.getActiveRacers().get(j).getMaxSpeed();
                racersData[i][3] = ""+arena.getActiveRacers().get(j).getCurrentLocation().getX();
                racersData[i][4] = "No";
                i++;
            }
                for (int j = arena.getBrokenRacers().size() - 1; j >= 0; j--) {
                    racersData[i][0] = arena.getBrokenRacers().get(j).getName();
                    racersData[i][1] = "" + arena.getBrokenRacers().get(j).getCurrentSpeed();
                    racersData[i][2] = "" + arena.getBrokenRacers().get(j).getMaxSpeed();
                    racersData[i][3] = "" + arena.getBrokenRacers().get(j).getCurrentLocation().getX();
                    racersData[i][4] = "Broken";
                    i++;
                }
            for(int j = arena.getDisqualifiedRacers().size()-1;j>=0;j--){
                racersData[i][0] = arena.getDisqualifiedRacers().get(j).getName();
                racersData[i][1] = ""+arena.getDisqualifiedRacers().get(j).getCurrentSpeed();
                racersData[i][2] = ""+arena.getDisqualifiedRacers().get(j).getMaxSpeed();
                racersData[i][3] = ""+arena.getDisqualifiedRacers().get(j).getCurrentLocation().getX();
                racersData[i][4] = "Failed";
                i++;
            }

            JTable table = new JTable(racersData, columns);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            JScrollPane scrollPane = new JScrollPane(table);

            JPanel dataPanel = new JPanel();
            dataPanel.add(scrollPane);

            if (RacersTable != null)
                RacersTable.dispose();

            RacersTable = new JFrame("Racers Information");
            RacersTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            RacersTable.setContentPane(dataPanel);
            RacersTable.pack();
            RacersTable.setVisible(true);

        }
    }

    public void CopyBtn(ActionEvent event){

        String copyRacerName = (String) copyRacerComboBox.getSelectedItem();
        EnumContainer.Color copyColor = null;

        if(raceStarted){
            JOptionPane.showMessageDialog(this,"Wait until the race is over to add a new copied racer.");
        }
        if (raceFinished){
            JOptionPane.showMessageDialog(this,"The race is already over, it is not possible to add a new copied racer.");
        }
        if(arena == null){
            JOptionPane.showMessageDialog(this,"In order to copy a racer, it is necessary to build an arena first." +
                    " new copied racer not added. ");
        }
        if (numOfRacers == maxRacers){
            JOptionPane.showMessageDialog(this,"The arena has reached its maximum number of racers, it is not possible to add a new copied racer");
        }
        if (numOfRacers == 0){
            JOptionPane.showMessageDialog(this,"In order to copy a racer, racers must be created first." +
                    " new copied racer not added. ");
        }

        chosenCopyColor = (String) copyColorComboBox.getSelectedItem();

        switch (chosenCopyColor){
            //"RED", "GREEN", "BLUE", "BLACK", "YELLOW"
            case "RED":
                copyColor = EnumContainer.Color.RED;
                break;
            case "GREEN":
                copyColor = EnumContainer.Color.GREEN;
                break;
            case "BLUE":
                copyColor = EnumContainer.Color.BLUE;
                break;
            case "BLACK":
                copyColor = EnumContainer.Color.BLACK;
                break;
            case "YELLOW":
                copyColor = EnumContainer.Color.YELLOW;
                break;
        }

        Racer copyRacer = null;

        for (int i = 0;i < numOfRacers;i++) {
            if (racers.get(i).getName()==copyRacerName)
                copyRacer = racers.get(i).clone();
        }

        try {
            copyRacer.upgrade(copyColor);
            racers.add(copyRacer);
            arena.addRacer(copyRacer);
            arena.initRace();
        }catch (Exception exception){new RuntimeException();}

        racersImages[numOfRacers] = new ImageIcon(new ImageIcon("icons/"+copyRacer.className()+copyColor+".png").getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT));
        numOfRacers++;
        copyRacer.introduce();
        updateFrame();
    }

    public void BuilderBtn(ActionEvent event){
        if(raceStarted && !raceFinished){
            JOptionPane.showMessageDialog(this,"Wait until the race is over.");
        }
        if (NumberOfRacersBuilderField.getText() == ""){
            JOptionPane.showMessageDialog(this,"please Enter number N first.");
        }
        if (Integer.parseInt(NumberOfRacersBuilderField.getText()) <=0 ||Integer.parseInt(NumberOfRacersBuilderField.getText())>20 ){
            JOptionPane.showMessageDialog(this,"please Enter number N between 1-20.");
        }

        int numOfRacersText = Integer.parseInt(NumberOfRacersBuilderField.getText());
        Director director = new Director(new carRaceBuilder(numOfRacersText));

        try {
            director.buildRace();
        }catch (RacerLimitException exception){
            exception.printStackTrace();
        }catch (RacerTypeException exception){
            exception.printStackTrace();
        }
        chosenArena = "LandArena";
        arenaLength = (int)director.getRace().getArena().getLength();
        arena = director.getRace().getArena();
        racers = director.getRace().getRacerArrayList();
        maxRacers = numOfRacersText;
        numOfRacers = numOfRacersText;
        raceStarted = raceFinished = false;

        int newHeight = (maxRacers + 1)*60;

        if (newHeight > 700)
            this.arenaHeight = newHeight;
        else
            this.arenaHeight = arenaHeight;

        racersImages = new ImageIcon[maxRacers];

        for (int i = 0 ; i < numOfRacers ; i++){
            racersImages[i] = new ImageIcon(new ImageIcon("icons/" + "Car" + racers.get(i).getColor() + ".png").getImage()
                    .getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        }

        updateFrame();

    }



        @Override
    public void actionPerformed(ActionEvent e) {}

}
