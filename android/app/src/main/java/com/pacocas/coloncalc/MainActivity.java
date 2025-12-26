package com.pacocas.coloncalc;

import android.os.Bundle;
import android.webkit.WebSettings;
import androidx.activity.OnBackPressedCallback; // Importante
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. SOLUCIÓN AL TAMAÑO DEL TEXTO (Mantenemos tu ajuste)
        if (this.bridge != null) {
            WebSettings settings = this.bridge.getWebView().getSettings();
            settings.setTextZoom(100); 
        }

        // 2. NUEVA FORMA MODERNA DE MANEJAR EL BOTÓN ATRÁS
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (bridge == null) return;

                String url = bridge.getWebView().getUrl();

                // Si estamos en el menú, cerramos la app
                if (url != null && (url.endsWith("/") || url.endsWith("index.html"))) {
                    finish();
                } else {
                    // Si estamos en la calculadora, enviamos el evento a JS
                    bridge.getWebView().evaluateJavascript(
                        "window.dispatchEvent(new Event('hardwareBackPress'));", 
                        null
                    );
                }
            }
        });
    }

    // Eliminamos por completo el método @Override public void onBackPressed()
    // Ya no es necesario porque el Callback de arriba toma el control.
}
