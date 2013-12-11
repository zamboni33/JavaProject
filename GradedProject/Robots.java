package GradedProject;

public class Robots {
    public static void main(String[] args) {
       args = new String[3];
       args[0] = "p2";
       args[1] = "src/test-task-times.txt";
       args[2] = "src/test-task-dependencies.txt";
       
//       args = new String[3];       
//       args[0] = "p3";
//       args[1] = "6";
//       args[2] = "src/test-cleanup.txt";
//       args[2] = "src/testNoah.txt";
    	
    	if (args.length == 0) {
            usage();
        }

        if (args[0].equals("p1")) {
            if (args.length != 3) {
                usage();
            }
            Problem1 p1 = new Problem1(args[1], args[2]);
            p1.solve();
        } else if (args[0].equals("p2")) {
            if (args.length != 3) {
                usage();
            }
            Problem2 p2 = new Problem2(args[1], args[2]);
            p2.solve();
        } else if (args[0].equals("p3")) {
            if (args.length != 3) {
                usage();
            }
            Problem3 p3 = new Problem3(args[1], args[2]);
            p3.solve();
        } else {
            usage();
        }
    }

    public static void usage() {
        System.err.println("Wrong arguments format. Usage:");
        System.err.println("  java Robots p1 <d> <robot-capabilities-file>");
        System.err.println("  java Robots p2 <task-times-file> <task-dependencies-file>");
        System.err.println("  java Robots p3 <h> <cleanup-file>");
        System.exit(1);
    }
}