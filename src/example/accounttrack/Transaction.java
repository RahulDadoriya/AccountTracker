package example.accounttrack;
import java.util.Calendar;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class Transaction extends Activity {
	Spinner account,mode;
	EditText balance,amount,date,time=null,description;
	ImageButton dateButton,timeButton;
	Button addButton;
	
	private static String DBNAME="AccountTracker.db";
	SQLiteDatabase db=null;
	String type="Deposit";
	String modeValue="Cash";
	
	TimePickerDialog timePickerDialog=null;
	String Transaction,Description;
	Editable Amount,Balance,edate,etime,edescription;
	//String Amount,Balance,edate,etime,edescription;
	String Date,Time;
	String optionsMode[]={"Cash","Cheque","ATM","Others"};
	
	
	
	private int year,month,day;
	static final int ID_DATEPICKER=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction);
		
		account=(Spinner)findViewById(R.id.spinnerAccount);
		mode=(Spinner)findViewById(R.id.spinnerMode);
		
		balance=(EditText)findViewById(R.id.editBalance);
		amount=(EditText)findViewById(R.id.editAmount);
		date=(EditText)findViewById(R.id.editDate);
		time=(EditText)findViewById(R.id.editTime);
		description=(EditText)findViewById(R.id.editDescription);
		
		dateButton=(ImageButton)findViewById(R.id.dateButton);
		timeButton=(ImageButton)findViewById(R.id.timeButton);
		addButton=(Button)findViewById(R.id.addButton);
		
		db=openOrCreateDatabase(DBNAME,Context.MODE_PRIVATE,null);
		
		
		Balance=balance.getText();
		
		edescription=description.getText();
		
		
		// Add date picker here
		dateButton.setOnClickListener(datePickerButtonOnClickListener);
		addButton.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Amount=amount.getText();
					edate=date.getText();
					etime=time.getText();
					try
					{
						//description.setText("You have "+type+" Rs. "+Amount+" On Dated "+Date+" at "+Time+" using "+modeValue+" Mode ");
						//db.execSQL("INSERT INTO Transaction(TransactionId,AccountNo,Date,Time,Mode,Amount,TransactionType,TransactionReferenceNo)"+" VALUES('"+ "','"+ "','"+edate+"','"+etime+"','"+ "','"+Amount+"','"+ "','"+ "');");
						db.execSQL("INSERT INTO Trans(TransId,AccountNo,Date,Mode,Amount,TransType) "+"VALUES('48','58','12/12/90','cash','1000','debit');");
						//Toast.makeText(getApplicationContext(),"Data has been saved",Toast.LENGTH_LONG).show();
						Toast.makeText(getApplicationContext(),"You have "+type+" Rs. "+Amount+" On Dated "+Date+" at "+etime+" using "+modeValue,Toast.LENGTH_LONG).show();
						
						Intent i=new Intent(getApplicationContext(),TransactionDetails.class);
						startActivity(i);
					}
					catch(Exception e)
					{
						Toast.makeText(getApplicationContext(),"I am here "+e,Toast.LENGTH_LONG).show();
					}
				}
				
			});
			
		
	
		
		// Add time picker here
			
		timeButton.setOnClickListener(new View.OnClickListener()
				{

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						Time=time.getText().toString();
						
						if(Time!=null && !Time.equals(""))
						{
							StringTokenizer st=new StringTokenizer(Time,":");
							String timeHour=st.nextToken();
							String timeMinute=st.nextToken();
							
							timePickerDialog=new TimePickerDialog(v.getContext(),(OnTimeSetListener) new TimePickHandler(),Integer.parseInt(timeHour),Integer.parseInt(timeMinute),true);
						}
						else
						{
							timePickerDialog=new TimePickerDialog(v.getContext(),new TimePickHandler(),Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),true );
						}
						
						timePickerDialog.show();
					}
					
				});
		
		
		// populate spinner mode with values
		
		ArrayAdapter <String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,optionsMode);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		mode.setAdapter(adapter);
		
		mode.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				modeValue=optionsMode[arg2];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Please Select Mode Of Transaction ",Toast.LENGTH_LONG).show();
			}
			
		});
		
			
	}// onCreate()  ends here
	
	
	private Button.OnClickListener datePickerButtonOnClickListener=new Button.OnClickListener()
	{

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Calendar c=Calendar.getInstance();
			year=c.get(Calendar.YEAR);
			month=c.get(Calendar.MONTH);
			day=c.get(Calendar.DAY_OF_MONTH);
			showDialog(ID_DATEPICKER);
		}
		
	};
	
	
	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
		case ID_DATEPICKER:
			Toast.makeText(Transaction.this,"Please Select Date",Toast.LENGTH_LONG).show();
			return new DatePickerDialog(this,myDateSetListener,year,month,day);
		default:
			return null;
		}
		
	}
	
	private DatePickerDialog.OnDateSetListener myDateSetListener=new DatePickerDialog.OnDateSetListener()
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			Date=(String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear)+"/"+String.valueOf(year));
			date.setText(Date);
		}
		
	};
	
	private class TimePickHandler implements OnTimeSetListener
	{

		@Override
		public void onTimeSet(TimePicker view, int hourDay, int minute)
		{
		// TODO Auto-generated method stub
		
			time.setText(hourDay+":"+minute);
		
			timePickerDialog.hide();
		}
	
	
	}
	
	public void onTransactionClicked(View view)
	{
		boolean checked=((RadioButton)view).isChecked();
		
		switch(view.getId())
		{
		case R.id.deposit:
			if(checked)
			type="Deposit";
			break;
		case R.id.withdrawal:
			if(checked)
			type="Withdrawal";
			break;
		default:
			type="Deposit";
			
		}
	}
	
}//main class ends here
