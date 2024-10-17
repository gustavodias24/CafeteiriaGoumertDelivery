package projeto.tcc.cafeteiriagoumertdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.UUID;

import projeto.tcc.cafeteiriagoumertdelivery.databinding.ActivityMainBinding;
import projeto.tcc.cafeteiriagoumertdelivery.databinding.ActivityRegistrarUsuarioBinding;
import projeto.guilherme.cafeteiriagoumertdelivery.model.UsuarioModel;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private ActivityRegistrarUsuarioBinding mainBinding;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
    private UsuarioModel usuarioModel;

    private boolean fazerCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityRegistrarUsuarioBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setTitle("Novo Registro");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        usuarioModel = new UsuarioModel();
        usuarioModel.setId(UUID.randomUUID().toString());
        fazerCadastro = true;


        mainBinding.botaoAcao.setOnClickListener(v -> {
            String nome, email, senha;

            nome = mainBinding.nome.getText().toString();
            email = mainBinding.email.getText().toString();
            senha = mainBinding.senha.getText().toString();

            if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                usuarioModel.setEmail(email);
                usuarioModel.setSenha(senha);

                usersRef.child(usuarioModel.getId()).setValue(usuarioModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        Toast.makeText(this, "Informações salvas no banco!", Toast.LENGTH_SHORT).show();
                });

                if (fazerCadastro) {
                    auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(this, VisualizarProdutosActivity.class));
                        }else{
                            Toast.makeText(this, "Essas informações não podem ser cadastradas!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}