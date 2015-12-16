package example.accounttrack;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class TransferAmount extends Activity {
	
	SQLiteDatabase db = null;
	private static String DBNAME = "AccountTracker.db";
	
	private int myYear, myMonth, myDay;
	static final int ID_DATEPICKER = 0;
	ImageButton date;
	Button tranfer;
	EditText edtTo, edtFrom, edtAmount, edtDate, edtDesc, edtTransRef;
	Editable dTo, dFrom, dAmount, dDate, dDesc, dTransRef;
	
	
	Spinner sp;
	ArrayAdapter<String> adapter;
	String mode[] = {"Cheque","Cash","ATM","Others"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer);

		db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
		
		date = (ImageButton) findViewById(R.id.transBtnDate);
		date.setOnClickListener(dateOnClickListener);
		
		edtTo = (EditText) findViewById(R.id.transTo);
		edtFrom = (EditText) findViewById(R.id.transFrom);
		edtAmount = (EditText) findViewById(R.id.transAmount);
		edtDate = (EditText) findViewById(R.id.transDate);
		edtDesc = (EditText) findViewById(R.id.transDesciption);
		edtTransRef = (EditText) findViewById(R.id.transedtRef);
		
		sp = (Spinner) findViewById(R.id.transMode);
		
		adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mode);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tranfer = (Button) findViewById(R.id.transBtnTransfer);
		
		
		tranfer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dTo = edtTo.getText();
				dFrom = edtFrom.getText();
				dAmount = edtAmount.getText();
				dDate = edtDate.getText();
				dDesc = edtDesc.getText();
				dTransRef = edtTransRef.getText();
				try{
					
				//	db.execSQL("INSERT INTO Trans "+"VALUES('"+dTo+"','"+dFrom+"','"++"')");
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		});
		
		
	}

	private ImageButton.OnClickListener dateOnClickListener = new ImageButton.OnClickListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Calendar c = Calendar.getInstance();

			myYear = c.get(Calendar.YEAR);
			myMonth = c.get(Calendar.MONTH);
			myDay = c.get(Calendar.DAY_OF_MONTH);

			showDialog(ID_DATEPICKER);

		}
	};

	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case ID_DATEPICKER:
			return new DatePickerDialog(this, myDateSetListener, myYear,
					myMonth, myDay);

		default:
			return null;
		}
	}

	private DatePickerDialog.OnDateSetListener myDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub

			String date = " " + String.valueOf(dayOfMonth) + " / " + ""
					+ monthOfYear + " / " + ""
					+ String.valueOf(year);

			EditText edt = (EditText) findViewById(R.id.transDate);
			
			edt.setText(date);

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transfer_amount, menu);
		return true;
	}

}
