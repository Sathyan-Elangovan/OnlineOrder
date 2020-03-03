package com.example.onlineorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity,price;
    String orderSummary,creamChoice,chocoChoice,name;
    TextView Quantity,Ordersummary;
    Button plus,minus,order;
    CheckBox creamCheckBox,chocolateCheckBox;
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Quantity=(TextView)findViewById(R.id.quantityvalue);
        plus=(Button)findViewById(R.id.plusbutton);
        minus=(Button)findViewById(R.id.minusbutton);
        order=(Button)findViewById(R.id.orderbutton);
        creamCheckBox=(CheckBox)findViewById(R.id.creamcheckbox);
        chocolateCheckBox=(CheckBox)findViewById(R.id.chocolatecheckbox);
        userName=(EditText)findViewById(R.id.username);

    }

    public void CompleteOrder(View view) {
        boolean haswhippedcream=creamCheckBox.isChecked();
        boolean hasChocolate=chocolateCheckBox.isChecked();
        int unitPrice=5;
        if(haswhippedcream==true)
        {
            creamChoice="YES";
            unitPrice+=1;
        }
        else if(haswhippedcream == false)
        {
            creamChoice = "NO";
        }
        if(hasChocolate==true)
        {
            chocoChoice="YES";
            unitPrice+=2;
        }
        else if(hasChocolate == false)
        {
            chocoChoice = "NO";
        }
        name=userName.getText().toString();
        price=quantity*unitPrice;
        orderSummary="Name :"+name+"\nOrdered Quantity  :"+quantity+"\nTotal price  :"+price+"\nWhipped Cream :"+creamChoice+"\nChocolate :"+chocoChoice;
        //Ordersummary.setText(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Your Order From SHOP_E");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }

    }

    public void decreaseQuantity(View view) {
        if(quantity>1) {
            quantity -= 1;
            Quantity.setText("" + quantity);
        }
        else {
            Toast.makeText(this, "Minium limit Reached", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void increaseQuantity(View view) {
        if(quantity>=100)
        {
            Toast.makeText(this, "Maximum Limit Reached", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            quantity += 1;
            Quantity.setText("" + quantity);
        }
    }
}
