package dsa450;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
   
    public static void main(String[] args) {
        String jsonData = "{\"machine\":{\"outlets\":{\"count_n\":4},\"total_items_quantity\":{\"hot_water\":500,\"hot_milk\":500,\"ginger_syrup\":100,\"sugar_syrup\":100,\"tea_leaves_syrup\":100},\"beverages\":{\"hot_tea\":{\"hot_water\":200,\"hot_milk\":100,\"ginger_syrup\":10,\"sugar_syrup\":10,\"tea_leaves_syrup\":30},\"hot_coffee\":{\"hot_water\":100,\"ginger_syrup\":30,\"hot_milk\":400,\"sugar_syrup\":50,\"tea_leaves_syrup\":30},\"black_tea\":{\"hot_water\":300,\"ginger_syrup\":30,\"sugar_syrup\":50,\"tea_leaves_syrup\":30},\"green_tea\":{\"hot_water\":100,\"ginger_syrup\":30,\"sugar_syrup\":50,\"green_mixture\":30}}}}";
        try {        
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject totlItems = (JSONObject)jsonObject.getJSONObject("machine").get("total_items_quantity");
            JSONObject beverages = (JSONObject)jsonObject.getJSONObject("machine").get("beverages");
            Iterator totalItems = totlItems.keys();
            Iterator beveragesIt = beverages.keys();
            HashMap<String , Integer> totalQuantityMap = new HashMap<>();
            HashMap<String , HashMap<String,Integer>> beveragesMap = new HashMap<>();
            while (totalItems.hasNext()){
                String key = (String) totalItems.next();
                totalQuantityMap.put(key,(int)totlItems.get(key));
            }
            while (beveragesIt.hasNext()){
                String key = (String) beveragesIt.next();
                JSONObject tempjson = beverages.getJSONObject(key);
                Iterator tempItr = tempjson.keys();
                HashMap<String, Integer> itemsMap = new HashMap<String, Integer>();
                while(tempItr.hasNext()) {
                	String k =(String) tempItr.next();
                	itemsMap.put(k,(int) tempjson.get(k));
                }
                beveragesMap.put(key, itemsMap);
                //itemsMap.clear();
                
            }
            System.out.println("beverages : "+beveragesMap);
            System.out.println("totalQuantity : "+totalQuantityMap);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }  
}