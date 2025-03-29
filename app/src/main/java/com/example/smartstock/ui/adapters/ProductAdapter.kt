package com.example.smartstock.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartstock.R
import com.example.smartstock.database.entities.ProductForBranch

class ProductAdapter ( var listProducts: List<ProductForBranch> , val onDeleteClick : (ProductForBranch) -> Unit): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        holder.render(listProducts[position] , onDeleteClick)
    }

    override fun getItemCount() = listProducts.size

    fun updateProducts(newProducts: List<ProductForBranch>){
        listProducts = newProducts
        notifyDataSetChanged()
    }
}