package com.teravin.kotlin_base_template.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class TeravinFragment<VDB : ViewDataBinding, VM : TeravinViewModel> :
    Fragment() {

    protected lateinit var viewDataBinding: VDB
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = inflateBinding(inflater)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        bindViewModel()

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            viewModel.errorState.collect {
                it?.let {
                    navigateToErrorScreen()
                }
            }
        }
    }

    protected abstract fun inflateBinding(inflater: LayoutInflater): VDB
    protected abstract fun bindViewModel()
    protected abstract fun navigateToErrorScreen()
}