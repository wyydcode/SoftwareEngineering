import android.content.ContentValues.TAG
import android.util.Log
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
            Log.d(TAG, "loadMistakes: ${_remainingMistakes.value}")
        }
    }

    // 检查答案并更新错题状态
    fun checkAnswer(question: Question, answer: Int) {
        question.isAnswered = true
        question.isCorrect = (answer == question.answer)

        viewModelScope.launch(Dispatchers.IO) {
            if (!question.isCorrect) {
                // 错题处理
            } else {
                dbHelper.deleteMistake(question)
                //loadMistakes() // 删除错题后重新加载
            }
        }
    }

    // 获取剩余错题数，推荐直接通过LiveData获取
    // 不再使用这个方法，改用observe方法来处理
    // 获取剩余错题数
    fun getRemainingMistakes(): Int {
        return _remainingMistakes.value ?: 0
    }
}

