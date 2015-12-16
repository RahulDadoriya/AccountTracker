package example.accounttrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomePage extends Activity {

	ImageButton btnAct, btnSum, btnWith, btnTran;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		btnAct = (ImageButton) findViewById(R.id.imgBtnAccount);
		btnSum = (ImageButton) findViewById(R.id.imgBtnSum);
		btnWith = (ImageButton) findViewById(R.id.imgBtnDep);
		btnTran = (ImageButton) findViewById(R.id.imgBtnTrans);

		btnAct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gettransfer = new Intent(getApplicationContext(),
						CreateAccount.class);
				startActivity(gettransfer);
			}
		});
		btnSum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gettransfer = new Intent(getApplicationContext(),
						TransactionDetails.class);
				startActivity(gettransfer);
			}
		});
		btnWith.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gettransfer = new Intent(getApplicationContext(),
						Transaction.class);
				startActivity(gettransfer);
			}
		});
		btnTran.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent gettransfer = new Intent(getApplicationContext(),
						TransferAmount.class);
				startActivity(gettransfer);
			}
		});
	}

}
