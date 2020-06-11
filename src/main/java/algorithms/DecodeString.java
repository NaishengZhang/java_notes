package algorithms;

import java.util.ArrayList;

public class DecodeString {

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        String input = "abc{d,e,f}gh{i,j}";
        ArrayList<String> res = new ArrayList<>();
        res.add(input);
        ds.decode(res, input);
        System.out.println(res);
    }

    private void decode(ArrayList<String> res, String input) {
        int size = res.size();
        int i = 0;
        while (i < size){
            String str = res.get(0);
            int index1 = str.indexOf('{');
            int index2 = str.indexOf('}');
            if (index1 == -1 || index2 == -1) {
                return;
            }
            String prefix = str.substring(0, index1);
            String suffix = str.substring(index2 + 1);
            String[] bucket = str.substring(index1 + 1, index2).split(",");
            for (String s : bucket) {
                res.add(prefix + s + suffix);
            }
            res.remove(str);
        }
        decode(res, input);
    }
}
