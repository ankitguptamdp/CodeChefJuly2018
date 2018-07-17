package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NoStringsAttached
{
    private static int calculateY(char[] charString)
    {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < 26; j++)
        {
            map.put(j, 0);
        }
        for (int i = charString.length - 1; i >= 0; i--)
        {
            for (int j = charString[i] - 'a' + 1; j <= 'z' - 'a'; j++)
            {
                count += map.get(j);
            }
            map.put(charString[i] - 'a', map.get(charString[i] - 'a') + 1);
        }
        return count;
    }

    private static int[] calculateMinimumEffect(int[][] prefixArray, int[][] suffixArray, String string)
    {
        int[] resultArray = new int[3];
        int maximumDifference = -Integer.MAX_VALUE;
        int abscissa = 0;
        int ordinate = 0;
        for (int i = 0; i < prefixArray.length; i++)
        {
            int temp = prefixArray[i][string.charAt(i) - 'a'] + suffixArray[i][string.charAt(i) - 'a'];
            for (int j = 0; j < 26; j++)
            {
                int localMaximumDifference = temp - (prefixArray[i][j] + suffixArray[i][j] + Math.abs(string.charAt(i) - 'a' - j));
                if (localMaximumDifference > maximumDifference)
                {
                    maximumDifference = localMaximumDifference;
                    abscissa = i;
                    ordinate = j;
                }
            }
        }
        resultArray[0] = maximumDifference;
        resultArray[1] = abscissa;
        resultArray[2] = ordinate;
        return resultArray;
    }

    private static int[][] suffixPart(int[][] suffixArray, String string)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < 26; j++)
        {
            map.put(j, 0);
        }
        for (int j = string.length() - 1; j >= 0; j--)
        {
            for (int k = 24; k >= 0; k--)
            {
                suffixArray[j][k] = suffixArray[j][k + 1] + map.get(k + 1);
            }
            map.put(string.charAt(j) - 'a', map.get(string.charAt(j) - 'a') + 1);
        }
        return suffixArray;
    }

    private static int[][] prefixPart(int[][] prefixArray, String string)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < 26; j++)
        {
            map.put(j, 0);
        }
        for (int j = 0; j < string.length(); j++)
        {
            for (int k = 1; k < 26; k++)
            {
                prefixArray[j][k] = prefixArray[j][k - 1] + map.get(k - 1);
            }
            map.put(string.charAt(j) - 'a', map.get(string.charAt(j) - 'a') + 1);
        }
        return prefixArray;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer T = Integer.parseInt(br.readLine());
        String[] stringArray = new String[T];
        int[] outputArray = new int[T];
        for (int i = 0; i < T; i++)
        {
            stringArray[i] = br.readLine();
        }
        for (int i = 0; i < T; i++)
        {
            String string = stringArray[i];
            int[][] prefixArray = new int[string.length()][26];
            prefixArray = prefixPart(prefixArray, string);
            int[][] suffixArray = new int[string.length()][26];
            suffixArray = suffixPart(suffixArray, string);
            int[] resultArray = calculateMinimumEffect(prefixArray, suffixArray, string);
            char[] charString = string.toCharArray();
            int X = Math.abs(charString[resultArray[1]] - (char) ('a' + resultArray[2]));
            charString[resultArray[1]] = (char) ('a' + resultArray[2]);
            int Y = calculateY(charString);
            outputArray[i] = X + Y;
        }
        for (int i = 0; i < T; i++)
        {
            System.out.println(outputArray[i]);
        }
    }
}
