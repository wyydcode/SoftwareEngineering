import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.softwareengineering.DatabaseHelper
import com.example.softwareengineering.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MathErrorViewModel(private val dbHelper: DatabaseHelper) : ViewModel() {

    private val _remainingMistakes = MutableLiveData<Int>()
    val remainingMistakes: LiveData<Int> get() = _remainingMistakes

    private val _mistakes = MutableLiveData<List<Question>>()
    val mistakes: LiveData<List<Question>> get() = _mistakes

    init {
        loadMistakes()
    }

    // 从数据库加载错题
    private fun loadMistakes() {
        viewModelScope.launch(Dispatchers.IO) {
            val mistakeList = dbHelper.getAllMistakes()
            _mistakes.postValue(mistakeList)
            _remainingMistakes.postValue(mistakeList.size)
        }
    }

    // 检查答案并更新错题状态
    fun checkAnswer(question: Question, answer: Int) {
        question.isAnswered = true
        question.isCorrect = (answer == question.answer)

        viewModelScope.launch(Dispatchers.IO) {
            if (!question.isCorrect) {

            } else {
                dbHelper.deleteMistake(question)
            }
        }
    }

    // 获取剩余错题数
    fun getRemainingMistakes(): Int {
        return _remainingMistakes.value ?: 0
    }
}
