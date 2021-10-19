package Hanoi;
import java.util.Scanner;

public class Towers {
    private Scanner in;
    private int n, current, r;
    private int[] hasItMoved, toDest;

    Towers() {
        this.in = new Scanner(System.in);
        this.current = 1;
        System.out.println("Enter the number of disks: ");
        this.n = in.nextInt();
        this.hasItMoved = new int[12];
        this.toDest = new int[12];

        for (int j = 0; j < this.toDest.length; j++) {
            this.toDest[j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            this.toDest[i] = 0;
        }
        r = n;

        hanoiStart(n, "Start", "Aux1", "Aux3", "Aux2", "Destination", this.current);
    }

    public void hanoiStart(int numOfDisks, String Start, String source, String dest, String aux, String last, int current) {
        move(1, Start, source, this.current);
        this.current++;
        H1(n, "Start", "Aux1", "Aux3", "Aux2", "Dest", this.current);
        move(1, dest, last, this.current);
        this.current++;
    }

    public int H1(int numOfDisks, String Start, String source, String dest, String aux, String last, int current) {

        if (numOfDisks == 1) {
            move(numOfDisks, source, aux, this.current);
            this.current++;
            move(numOfDisks, aux, dest, this.current);
            this.current++;
        } else if (numOfDisks == 2) {
            move(numOfDisks - 1, source, aux, this.current);
            this.current++;
            move(numOfDisks - 1, aux, dest, this.current);
            this.current++;
            if (this.current == 4) {
                move(numOfDisks, Start, source, this.current);
                this.current++;
            }
            move(numOfDisks, source, aux, this.current);
            this.current++;
            move(numOfDisks - 1, dest, aux, this.current);
            this.current++;
            move(numOfDisks - 1, aux, source, this.current);
            this.current++;
            move(numOfDisks, aux, dest, this.current);
            this.current++;
            if (this.r == 2) {
                move(2, dest, last, this.current);
                this.current++;
            }
            move(numOfDisks - 1, source, aux, this.current);
            this.current++;
            move(numOfDisks - 1, aux, dest, this.current);
            this.current++;
        } else if (numOfDisks > 2) {
            this.current = H1(numOfDisks - 1, Start, source, dest, aux, last, this.current);
            if (this.hasItMoved[numOfDisks] != 1) {
                move(numOfDisks, Start, source, this.current);
                this.current++;
                this.hasItMoved[numOfDisks] = 1;
            }
            move(numOfDisks, source, aux, this.current);
            this.current++;
            this.current = H1(numOfDisks - 1, Start, dest, source, aux, last, this.current);
            move(numOfDisks, aux, dest, this.current);
            this.current++;
            if (this.toDest[numOfDisks + 1] != 0) {
                move(numOfDisks, dest, last, this.current);
                this.current++;
                this.toDest[numOfDisks] = 1;
            }
            if (numOfDisks == r) {
                this.r--;
                this.current = H1(numOfDisks - 1, Start, source, dest, aux, last, this.current);
            }
        }
        return this.current;
    }

    public void move(int aDisk, String source, String dest, int currentStep) {
        System.out.println("Move " + this.current + ": Move disk " + aDisk + " from " + source + " to " + dest);
    }
}

