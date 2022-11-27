# Genspark-Group-Challenge
***Team Members***
**Affan Fareed, Alonso del Arte, Jacob Stout, Kevin Drake, Moe Yassin, and Steven Lofquist**
# Challenge 1: Random Strings to Columns
## Motivation
*This program is our first group project. We were instructed to pick teams, and choose a challenge. We chose challenge 1: An Input File, that displays random strings. We will need to write this program to display the data in a Column Fashion.*
## How to Run
1. Start by cloning (git clone) repository in desired directory.<br> 
2.Open IntelliJ and add all files.<br> 
3.Make sure all file directories are marked properly and in correct spots, Test folder marked as Test,   -Project Folder for FXML is in Resource package<br> 
4.Make sure you have JavaFX installed<br> 
5.If all that is true, click run, click open file button<br> 
6.Select (TextInput[TextInput.txt]) then data should be shown in column wise format
![Grid](https://github.com/Cup-of-Java/genspark-group-challenge/blob/main/GroupScreenShot.png)
## Code Snippet
*We chose this selection of code, because this is the Controller class. Without this, there would be no communication between FXML file, and program. This needs to be set up properly, as well as FXML file is pointing to this Controller.java, and RunApplication class is creating an Instance of FXMLLoader with fxml file.*
```Java
public class Controller {
    List<String> sortedList;
    ArrayList<Text> nodes;
    @FXML
    GridPane gridPane;

    @FXML
    public void displayList(String path) {
        nodes = new ArrayList<>();
        try {
            sortedList = SortInput.sortInput(path).stream().toList();
            for (String s:sortedList) {
                nodes.add(new Text("  "+ s +"  "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(2, 5, 2,5));

        int x=0, y=0;

        for (int i = 0; i < nodes.size(); i++) {
            gridPane.add(nodes.get(i), x, y);
            y++;
            if(y % 10 == 0){
               x++;
               y=0;
            }
        }
    }
    
   ```
## Tests
1. We have run many various kinds of tests while writing the program.
2. Test Package, has JUnit testing. Testing all methods that return some sort of Data, whether thats an Integer, or a sorted list of Strings. 
## Contributors
[Affan Fareed](https://github.com/ItMeansBigMountain), [Alonso del Arte](https://github.com/Alonso-del-Arte), [Jacob Stout](https://github.com/JediJake66), [Kevin Drake](https://github.com/KDrake80), [Moe Yassine](https://github.com/JintekiWarrior), [Steven Lofquist](https://github.com/StevenLof777)