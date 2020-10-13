package com.example.yournight

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_random.*
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RandomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RandomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var container: ArrayList<RandomList>? = null
    private var recipeAdapter: RandomListAdapter? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        container = ArrayList()

        recipeAdapter = RandomListAdapter(container!!, activity!!)
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        randomListView.setLayoutManager(layoutManager)
        randomListView.setAdapter(recipeAdapter)

        addElementRandomButton.setOnClickListener{

            val possibility = RandomList()
            container!!.add(possibility)
            recipeAdapter!!.notifyDataSetChanged()
        }

        randomButton.setOnClickListener{
            onRandomClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RandomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RandomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun onRandomClick() {

        if(addText.length() > 0 && addText2.length() > 0) {
            var numRandom = 2 + container!!.size
            var min = 1
            var max = numRandom
            var random: Int = Random().nextInt(max - min + 1) + min
            Log.d("Random:", random.toString())
            if(random == 1) {
                resultRandom.text = addText.text.toString()
            } else if (random == 2) {
                resultRandom.text = addText2.text.toString()
            } else {
                resultRandom.text = container!![random - 3].toString()
            }
        }
    }
}

/*
TODO:
    - rimuovere il titoletto nel page adapter?
    - scroll view nel random fragment
    - sistemare home page
    - random fragment non riusciamo a stampare il nome effettivo
    - rimuovere il nome in alto nell'app
 */