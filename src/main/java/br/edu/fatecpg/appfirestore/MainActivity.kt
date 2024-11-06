package br.edu.fatecpg.appfirestore

import ListaProdutosActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.appfirestore.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        // Configuração do botão "Salvar" para salvar dados no Firestore
        binding.btnSalvar.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val categoria = binding.edtCategoria.text.toString()
            val preco = binding.edtPreco.text.toString()
            val produto = hashMapOf(
                "nome" to nome,
                "categoria" to categoria,
                "preco" to preco
            )
            db.collection("produtos")
                .add(produto)
                .addOnSuccessListener {
                    Toast.makeText(this, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()
                    binding.edtNome.text.clear()
                    binding.edtCategoria.text.clear()
                    binding.edtPreco.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Erro ao salvar produto", Toast.LENGTH_SHORT).show()
                }
        }

        // Configuração do FloatingActionButton para abrir uma nova Activity
        binding.fabAdicionarProduto.setOnClickListener {
            // Intenção para abrir uma nova tela de cadastro, se desejar
            val intent = Intent(this, ListaProdutosActivity::class.java)
            startActivity(intent)
        }
    }
}
