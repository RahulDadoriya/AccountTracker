package example.accounttrack;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionDetails extends Activity {
	Spinner account,orderBy;
	EditText editSearch;
	Button deleteAll,search;
	
	SQLiteDatabase db = null;
	private static String DBNAME = "AccountTracker.db"; 
	
	public String list[];
	protected ListView lv;
	 
	String orderOptions[]={"Date","Amount"};
	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transactiondetails);
		
		account=(Spinner)findViewById(R.id.spinnerAccount);
		orderBy=(Spinner)findViewById(R.id.spinnerOrderBy);
		
		editSearch=(EditText)findViewById(R.id.editSearch);
		
		deleteAll=(Button)findViewById(R.id.deleteAll);
		
		search=(Button)findViewById(R.id.searchButton);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,orderOptions);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		orderBy.setAdapter(adapter);
		
		orderBy.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				switch(arg2)
				{
				case 0:
					editSearch.setText("24/10/1992");
					break;
					
				case 1:
					editSearch.setText("0.00");
					break;
					
				default:
					editSearch.setText("24/10/1992");
					break;
				
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext()," Please Select ",Toast.LENGTH_LONG).show();
			}
			
		});
		
		deleteAll.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// write delete query here
			}
			
		});
		
		
		//This is for ListView
		lv = (ListView) findViewById(R.id.listView);
		
		db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
		
		try{
			Cursor c = db.rawQuery("SELECT * FROM Transaction", null);
			
			if (c!=null)
			{
				int row_count = c.getCount();
				list = new String[row_count];
				
				if(c.moveToFirst()){
					
					do{
						list[i] = c.getString(c.getColumnIndex("AccountNo"));
						i++;
					}while(c.moveToNext());
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		lv.setAdapter(new MyAdapter());
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}});
		
	}

	
	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LayoutInflater layinf = getLayoutInflater();
			View rview = convertView;
			
			if (rview == null){
				rview = layinf.inflate(R.layout.transactiondetails,parent,false);
				final TextView tv = (TextView) rview.findViewById(R.id.txtforlist);
				tv.setText(list[position]);
			}
			return rview;
			
		}}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transfer_amount, menu);
		return true;
	}

}


