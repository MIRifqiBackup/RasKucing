package com.miftakhulilmanrifqi82.navigation.raskucing

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftakhulilmanrifqi82.navigation.raskucing.adapter.MainAdapter
import com.miftakhulilmanrifqi82.navigation.raskucing.model.ModelMain
import kotlinx.android.synthetic.main.fragment_utama.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class UtamaFragment : Fragment() {

    var modelMain: MutableList<ModelMain> = ArrayList()
    lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_utama, container, false)

        // transparent background searchview
        val searchPlateId = view.searchTanaman.getContext()
            .resources.getIdentifier("android:id/search_plate", null, null)
        val searchPlate = view.searchTanaman.findViewById<View>(searchPlateId)
        searchPlate.setBackgroundColor(Color.TRANSPARENT)
        view.searchTanaman.setImeOptions(EditorInfo.IME_ACTION_DONE)
        view.searchTanaman.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mainAdapter.filter.filter(newText)
                return true
            }
        })

        view.rvListTanaman.setLayoutManager(LinearLayoutManager(view.context))
        view.rvListTanaman.setHasFixedSize(true)

        // get data json
        getNamaTanaman(view)
        return view
    }

    fun getNamaTanaman(view: View) {
        try {
            val stream = view.context.assets.open("daftar_ras_kucing.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArray = jsonObject.getJSONArray("daftar_ras_kucing")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val dataApi = ModelMain()
                    dataApi.nama = jsonObjectData.getString("nama")
                    dataApi.deskripsi = jsonObjectData.getString("deskripsi")
                    dataApi.image = jsonObjectData.getString("image_url")
                    modelMain.add(dataApi)
                }

                mainAdapter = MainAdapter(view.context, modelMain)
                view.rvListTanaman.adapter = mainAdapter
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
            Toast.makeText(view.context, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
        }
    }
}
