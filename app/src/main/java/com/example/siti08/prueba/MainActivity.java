package com.example.siti08.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.gov.minminas.subsidiosGLP.actividad.EscanerBarras;
import co.gov.minminas.subsidiosGLP.entidad.Pregunta;
import co.gov.minminas.subsidiosGLP.entidad.Opcion;
import co.gov.minminas.subsidiosGLP.publico.Mediador;
import co.gov.minminas.subsidiosGLP.publico.ParametroEnum;
import co.gov.minminas.subsidiosGLP.publico.RegistroSubsidio;

public class MainActivity extends EscanerBarras implements View.OnClickListener {

    private TextView tv_msg;
    private View btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RegistroSubsidio registroSubsidio = Mediador.fabricarRegistroSubsidio(this);
        if (registroSubsidio == null){
            tv_msg.setText("Variable nula");
        }
        else {
            registroSubsidio.capturarDocumento(this);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Se invoca el método de la superclase para procesar la trama // capturada
        super.onActivityResult(requestCode,resultCode,data);
        // Se instancia el objeto tipo RegistroSubsidio utilizando la clase Mediador.
        RegistroSubsidio registroSubsidio = Mediador.fabricarRegistroSubsidio(this);
        // Se verifica si se presento algún error durante la captura
        if(!registroSubsidio.get(ParametroEnum.ERROR).isEmpty()){
        // Se muestra el error al vendedor.
            this.tv_msg.setText(registroSubsidio.get(ParametroEnum.ERROR));
        } else {
            // Se captura el número de documento del beneficiario.
            String beneficiario = registroSubsidio.get(ParametroEnum.BENEFICIARIO);
        }
    }
}
