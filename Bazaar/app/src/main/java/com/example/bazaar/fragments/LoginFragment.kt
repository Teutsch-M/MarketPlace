package com.example.bazaar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bazaar.R
import com.example.bazaar.repository.Repository
import com.example.bazaar.viewmodels.LoginViewModel
import com.example.bazaar.viewmodels.LoginViewModelFactory
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(this.requireContext(), Repository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val editText1: EditText = view.findViewById(R.id.edittext_email_login_fragment)
        val editText2: EditText = view.findViewById(R.id.edittext_password_login_fragment)
        val button1: Button = view.findViewById(R.id.button_login_login_fragment)
        val button2: Button = view.findViewById(R.id.button_signup_login_fragment)

        button2.setOnClickListener{

            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            Log.i("XXX", "navigate to register")

        }

        button1.setOnClickListener {

            loginViewModel.user.value.let {
                if (it != null) {
                    it.username = editText1.text.toString()
                }
                if (it != null) {
                    it.password = editText2.text.toString()
                }
            }

            lifecycleScope.launch {
                loginViewModel.login()
            }

        }

        loginViewModel.token.observe(viewLifecycleOwner){

            Log.d("xxx", "navigate to list")
            ///findNavController().navigate(R.id.action_loginFragment_to_listFragment)

        }

        return view

    }

}