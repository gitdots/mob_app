package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class PredictionsFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private val model : OverviewModel by viewModels{
        OverviewModel.Factory(getTodayDate())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_predictions, container, false)
        view.findViewById<Button>(R.id.go_button)?.setOnClickListener(this)
        view.findViewById<TextView>(R.id.current_date).text = getShowDate()

        val rec = ParentRecycler()

        model.data.observe(viewLifecycleOwner) {
            Log.d("DD",it.toString())

        }

        return view
    }

    override fun onClick(v: View?) {
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_predictionsFragment_to_resultsFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_custom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_contact -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:") // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "contact@probet.live")
                startActivity(intent)

                true
            }
            R.id.action_premium -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://probet.live"))
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getShowDate() : String{
        val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
        val date = LocalDate.parse(LocalDate.now().toString())
        val formattedDate = date.format(formatter)
        return formattedDate.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayDate() : String{
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val date = LocalDate.parse(LocalDate.now().toString())
        val formattedDate = date.format(formatter)
        return formattedDate.toString()
    }

}