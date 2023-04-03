package com.teravin.kotlin_base_template.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.teravin.kotlin_base_template.databinding.FragmentErrorBinding
import com.teravin.kotlin_base_template.util.TeravinFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ErrorFragment : TeravinFragment<FragmentErrorBinding, ErrorViewModel>() {
    override val viewModel by viewModels<ErrorViewModel>()

    override fun inflateBinding(inflater: LayoutInflater): FragmentErrorBinding =
        FragmentErrorBinding.inflate(inflater)


    override fun bindViewModel() {
        viewDataBinding.viewModel = viewModel
    }

    override fun navigateToErrorScreen() {
        //Do Nothing
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = ErrorFragmentArgs.fromBundle(requireArguments())
        viewModel.setLocalError(args.localError)

        lifecycleScope.launch {
            viewModel.result.collect {
                findNavController().navigateUp()
            }
        }
    }
}