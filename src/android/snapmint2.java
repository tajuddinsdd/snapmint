package cordova.plugins.snapmint2;

import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class echoes a string called from JavaScript.
 */
public class snapmint2 extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if (action.equals("openSnapmint")) {
            this.openSnapmint(args, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public static String generateCheckSum(String checkSumString) {
        Log.d("STR",checkSumString);
        String generatedCheckSum = "";
        MessageDigest messageDigest = null;
        try {
          messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        byte[] digest = messageDigest.digest(checkSumString.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
          stringBuilder.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    private void openSnapmint(JSONArray args, CallbackContext callbackContext) {
        if (args != null) {
            JSONObject finalData = null;
            String base_url = "";
            String option = "check_out";
            String suc_url = "";
            String fail_url = "";
            String checkSum = "";
            String finalDataString = "";
            try {
                JSONObject jsonObject = args.getJSONObject(0);
                finalData = jsonObject.getJSONObject("finalData");
                Log.d("finalData",finalData.toString());
                Log.d("authenticity_token",finalData.getString("authenticity_token"));
                base_url = jsonObject.getString("base_url");
              Log.d("base_url",base_url);
                suc_url = jsonObject.getString("suc_url");
                fail_url = jsonObject.getString("fail_url");
                if(finalData.has("utf8")&&!finalData.getString("utf8").equals("")){
                  finalDataString = finalDataString + "?utf8="+ finalData.getString("utf8");
                }
                finalDataString = finalDataString + "&co_source=sdk";
                if(finalData.has("authenticity_token")&&!finalData.getString("authenticity_token").equals("")){
                  finalDataString = finalDataString + "&authenticity_token="+ finalData.getString("authenticity_token");
                }
                if(finalData.has("source")&&!finalData.getString("source").equals("")){
                  finalDataString = finalDataString + "&source="+ finalData.getString("source");
                }
                if(finalData.has("user_type")&& !finalData.getString("user_type").equals("")){
                  finalDataString = finalDataString + "&user_type="+ finalData.getString("user_type");
                }
                if(finalData.has("mobile")&&!finalData.getString("mobile").equals("")){
                  finalDataString = finalDataString + "&mobile="+ finalData.getString("mobile");
                }
                if(finalData.has("merchant_id")&&!finalData.getString("merchant_id").equals("")){
                  finalDataString = finalDataString + "&merchant_id="+ finalData.getString("merchant_id");
                }
                if(finalData.has("store_id")&&!finalData.getString("store_id").equals("")){
                  finalDataString = finalDataString  + "&store_id="+ finalData.getString("store_id");
                }
                if(finalData.has("order_id")&&!finalData.getString("order_id").equals("")){
                  finalDataString = finalDataString + "&order_id="+ finalData.getString("order_id");
                }
                if(finalData.has("order_value")&&!finalData.getString("order_value").equals("")){
                  finalDataString = finalDataString + "&order_value="+ finalData.getString("order_value");
                }
                if(finalData.has("merchant_confirmation_url")&&!finalData.getString("merchant_confirmation_url").equals("")){
                  finalDataString = finalDataString + "&merchant_confirmation_url="+ finalData.getString("merchant_confirmation_url");
                }
                if(finalData.has("merchant_failure_url")&&!finalData.getString("merchant_failure_url").equals("")){
                  finalDataString = finalDataString + "&merchant_failure_url="+ finalData.getString("merchant_failure_url");
                }
                if(finalData.has("shipping_fees")&&!finalData.getString("shipping_fees").equals("")){
                  finalDataString = finalDataString + "&shipping_fees="+ finalData.getString("shipping_fees");
                }
                if(finalData.has("udf1")&&!finalData.getString("udf1").equals("")){
                  finalDataString = finalDataString + "&udf1="+ finalData.getString("udf1");
                }
                if(finalData.has("udf2")&&!finalData.getString("udf2").equals("")){
                  finalDataString = finalDataString + "&udf2="+ finalData.getString("udf2");
                }
                if(finalData.has("udf3")&&!finalData.getString("udf3").equals("")){
                  finalDataString = finalDataString + "&udf3="+ finalData.getString("udf3");
                }
                if(finalData.has("full_name")&&!finalData.getString("full_name").equals("")){
                  finalDataString = finalDataString + "&full_name="+ finalData.getString("full_name");
                }
                if(finalData.has("email")&&!finalData.getString("email").equals("")){
                  finalDataString = finalDataString + "&email="+ finalData.getString("email");
                }
                if(finalData.has("billing_first_name")&&!finalData.getString("billing_first_name").equals("")){
                  finalDataString = finalDataString + "&billing_first_name="+ finalData.getString("billing_first_name");
                }
                if(finalData.has("billing_middle_name")&&!finalData.getString("billing_middle_name").equals("")){
                  finalDataString = finalDataString + "&billing_middle_name="+ finalData.getString("billing_middle_name");
                }
                if(finalData.has("billing_last_name")&&!finalData.getString("billing_last_name").equals("")){
                  finalDataString = finalDataString + "&billing_last_name="+ finalData.getString("billing_last_name");
                }
                if(finalData.has("billing_full_name")&&!finalData.getString("billing_full_name").equals("")){
                  finalDataString = finalDataString + "&billing_full_name="+ finalData.getString("billing_full_name");
                }
                if(finalData.has("billing_address_line1")&&!finalData.getString("billing_address_line1").equals("")){
                  finalDataString = finalDataString + "&billing_address_line1="+ finalData.getString("billing_address_line1");
                }
                if(finalData.has("billing_address_line2")&&!finalData.getString("billing_address_line2").equals("")){
                  finalDataString = finalDataString + "&billing_address_line2="+ finalData.getString("billing_address_line2");
                }
                if(finalData.has("billing_city")&&!finalData.getString("billing_city").equals("")){
                  finalDataString = finalDataString + "&billing_city="+ finalData.getString("billing_city");
                }
                if(finalData.has("billing_zip")&&!finalData.getString("billing_zip").equals("")){
                  finalDataString = finalDataString + "&billing_zip="+ finalData.getString("billing_zip");
                }
                if(finalData.has("shipping_first_name")&&!finalData.getString("shipping_first_name").equals("")){
                  finalDataString = finalDataString + "&shipping_first_name="+ finalData.getString("shipping_first_name");
                }
                if(finalData.has("shipping_middle_name")&&!finalData.getString("shipping_middle_name").equals("")){
                  finalDataString = finalDataString + "&shipping_middle_name="+ finalData.getString("shipping_middle_name");
                }
                if(finalData.has("shipping_last_name")&&!finalData.getString("shipping_last_name").equals("")){
                  finalDataString = finalDataString + "&shipping_last_name="+ finalData.getString("shipping_last_name");
                }
                if(finalData.has("shipping_full_name")&&!finalData.getString("shipping_full_name").equals("")){
                  finalDataString = finalDataString + "&shipping_full_name="+ finalData.getString("shipping_full_name");
                }
                if(finalData.has("shipping_address_line1")&&!finalData.getString("shipping_address_line1").equals("")){
                  finalDataString = finalDataString + "&shipping_address_line1="+ finalData.getString("shipping_address_line1");
                }
                if(finalData.has("shipping_address_line2")&&!finalData.getString("shipping_address_line2").equals("")){
                  finalDataString = finalDataString + "&shipping_address_line2="+ finalData.getString("shipping_address_line2");
                }
                if(finalData.has("shipping_city")&&!finalData.getString("shipping_city").equals("")){
                  finalDataString = finalDataString + "&shipping_city="+ finalData.getString("shipping_city");
                }
                if(finalData.has("shipping_zip")&&!finalData.getString("shipping_zip").equals("")){
                  finalDataString = finalDataString + "&shipping_zip="+ finalData.getString("shipping_zip");
                }
                if(finalData.has("products_sku")&&!finalData.getString("products_sku").equals("")){
                  finalDataString = finalDataString + "&products[][sku]="+ finalData.getString("products_sku");
                }
                if(finalData.has("products_unit_price")&&!finalData.getString("products_unit_price").equals("")){
                  finalDataString = finalDataString + "&products[][products_unit_price]="+ finalData.getString("products_unit_price");
                }
                if(finalData.has("products_quantity")&&!finalData.getString("products_quantity").equals("")){
                  finalDataString = finalDataString + "&products[][products_quantity]="+ finalData.getString("products_quantity");
                }
                if(finalData.has("merchant_key")&&!finalData.getString("merchant_key").equals("")){
                  finalDataString = finalDataString + "&merchant_key="+ finalData.getString("merchant_key");
                }
                if(finalData.has("merchant_token")&&!finalData.getString("merchant_token").equals("")){
                  finalDataString = finalDataString + "&merchant_token="+ finalData.getString("merchant_token");
                }
                try {
                  finalDataString = finalDataString + "&checksum_hash="+ generateCheckSum(finalData.getString("merchant_key") + "|" + finalData.getString("order_id") +"|"+ finalData.getString("order_value") +"|"+ finalData.getString("full_name") +"|"+ finalData.getString("email") +"|"+ finalData.getString("merchant_token"));
                } catch (Exception e) {
                  Log.d("Errror", e.toString());
                }
                Log.d("finalDataString",finalDataString.toString());
            } catch (Exception e){ }
            callbackContext.success(finalDataString);
        } else {
           callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
