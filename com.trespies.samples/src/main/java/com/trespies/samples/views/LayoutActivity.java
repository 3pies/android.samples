package com.trespies.samples.views;

import android.app.Activity;
import android.os.Bundle;

import com.trespies.samples.R;
import com.trespies.samples.enumerations.ItemWidget;

import java.lang.reflect.Field;

/**
 * Clase base para inicializar por defecto todo lo que necesitemos en una {@link Activity}
 */
public class LayoutActivity extends Activity {

    /**
     * Identificador de la vista que utilizará la Activity
     * @return
     */
    protected int getLayout(){return 0;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getLayout() != 0){
            setContentView(getLayout());
        }

        for(Field field: ((Object) this).getClass().getFields()){
            if(field.isAnnotationPresent(ItemWidget.class)){
                ItemWidget itemWidget = field.getAnnotation(ItemWidget.class);
                try {
                    field.set(this, (itemWidget.className().cast(findViewById(itemWidget.identifier()))) );
                } catch (IllegalAccessException e) {
                }
            }
        }

    }

}
