// ---------------------------------------------------------------
// Sepehr Raissian
// Sraissia@ucsc.edu
// 12B pa4
// 11/22/16
// Simulation.java
// ----------------------------------------------------------------
import java.io.*;
import java.util.Scanner;
public class Simulation {
public static void main(String [] args) throws IOException{
        
          if(args.length < 1){
                  System.err.println("No infile given");
               System.exit(1);
             }
          Scanner in = null;// input file scanner
          PrintWriter report = null;// output file 
          PrintWriter trace = null;
      int m; // jobs
      Queue jobs = new Queue();
      
                        // opening input out files
                 in = new Scanner(new File(args[0]));
                m = Integer.parseInt(in.nextLine());
                 report = new PrintWriter(new FileWriter(args[0]+".rpt"));
                 trace = new PrintWriter(new FileWriter(args[0]+".trc"));
                 //report = new PrintWriter(new FileWriter(args[0] + ".rpt"));
                 //getting numbe of jobs
                 // getting the arrival time and duration
                 for(int i=0;i<m;i++)
                 {
                     jobs.enqueue(getJob(in));
                 }
                        in.close();
                        
                //System.out.println(jobs.toString());
                report.println("Report file: " + (args[0]+".rpt"));
                report.println(m + " Jobs:");
                report.println(jobs.toString());
                report.println();
                report.print("***********************************************************");
                //System.out.println(m);
                Queue[]  processor= new Queue[m+1]; // init Queue array of size m+1. +1 for storage
                //processor[0] will be used for storage
                for(int i =0;i<m+1;i++)
                {
                        processor[i] = new Queue();
                }
                while(!jobs.isEmpty())
                {
                        processor[0].enqueue(jobs.dequeue());
                }
                //System.out.println(processor[0].toString());
                int arr = ((Job) processor[0].peek()).getArrival();
                
                boolean finished=false;
                int time=0;
                while(!finished)
                {
                        time++;
                        if(time == arr) // if time is up
                        {
                                ((Job) processor[1].peek()).computeFinishTime(time);
                                arr = ((Job) processor[1].peek()).getFinish();
                                if(processor[1].length() == 2)
                                {
                                        processor[0].enqueue(processor[1].dequeue());
                                }
//                              if(processor[1].length() <=2)
//                              {
//                                      processor[1].enqueue(processor[0].dequeue());
//                              }
//                              ((Job) processor[1].peek()).computeFinishTime(time);
//                              arr = ((Job) processor[1].peek()).getFinish();
                                //processor[0].enqueue(processor[1].dequeue());
                        }
                        else if (processor[1].length() <=2)
                        {
                                Object a = processor[0].dequeue();
                                if(((Job)a).getFinish() == -1)
                                {
                                        processor[1].enqueue(a);
                                }
                                else
                                {
                                        finished = true;
                                }
                                
                        }
                        trace.println("time: "+time);
                        trace.println("processor[0]" + (processor[0].toString()));
                        trace.println("processor[1]" + (processor[1].toString()));
                        //report.println("processor[0]" + (processor[0].toString()));
                        //report.println("processor[1]" + (processor[1].toString()));
                }
                report.close();
                trace.close();
        }
           public static Job getJob(Scanner in) {
                 String[] s = in.nextLine().split(" ");
                       int a = Integer.parseInt(s[0]);
                             int d = Integer.parseInt(s[1]);
                                   return new Job(a, d);
                                      }
}

