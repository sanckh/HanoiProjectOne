package Hanoi;
import java.util.Scanner;


public class theGame {
    private Scanner in;
    private int n, current, r; //initialize for num of disks
    private int numTowers, currentTower, t; // initialize for num of towers
    private int[] hasItMoved, toDest; //init array for number of disks


    //you just lost
    theGame() {
        this.in = new Scanner(System.in);
        this.current = 1;
        System.out.println("Please enter the number of disks you'd like to use: ");
        this.n = in.nextInt();
        this.currentTower = 1;
        System.out.println("Please enter the number of towers you'd like to use (5-10): ");
        this.numTowers = in.nextInt();
        this.hasItMoved = new int[41]; //max of 40 disks
        this.toDest = new int[41];

        for (int j = 0; j < this.toDest.length; j++) {
            this.toDest[j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            this.toDest[i] = 0;
        }
        r = n;

        hanoiStart(n, "towerOne", "towerTwo", "towerFour", "towerThree", "towerFive", this.current);
    }

    public void hanoiStart(int numOfDisks, String towerOne, String towerTwo, String towerFour, String towerThree, String towerFive, int current) {
        move(1, towerOne, towerTwo, this.current);
        this.current++;
        Solve(n, "towerOne", "towerTwo", "towerFour", "towerThree", "towerFive", this.current);
        move(1, towerFour, towerFive, this.current);
        this.current++;
    }

    public int Solve(int numOfDisks, String towerOne, String towerTwo, String towerFour, String towerThree, String towerFive, int current) {
//below is the recursion programming for the disks movement
        if (numOfDisks == 1) {
            move(numOfDisks, towerTwo, towerThree, this.current);
            this.current++;
            move(numOfDisks, towerThree, towerFour, this.current);
            this.current++;
        } else if (numOfDisks == 2) {
            move(numOfDisks - 1, towerTwo, towerThree, this.current);
            this.current++;
            move(numOfDisks - 1, towerThree, towerFour, this.current);
            this.current++;
            if (this.current == 4) {
                move(numOfDisks, towerOne, towerTwo, this.current);
                this.current++;
            }
            move(numOfDisks, towerTwo, towerThree, this.current);
            this.current++;
            move(numOfDisks - 1, towerFour, towerThree, this.current);
            this.current++;
            move(numOfDisks - 1, towerThree, towerTwo, this.current);
            this.current++;
            move(numOfDisks, towerThree, towerFour, this.current);
            this.current++;
            if (this.r == 2) {
                move(2, towerFour, towerFive, this.current);
                this.current++;
            }
            move(numOfDisks - 1, towerTwo, towerThree, this.current);
            this.current++;
            move(numOfDisks - 1, towerThree, towerFour, this.current);
            this.current++;
        } else if (numOfDisks > 2) {
            this.current = Solve(numOfDisks - 1, towerOne, towerTwo, towerFour, towerThree, towerFive, this.current);
            if (this.hasItMoved[numOfDisks] != 1) {
                move(numOfDisks, towerOne, towerTwo, this.current);
                this.current++;
                this.hasItMoved[numOfDisks] = 1;
            }
            move(numOfDisks, towerTwo, towerThree, this.current);
            this.current++;
            this.current = Solve(numOfDisks - 1, towerOne, towerFour, towerTwo, towerThree, towerFive, this.current);
            move(numOfDisks, towerThree, towerFour, this.current);
            this.current++;
            if (this.toDest[numOfDisks + 1] != 0) {
                move(numOfDisks, towerFour, towerFive, this.current);
                this.current++;
                this.toDest[numOfDisks] = 1;
            }
            if (numOfDisks == r) {
                this.r--;
                this.current = Solve(numOfDisks - 1, towerOne, towerTwo, towerFour, towerThree, towerFive, this.current);
            }
        }
        return this.current;
    }
//print the movements to the console
    public void move(int aDisk, String source, String dest, int currentStep) {
        System.out.println("Move " + this.current + ": Move disk " + aDisk + " from " + source + " to " + dest);
    }
}

