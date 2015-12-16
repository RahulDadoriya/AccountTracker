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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class CreateAccount extends Activity {
	
	
	EditText accNo, accName, bankName, currBal, dateOp;
	RadioButton rsaving, rcurrent;
	Button save,details;
	ImageButton cal;
	int day, month, year;
	String type;
	Editable AccNo,AccName,BankName,DateOp, CurrBal,Type;
	SQLiteDatabase db=null;
	private static String NAME="AccountTracker.db";
	
	static final int ID_DATEPICKER=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createaccount);
		
		accNo=(EditText)findViewById(R.id.accNo);
		accName=(EditText)findViewById(R.id.accName);
		bankName=(EditText)findViewById(R.id.bankName);
		currBal=(EditText)findViewById(R.id.currBal);
		dateOp=(EditText)findViewById(R.id.dateOp);
		rsaving=(RadioButton)findViewById(R.id.rsaving);
		rcurrent=(RadioButton)findViewById(R.id.rcurrent);
		save=(Button)findViewById(R.id.save);
		details=(Button)findViewById(R.id.details);
		cal=(ImageButton)findViewById(R.id.cal);
		cal.setOnClickListener(datePicker);
		rsaving.setChecked(true);
		db= openOrCreateDatabase(NAME, Context.MODE_PRIVATE, null);
		
		save.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AccNo=accNo.getText();
				AccName=accName.getText();
				BankName=bankName.getText();
				CurrBal=currBal.getText();
				DateOp=dateOp.getText();
				if(rsaving.isChecked())
					type="saving";
				else
					type="current";
				
				try
				{
				
					db.execSQL("INSERT INTO AccountDetails(AccountNo, AccountName, DateOfOpening,Type, BankName, ClearBalance)" +
						"VALUES("+AccNo+",'"+AccName+"','"+DateOp+"','"+type+"','"+BankName+"',"
						+CurrBal+");");
				
				
					Toast.makeText(CreateAccount.this,"Data Saved", Toast.LENGTH_LONG).show();
				}
				catch(Exception e)
				{
					Toast.makeText(CreateAccount.this, e.toString(), Toast.LENGTH_LONG).show();
				}
			}
		});
		details.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("DELETE FROM AccountDetails;");
				Toast.makeText(CreateAccount.this,"Accounts Deleted", Toast.LENGTH_LONG).show();
			}
		});
		
		
	}
	private Button.OnClickListener datePicker= new Button.OnClickListener()
	{
		@SuppressWarnings("deprecation")
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Calendar c= Calendar.getInstance();
			year= c.get(Calendar.YEAR);
			month=c.get(Calendar.MONTH);
			day=  c.get(Calendar.DAY_OF_MONTH);
			showDialog(ID_DATEPICKER);
		}		
	};
	protected Dialog onCreateDialog(int id)
	{
		switch(id)
		{
		case ID_DATEPICKER:
			//Toast.
			return new DatePickerDialog(this, myDateSetListener, year, month, day);
		default:
			return null;
		}
	}
	private DatePickerDialog.OnDateSetListener myDateSetListener= 
							new DatePickerDialog.OnDateSetListener() 
	{
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			String date= String.valueOf(dayOfMonth)+"/"+
			String.valueOf(monthOfYear+1)+"/"+String.valueOf(year);
					
			
			dateOp.setText(date);
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.transfer_amount, menu);
		return true;
	}
}
/*
AccNo=accNo.getText().toString();
AccName=accName.getText().toString();
BankName=bankName.getText().toString();
CurrBal=currBal.getText().toString();
DateOp=dateOp.getText().toString();*/

//db.execSQL("DELETE FROM AccountDetails;");
//db.execSQL("INSERT INTO AccountDetails(AccountNo, AccountName, DateOfOpening,Type, BankName,BranchName, ClearBalance,UnclearBalance) 
//								VALUES(45,'A','24/09/1991','saving','S','K',10,100);");

//Toast.makeText(CreateAccount.this, "OK", Toast.LENGTH_LONG).show();


/*	
String[] accno= {AccNo.toString()};
Toast.makeText(CreateAccount.this,String.valueOf(accno), Toast.LENGTH_LONG).show();
Cursor c=db.rawQuery("SELECT * FROM AccountDetails WHERE AccountNo=?", accno);
int count=c.getCount();

if(count==1)
{
	
	db.execSQL("UPDATE TABLE AccountDetails SET AccountName='"+AccName+"' AND DateOfOpening='"+DateOp+"' AND " +
			"Type='"+type+"' AND BankName='"+BankName+"'AND ClearBalance="+CurrBal+"  WHERE AccountNo="+accno+";");
	
	Toast.makeText(CreateAccount.this,"done update", Toast.LENGTH_LONG).show();
}
else
*/