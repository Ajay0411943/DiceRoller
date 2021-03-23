package easv.oe.dicecup

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class   MainActivity : AppCompatActivity() {

    private val HISTORY_NAME = "history"

    private val TAG: String = "asd"
    val mGenerator = Random()

    val diceIds = arrayOf(0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4
        , R.drawable.dice5, R.drawable.dice6)

    val mHistory = mutableListOf<Pair<String, List<Int>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("history", mHistory.toTypedArray())
            startActivityForResult(intent, 5)
        }


        var numbers = arrayOf (1, 2, 3, 4, 5, 6)

        numbersSpinner.adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, numbers)

        numbersSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position+1 === numbers[0]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.INVISIBLE
                    imgDice3.visibility = View.INVISIBLE
                    imgDice4.visibility = View.INVISIBLE
                    imgDice5.visibility = View.INVISIBLE
                    imgDice6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[1]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.VISIBLE
                    imgDice3.visibility = View.INVISIBLE
                    imgDice4.visibility = View.INVISIBLE
                    imgDice5.visibility = View.INVISIBLE
                    imgDice6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[2]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.VISIBLE
                    imgDice3.visibility = View.VISIBLE
                    imgDice4.visibility = View.INVISIBLE
                    imgDice5.visibility = View.INVISIBLE
                    imgDice6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[3]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.VISIBLE
                    imgDice3.visibility = View.VISIBLE
                    imgDice4.visibility = View.VISIBLE
                    imgDice5.visibility = View.INVISIBLE
                    imgDice6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[4]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.VISIBLE
                    imgDice3.visibility = View.VISIBLE
                    imgDice4.visibility = View.VISIBLE
                    imgDice5.visibility = View.VISIBLE
                    imgDice6.visibility = View.INVISIBLE
                }
                if (position+1 === numbers[5]){
                    imgDice1.visibility = View.VISIBLE
                    imgDice2.visibility = View.VISIBLE
                    imgDice3.visibility = View.VISIBLE
                    imgDice4.visibility = View.VISIBLE
                    imgDice5.visibility = View.VISIBLE
                    imgDice6.visibility = View.VISIBLE
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickRoll(view: View) {
        val d1 = mGenerator.nextInt(6)+1
        val d2 = mGenerator.nextInt(6)+1
        val d3 = mGenerator.nextInt(6)+1
        val d4 = mGenerator.nextInt(6)+1
        val d5 = mGenerator.nextInt(6)+1
        val d6 = mGenerator.nextInt(6)+1

        imgDice1.setImageResource(diceIds[d1])
        imgDice2.setImageResource(diceIds[d2])
        imgDice3.setImageResource(diceIds[d3])
        imgDice4.setImageResource(diceIds[d4])
        imgDice5.setImageResource(diceIds[d5])
        imgDice6.setImageResource(diceIds[d6])

        if(mHistory.size >= 5){
            mHistory.removeAt(0)
        }

        when (numbersSpinner.selectedItemPosition) {
            0 -> mHistory.add(Pair(getCurrentTime(), listOf(d1)))
            1 -> mHistory.add(Pair(getCurrentTime(), listOf(d1, d2)))
            2 -> mHistory.add(Pair(getCurrentTime(), listOf(d1, d2, d3)))
            3 -> mHistory.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4)))
            4 -> mHistory.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4, d5)))
            5 -> mHistory.add(Pair(getCurrentTime(), listOf(d1, d2, d3, d4, d5, d6)))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTime(): String {
        val time = LocalDateTime.now()
        var formatter = DateTimeFormatter
            .ofPattern("HH:mm:ss")
        return "zero"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "History Saved")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5) {
            if (resultCode == 10)
                mHistory.clear()
        }
    }
}
