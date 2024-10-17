package projeto.tcc.cafeteiriagoumertdelivery.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import projeto.tcc.cafeteiriagoumertdelivery.R;
import projeto.tcc.cafeteiriagoumertdelivery.model.ProdutoModel;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MyViewHolder> {

    List<ProdutoModel> list;
    Activity a;
    boolean gerente;

    public AdapterProdutos(List<ProdutoModel> list, Activity a, boolean gerente) {
        this.list = list;
        this.a = a;
        this.gerente = gerente;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProdutoModel produtoSeleciconado = list.get(position);

        if (gerente) {
            holder.editarProduto.setVisibility(View.VISIBLE);
        }

        holder.textNome.setText(produtoSeleciconado.getNome());
        holder.textPreco.setText("R$ " + produtoSeleciconado.getPreco());
        Picasso.get().load(produtoSeleciconado.getImagem()).into(holder.fotoProduto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Button editarProduto;
        TextView textPreco, textNome;
        ImageView fotoProduto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            editarProduto = itemView.findViewById(R.id.editar_produto);
            textPreco = itemView.findViewById(R.id.textPreco);
            textNome = itemView.findViewById(R.id.textProduto);
            fotoProduto = itemView.findViewById(R.id.imageProduto);
        }
    }
}
