package org.nestordevelopments.lapurisima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Base1f extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base1f);
        PrincipalFragment fragmentPrincipal = new PrincipalFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutBase, fragmentPrincipal).commit();
    }
}
