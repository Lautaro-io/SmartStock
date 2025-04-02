package com.example.smartstock.ui.home

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartstock.R
import com.example.smartstock.database.entities.ProductForBranch
import com.example.smartstock.database.entities.Sucursal
import com.example.smartstock.database.remote.ApiService
import com.example.smartstock.database.remote.ProductDataResponse
import com.example.smartstock.databinding.ActivityHomeBinding
import com.example.smartstock.ui.adapters.ProductAdapter
import com.example.smartstock.viewmodel.BranchProductViewModel
import com.example.smartstock.viewmodel.SucursalViewModel
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
    private val branchViewModel: SucursalViewModel by viewModels()
    private lateinit var rvProducts: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var productAdapter: ProductAdapter

    private var sucId: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofitProduct()
        sucId = intent?.getIntExtra("SUC_ID", 0)!!
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val userName = sharedPreferences.getString("USER_NAME", "") ?: ""

        initUI()

    }

    private fun initUI() {
        val sucName = intent.getStringExtra("SUC_NAME").toString()


        binding.tvSucName.setText("Sucursal : ${sucName}")
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
        binding.btnNewBranch.setOnClickListener {
            showDialogAddNewBranch()
        }
    }


    private fun showDialogAddProduct() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_product_view)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)

        val spinner: Spinner = dialog.findViewById(R.id.spinnerSucursales)

        branchViewModel.allSucursales.observe(this) { sucursales ->
            val sucursalesNames = sucursales.map{it.name}
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sucursalesNames)
            spinner.adapter = adapter
        }

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
            val selectedPosition = spinner.selectedItemPosition // esto me devuelve el indice del item seleccionado
            val selectedSucursal = branchViewModel.allSucursales.value?.get(selectedPosition) // aca uno el int que me devolvio el item seleccionado para "agarrarlo de la lista total de sucursales "
            if (productName.isNotEmpty() && productCode.isNotEmpty() && productCount > 0) {
                if (sucId > 0) {
                    val product =
                        ProductForBranch(
                            0,
                            productName,
                            productCount,
                            expireDate,
                            selectedSucursal?.id ?: 0,
                            urlImage = url ?: ""
                        )
                     lifecycleScope.launch {

                        productViewModel.insertProduct(product)
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
                //Toast.makeText(this,"No se encontro ningun producto con ese codigo.", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun showDialogAddNewBranch() {
        val dialog = Dialog(this)




        dialog.setContentView(R.layout.dialog_new_branch)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        val userName = sharedPreferences.getString("USER_NAME", "Invitado")
        val etUserName: EditText = dialog.findViewById(R.id.etUserName)
        etUserName.setText(userName)
        val etSucName: EditText = dialog.findViewById(R.id.etNewSucName)
        val btnAddSuc: Button = dialog.findViewById(R.id.addNewSucBtn)

        btnAddSuc.setOnClickListener {
            val nameUser:String? = userName
            val sucName = etSucName.text.toString().trim()

            if (nameUser?.isNotEmpty()!! && sucName.isNotEmpty()) {
                lifecycleScope.launch {
                    val newSuc = Sucursal(0, sucName, userName!!)
                    branchViewModel.insertBranch(newSuc)
                    Log.i("CHELO", "${newSuc.toString()} agregado con exito! ")
                    dialog.dismiss()
                }
                Toast.makeText(
                    this@HomeActivity,
                    "Sucursal $sucName agregada con exito!",
                    Toast.LENGTH_SHORT
                ).show()
            } else{
                Toast.makeText(this@HomeActivity, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()

    }

}