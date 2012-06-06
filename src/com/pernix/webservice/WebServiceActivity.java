package com.pernix.webservice;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.transport.HttpTransportSE;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;



public class WebServiceActivity extends Activity {
    /** Called when the activity is first created. */
    private static final String accionSoap = "http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicosXML";
    private static final String Metodo = "ObtenerIndicadoresEconomicosXML";
    private static final String namespace = "http://ws.sdde.bccr.fi.cr";
    private static final String url = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx";
    
    TextView tv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = (TextView) findViewById(R.id.text1);

        try {

	        //Modelo el request
	        SoapObject request = new SoapObject(namespace, Metodo);
	        
	        PropertyInfo propInfo=new PropertyInfo();
	        propInfo.name="tcIndicador";
	        propInfo.type=PropertyInfo.STRING_CLASS;
	        request.addProperty(propInfo, "318"); //317 compra 318 venta
	        
	        
	        PropertyInfo propInfo1=new PropertyInfo();
	        propInfo1.name="tcFechaInicio";
	        propInfo1.type=PropertyInfo.STRING_CLASS;
	        request.addProperty(propInfo1, "28/05/2012");
	        
	        
	        PropertyInfo propInfo2=new PropertyInfo();
	        propInfo2.name="tcFechaFinal";
	        propInfo2.type=PropertyInfo.STRING_CLASS;
	        request.addProperty(propInfo2, "28/05/2012");
	        
	        
	        PropertyInfo propInfo3=new PropertyInfo();
	        propInfo3.name="tcNombre";
	        propInfo3.type=PropertyInfo.STRING_CLASS;
	        request.addProperty(propInfo3, "PRUEBA");
	        
	        
	        
	        PropertyInfo propInfo4=new PropertyInfo();
	        propInfo4.name="tnSubNiveles";
	        propInfo4.type=PropertyInfo.STRING_CLASS;
	        request.addProperty(propInfo4, "N");
	        
	        //Modelo el Sobre
	        SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER12);
	        
	          sobre.dotNet = true;
	
	        
	        
	        sobre.setOutputSoapObject(request);
	
	        //Modelo el transporte
	        org.ksoap2.transport.HttpTransportSE transporte = new HttpTransportSE(url) ; 
	        
	
	        //Llamada
	        transporte.call(accionSoap, sobre);
	
	        //Resultado
	        SoapPrimitive resultado = (SoapPrimitive) sobre.getResponse();
	
	        tv.setText("" + resultado);

        }
        catch (Exception e)
        {
        	tv.setText(e.getMessage());
        }

        }
}