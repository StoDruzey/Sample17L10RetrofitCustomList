package com.example.sample17l10retrofitcustomlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sample17l10retrofitcustomlist.databinding.FragmentFirstBinding
import com.example.sample17l10retrofitcustomlist.databinding.ItemUserBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var currentRequest: Call<List<User>>? = null
    private val adapter by lazy { UserAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(layoutInflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            recyclerView.adapter = adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubInterface = retrofit.create<GithubInterface>()
        currentRequest = githubInterface
            .getUsers(10, 30)
            .apply {
                enqueue(object : Callback<List<User>> {
                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {
                        if (response.isSuccessful) {
                            val users = response.body() ?: return
                            adapter.submitList(users)
                        }
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}