package GradedProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem1 {
    private int d;
    private Scanner capabilitiesFile;
    
    public Problem1(String days, String capabilities) {
        d = Integer.parseInt(days);
        File f = new File(capabilities);
        try {
            capabilitiesFile = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.printf("Could not find <robot-capabilities-file> (%s)\n", f.toString());
            System.exit(1);
        }
    }

    /**
     * This is an example of how to parse the input file. Please reference,
     * update, or discard it as appropriate to your implementation.
     * You may want to read in the data from the file and save it in private
     * variables for later use in the solve() method.
     * 
     * You can also adapt this code for Problem2 and Problem3.
     * For Problem2 you will have to read 2 input files.
     */
    private void parseInput() {
        int n = capabilitiesFile.nextInt();
        int m = capabilitiesFile.nextInt();
        System.out.printf("n=%d m=%d\n", n, m);

        capabilitiesFile.nextLine();
        int k = 1;
        while (capabilitiesFile.hasNext()) {
            String s = capabilitiesFile.nextLine();
            String[] a = s.split(" ");
            System.out.print("Robot " + k + ": ");
            for (int i = 0; i < a.length; i++) {
                System.out.printf("%s ", a[i]);
            }
            System.out.println();
            k++;
        }
    }
    
    public void solve() {
        parseInput();
        
        // TODO your code here
    }
}
