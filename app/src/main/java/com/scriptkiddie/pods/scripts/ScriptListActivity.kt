package com.scriptkiddie.pods.scripts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.View

import com.scriptkiddie.R

import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * An activity representing a list of Scripts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ScriptDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ScriptListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScriptListActivityUI(ScriptAdapter(ScriptLoader.ITEMS)).setContentView(this)
    }
}

class ScriptListActivityUI(val mAdapter: ScriptAdapter) : AnkoComponent<ScriptListActivity> {
    override fun createView(ui: AnkoContext<ScriptListActivity>): View {
        return with(ui) {
            coordinatorLayout {
                lparams {
                    width = matchParent
                    height = matchParent
                }
                fitsSystemWindows = true

                appBarLayout(R.style.AppTheme_AppBarOverlay) {
                    lparams {
                        width = matchParent
                        height = wrapContent
                    }

                    toolbar {
                        lparams {
                            width = wrapContent
                            height = dimen(R.dimen.abc_action_bar_default_height_material)
                        }
                        id = R.id.toolbar
                        title = ui.owner.title
                    }.let {
                        toolbar -> ui.owner.setSupportActionBar(toolbar)
                    }
                }
                frameLayout {
                    lparams {
                        width = matchParent
                        height = matchParent
                    }
                    // app:layout_behavior="@string/appbar_scrolling_view_behavior">

                    recyclerView {
                        lparams {
                            width = matchParent
                            height = matchParent
                            leftMargin = dip(16)
                            rightMargin = dip(16)
                            layoutManager = LinearLayoutManager(ctx)
                            adapter = mAdapter
                        }
                    }
                }
                floatingActionButton {
                    lparams {
                        width = wrapContent
                        height = wrapContent
                        gravity = Gravity.BOTTOM or Gravity.END
                        margin = dip(16)
                    }
                    image = ctx.getDrawable(android.R.drawable.ic_dialog_email)

                    onClick {
                        Snackbar.make(
                                ui.view,
                                "Replace with your own action",
                                Snackbar.LENGTH_LONG
                        ).setAction("Action", null).show()
                    }
                }
            }
        }
    }
}