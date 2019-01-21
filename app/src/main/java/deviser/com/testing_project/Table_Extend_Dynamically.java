package deviser.com.testing_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Table_Extend_Dynamically extends AppCompatActivity {

    TableLayout tableLayout;
    TableRow tableRow;
  //  LinearLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table__extend__dynamically);

         tableLayout=findViewById(R.id.table_layout);

      //   params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void extendTable(View v){
        AddRow();
    }

    public void AddRow(){

        tableRow=new TableRow(this);
        tableRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        EditText et=new EditText(this);
        et.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tableRow.addView(et);
        tableLayout.addView(tableRow);
    }
}
