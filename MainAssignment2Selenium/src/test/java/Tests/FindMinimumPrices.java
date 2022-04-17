package Tests;

import java.util.ArrayList;

public class FindMinimumPrices {
    public String getMinimumBrand(ArrayList<Integer> myList, ArrayList<String> stringList){
        int i = 0;
        int flag = 0;
        int min = myList.get(0);
        for(i = 1 ; i < myList.size() ; i++){
            if(min>myList.get(i)){
                min = myList.get(i);
                flag = i;
            }
        }
        return stringList.get(flag);

    }

}
