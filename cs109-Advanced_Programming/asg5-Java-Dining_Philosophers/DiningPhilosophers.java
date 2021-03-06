/* $Id: DiningPhilosophers.java,v 1.2 2012/12/05 04:49:23 dmf Exp $
 * Derek Frank, dmfrank@ucsc.edu
 *
 * NAME
 *    DiningPhilosophers
 *
 * DESCRIPTION
 *    The class containing main().  Opens and scans a file.  Expects
 *    the first line to contain a single int specifying the number of
 *    following lines and number of Philosopher Threads to be created.
 *    The rest of the file should contain two ints on each line.  The
 *    first int specifies the amount of eating time in milliseconds a
 *    Philosopher Thread will spend eating.  The second int specifies
 *    the amount of thinking time in milliseconds the same Philosopher
 *    Thread will spend thinking.  Each Philosopher Thread will then
 *    be created and started.  No deadlocking should occur.
 */

import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;

class DiningPhilosophers {

   //
   // doThreading
   //    Takes paramaters that specify the number of Philosophers
   //    (Threads) to be created, along with their corresponding
   //    eat and think times in arrays of ints.
   //
   static void doThreading (int n, int[] eTime, int[] tTime) {
      // Throw some errors if information does not correspond.
      if (n != eTime.length)
         throw new Error
            ("number of times does not equal specified number");
      
      String left = "left";
      String right = "right";
      String hand = "left";
     
      Fork[] utensil = new Fork [n];
      Philosopher[] phils = new Philosopher [n];
      Thread[] philsThread = new Thread [n];

      for (int i = 0; i < n; ++i) {
         // Alternate handedness to keep from deadlocking.
         if ( hand.equals (left) ) { hand = right; }
         else { hand = left; }
         // Make Forks and Philosophers
         if (i != (n-1)) utensil[i] = new Fork ();
         if (i != 0) {
            phils[i] = new Philosopher (i+1, eTime[i], tTime[i],
                                        utensil[i-1], utensil[i], hand);
         }else if (i == 0) {
            utensil[n-1] = new Fork ();
            phils[i] = new Philosopher (i+1, eTime[i], tTime[i],
                                        utensil[n-1], utensil[i], hand);
         }
         // Make Threads out of Philosophers.
         philsThread[i] = new Thread (phils[i]);
      }

      // Start Threads.
      for (int i = 0; i < n; ++i)
         philsThread[i].start();

   }
   
   //
   // scanfile
   //    Scans through a file and on doThreading() to create and start
   //    Philosophers to eat and think with the information provided by
   //    the file.
   //
   static void scanfile (String filename, Scanner scan) {
      int n = scan.nextInt();
      int[] first = new int[n];
      int[] second = new int[n];
      for (int i = 0; scan.hasNextLine() && i < n; ++i) {
         first[i] = scan.nextInt();
         second[i] = scan.nextInt();
      }
      doThreading (n, first, second);
   }

   //
   // main
   //    Calls scanfile() to read files specified in args[]
   //
   public static void main (String[] args) {
      if (args.length == 0) {
         err.printf ("no file names specified:%n");
         err.printf
            ("DiningPhilosophers usage: DiningPhilosophers file...%n");
      }else {
         for (int i = 0; i < args.length; ++i) {
            String filename = args[i];
            try {
               Scanner scan = new Scanner (new File (filename));
               scanfile (filename, scan);
               scan.close();
            }catch (IOException error) {
               out.printf ("%s%n", error);
            }
         }
      }
   }
}



