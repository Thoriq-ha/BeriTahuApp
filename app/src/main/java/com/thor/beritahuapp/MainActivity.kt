package com.thor.beritahuapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.adapter.BeritaAdapter
import com.thor.beritahuapp.databinding.ActivityMainBinding
import com.thor.beritahuapp.model.BeritaResponse
import com.thor.beritahuapp.network.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
//    lateinit var adapter: BeritaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var data : ArrayList<ArticlesItem>
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (isConnect()) {
            var par = "tesla"
            var str = when (par) {
                "tesla" -> "everything?q=tesla&from=2021-02-07&sortBy=publishedAt&apiKey=1cd906d561f34bcda0ba8088d9765da5"
                "apple" -> "everything?q=apple&from=2021-03-06&to=2021-03-06&sortBy=popularity"
                "Us" -> "top-headlines?country=us&category=business"
                "tech" -> "top-headlines?sources=techcrunch"
                else -> ""
            }
            getData(str)


        } else {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Tidak bisa terhubung dengan jaringan", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NotConnected::class.java)
            startActivity(intent)


        }
    }


    private fun getData(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
//        val response : Response<BeritaResponse> = service

        NetworkModule.service().getData().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(
                    call: Call<BeritaResponse>,
                    response: Response<BeritaResponse>
            ) {
                Log.d("response succes", response.message())
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    val data = response.body()?.articles
                    Log.d("opo", data?.size?.toString()!!)
                    if (data?.size ?: 0 > 0) {
                        binding.listKategori1.adapter = BeritaAdapter(data, this@MainActivity)


                        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                BeritaAdapter(data, this@MainActivity).filter.filter(newText)
                                return false
                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
            }
        })

//        NetworkModule.service().getData().enqueue(object : Callback<BeritaResponse> {
//            override fun onResponse(
//                call: Call<BeritaResponse>,
//                response: Response<BeritaResponse>
//            ) {
//                Log.d("response succes", response.message())
//                if (response.isSuccessful) {
//                    binding.progressBar.visibility = View.GONE
//                    val data = response.body()?.articles
//                    Log.d("opo", data?.size?.toString()!!)
//                    if (data?.size ?: 0 > 0) {
//                        binding.listKategori1.adapter = BeritaAdapter(data, this@MainActivity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//
//        NetworkModule.service().getKategoriApple().enqueue(object : Callback<BeritaResponse> {
//            override fun onResponse(
//                call: Call<BeritaResponse>,
//                response: Response<BeritaResponse>
//            ) {
//                Log.d("response succes", response.message())
//                if (response.isSuccessful) {
//                    binding.progressBar.visibility = View.GONE
//                    val data = response.body()?.articles
//                    Log.d("opo", data?.size?.toString()!!)
//                    if (data?.size ?: 0 > 0) {
//                        binding.listKategori2.adapter = BeritaAdapter(data, this@MainActivity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//        NetworkModule.service().getKategoriUs().enqueue(object : Callback<BeritaResponse> {
//            override fun onResponse(
//                call: Call<BeritaResponse>,
//                response: Response<BeritaResponse>
//            ) {
//                Log.d("response succes", response.message())
//                if (response.isSuccessful) {
//                    binding.progressBar.visibility = View.GONE
//                    val data = response.body()?.articles
//                    Log.d("opo", data?.size?.toString()!!)
//                    if (data?.size ?: 0 > 0) {
//                        binding.listKategori3.adapter = BeritaAdapter(data, this@MainActivity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
//        NetworkModule.service().getKategoriTechCrunch().enqueue(object : Callback<BeritaResponse> {
//            override fun onResponse(
//                call: Call<BeritaResponse>,
//                response: Response<BeritaResponse>
//            ) {
//                Log.d("response succes", response.message())
//                if (response.isSuccessful) {
//                    binding.progressBar.visibility = View.GONE
//                    val data = response.body()?.articles
//                    Log.d("opo", data?.size?.toString()!!)
//                    if (data?.size ?: 0 > 0) {
//                        binding.listKategori4.adapter = BeritaAdapter(data, this@MainActivity)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
//                binding.progressBar.visibility = View.GONE
//            }
//        })
    }

    fun isConnect(): Boolean {
        val connect: ConnectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }


}