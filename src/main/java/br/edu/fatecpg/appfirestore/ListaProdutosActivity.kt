import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fatecpg.appfirestore.Class.Produto
import br.edu.fatecpg.appfirestore.R
import br.edu.fatecpg.appfirestore.databinding.ActivityListaProdutosBinding
import com.google.firebase.firestore.FirebaseFirestore

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding
    private val db = FirebaseFirestore.getInstance()
    private val produtos = mutableListOf<Produto>()
    private val adapter = ProdutoAdapter(produtos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando o binding
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando o RecyclerView com o LayoutManager e o adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Obtendo os dados da coleção "Produtos" no Firestore
        db.collection("Produtos")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val produto = document.toObject(Produto::class.java)
                    produtos.add(produto)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                // Tratamento de erro (opcional)
                e.printStackTrace()
            }
    }
}
