//package ui;
//
//
//import model.Map;
//
//import javax.swing.*;
//
//// represents the menu the users can navigate through to find actions they would like to perform
//// such as search interface and the drop-downs
//public class MenuPanel extends JPanel {
//
//    boolean isActive;
//
//    private JMenu mainMenu;
//
//    private JMenuItem search;
//    private JMenuItem viewFav;
//    private JMenuItem viewAll;
//    private JMenuItem save;
//    private JMenuItem load;
//
//    public MenuPanel(Map map) {
//        mainMenu = new JMenu();
//        isActive = false;
//
//        search = new JMenuItem("Search");
//        viewFav = new JMenuItem("View Favourites");
//        viewAll = new JMenuItem("View All");
//        save = new JMenuItem("save");
//        load = new JMenuItem("load");
//
//        add(search);
//        add(viewAll);
//        add(viewFav);
//        add(save);
//        add(load);
//    }
//
//    // TODO represents main sidebar menu that pops up when hitting menu button
//    public void mainMenu() {
//
//
//    }
//
//    // TODO after keyEvent decide if you should close or open the menu
//    public void openOrClose() {
//        if (isActive) {
//            mainMenu();
//        } else {
//            quitMenu();
//        }
//
//    }
//
//    // TODO represents the action performed when viewAll is selected
//    public void viewAll(){
//
//    }
//
//    // TODO represents the action performed when viewFav is selected
//    public void viewFav() {
//
//    }
//
//    // TODO represents the search bar and the results shown for search
//    public void search() {
//
//    }
//
//    // TODO represents the screen that pops up when remove pins from fav/all is selected
//    public void removePins() {
//
//    }
//
//    // TODO decide if viewFav or viewAll should be displayed
//    public void allOrFavView() {
//
//    }
//
//    // TODO quit main menu, go back to og main screen with pins displayed as normal
//    public void quitMenu() {
//
//    }
//
//    public boolean getIsActive() {
//        return isActive;
//    }
//}
