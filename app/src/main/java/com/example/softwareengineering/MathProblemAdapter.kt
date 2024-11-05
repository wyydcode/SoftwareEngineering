import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.softwareengineering.Question
import com.example.softwareengineering.R

class MathProblemAdapter(private val questions: List<Question>) : RecyclerView.Adapter<MathProblemAdapter.MathProblemViewHolder>() {

    class MathProblemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionText: TextView = itemView.findViewById(R.id.question_text)
        val answerInput: EditText = itemView.findViewById(R.id.answer_input)
        val correctIcon: ImageView = itemView.findViewById(R.id.correct_icon)
        val incorrectIcon: ImageView = itemView.findViewById(R.id.incorrect_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MathProblemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_math_problem, parent, false)
        return MathProblemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MathProblemViewHolder, position: Int) {
        val question = questions[position]
        holder.questionText.text = "${question.operand1} ${question.operator} ${question.operand2} ="

        holder.answerInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val answer = holder.answerInput.text.toString().toIntOrNull()
                if (answer != null) {
                    // 检查答案
                    if (answer == question.answer) {
                        holder.correctIcon.visibility = View.VISIBLE
                        holder.incorrectIcon.visibility = View.GONE
                    } else {
                        holder.correctIcon.visibility = View.GONE
                        holder.incorrectIcon.visibility = View.VISIBLE
                    }
                } else {
                    holder.correctIcon.visibility = View.GONE
                    holder.incorrectIcon.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int = questions.size
}
