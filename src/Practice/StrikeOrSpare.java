package Practice;

import java.io.*;

class StrikeOrSpare
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Integer T=Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++)
        {
            Integer N=Integer.parseInt(br.readLine());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("1 1");
            for(int j=0;j<N/2;j++)
            {
                stringBuilder.append("0");
            }
            String finalString = stringBuilder.toString();
            System.out.println(finalString);
        }
    }
}
