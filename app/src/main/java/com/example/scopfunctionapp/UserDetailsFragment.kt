package com.example.scopfunctionapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private lateinit var responseDTO: ResponseDTO
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClick.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {
        val apiClient=Network.getInstance().create(ApiClient::class.java)
        apiClient.getUserDetails(2).enqueue(object : Callback<ResponseDTO>{
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                response.body()?.run {
                    responseDTO=this
                    responseDTO.data?.apply {
                        txtFirstName.text=first_name
                        txtLastName.text=last_name
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}