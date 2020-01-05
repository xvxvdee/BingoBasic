import java.util.*;

// Make sure the random drawn number is unique
// Check for bingo winning scenarios
public class BingoGame {
    public static void main(String[] args) {
        int pOneChoice=2, pTwoChoice=2;
        int versus=0,cardSize = 2;

        String[][] playerOne = new String[cardSize][cardSize];
        String[][] playerTwo = new String[cardSize][cardSize];
        String[][] computer = new String[cardSize][cardSize];
        ArrayList<String> uniqueness = new ArrayList<>();
        boolean p1Win=false,p2Win=false;

        Scanner input = new Scanner(System.in);

        System.out.println("WELCOME TO BINGO *^____^*");
        System.out.println("Would you like to play with a friend or against the computer?");
        System.out.println("PRESS 1: Play with a friend");
        System.out.println("PRESS 2: Play against the computer");
        do {
            try {
                System.out.print("Choice: ");
                String versusTemp = input.nextLine();
                versus = Integer.parseInt(versusTemp);
            }
            catch (NumberFormatException ex){
                System.out.println("Please enter an option! ");
            }
        } while (versus != 1 && versus != 2);

        switch (versus) {
            case 1:
                System.out.println("Here are your bingo cards. Your friend is player two.");
                System.out.println();
                filling(playerOne,cardSize);
                filling(playerTwo,cardSize);
                printCardsSideBySide(playerOne, playerTwo,cardSize);

                while (!p1Win || !p2Win){
                    System.out.println();
                    String num = drawRandomNum(uniqueness);

                    System.out.println("Player 1, Is " + num + " on your card?");
                    System.out.println("PRESS 1: YES \t PRESS 0: NO");
                    do {
                        try{
                            System.out.print("Answer: ");
                            String pOneChoiceTemp = input.nextLine();
                            pOneChoice = Integer.parseInt(pOneChoiceTemp);
                        }catch (NumberFormatException ex) {
                            System.out.println("Please enter an option! ");
                        }
                    } while (pOneChoice != 1 && pOneChoice != 0);

                    String[][] u1 = checkBoard(playerOne, num, pOneChoice,cardSize);

                    System.out.println();
                    System.out.println("Player 2, Is " + num + " on your card?");
                    System.out.println("PRESS 1: YES \t PRESS 0: NO");
                    do {
                        try{
                            System.out.print("Answer: ");
                            String pTwoChoiceTemp = input.nextLine();
                            pTwoChoice = Integer.parseInt(pTwoChoiceTemp);
                        }catch (NumberFormatException ex) {
                            System.out.println("Please enter an option! ");
                        }
                    } while (pTwoChoice != 1 && pTwoChoice != 0);

                    String[][] u2 = checkBoard(playerTwo, num, pTwoChoice,cardSize);

                    if (checkPatternHorizontal(playerOne,cardSize) || checkPatternVertical(playerOne,cardSize)|| checkPatternDiagonal(playerOne,cardSize)){
                        p1Win = true;
                    }
                    else if (checkPatternHorizontal(playerTwo,cardSize) || checkPatternVertical(playerTwo,cardSize)|| checkPatternDiagonal(playerTwo,cardSize)){
                        p2Win=true;
                    }

                    System.out.println();
                    printCardsSideBySide(u1, u2,cardSize);

                }
                break;
            case 2:
                break;


        }


    }

    public static String drawRandomNum(ArrayList<String> unique) {
        String num = "";
        num = randomNumGen();

        for (int i=0; i<76;i++) {
            boolean checking = alreadyIn1D(num, unique);
            if (checking == true) {
                num = randomNumGen();
                continue;
            }
            else {
                unique.add(num);
                break;
            }
        }
        return num;

    }

    public static void printBingoCard(String[][] user) {
        for (String[] element : user) {
            for (String y : element) {
                System.out.print(y + "\t");
            }
            System.out.println();
        }
    }

    public static boolean alreadyIn1D(String num,ArrayList<String>player) {
        for (int i = 0; i < player.size(); i++) {
            if (num.equals(player.get(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean alreadyIn(String num, String[][] player) {
        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player.length; j++) {
                if (num.equals(player[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void filling(String[][] user, int cardSize) {
        Random randGen = new Random();
        int randNum;
        String randNumStr;
        for (int j = 0; j < 100; j++) {
            for (int a = 0; a < cardSize; a++) {
                for (int b = 0; b < cardSize; b++) {
                    randNum = randGen.nextInt(75);
                    randNumStr = Integer.toString(randNum);
                    boolean checking = alreadyIn(randNumStr, user);
                    if (checking == true) {
                        continue;
                    } else {
                        user[a][b] = randNumStr;
                    }
                }
            }
        }
    }

    public static void printCardsSideBySide(String[][] user1, String[][] user2, int cardSize) {
        System.out.println("\t PLAYER 1 \t\t\t\t\t PLAYER 2");

        for (int a = 0; a <cardSize; a++) {
            for (int b = 0; b < cardSize; b++) {
                System.out.print(user1[a][b] + "\t");
            }
            System.out.print("\t\t");
            for (int c = 0; c < cardSize; c++) {
                System.out.print(user2[a][c] + "\t");
            }
            System.out.println();
        }
    }

    public static String randomNumGen() {
        int randTemp;
        Random randGen = new Random();
        randTemp = (randGen.nextInt(75))+1;
        String randNum = Integer.toString(randTemp);
        return randNum;
    }

    public static String[][] checkBoard(String[][] user, String num, int userAns,int cardSize) {
        boolean elementContained = false;

        for (int a = 0; a < cardSize; a++) {
            for (int b = 0; b < cardSize; b++) {
                switch (userAns) {
                    case 0:
                        if (user[a][b].equals(num)) {
                            System.out.println("Awe you missed " + num + " on your bingo card. (。﹏。*)");
                        }
                        break;
                    case 1:
                        if (user[a][b].equals(num)) {
                            user[a][b] = "X";
                            elementContained = true;
                        }

                        break;
                }
            }
        }
        if ((elementContained == false) && (userAns == 1)) {
            System.out.println("Nice try! " + num + " is not on your bingo card （︶^︶）");
        }
        return user;
    }

    public static boolean checkPatternDiagonal(String[][] user, int cardSize) {
        boolean pattern_One = false, pattern_Two = false;
        int count1=0,count2=0;
        for (int i = 0; i < user.length; i++) {
            for (int j = 0; j < user.length; j++) {
                if (i == j && user[i][j].equals("X")) {pattern_One = true; count1++;}
                else{ pattern_One = false; count1=0;}
            }
        }

        for (int i = 0; i < user.length; i++) {
            for (int j = 0; j < user.length; j++) {
                if (((i+j)== user.length-1) && user[i][j].equals("X")) { pattern_Two= true; count2++; }
                else{ pattern_Two = false; count2=0;}

            }
        }

        if (pattern_One && count1==cardSize){ return pattern_One; }
        else if(pattern_Two && count2==cardSize) {return pattern_Two;}
        else return false;
    }

    public static boolean checkPatternVertical(String[][] user,int cardSize){
        boolean check = false;
        int col =0;
        int count =0;

        do {
            for (int b = 0; b < user.length; b++) {
                if (user[b][col].equals("X")) {
                    check = true;
                    count++;
                }
                else {
                    check = false;
                    count = 0;
                }
            }
            if (check && count == cardSize ) {
                col=user.length;
                return check;
            } else col++;
        }while(col !=user.length);
        return check;
    }
    public static boolean checkPatternHorizontal(String[][] user, int cardSize){
        boolean check = false;
        int row =0;
        int count =0;

        do {
            for (int b = 0; b < user.length; b++) {
                if (user[row][b].equals("X")) {
                    check = true;
                    count++;
                }
                else {
                    check = false;
                    count = 0;
                }
            }
            if (check && count == cardSize ) {
                row=user.length;
                return check;
            } else row++;
        }while(row !=user.length);
        return check;
    }



}


