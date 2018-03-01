package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {

        quantity += 1;
        if (quantity >= 101) {
            Toast.makeText(this, getString(R.string.increment), Toast.LENGTH_SHORT).show();
            quantity = 100;
            return;
        }
        displayquantity(quantity);
    }

    public void decrement(View view) {

        quantity -= 1;
        if (quantity < 1) {
            Toast.makeText(this, getString(R.string.decrement), Toast.LENGTH_SHORT).show();
            quantity = 1;
            return;
        }
        displayquantity(quantity);
    }

    public int calcprice(int quantity, int factor) {

        return quantity * factor;
    }

    public String retname() {
        EditText name = (EditText) findViewById(R.id.user_name);
        String userName = name.getEditableText().toString();
        return userName;
    }

    public String createordersummary(int calcprice, boolean checkValue1, boolean checkValue2) {
        String summary = getString(R.string.name) + retname() +
                "\n" + getString(R.string.addcream) + checkValue1 +
                "\n" + getString(R.string.addchoco) + checkValue2 +
                "\n" + getString(R.string.quan) + ": " + quantity + "\n" + getString(R.string.total) + calcprice +
                "\n" + getString(R.string.thankyou);
        return summary;
    }

    public void submitOrder(View view) {
        int factor = 5;
        CheckBox check1 = (CheckBox) findViewById(R.id.check1);
        boolean checkValue1 = check1.isChecked();
        CheckBox check2 = (CheckBox) findViewById(R.id.check2);
        boolean checkValue2 = check2.isChecked();
        if (checkValue1 == true) {
            factor = factor + 1;
        }
        if (checkValue2 == true) {
            factor = factor + 2;
        }
        int price = calcprice(quantity, factor);
//        ******EMAIL INTENT CODE SNIPPET.******
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For");
//        intent.putExtra(Intent.EXTRA_TEXT,createordersummary(price, checkValue1, checkValue2) );
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        displayMessage(createordersummary(price, checkValue1, checkValue2));

    }

    private void displayMessage(String priceMessage) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}