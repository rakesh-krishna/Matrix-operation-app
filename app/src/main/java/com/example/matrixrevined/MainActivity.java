package com.example.matrixrevined;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int rowNum=4;
    int colNum=5;
    Button button1;
    Button button2;
    TextView row;
    TextView col;
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
        row=(TextView) findViewById(R.id.textView);
        col=(TextView) findViewById(R.id.textView2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//        setContentView(gridLayout);
    }

    //putting the edit text according to row and column index
    private void setPos(EditText editText, int row, int column) {
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
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

            editTexts = new EditText[rowNum][colNum];

            gridLayout = (GridLayout) findViewById(R.id.gridLayout);

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
//            editTexts=null;
        }
        if(v==button2)
        {
            for(int i=0;i<rowNum;i++)
            {
                for(int j=0;j<colNum;j++)
                {
                    mat[i][j]= Integer.parseInt(editTexts[i][j].getText().toString());
                }
            }

            for(int i=0;i<colNum;i++)
            {
                for(int j=0;j<rowNum;j++)
                {
                    mat2[i][j]= mat[j][i];
                }
            }

            gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            gridLayout.removeAllViews();
            editTexts2 = new EditText[colNum][rowNum];
            gridLayout.setRowCount(colNum);
            gridLayout.setColumnCount(rowNum);

            for (int i = 0; i < colNum; i++) {
                for (int j = 0; j < rowNum; j++) {
                    editTexts2[i][j] = new EditText(this);
                    setPos(editTexts2[i][j], i, j);
                    editTexts2[i][j].setText(String.valueOf(mat2[i][j]));
                    gridLayout.addView(editTexts2[i][j]);
                }
            }
        }
        editTexts2=null;

    }
}
