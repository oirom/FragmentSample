package com.example.fragmentsample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()

        var menu = mutableMapOf("name" to "Taro Yamada", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Hanako Yamada", "age" to "18")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Taro Tanaka", "age" to "21")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Hanako Tanaka", "age" to "19")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Taro Ono", "age" to "22")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Hanako Ono", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Akio Yamada", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Kanako Yamada", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Akio Tanaka", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Kanako Tanaka", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Akio Ono", "age" to "20")
        menuList.add(menu)
        menu = mutableMapOf("name" to "Kanako Ono", "age" to "20")
        menuList.add(menu)

        val from = arrayOf("name", "age")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(activity, menuList, android.R.layout.simple_list_item_2, from, to)

        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()

        return view
    }

    private var _isLayoutXLarge = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val menuThanksFrame = activity?.findViewById<View>(R.id.menuThanksFrame)
        if (menuThanksFrame == null) {
            _isLayoutXLarge = false
        }
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val menu = parent.getItemAtPosition(position) as MutableMap<String, Any>
            val menuName = menu["name"] as String
            val menuAge = menu["age"] as String

            val bundle = Bundle()
            bundle.putString("menuName", menuName)
            bundle.putString("menuAge", "$menuAge yo")

            if (_isLayoutXLarge) {
                val transaction = fragmentManager?.beginTransaction()
                val menuThanksFragment = MenuThanksFragment()
                menuThanksFragment.arguments = bundle
                transaction?.replace(R.id.menuThanksFrame, menuThanksFragment)
                transaction?.commit()
            }
            else {
                val intent2MenuThanks = Intent(activity, MenuThanksActivity::class.java)
                intent2MenuThanks.putExtras(bundle)
                startActivity(intent2MenuThanks)
            }
        }
    }
}