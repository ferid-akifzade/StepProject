package controller;

class Menu {
    String getMainMenu() {
        return "1) Online board [-b] \n" +
                "2) Show the flight info [-s] city, desired-sits\n" +
                "3) Reserve this flight [-r] flight-id, desired-sits \n" +
                "4) Cancel the booking [-c] booking id \n" +
                "5) My flights [-m]\n" +
                "6) Log out - End Session [-l] \n" +
                "7) Exit [-e]\n";
    }
}
