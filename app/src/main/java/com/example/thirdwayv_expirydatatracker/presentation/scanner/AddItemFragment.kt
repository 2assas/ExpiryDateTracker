package com.example.thirdwayv_expirydatatracker.presentation.scanner

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.thirdwayv_expirydatatracker.R
import com.example.thirdwayv_expirydatatracker.databinding.FragmentAddItemBinding
import com.example.thirdwayv_expirydatatracker.domain.Item
import com.example.thirdwayv_expirydatatracker.framework.ExpiryTrackerViewModelFactory
import com.example.thirdwayv_expirydatatracker.framework.NotificationReceiver
import com.example.thirdwayv_expirydatatracker.presentation.home.HomeViewModel
import com.google.gson.Gson
import java.text.SimpleDateFormat


class AddItemFragment : Fragment() {
    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var itemsList: List<Item>
    private var hours: Int = 0
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProviders.of(this, ExpiryTrackerViewModelFactory)[HomeViewModel::class.java]
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        setupViews()
        observers()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setupViews() {
        homeViewModel.loadScannedItems()
        val barcode = arguments?.getString("barcode").toString()
        binding.itemSerial.text = barcode
        val calendar: Calendar = Calendar.getInstance()
        binding.addButton.setOnClickListener {
            if (validation()) {
                when ((itemsList.size + 1) % 4) {
                    1 -> hours = 6
                    2 -> hours = 12
                    3 -> hours = 18
                    0 -> hours = 24
                }
                calendar.add(Calendar.HOUR, hours)
                val expiryDate: String = format.format(calendar.time)
                homeViewModel.addScannedItem(
                    Item(
                        itemsList.size + 1,
                        binding.itemName.text.toString(),
                        binding.itemCategory.text.toString(),
                        expiryDate
                    )
                )
                setNotifications(calendar, itemsList)
                Navigation.findNavController(binding.root).navigate(R.id.navigation_home)
            }
        }
    }

    private fun validation(): Boolean {
        return when {
            binding.itemCategory.text.isEmpty() -> {
                binding.itemCategory.error = "Required"
                false
            }
            binding.itemName.text.isEmpty() -> {
                binding.itemName.error = "Required"
                false
            }
            else -> {
                true
            }
        }
    }

    private fun observers() {
        homeViewModel.scannedItems.observe(viewLifecycleOwner, {
            itemsList = it
        })
    }

    private fun setNotifications(calendar: Calendar, itemList: List<Item>) {
        val intent = Intent(
            requireActivity(),
            NotificationReceiver::class.java
        )
        // Store the item list as JSON file using GSON
        val gson = Gson()
        val json: String = gson.toJson(itemList)
        intent.putExtra("itemList", json)
        val pendingIntent = PendingIntent.getBroadcast(
            requireActivity(), 100, intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val alarmManager = requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            alarmManager!!.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }
}
