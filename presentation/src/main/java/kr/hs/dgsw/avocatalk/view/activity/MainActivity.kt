package kr.hs.dgsw.avocatalk.view.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import kr.hs.dgsw.avocatalk.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
