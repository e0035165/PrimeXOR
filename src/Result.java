import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;




class Result {
    public static boolean[] primearray = new boolean[8192];
    public static final Integer MOD = 1000000007;
    public static Long primeXor(List<Integer> a)
    {

        Set<Integer>tester = new LinkedHashSet<Integer>();
        List<Integer>newlist = new ArrayList<>();

        int[]primetime = new int[8192];
        for(Integer i : a)
        {
            tester.add(i);
            primetime[i]++;
        }
        for(Integer i:tester)
        {
            newlist.add(i);
        }
      /*FileWriter fw = null;
      try {
      //fr = new FileReader("C:\\Users\\ksathyas\\OneDrive - Capgemini\\Documents\\tc2.txt");
      fw = new FileWriter("C:\\Users\\ksathyas\\OneDrive - Capgemini\\Documents\\Answer.txt");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
      BufferedWriter buffwrite = new BufferedWriter(fw);*/
        long[][]matrix = new long[newlist.size() + 1][8192];
        for(int i=0;i<=newlist.size();i++)
        {
            for(int j=0;j<8192;j++)
            {
                matrix[i][j] = 0;
            }

        }


        for(int j=0;j<8192;j++)
        {
            matrix[newlist.size()][j] = primearray[j] == true ? 1 : 0;
        }

        for(int i=newlist.size()-1;i>=0;--i)
        {
            for(int j=0;j<8192;++j)
            {
                long n = primetime[newlist.get(i)];
                long in = 0;
                long out = 0;

                if(n%2 == 0)
                {
                    out = (n/2) + 1;
                    in = (n/2);
                } else {
                    out = (n+1)/2;
                    in = out;
                }

                long A = out*matrix[i+1][j];
                long B = in*matrix[i+1][j^newlist.get(i)];
                if(A >= MOD)
                {
                    A %= MOD;
                }

                if(B >= MOD)
                {
                    B %= MOD;
                }
                matrix[i][j] = (A + B)%MOD;

            }
        }
        return matrix[0][0];
    }
    public static long Naive(int x, List<Integer>lap, int carried_forward, int[] primetime)
    {

        if(x == lap.size())
        {
            if(primearray[carried_forward])
            {
                return 1;
            } else {
                return 0;
            }
        }
        long n = primetime[lap.get(x)];
        long in = 0;
        long out = 0;
        //0-2-4-6 => 2 carried_forwards
        //1-3-5-7-9
        if(n%2 == 0)
        {
            out = (n/2) + 1;
            in = (n/2);
        } else {
            out = (n+1)/2;
            in = out;
        }



        int temp = carried_forward ^ lap.get(x);



        long A = out * (Naive(x+1,lap,carried_forward,primetime));
        long B = in*Naive(x+1,lap,temp,primetime);
        if(A >= MOD)
        {
            A %= MOD;
        }

        if(B >= MOD)
        {
            B %= MOD;
        }

        if(A + B >= MOD)
        {
            return (A+B)%MOD;
        } else {
            return A+B;
        }

    }

    public static void SetArray()
    {
        for(int i=0;i<8192;i++)
        {
            primearray[i] = true;
            for(int j=i/2;j>1;j--)
            {
                if(i%j == 0)
                {
                    primearray[i] = false;
                    break;
                }
            }
        }

        primearray[0] = false;
        primearray[1] = false;
    }

}
