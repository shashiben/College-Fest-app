package com.cse.cynosure.Payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cse.cynosure.MainActivity;
import com.cse.cynosure.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PaytmPayment extends AppCompatActivity {
    String customerId = "";
    String orderId = "";
    String mid = "JmGlzG94695433162253";
    String amount = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm_payment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        customerId = generateString();
        orderId = generateString();
        amount = getIntent().getStringExtra("amount");
        getCheckSum cs = new getCheckSum();
        cs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class getCheckSum extends AsyncTask<ArrayList<String>, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(PaytmPayment.this);
        String url="https://cyno2k20.000webhostapp.com/paytm/generateChecksum.php";
        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";

        String CHECKSUMHASH ="";
        @Override
        protected void onPreExecute() {
            this.dialog.setTitle("Payment");
            this.dialog.setMessage("Initialising");
            this.dialog.show();
        }
        @SafeVarargs
        protected final String doInBackground(ArrayList<String>... alldata) {
            JsonParse jsonParser = new JsonParse(PaytmPayment.this);
            String param=
                    "MID="+mid+
                            "&ORDER_ID=" + orderId+
                            "&CUST_ID="+customerId+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT="+amount+"&WEBSITE=DEFAULT"+
                            "&CALLBACK_URL="+ "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId+"&INDUSTRY_TYPE_ID=Retail";
            Log.e("PostData",param);
            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            Log.e("CheckSum result >>",jsonObject.toString());
            Log.e("CheckSum result >>",jsonObject.toString());
            try {
                CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                Log.e("CheckSum result >>",CHECKSUMHASH);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return CHECKSUMHASH;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            //  PaytmPGService Service = PaytmPGService.getStagingService();
            PaytmPGService Service = PaytmPGService.getProductionService();
            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("MID", mid);
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID", customerId);
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", amount);
            paramMap.put("WEBSITE", "DEFAULT");
            paramMap.put("CALLBACK_URL" ,"https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId);
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            Service.startPaymentTransaction(PaytmPayment.this, true,
                    true,
                    new PaytmPaymentTransactionCallback() {
                        @Override
                        public void onTransactionResponse(Bundle bundle) {
                            Toast.makeText(PaytmPayment.this, "Payment successful", Toast.LENGTH_LONG).show();
                            if(bundle.getString("STATUS").equals("TXN_SUCCESS")){
                                FirebaseFirestore db=FirebaseFirestore.getInstance();

                                final  Intent i=getIntent();
                                Map<String, Object> user = new HashMap<>();
                                Bundle b=i.getExtras();
                                assert b != null;

                                user.put("Name",b.getString("name"));
                                user.put("College", b.getString("college"));
                                user.put("Mode","App");
                                user.put("Phone",b.getString("phone"));
                                user.put("Accommodation",b.getString("Accommodation"));
                                db.collection("users").document(Objects.requireNonNull(b.getString("email")))
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                showNotification(PaytmPayment.this,"Register Successful","Thanks for Registering");
                                                startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                                                Animatoo.animateFade(PaytmPayment.this);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(PaytmPayment.this, "Error writing document", Toast.LENGTH_LONG).show();
                                            }
                                        });
                            }else{
                                Toast.makeText(PaytmPayment.this, "Transaction Cancelled", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                                Animatoo.animateFade(PaytmPayment.this);
                                finish();
                            }

                        }


                        @Override
                        public void networkNotAvailable() {
                            Log.e("Trans ", "Network Not Available" );
                            Toast.makeText(PaytmPayment.this, "Network Not Available", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                        }

                        @Override
                        public void clientAuthenticationFailed(String inErrorMessage) {
                            Log.e("Trans ", " Authentication Failed  "+ inErrorMessage );
                            Toast.makeText(PaytmPayment.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                        }

                        @Override
                        public void someUIErrorOccurred(String inErrorMessage) {
                            Log.e("Trans ", " ui fail respon  "+ inErrorMessage );
                            Toast.makeText(PaytmPayment.this, "UI Error Occurred", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                        }

                        @Override
                        public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                            Log.e("Trans ", " error loading pagerespon true "+ inErrorMessage + "  s1 " + inFailingUrl);
                            Toast.makeText(PaytmPayment.this, "onErrorLoadingWebPage", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                        }

                        @Override
                        public void onBackPressedCancelTransaction() {
                            Toast.makeText(PaytmPayment.this, "Transaction Cancel", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                            Log.e("Bankai", " cancel call back respon  " );
                        }

                        @Override
                        public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                            Toast.makeText(PaytmPayment.this, "Transaction Cancel", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(PaytmPayment.this, MainActivity.class));
                            Animatoo.animateFade(PaytmPayment.this);
                            finish();
                            Log.e("Trans ", "  transaction cancel " );
                        }
                    });
        }
    }
    private String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
    public void showNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationCompat.BigPictureStyle bigPictureStyle=new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.succesful)).build();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title).setStyle(bigPictureStyle)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        Intent intent=new Intent(PaytmPayment.this,MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

}