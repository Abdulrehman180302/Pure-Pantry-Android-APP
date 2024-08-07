package ghanam.com.abdo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import ghanam.com.abdo.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    lateinit var binding:FragmentSignUpBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.signUpButton.setOnClickListener {
            validateSignUp(it)
        }

        binding.loginLink.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        return binding.root
    }

    private fun validateSignUp(it: View?) {

        binding.apply {
            val email= emailTextInput.text.toString()
            val password=passwordTextInput.text.toString()
            val confirmPassword= confirmPasswordTextInput.text.toString()
            if (email.isEmpty() or password.isEmpty() or confirmPassword.isEmpty()){
                Toast.makeText(context,"Please complete all fields!",Toast.LENGTH_LONG).show()
                return
            }
            if (password != confirmPassword){
                Toast.makeText(context,"password not matching",Toast.LENGTH_LONG).show()
                return
            }
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(context,"You have successfully Signed up",Toast.LENGTH_LONG).show()
                            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.action_signUpFragment_to_loginFragment) }
                        }
                        }
                    }
                }
            }
            //if (it != null) {
               // Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_loginFragment)
            }

        }




