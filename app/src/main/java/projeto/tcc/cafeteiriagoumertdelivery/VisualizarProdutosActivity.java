package projeto.tcc.cafeteiriagoumertdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import projeto.tcc.cafeteiriagoumertdelivery.databinding.ActivityMainBinding;
import projeto.tcc.cafeteiriagoumertdelivery.databinding.ActivityVisualizarProdutosBinding;

public class VisualizarProdutosActivity extends AppCompatActivity {

    private ActivityVisualizarProdutosBinding mainBinding;
    private DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityVisualizarProdutosBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Produtos");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        try {
            bundle = getIntent().getExtras();
            if (bundle.getBoolean("gerente", false)) {
                mainBinding.adicionarProduto.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
        }
        mainBinding.adicionarProduto.setOnClickListener(v -> {
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(VisualizarProdutosActivity.this).inflate(R.menu.menu_carrinho, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.carrinho) {
            startActivity(new Intent(this, CarrinhoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}