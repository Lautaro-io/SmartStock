package com.example.smartstock.ui.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartstock.R
import com.example.smartstock.database.entities.ProductForBranch
import com.example.smartstock.database.remote.ApiService
import com.example.smartstock.database.remote.ProductDataResponse
import com.example.smartstock.databinding.ActivityHomeBinding
import com.example.smartstock.ui.adapters.ProductAdapter
import com.example.smartstock.viewmodel.BranchProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var retrofit: Retrofit
    private val productViewModel: BranchProductViewModel by viewModels()
    private lateinit var rvProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    private var sucId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofitProduct()
        sucId = intent?.getIntExtra("SUC_ID", 0)!!
        initUI()

    }

    private fun initUI() {
        val sucName = intent.getStringExtra("SUC_NAME").toString()

        binding.tvSucName.text = "Sucursal : ${sucName}"
        binding.fobAddProduct.setOnClickListener { showDialogAddProduct() }
        rvProducts = binding.productsRv
        rvProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        productAdapter = ProductAdapter(emptyList()) { product ->
            Log.i("CHELO", "Eliminando producto")
            lifecycleScope.launch { productViewModel.deleteProduct(product) }
        }
        rvProducts.adapter = productAdapter
        productViewModel.allProducts.observe(this) { productos ->
            productAdapter.updateProducts(productos)
        }
    }


    private fun showDialogAddProduct() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_product_view)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        val name: EditText = dialog.findViewById(R.id.nameProduct)
        val codeBar: EditText = dialog.findViewById(R.id.barCode)
        val countProduct: EditText = dialog.findViewById(R.id.countProduct)
        val expireDateDay: EditText = dialog.findViewById(R.id.expireDateDay)
        val expireDateMonth: EditText = dialog.findViewById(R.id.expireDateMes)
        val expireDateYear: EditText = dialog.findViewById(R.id.expireDateYear)
        val btnAddProduct: Button = dialog.findViewById(R.id.btnAddProduct)
        val btnSearch: Button = dialog.findViewById(R.id.btnScanProduct)
        var url: String? = null
        btnSearch.setOnClickListener {
            lifecycleScope.launch {
                val code = codeBar.text.toString()
                    .trim() //Primero se obtiene el code y despues se usa para llamar a la funcion de retrofit
                if (code.isNotEmpty()) {
                    val product = searchProduct(code)
                    Log.i("CHELO", product?.product?.name.toString())
                    name.setText(product?.product?.name)
                    url = product?.product?.image.toString()


                } else {
                    Log.i("CHELO", "Error")
                }
            }
        }

        btnAddProduct.setOnClickListener {
            val productName = name.text.toString().trim()
            val productCode = codeBar.text.toString().trim()
            val productCount = countProduct.text.toString().trim().toIntOrNull() ?: 0
            val expireDate =
                "${expireDateDay.text.toString()}/${expireDateMonth.text.toString()}/${expireDateYear.text.toString()}"
            if (productName.isNotEmpty() && productCode.isNotEmpty() && productCount > 0) {
                if (sucId > 0) {
                    Log.i("CHELO", "SucID es mayor a 0")
                    val product =
                        ProductForBranch(
                            0,
                            productName,
                            productCount,
                            expireDate,
                            sucId,
                            urlImage = url ?: ""
                        )
                    lifecycleScope.launch {

                        productViewModel.insertProduct(product)
                        Log.i("CHELO", "${product.toString()} agregado con exito.")
                        dialog.dismiss()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "No se encontro una sucursal para este producto.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "No se pudo agregar el producto,complete todos los campos.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }


        dialog.show()

    }


    private fun getRetrofitProduct(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://world.openfoodfacts.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    suspend fun searchProduct(code: String): ProductDataResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getProduct(code)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }


    }

}