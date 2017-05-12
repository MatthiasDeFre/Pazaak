package cui;

import domain.DomainController;
import exceptions.noCorrectBirthyearException;
import exceptions.userExistsException;
import exceptions.noCorrectNameException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class cui {

    private DomainController dc;
    private String name;
    private int inputNumber;
    private ResourceBundle rs;
    boolean badInput = true;
    private boolean playedCard = false;
    private boolean cantNextTurn = false;
    private boolean continueGame = true;

    Scanner s = new Scanner(System.in);

    public cui(DomainController dc) {
        this.dc = dc;
    }

    public void startPazaak() {
        boolean running = true;

        Locale currentLocale = Locale.getDefault();
        rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());

        do {
            try {
                System.out.println("For English press 1 \nVoor Nederlands druk op 2 \nPour Français appuyez 3.");
                switch (s.nextInt()) {
                    case 1:
                        //ENGLISH
                        currentLocale = new Locale("en_US");
                        badInput = false;
                        break;
                    case 2:
                        //NEDERLANDS
                        currentLocale = new Locale("nl_BE");
                        badInput = false;
                        break;
                    case 3:
                        //FRENCH
                        currentLocale = new Locale("fr_FR");
                        badInput = false;
                        break;
                    default:
                        System.out.println("Wrong number");
                        break;
                }
            } catch (InputMismatchException re) {
                System.out.println("Value is not valid \nOngeldeige waarde \nValue invalable");
                s.nextLine();
            }

        } while (badInput);

        Locale.setDefault(currentLocale);
        rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        badInput = true;
        do {
            do {
                try {
                    s.nextLine();
                    System.out.println(String.format(rs.getString("welcome")));
                    switch (s.nextInt()) {
                        //Test
                        case 1:
                            //methode om een nieuwe gerbuiker aan te maken
                            register();
                            badInput = false;
                            break;

                        case 2:
                            //methode voor nieuwe wedstrijd starten
                            startGame();
                            badInput = false;
                            break;
                        case 3:
                            //methode voor bestaande wedstrijd verder te doen
                            loadMatch();
                            badInput = false;
                            break;
                        case 4:
                            //methode voor login
                            System.out.println(rs.getString("end"));
                            badInput = false;
                            running = false;
                            break;

                        default:
                            System.out.println("Wrong number");
                            break;
                    }
                } catch (userExistsException | noCorrectBirthyearException | noCorrectNameException uex) {
                    System.out.println(uex.getMessage());
                } catch (InputMismatchException ime) {
                    System.out.println(rs.getString("mismatch"));
                }
            } while (badInput);
        } while (running);

    }

    private String giveCards() {
        String back = "";
        String[][] arr;
        arr = dc.getPlayerData();
        for (int i = 1; i <= arr.length - 1; i++) {
            back += arr[i][0];
            back += arr[i][1];
            back += " ";
        }
        return back;
    }

    private void register() {
        try {

            int date;
            System.out.println(String.format(rs.getString("inputRegister")));
            System.out.print(String.format(rs.getString("name")));
            name = s.next();
            System.out.print(String.format(rs.getString("date")));
            date = s.nextInt();
            dc.register(name, date);
            System.out.println(rs.getString("yourCards"));
            System.out.println(String.format(giveCards()));
        } catch (noCorrectBirthyearException ne) {
            System.out.println(ne.getMessage());
        }
    }

    private void startGame() {
        dc.makeMatch();
        while (dc.getAmountPlayersStillNeeded() > 0) {
            System.out.println(rs.getString("select") + " " + dc.getAmountPlayersStillNeeded() + " " + rs.getString("need"));
            for (String matchName : dc.getPlayerNames()) {
                System.out.println(matchName);
            }
            System.out.println(rs.getString("giveName"));
            name = s.next();
            dc.selectPlayer(name);
        }

        Boolean invoerYn = false;
        String input;

        do {
            System.out.println(rs.getString("chosenPlayers"));
            for (String matchPlayer : dc.getChosenPlayerNames()) {
                System.out.println(matchPlayer);
            }
            System.out.println(rs.getString("yesNo"));

            input = s.next().substring(0, 1);

            if (input.toLowerCase().equals("o") || input.toLowerCase().equals("j") || input.toLowerCase().equals("y")) {
                invoerYn = true;
            } else {
                if (input.toLowerCase().equals("n")) {
                    System.out.println(rs.getString("returningMain"));
                    invoerYn = true;
                } else {
                    System.out.println(rs.getString("wrongInput"));
                    invoerYn = false;
                }
            }

        } while (invoerYn == false);
        makeMatchDeck();
        matchStarted();

    }

    private void makeMatchDeck() {
        while ((dc.getPlayersWithoutMatchDeck().length) > 0) {
            badInput = true;
            System.out.println(rs.getString("playersWithout"));
            for (String matchPlayer : dc.getPlayersWithoutMatchDeck()) {
                System.out.println(matchPlayer);
            }
            System.out.println(rs.getString("selectWithout"));
            name = s.next();
            dc.selectPlayerWithoutMatchDeck(name);

            // String[][] selectedCards = new String[6][2];
            String[][] selectedCards = new String[0][2];
            String[][] selectedCardsCopy;

            //   dc.buyCard(index - 1, );
            for (int i = 0; i <= 5; i++) {

                System.out.println(rs.getString("buyCard"));
                String input = s.next().substring(0, 1);
                if (input.toLowerCase().equals("o") || input.toLowerCase().equals("j") || input.toLowerCase().equals("y")) {
                    boolean buyCard = true;
                    while (buyCard) {
                        boolean buyAnotherCard = true;
                        do {
                            System.out.println(rs.getString("whichCardBuy"));
                            int index = 0;
                            for (String[] string : dc.showBuyableCards()) {
                                System.out.println(++index + ": " + Arrays.toString(string));
                            }

                            index = s.nextInt() - 1;
                            dc.buyCard(dc.showBuyableCards()[index]);
                            System.out.println(rs.getString("buyCard"));
                            input = s.next().substring(0, 1);
                            if (input.toLowerCase().equals("o") || input.toLowerCase().equals("j") || input.toLowerCase().equals("y")) {
                                buyAnotherCard = true;
                            } else {
                                if (input.toLowerCase().equals("n")) {
                                    System.out.println(rs.getString("returningMain"));
                                    buyAnotherCard = true;
                                } else {
                                    System.out.println(rs.getString("wrongInput"));
                                    buyAnotherCard = false;
                                }
                            }

                        } while (buyAnotherCard == false);
                        i--;
                        /*  do 
                       {
                          boolean notBuyCard;
                          buyCard = s.nextBoolean(); 
                       } while(notBuyCard)*/
                    }
                } else if (input.toLowerCase().equals("n")) {
                    System.out.println(rs.getString("youNeed") + " " + (6 - i) + " " + rs.getString("more"));
                    selectedCardsCopy = selectedCards;
                    int count = 1;
                    System.out.println(rs.getString("chooseNumber"));
                    for (String[] available : dc.showAvailableCards(selectedCards)) {
                        System.out.println("[" + count++ + "] " + available[0] + available[1]);
                    }
                    inputNumber = s.nextInt();
                    String[][] available = dc.showAvailableCards(selectedCards);
                    if (inputNumber < 1 || inputNumber > available.length) {
                        System.out.println(rs.getString("errorNumber"));
                    }

                    selectedCards = new String[i + 1][2];
                    for (int j = 0; j < selectedCardsCopy.length; j++) {
                        selectedCards[j][0] = selectedCardsCopy[j][0];
                        selectedCards[j][1] = selectedCardsCopy[j][1];

                    }

                    selectedCards[i][0] = available[inputNumber - 1][0];
                    if (selectedCards[i][0].equals("-")) {
                        selectedCards[i][1] = "-" + available[inputNumber - 1][1];
                    } else {
                        selectedCards[i][1] = available[inputNumber - 1][1];
                    }
                }
            }
            dc.makeMatchDeck(selectedCards);

        }
    }

    private void matchStarted() {
        System.out.println(rs.getString("matchStarted") + " ");
        s.nextLine();
        System.out.println(rs.getString("wantAI"));
        String input = s.nextLine().substring(0, 1);
        if (input.toLowerCase().equals("o") || input.toLowerCase().equals("j") || input.toLowerCase().equals("y")) {
            dc.setAIMatch(true);
        } else {
            if (input.toLowerCase().equals("n")) {
                dc.setAIMatch(false);
            } else {
                System.out.println(rs.getString("wrongInput"));
            }
        }
        playMatch();
    }

    private void playMatch() {
        int roundAmount = 1;
        do {
            startNewRound();
            roundAmount++;
        } while (dc.matchEnded() == false || continueGame == true);
        System.out.println(rs.getString("winnerIs") + dc.whoWon());
    }

    private void startNewRound() {
        System.out.println(rs.getString("roundStarted"));

        dc.startNewRound();
        playedCard = false;
        do {
            if (playedCard == false) {
                dc.nextTurn();
            }

            System.out.println(Arrays.deepToString(dc.getRoundSituation()));
            String[][] situation = dc.getRoundSituation();
            int index = 0;
            for (String playerName : dc.getChosenPlayerNames()) {

                System.out.println(rs.getString("score") + playerName + " " + situation[2][index]);
                index++;
            }
            System.out.println(rs.getString("whoseTurn") + Arrays.toString(situation[4]));
            System.out.println(rs.getString("yourCards") + Arrays.toString(situation[3]));

            // System.out.println(Arrays.deepToString(dc.getRoundSituation()));
            if (!dc.isAIMatch() || dc.getAIWantsNextTurn() == false) {
                System.out.println(rs.getString("whatWant"));
                turnChoice(s.nextInt());
                s.nextLine();
            }
        } while (!dc.roundEnded());
        System.out.println(Arrays.deepToString(dc.getRoundSituation()));
        System.out.println(rs.getString("wantSave"));

        String input = s.nextLine();
        if (input.toLowerCase().equals("o") || input.toLowerCase().equals("j") || input.toLowerCase().equals("y")) {
            System.out.println(rs.getString("whichName"));
            input = s.nextLine();
            dc.saveMatch(input);
        } else {
            if (input.toLowerCase().equals("n")) {
                continueGame = false;
            } else {
                System.out.println(rs.getString("wrongInput"));
            }
        }

    }

    private void turnChoice(int choice) {
        switch (choice) {
            case 1:
                playedCard = false;
                break;
            case 2:

                //Indien geen kaarten moet hier nog komen
                int count = 0;
                String[][] situation = dc.getRoundSituation();
                if (situation[3].length != 0) {
                    System.out.println(rs.getString("whichCard"));
                    for (String string : situation[3]) {
                        System.out.println(++count + ": " + string);
                    }
                    dc.playCard(s.nextInt());
                    System.out.println(Arrays.deepToString(dc.getRoundSituation()));
                    playedCard = true;
                } else {
                    System.out.println(rs.getString("noCards"));
                    playedCard = true;
                }

            case 3:
                dc.freezeBoard();
                playedCard = false;
                break;

        }
    }

    private void loadMatch() {
        Boolean input = true;
        do {
            try {
                System.out.println(rs.getString("whichSave"));
                System.out.println(Arrays.toString(dc.getSavegameNames()));
                s.nextLine();
                dc.loadMatch(s.nextLine());
                playMatch();
                input = false;
            } catch (IndexOutOfBoundsException ioe) {
                System.out.println(rs.getString("errorLoad"));
            }
        } while (input);
    }

    private void buyCard() {
        System.out.println(rs.getString("whichPlayer"));
        for (String matchName : dc.getPlayerNames()) {
            System.out.println(matchName);

        }
    }
    
    private String getPossibleChanges(){
   
    return "helemaal niks";
    }
}
