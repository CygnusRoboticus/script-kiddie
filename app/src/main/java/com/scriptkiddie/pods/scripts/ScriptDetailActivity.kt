package com.scriptkiddie.pods.scripts

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.ActionBar
import android.view.Gravity
import android.view.MenuItem

import com.scriptkiddie.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.support.v4.nestedScrollView

/**
 * An activity representing a single Script detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ScriptListActivity].
 */
class ScriptDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScriptDetailActivityUI().setContentView(this)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString(ScriptDetailFragment.ARG_ITEM_ID,
                    intent.getStringExtra(ScriptDetailFragment.ARG_ITEM_ID))
            val fragment = ScriptDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction().add(R.id.script_detail_container, fragment).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(Intent(this, ScriptListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

class ScriptDetailActivityUI : AnkoComponent<ScriptDetailActivity> {
    override fun createView(ui: AnkoContext<ScriptDetailActivity>): View {
        return with(ui) {
            coordinatorLayout {
                lparams {
                    width = matchParent
                    height = matchParent
                }
                fitsSystemWindows = true

                appBarLayout(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                    lparams {
                        width = matchParent
                        height = dimen(R.dimen.app_bar_height)
                    }
                    fitsSystemWindows = true

                    collapsingToolbarLayout {
                        lparams {
                            width = matchParent
                            height = matchParent
                        }
                        id = R.id.toolbar_layout
                        fitsSystemWindows = true
                        contentScrim = ctx.getDrawable(R.color.colorPrimary)
                        // app:layout_scrollFlags="scroll|exitUntilCollapsed"

                        toolbar {
                            lparams {
                                width = matchParent
                                height = dimen(R.dimen.app_bar_height)
                            }
                            popupTheme = R.style.ThemeOverlay_AppCompat_Light
                            // app:layout_collapseMode="pin"
                        }.let {
                            toolbar -> {
                                ui.owner.setSupportActionBar(toolbar)
                                ui.owner.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                            }
                        }
                    }
                }
                nestedScrollView {
                    lparams {
                        width = matchParent
                        height = matchParent
                    }
                    id = R.id.script_detail_container
                    // app:layout_behavior="@string/appbar_scrolling_view_behavior" />

                    //script detail
                }
                floatingActionButton {
                    lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.CENTER or Gravity.START
                        margin = dimen(R.dimen.fab_margin)
                        anchorId = R.id.script_detail_container
                        anchorGravity = Gravity.TOP or Gravity.END
                    }
                    image = ctx.getDrawable(android.R.drawable.stat_notify_chat)

                    onClick {
                        Snackbar.make(
                            view,
                            "Replace with your own detail action",
                            Snackbar.LENGTH_LONG
                        ).setAction("Action", null).show()
                    }
                }
            }
        }
    }
}