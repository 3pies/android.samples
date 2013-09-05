package com.trespies.samples;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.trespies.samples.enumerations.ItemWidget;
import com.trespies.samples.views.LayoutActivity;

public class MainActivity extends LayoutActivity {

    /**
     * La clase padre busca los elementos con la annotation {@link ItemWidget} y los inicializará
     */
    @ItemWidget(identifier = R.id.lblHello, className = TextView.class)
    public TextView lblHello;

    @ItemWidget(identifier = R.id.chk01, className = CheckBox.class)
    public CheckBox chk01;

    @ItemWidget(identifier = R.id.btn02, className = Button.class)
    public Button btn02;

    @Override
    protected int getLayout(){return R.layout.activity_main;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Aquí podemos ver como no es necesario inicializar {@link btn02} y {@link chk01} para comenzar a utilizarlos
         */
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chk01.setChecked(!chk01.isChecked());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
