import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // convert to binary and split into strings of consecutive ones
        String[] groupings = Integer.toBinaryString(n).split("0");
        // sorting strings only composed of a single character ("1") orders them by length
        Arrays.sort(groupings);
        
        // print length of lexicographically last string
        System.out.println(groupings[groupings.length - 1].length());
    }
}
