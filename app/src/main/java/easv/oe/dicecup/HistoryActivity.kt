package easv.oe.dicecup

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.btnClear

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        if (intent.extras?.getSerializable("history") != null) {
            val history =
                intent.extras?.getSerializable("history") as Array<Pair<String, List<Int>>>

            val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, history
            )

            livHistory.adapter = adapter

            btnClear.setOnClickListener {
                setResult(10)
                //finish()
            }

            btnGoBack.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}