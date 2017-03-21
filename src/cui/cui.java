package cui;

import domain.DomainController;
import exceptions.noCorrectBirthyearException;
import exceptions.userExistsException;
import exceptions.noCorrectNameException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class cui
{

    private DomainController dc;
    private String name;
    private ResourceBundle rs;
    boolean badInput = true;

    Scanner s = new Scanner(System.in);

    public cui(DomainController dc)
    {
        this.dc = dc;
    }

    public void startPazaak()
    {
        boolean running = true;

        Locale currentLocale = Locale.getDefault();
        rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        do
        {
            System.out.println("For English press 1 \nVoor Nederlands druk op 2 \nPour Français appuyez 3.");

            switch (s.nextInt())
            {
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
        } while (badInput);

        Locale.setDefault(currentLocale);
        rs = ResourceBundle.getBundle("lang/Lang", Locale.getDefault());
        badInput = true;
        do
        {
            do
            {
                try
                {
                    s.nextLine();
                    System.out.println(String.format(rs.getString("welcome")));
                    switch (s.nextInt())
                    {
                        //Test
                        case 1:
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
                            System.out.printf("[WIP]");
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
                } catch (userExistsException | noCorrectBirthyearException | noCorrectNameException uex)
                {
                    System.out.println(uex.getMessage());
                } catch (InputMismatchException ime)
                {
                    System.out.println(rs.getString("mismatch"));
                }
            } while (badInput);
        } while (running);

    }

    public String giveCards()
    {
        String back = "";
        String[][] arr;
        arr = dc.getPlayerData();
        for (int i = 1; i <= arr.length - 1; i++)
        {
            back += arr[i][0];
            back += arr[i][1];
            back += " ";
        }
        return back;
    }

    public void register()
    {
        int date;
        System.out.println(String.format(rs.getString("inputRegister")));
        System.out.print(String.format(rs.getString("name")));
        name = s.next();
        System.out.print(String.format(rs.getString("date")));
        date = s.nextInt();
        dc.register(name, date);
        System.out.println(rs.getString("yourCards"));
        System.out.println(String.format(giveCards()));
    }

    public void startGame()
    {
        dc.makeMatch();
        while (dc.getAmountPlayersStillNeeded() > 0)
        {
            System.out.println(rs.getString("select") + " " + dc.getAmountPlayersStillNeeded() + " " + rs.getString("need"));
            for (String matchName : dc.getPlayerNames())
            {
                System.out.println(matchName);
            }
            System.out.println(rs.getString("giveName"));
            name = s.next();
            dc.selectPlayer(name);
        }

        Boolean invoerYn = false;
        String input;
        int inputnumber;
        
            do
            {
                System.out.println(rs.getString("chosenPlayers"));
                for (String matchPlayer : dc.getChosenPlayerNames())
                {
                    System.out.println(matchPlayer);
                }
                System.out.println(rs.getString("yesNo"));

                input = s.next().toLowerCase();
                if (input.equals("yes") || input.equals("no"))
                {

                    if (input.equals("yes"))
                    {
                        invoerYn = true;
                    } else
                    {
                        System.out.println(rs.getString("returningMain"));
                        invoerYn = true;
                    }
                } else
                {
                    System.out.println(rs.getString("notYN"));
                    invoerYn = false;
                }

            } while (invoerYn == false);

            while ((dc.getPlayersWithoutMatchDeck().length) > 0)
            {
                badInput = true;
                System.out.println(rs.getString("playersWithout"));
                for (String matchPlayer : dc.getPlayersWithoutMatchDeck())
                {
                    System.out.println(matchPlayer);
                }
                System.out.println(rs.getString("selectWithout"));
                name = s.next();
                dc.selectPlayerWithoutMatchDeck(name);

                // String[][] selectedCards = new String[6][2];
                String[][] selectedCards = new String[0][2];
                String[][] selectedCardsCopy;

                for (int i = 0; i <= 5; i++)
                {
                    System.out.println(rs.getString("youNeed") + " " + (6 - i) + " " + rs.getString("more"));
                    selectedCardsCopy = selectedCards;
                    int count = 1;
                    System.out.println(rs.getString("chooseNumber"));
                    for (String[] available : dc.showAvailableCards(selectedCards))
                    {
                        System.out.println("[" + count++ + "] " + available[0] + available[1]);
                    }
                    inputnumber = s.nextInt();
                    String[][] available = dc.showAvailableCards(selectedCards);
                    if (inputnumber < 1 || inputnumber > available.length)
                    {
                        System.out.println(rs.getString("errorNumber"));
                    }

                    selectedCards = new String[i + 1][2];
                    for (int j = 0; j < selectedCardsCopy.length; j++)
                    {
                        selectedCards[j][0] = selectedCardsCopy[j][0];
                        selectedCards[j][1] = selectedCardsCopy[j][1];

                    }

                    selectedCards[i][0] = available[inputnumber - 1][0];
                    selectedCards[i][1] = available[inputnumber - 1][1];

                }
                dc.makeMatchDeck(selectedCards);

            }
       
        matchStarted();

    }

    public void matchStarted()
    {
        int roundAmount = 1;
        do
        {
            System.out.println(rs.getString("roundStarted") + " " + roundAmount);
            startNewRound();
            roundAmount++;
        } while (dc.matchEnded() == false);
    }

    public void startNewRound()
    {

    }
}
