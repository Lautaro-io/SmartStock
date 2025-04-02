package com.example.smartstock.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.smartstock.database.entities.ProductForBranch
import com.example.smartstock.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    fun render(product: ProductForBranch ,onDeleteClick : (ProductForBranch) -> Unit) {

        Picasso.get().load(product.urlImage).into(binding.ivProductPhoto)
        binding.tvNameProduct.text = "Nombre : ${product.name_product}"
        binding.tvCountProduct.text = "Cantidad : ${product.stock.toString()}"
        binding.tvExpireDate.text = "Fecha de vencimiento : ${product.expire_date}"
        binding.deleteBtn.setOnClickListener { onDeleteClick(product)  }


    }
}