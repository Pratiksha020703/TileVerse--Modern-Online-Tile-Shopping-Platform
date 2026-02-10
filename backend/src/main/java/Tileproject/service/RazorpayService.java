package Tileproject.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.razorpay.Utils;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class RazorpayService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    public String getKeyId() {
        return keyId;
    }
// public JSONObject createOrder(int amount) throws RazorpayException {

//     RazorpayClient client = new RazorpayClient(keyId, keySecret);

//     JSONObject orderRequest = new JSONObject();

//     orderRequest.put("amount", amount * 100);   // 🔥 convert rupees → paise
//     orderRequest.put("currency", "INR");
//     orderRequest.put("receipt", "order_" + System.currentTimeMillis());
//     orderRequest.put("payment_capture", 1);

//     return client.orders.create(orderRequest);
// }

public JSONObject createOrder(int amount) throws Exception {

    RazorpayClient client = new RazorpayClient(keyId, keySecret);

    JSONObject orderRequest = new JSONObject();
    orderRequest.put("amount", amount * 100);
    orderRequest.put("currency", "INR");
    orderRequest.put("receipt", "order_" + System.currentTimeMillis());
    orderRequest.put("payment_capture", 1);

    com.razorpay.Order order = client.orders.create(orderRequest);

    return order.toJson();   // 🔥 THIS FIX
}





public boolean verifyPayment(String orderId, String paymentId, String signature) throws Exception {

    String data = orderId + "|" + paymentId;

    return Utils.verifySignature(data, signature, keySecret);
}


}


// package Tileproject.service;

// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.razorpay.RazorpayClient;
// import com.razorpay.RazorpayException;
// import com.razorpay.Utils;

// import com.razorpay.Order;



// @Service
// public class RazorpayService {

//     @Value("${razorpay.key.id}")
//     private String keyId;

//     @Value("${razorpay.key.secret}")
//     private String keySecret;

//     public String getKeyId() {
//         return keyId;
//     }

//     // 🔥 Create Razorpay order
//     public Order createOrder(int amount) throws RazorpayException {

//     RazorpayClient client = new RazorpayClient(keyId, keySecret);

//     JSONObject orderRequest = new JSONObject();
//     orderRequest.put("amount", amount * 100);   // rupees → paise
//     orderRequest.put("currency", "INR");
//     orderRequest.put("receipt", "order_" + System.currentTimeMillis());
//     orderRequest.put("payment_capture", 1);

//     return client.orders.create(orderRequest);   // returns Razorpay Order object
// }

//     // 🔐 Verify payment
//     public boolean verifyPayment(String orderId, String paymentId, String signature) throws Exception {

//         String data = orderId + "|" + paymentId;
//         return Utils.verifySignature(data, signature, keySecret);
//     }
// }
