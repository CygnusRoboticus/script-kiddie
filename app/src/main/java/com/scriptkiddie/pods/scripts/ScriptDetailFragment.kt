package com.scriptkiddie.pods.scripts

import android.app.Activity
import android.support.design.widget.CollapsingToolbarLayout
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.scriptkiddie.R
import com.scriptkiddie.pods.scripts.ScriptLoader
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView

/**
 * A fragment representing a single Script detail screen.
 * This fragment is either contained in a [ScriptListActivity]
 * in two-pane mode (on tablets) or a [ScriptDetailActivity]
 * on handsets.
 */
class ScriptDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: Script? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = ScriptLoader.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]

            val activity = this.activity
            val appBarLayout = activity.findViewById(R.id.toolbar_layout) as CollapsingToolbarLayout
            if (appBarLayout != null) {
                appBarLayout.title = mItem!!.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.script_detail, container, false)

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            (rootView.findViewById(R.id.script_detail) as TextView).text = mItem!!.body
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}

class ScriptDetailFragmentUI : AnkoComponent<ScriptDetailFragment> {
    override fun createView(ui: AnkoContext<ScriptDetailFragment>): View {
        return with(ui) {
            textView {
                text = "asdf"
            }
        }
    }
}