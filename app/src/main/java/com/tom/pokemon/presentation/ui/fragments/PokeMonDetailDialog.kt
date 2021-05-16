package com.tom.pokemon.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import coil.load
import androidx.lifecycle.observe
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tom.pokemon.R
import com.tom.pokemon.databinding.ItemPokemonDetailBinding
import com.tom.pokemon.presentation.viewmodels.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokeMonDetailDialog : DialogFragment() {

    private val viewModel by sharedViewModel<SearchViewModel>()
    private lateinit var itemPokemonDetailBinding: ItemPokemonDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemPokemonDetailBinding = ItemPokemonDetailBinding.inflate(inflater, container, false)
        return itemPokemonDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel){
            pokeMonDetail.observe(viewLifecycleOwner){pokeMonDetailResource ->
                val pokeMonDetail = pokeMonDetailResource.data!!
                itemPokemonDetailBinding.apply {
                    lifecycleOwner = viewLifecycleOwner
                    pokemonDetail = pokeMonDetail
                }

                itemPokemonDetailBinding.root.apply {
                    findViewById<ImageView>(R.id.image)?.load(pokeMonDetail.sprite)
                    if (pokeMonDetail.location == null)
                        findViewById<TextView>(R.id.location).text = "서식지 정보 없음"
                    else {
                        findViewById<TextView>(R.id.location).text = "서식지 정보 있음"
                        findViewById<ImageView>(R.id.image).setOnClickListener {
                            dismiss()
                            requireActivity().supportFragmentManager.fragmentFactory = MyFragmentFactory(pokeMonDetail)
                            val fragment = requireActivity().supportFragmentManager.fragmentFactory.instantiate(requireActivity().classLoader!!, MapFragment::class.java.name)
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.nav_host_fragment, fragment)
                                .commitNow()
//                            findNavController().navigate(
//                                    R.id.action_searchFragment_to_mapFragment,
//                                    Bundle().apply {
//                                        putParcelable("pokeMonDetail", pokeMonDetail)
//                                    }
//                            )
                        }
                    }
                }
            }
        }
    }
}

//class PokeMonDetailDialog : BottomSheetDialogFragment(){
//
//    private lateinit var itemPokemonDetailBinding: ItemPokemonDetailBinding
//    private val viewModel: SearchViewModel by viewModels({requireParentFragment()})
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        itemPokemonDetailBinding = ItemPokemonDetailBinding.inflate(inflater, container, false)
//        return itemPokemonDetailBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        with(viewModel){
//            pokeMonDetail.observe(viewLifecycleOwner){pokeMonDetail ->
//                itemPokemonDetailBinding.apply {
//                    lifecycleOwner = viewLifecycleOwner
//                    pokemonDetail = pokeMonDetail
//                }.root.apply {
//                    findViewById<ImageView>(R.id.image)?.load(pokeMonDetail.sprite)
//                    if (pokeMonDetail.location == null)
//                        findViewById<TextView>(R.id.location).text = "서식지 정보 없음"
//                    else {
//                        findViewById<TextView>(R.id.location).text = "서식지 정보 있음"
//                        findViewById<ImageView>(R.id.image).setOnClickListener {
//                            dismiss()
//                            findNavController().navigate(
//                                    R.id.action_searchFragment_to_mapFragment,
//                                    Bundle().apply {
//                                        putParcelable("pokeMonDetail", pokeMonDetail)
//                                    }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}