import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.appfirestore.Class.Produto
import br.edu.fatecpg.appfirestore.R

class ProdutoAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ProdutoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_produto, parent, false)
        return ProdutoViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.bind(produto)
    }
    override fun getItemCount() = produtos.size
    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeText: TextView = itemView.findViewById(R.id.edtNome)
        private val categoriaText: TextView =
            itemView.findViewById(R.id.edtCategoria)
        private val precoText: TextView = itemView.findViewById(R.id.edtPreco)
        fun bind(produto: Produto) {
            nomeText.text = produto.nome
            categoriaText.text = produto.categoria
            precoText.text = produto.preco
        }
    }
}
