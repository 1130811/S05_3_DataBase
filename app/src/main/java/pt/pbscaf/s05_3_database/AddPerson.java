package pt.pbscaf.s05_3_database;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by laptop on 22/03/2016.
 */
public class AddPerson extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_people);



        /***BOTAO OK**/
        Button bOk = (Button) findViewById(R.id.btnAdicNovo);
        assert bOk != null;
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ler do ediText

                EditText nomeText = (EditText) findViewById(R.id.primeiroNome);
                String nome = nomeText.getText().toString();

                EditText sobrenomeText = (EditText) findViewById(R.id.ultimoNome);
                String sobrenome = sobrenomeText.getText().toString();

                if (sobrenome.isEmpty() || nome.isEmpty()){
                    Toast.makeText(AddPerson.this, "Preencha ambos os campos!", Toast.LENGTH_SHORT).show();
                }else{
                    DBAdapter adapter = new DBAdapter(getApplicationContext());
                    adapter.addPerson(nome,sobrenome);
                    finish();
                }



            }
        });

    }


}
