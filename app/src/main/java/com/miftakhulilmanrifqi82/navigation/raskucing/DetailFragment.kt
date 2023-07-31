package com.miftakhulilmanrifqi82.navigation.raskucing

import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.miftakhulilmanrifqi82.navigation.raskucing.databinding.FragmentDetailBinding
import com.miftakhulilmanrifqi82.navigation.raskucing.model.ModelMain
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    companion object {
        const val DETAIL_TANAMAN = "DETAIL_TANAMAN"
    }

    private lateinit var binding: FragmentDetailBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        // Mengakses data yang dikirimkan melalui Bundle
        val modelMain = arguments?.getSerializable(DETAIL_TANAMAN) as ModelMain

        // Menampilkan data pada TextView di layout
        binding.tvNamaTanaman.text = modelMain.nama
        binding.tvManfaatTanaman.text = modelMain.deskripsi
        Glide.with(this)
            .load(modelMain.image)
            .into(imageTanaman)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Mem-parse layout XML menggunakan view binding
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        // Mengakses Toolbar di dalam layout
        val toolbar = binding.toolbar

        // Menambahkan onClickListener pada Toolbar
        toolbar.setNavigationOnClickListener {
            // Menggunakan NavController untuk berpindah ke Fragment lain
            findNavController().navigate(R.id.action_detailFragment_to_utamaFragment)
        }

        // Menghasilkan root view dari layout yang telah di-parse
        return binding.root
    }
}
