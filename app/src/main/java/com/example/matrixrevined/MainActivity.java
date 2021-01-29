package com.example.matrixrevined;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int rowNum=4;
    int colNum=5;
    Button button1;
    Button button2;
    Spinner spinner;
    String[] nubs = {"Transpose","Upper Diagonal","Lower Diagonal"};
//    Button button3;
    TextView row;
    TextView col;
    int click_count;
    int mat[][];
    int mat2[][];
    EditText[][] editTexts;
    EditText[][] editTexts2;
    {
        mat = new int[100][100];
        mat2 = new int[100][100];
    }
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
//        button2=(Button) findViewById(R.id.button3);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nubs);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        spinner.setAdapter(aa);
        row=(TextView) findViewById(R.id.textView);
        col=(TextView) findViewById(R.id.textView2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//        setContentView(gridLayout);
    }

    //putting the edit text according to row and column index
    private void setPos(EditText editText, int row, int column) {
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setVisibility(View.VISIBLE);
        param.width = 100;
        param.height = 150;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(row);
        param.columnSpec = GridLayout.spec(column);
        editText.setLayoutParams(param);
    }

    @Override
    public void onClick(View v) {
        if(v==button1)
        {
            rowNum=Integer.parseInt(row.getText().toString());
            colNum=Integer.parseInt(col.getText().toString());
            Intent passedIntent = getIntent();
            Bundle extras = passedIntent.getExtras();



            gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            gridLayout.removeAllViews();
            editTexts = new EditText[rowNum][colNum];
            //define how many rows and columns to be used in the layout
            gridLayout.setRowCount(rowNum);
            gridLayout.setColumnCount(colNum);

            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    editTexts[i][j] = new EditText(this);
                    setPos(editTexts[i][j], i, j);
                    gridLayout.addView(editTexts[i][j]);
                }
            }
        }
        if(v==button2){
            switch (spinner.getSelectedItemPosition()){
                case  0:
                {
                    for(int i=0;i<rowNum;i++)
                    {
                        for(int j=0;j<colNum;j++)
                        {
                            mat[i][j]= Integer.parseInt(editTexts[i][j].getText().toString());
                        }
                    }
                    gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                    gridLayout.removeAllViews();
                    editTexts = new EditText[colNum][rowNum];
                    gridLayout.setRowCount(colNum);
                    gridLayout.setColumnCount(rowNum);
                    for(int i=0;i<colNum;i++)
                        {
                            for(int j=0;j<rowNum;j++)
                            {
                                mat2[i][j]= mat[j][i];
                            }
                        }
                    for (int i = 0; i < colNum; i++) {
                        for (int j = 0; j < rowNum; j++) {
//                            print()
                            editTexts[i][j] = new EditText(this);
                            setPos(editTexts[i][j], i, j);
                            editTexts[i][j].setText(String.valueOf(mat2[i][j]));
                            gridLayout.addView(editTexts[i][j]);
                        }

                    }
                    int temp=colNum;
                    colNum=rowNum;
                    rowNum=temp;
                    break;
                }
                case 1:
                {
                    for(int i = 0; i < rowNum; i++){
                            for(int j = 0; j < colNum; j++){
                                if(i > j) editTexts[i][j].setText("");
                            }
                        }
                    break;
                }
                case 2:
                {
                    for(int i = 0; i < rowNum; i++){
                        for(int j = 0; j < colNum; j++){
                            if(i < j) editTexts[i][j].setText("");
                        }
                    }
                }
                default:
                        System.out.println("Default case ");
            }
        }
//        if(v==button2)
//        {
//            for(int i=0;i<rowNum;i++)
//            {
//                for(int j=0;j<colNum;j++)
//                {
//                    mat[i][j]= Integer.parseInt(editTexts[i][j].getText().toString());
//                }
//            }
//
//            for(int i=0;i<colNum;i++)
//            {
//                for(int j=0;j<rowNum;j++)
//                {
//                    mat2[i][j]= mat[j][i];
//                }
//            }
//
//            gridLayout = (GridLayout) findViewById(R.id.gridLayout);
//            gridLayout.removeAllViews();
//            editTexts2 = new EditText[colNum][rowNum];
//            gridLayout.setRowCount(colNum);
//            gridLayout.setColumnCount(rowNum);
//
//            for (int i = 0; i < colNum; i++) {
//                for (int j = 0; j < rowNum; j++) {
//                    editTexts2[i][j] = new EditText(this);
//                    setPos(editTexts2[i][j], i, j);
//                    editTexts2[i][j].setText(String.valueOf(mat2[i][j]));
//                    gridLayout.addView(editTexts2[i][j]);
//                }
//            }
////            editTexts2=null;
//        }
//        if(v==button3){
//            for(int i = 0; i < rowNum; i++){
//                for(int j = 0; j < colNum; j++){
//                    if(i > j) editTexts[i][j].setVisibility(View.INVISIBLE);
//                }
//            }
//
//        }
        }
}
