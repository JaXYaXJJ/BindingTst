package hackeru.zakalinskyevgeny.bindingtst

import android.animation.ValueAnimator
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import hackeru.zakalinskyevgeny.bindingtst.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root) //root element
        bindingClass.myMainTxt.text = "Binding works! "

        bindingClass.imageEarth.setOnClickListener {
            bindingClass.myMainTxt.text = "I want to believe "

            val layoutParams = bindingClass.imageUfo.layoutParams as ConstraintLayout.LayoutParams
            val startAngle = layoutParams.circleAngle
            val endAngle = startAngle + 440

            val layoutParams2 = bindingClass.imageCar.layoutParams as ConstraintLayout.LayoutParams
            val startAngle2 = layoutParams2.circleAngle
            val endAngle2 = startAngle2 - 360

            around(startAngle, endAngle, bindingClass.imageUfo.id)
            around(startAngle2, endAngle2, bindingClass.imageCar.id)
        }

        bindingClass.imageUfo.setOnClickListener {
            bindingClass.myMainTxt.text = "Attention Earth-people! \n" +
                    "This is an invasion by flying saucers! "
        }
    }

    private fun around(start: Float, end: Float, id: Int) {
        val anim = ValueAnimator.ofFloat(start, end)
        anim.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            val layoutParams = when (id) {
                bindingClass.imageUfo.id -> bindingClass.imageUfo.layoutParams as ConstraintLayout.LayoutParams
                else -> bindingClass.imageCar.layoutParams as ConstraintLayout.LayoutParams
            }
            layoutParams.circleAngle = animatedValue
            if (layoutParams != bindingClass.imageUfo.layoutParams) {
                bindingClass.imageCar.rotation = (animatedValue)
            } else {
                bindingClass.imageUfo.layoutParams = layoutParams
            }
        }
        anim.duration = 5000
        anim.interpolator = LinearInterpolator()
        anim.start()
    }
}
