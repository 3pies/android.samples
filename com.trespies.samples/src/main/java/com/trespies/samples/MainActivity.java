package com.trespies.samples;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.trespies.samples.enumerations.ItemWidget;
import com.trespies.samples.views.LayoutActivity;

public class MainActivity extends LayoutActivity implements Validator.ValidationListener {

    /**
     * La clase padre busca los elementos con la annotation {@link ItemWidget} y los inicializará
     */
    @Required(order = 1, message = "El email es obligatorio")
    @Email(order = 2, message = "Debes introducir un email válido")
    @ItemWidget(identifier = R.id.txtEmail, className = EditText.class)
    public EditText txtEmail;

    @Required(order = 3, message = "La password es obligatoria")
    @TextRule(order =4, minLength = 6, maxLength = 20, message = "Introduce entre 6 a 20 caracteres")
    @ItemWidget(identifier = R.id.txtPassword, className = EditText.class)
    public EditText txtPassword;

    @Checked(order = 5, message = "Debes aceptar las condiciones")
    @ItemWidget(identifier = R.id.chk01, className = CheckBox.class)
    public CheckBox chk01;

    @ItemWidget(identifier = R.id.btn02, className = Button.class)
    public Button btn02;

    public Validator validator;

    @Override
    protected int getLayout(){return R.layout.activity_main;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        validator = new Validator(this);
        validator.setValidationListener(this);

        /**
         * Aquí podemos ver como no es necesario inicializar {@link btn02} y {@link chk01} para comenzar a utilizarlos
         */
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validator.validate();
            }
        });

    }

    @Override
    public void preValidation() {

    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Validado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(View failedView, Rule failedRule) {
        Log.w("onFailure", "Mensage error " + failedRule.getFailureMessage());
        Log.w("onFailure", "failedView ID " + failedView.getId());
        Toast.makeText(this, failedRule.getFailureMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationCancelled() { }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
