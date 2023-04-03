package com.teravin.kotlin_base_template.ui.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.teravin.kotlin_base_template.databinding.SampleFragmentBinding
import com.teravin.kotlin_base_template.util.TeravinFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment : TeravinFragment<SampleFragmentBinding, SampleViewModel>() {
    override val viewModel: SampleViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater
    ): SampleFragmentBinding = SampleFragmentBinding.inflate(inflater)

    override fun bindViewModel() {
        viewDataBinding.viewModel = viewModel
    }

    override fun navigateToErrorScreen() {
        val action =
            SampleFragmentDirections.actionSampleFragmentToErrorFragment(viewModel.errorState.value!!)
        findNavController().navigate(action)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSampleFromDatabase()
    }
}