import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class messingAround {
    public static void main(String[] args) {
        ArrayList<String> uniqueness = new ArrayList<>();

        int blah =1;
        while(blah!=76) {
           System.out.print(drawRandomNum(uniqueness) + " ");
           if(blah%10 ==0){
               System.out.println();
           }
            blah++;
        }




        /*
        String [][] matrix = {{"X","2","3","4",},{"4","X","2","1"},{"7","8","X","6"},{"6","5","4","X"}};
        String [][] matrix2 = {{"1","2","3","O",},{"4","3","O","1"},{"7","O","4","6"},{"O","5","4","1"}};
        String [][] matrix3 = {{"1","X","3","8",},{"2","X","6","1"},{"3","X","4","6"},{"4","X","4","1"}};
        String [][] matrix4 = {{"1","X","3","8",},{"2","X","6","1"},{"3","X","4","6"},{"4","X","4","1"}};

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // Condition for principal diagonal
                if (i == j && matrix[i][j].equals("X")) {
                    System.out.print(matrix[i][j]);
                }
            }
        }


        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                // Condition for principal diagonal
                if (((i+j)== matrix2.length-1) && matrix2[i][j].equals("O")) {
                    System.out.print(matrix2[i][j]);
                }
            }
        }


        System.out.println("");
        boolean check = false;
        int col =0;
        int count =0;

        do {
            for (int b = 0; b < matrix4.length; b++) {
                System.out.println(matrix4[b][col]);
                if (matrix4[b][col] == "X") {
                    check = true;
                    count++;
                } else {
                    check = false;
                    count = 0;
                }
            }
            if (check && count == 4 ) {
                col=matrix4.length;
                System.out.println("You win");
            } else col++;
        }while(col !=matrix4.length);



        while (col!=matrix4.length) {
            for (int a = 0; a < matrix4.length; a++) {
                if (matrix4[a][col].equals("X")) {
                    check = true;
                }
                else {check = false;}
            }
            if (check){
                break;
            }
            else{
            col++;}
            if (check){System.out.println("hey winner");
            }
        }



        for (String [] element:matrix4){
            for (String x:element){
                System.out.print(x);
            }
            System.out.println();
        }


        */
    }

    public static String randomNumGen() {
        int randTemp;
        Random randGen = new Random();
        randTemp = (randGen.nextInt(75))+1;
        String randNum = Integer.toString(randTemp);
        return randNum;
    }
    public static boolean alreadyIn1D(String num,ArrayList<String>player) {
        for (int i = 0; i < player.size(); i++) {
            if (num.equals(player.get(i))) {
                return true;
            }
        }

        return false;
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
}
