package Practice;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

//Partially Solved

public class NoMinimumNoMaximum
{
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do
            {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    
    static long[][] ncr=new long[5001][5001];
    private static final int MOD=1000000007;
    private static final int MODMINUSONE=MOD-1;

    public static long fastPower(long a,long b)
    {
        long result=1;
        while(b>0)
        {
            long lastBit = b & 1;
            if (lastBit == 1)
            {
                result = (result*a)%MOD;
            }
            a = (a*a)%MOD;
            b >>= 1;
        }
        return result;
    }

    static void buildPascalTriangle()
    {
        ncr[1][0]=ncr[1][1]=1;
        for(int i=2;i<=5000;i++)
        {
            ncr[i][0]=ncr[i][i]=1;
            for(int j=1;j<i;j++)
            {
                ncr[i][j]=(ncr[i-1][j-1]+ncr[i-1][j])%(MODMINUSONE);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        Reader s = new Reader();
        buildPascalTriangle();
        int T=s.nextInt();
        for(int i=0;i<T;i++)
        {
            int N=s.nextInt();
            int K=s.nextInt();
            int[] a=new int[N];
            for(int j=0;j<N;j++)
            {
                a[j]=s.nextInt();
            }
            Arrays.sort(a);
            long[] power=new long[N];
            power[0]=power[N-1]=0;
            for(int j=1;j<N-1;j++)
            {
                power[j]=((ncr[N-1][K-1]-ncr[N-1-j][K-1]+MODMINUSONE)%MODMINUSONE-ncr[j][K-1]+MODMINUSONE)%MODMINUSONE;
            }
            long answer=1;
            for(int j=1;j<N-1;j++)
            {
                answer=(answer*fastPower(a[j],power[j]))%MOD;
            }
            System.out.println(answer%MOD);
        }
    }
}
