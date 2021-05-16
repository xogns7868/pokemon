package com.tom.pokemon.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tom.pokemon.R
import com.tom.pokemon.data.handler.Resource
import com.tom.pokemon.data.handler.Status
import com.tom.pokemon.databinding.FragmentSearchBinding
import com.tom.pokemon.databinding.ItemPokemonDetailBinding
import com.tom.pokemon.domain.entity.PokeMonDetail
import com.tom.pokemon.presentation.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.item_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel by sharedViewModel<SearchViewModel>() //fragment에서 viewModel을 최초로 생성
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
//        val bottomSheet = binding.root.findViewById<ConstraintLayout>(R.id.persistent_bottom_sheet)
//        bottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(bottomSheet)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextSearch.addTextChangedListener {
            viewModel.searchPokeMons(it?.toString() ?: return@addTextChangedListener)
        }

        binding.recyclerView.adapter = SearchAdapter().apply {
            itemOnClick = {
                binding.root.hideKeyboard()
                viewModel.selectPokeMon(it.id, it.name)
            }
        }
        with(viewModel) {
            pokeMonList.observe(viewLifecycleOwner) {
                isSuccess(it){
                    (binding.recyclerView.adapter as SearchAdapter).setItems(it.data!!)
                }
            }

            pokeMonDetail.observe(viewLifecycleOwner) {
                isSuccess(it) {
                    PokeMonDetailDialog().show(childFragmentManager, "null")
                }
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun isSuccess(resource: Resource<*>, func: () -> Unit){
        when(resource.status){
            Status.SUCCESS -> func()
            Status.ERROR -> Log.e("Error", "Network Error")
            Status.LOADING -> Log.e("Loading", "Please Wait Loading")
        }
    }
}